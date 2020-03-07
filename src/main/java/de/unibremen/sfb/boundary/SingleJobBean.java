package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Getter
@Setter
@Slf4j
public class SingleJobBean implements Serializable {

    private ProzessSchritt ps;

    private List<QualitativeEigenschaft> verEigenschaften;
    private List<QualitativeEigenschaft> ausEigenschaften;

    /**
     * saves a comment the user can type in to be added to all samples of a prozessschritt
     */
    private String kommentarForAll;

    @Inject
    private ProbenService probeService;

    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    @Inject
    private  ProzessSchrittService prozessSchrittService;

    @Inject
    private ProzessSchrittService psService;

    @Inject
    private AuftragService auftragService;

    String jsonString;

    public void init() {
        verEigenschaften = qualitativeEigenschaftService.getEigenschaften();
        if (this.ps.getProzessSchrittZustandsAutomat().getCurrent().equals("Erstellt")) {
            this.ps.getProzessSchrittZustandsAutomat().setCurrent(this.ps.getProzessSchrittZustandsAutomat().getZustaende().get(0));
            log.info("set current Zustand to " + this.ps.getProzessSchrittZustandsAutomat().getCurrent() + "for this.ps: " + this.ps.getId());
        }
        try {
            psService.editPS(ps);
        } catch (ProzessSchrittNotFoundException e) {
            log.error(e.getLocalizedMessage());
        }
    }

    public String singlejob(int id) {
        try {
            this.ps = prozessSchrittService.getObjById(id);
            init();
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
        }
        return "singlejob.xhtml";
    }

    public String getLetzterZustand(ProzessSchritt ps) {
        return ps.getProzessSchrittZustandsAutomat().getZustaende().get(ps
                .getProzessSchrittZustandsAutomat().getZustaende().size()-1);
    }

    public void toJson() {
        List<ProzessSchrittParameter> r = new ArrayList<>();
        probeService.jsonObjects(jsonString, r);
    }

    /**
     * adds a comment to a process step
     */
    public void addComment() {
        if(ps == null) {
            errorMessage("invalid input");
        }
        else {
            List<Probe> proben = new ArrayList<>();
            for (Traeger t :
                    auftragService.getAuftrag(ps).getTraeger()) {
                proben.addAll(t.getProben());
            }
            for (Probe p : proben) {
                try {
                    probeService.addProbenComment(p, kommentarForAll);
                } catch (ProbeNotFoundException | DuplicateKommentarException e) {
                    e.printStackTrace();
                    log.info("the sample " + p.getProbenID() + " could not be found while trying to add comment " + kommentarForAll);
                }
                catch(IllegalArgumentException e) {
                    errorMessage("invalid input");
                }
            }
            message("added comment to all samples");
        }
        kommentarForAll = "";
    }

    /**
     * returns the samples of this prozessschritt
     * @return a list containing the samples
     */
    public List<Probe> getProben() {
        try {
            prozessSchrittService.getObjById(ps.getId());
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
        }
        List<Probe> proben = new ArrayList<>();
        for (Traeger t :
                auftragService.getAuftrag(ps).getTraeger()) {
            proben.addAll(t.getProben());
        }
        return proben;
    }


    /**
     * weise den Proben des akutellen ProzessSchrittes Proben zu
     */
    public void zuweisen() {
        var proben = getProben();
        for (Probe p :
                proben) {
            var list = p.getEigenschaften();
            list.addAll(ausEigenschaften);
            p.setEigenschaften(list);
            try {
                probeService.update(p);
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
        facesNotification("Succesfully added: ");
    }

    /**
     * returns the ProzessSchrittParameter of the ProzessSchritt this bean manages
     * @return a list containing the prozessSchrittParameter
     */
    public List<ProzessSchrittParameter> getParameter() {
      return ps.getProzessSchrittParameters(); // FIXME Nullpointer because technologe/index.xhtml does not load prozessSchrittList
    }

    /**
     * downloads the parameters as a json file
     */
    public void download() {
        download(getParameter());
    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

    public void message(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, e, null));
        log.info("displayed message to user: " +e);
    }

    /**
     * downloads a process step parameter
     * @param psp the parameter
     */
    public void download(List<ProzessSchrittParameter> psp) {
        var config = new JsonbConfig().withFormatting(true);
        var jsonb = JsonbBuilder.create(config);

        String result = jsonb.toJson(psp);
        String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.write(result);
        log.info("Successfully exported json to " + fileName);
        facesNotification("Successfully exported json to " + fileName);
    }

    /**
     * finds the station this process step is currently at
     * @return the station
     */
    public ExperimentierStation findStation() {
        return psService.findStation(ps); // FIXME Nullpointer
    }

    /**
     * sets the state of a ProzessSchritt on further than it was
     * @throws DuplicateProzessSchrittLogException if PSL already exits
     * @throws ProzessSchrittZustandsAutomatNotFoundException if automat does not exist
     */
    public void setJobZustand() throws DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException {
        try {
            try {
                psService.oneFurther(ps);
            } catch (ExperimentierStationNotFoundException | ProzessSchrittLogNotFoundException | ProzessSchrittNotFoundException e) {
                e.printStackTrace();
            }
        } catch(IllegalArgumentException e) {
                errorMessage("invalid input");
        }
        String letzterZustand = ps.getProzessSchrittZustandsAutomat().getZustaende().get(
                ps.getProzessSchrittZustandsAutomat().getZustaende().size() -1);
        try {
            psService.editPS(ps);
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
        }
        if(letzterZustand.equals(ps.getProzessSchrittZustandsAutomat().getCurrent())){
            facesNotification("prozessSchritt wurde beendet! ");
        }
        else{
            facesNotification("ProzessSchritt gewechselt auf: " + ps.getProzessSchrittZustandsAutomat().getCurrent());
        }
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, message, null));
    }
}
