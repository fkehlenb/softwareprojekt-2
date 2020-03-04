package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.omnifaces.util.Faces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Named
@SessionScoped
@Getter
@Setter
@Slf4j
public class SingleJobBean implements Serializable {

    private ProzessSchritt ps;

    private List<ProzessSchrittParameter> verProzessSchrittParameters;
    private List<ProzessSchrittParameter> ausProzessSchrittParameters;

    /**
     * saves a comment the user can type in to be added to all samples of a prozessschritt
     */
    private String kommentarForAll;

    @Inject
    private ProbenService probeService;

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private ProzessSchrittService psService;

    @Inject
    private AuftragService auftragService;

    @PostConstruct
    public void init() {
        verProzessSchrittParameters = prozessSchrittParameterService.getParameterList();
        // FIXME Zustandswehcsel
    }

    public String singlejob(ProzessSchritt ps) {
        this.ps = ps;
        return "singlejob.xhtml";
    }

    public String getLetzterZustand(ProzessSchritt ps) {
        return ps.getProzessSchrittZustandsAutomat().getZustaende().get(ps
                .getProzessSchrittZustandsAutomat().getZustaende().size()-1);
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
                for (Probe p :
                        t.getProben()) {
                    proben.add(p);
                }
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
     * @param ps the process step
     * @return a list containing the samples
     */
    public List<Probe> getProben(ProzessSchritt ps) {
        List<Probe> proben = new ArrayList<>();
        for (Traeger t :
                auftragService.getAuftrag(ps).getTraeger()) {
            for (Probe p :
                    t.getProben()) {
                proben.add(p);
            }
        }
        return proben;
    }


    /**
     * weise den Proben des akutellen ProzessSchrittes Proben zu
     */
    public void zuweisen() {
        for (Probe p :
                getProben(ps)) {
            var list = p.getParameter();
            list.addAll(ausProzessSchrittParameters);
            p.setParameter(list);
            try {
                probeService.update(p);
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
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
        try {
            Faces.sendFile(prozessSchrittParameterService.toJSON(psp), true);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
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
     */
    public void setJobZustand() throws DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException {
        try {
            try {
                psService.oneFurther(ps);
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
            } catch (ProzessSchrittNotFoundException e) {
                e.printStackTrace();
            } catch (ProzessSchrittLogNotFoundException e) {
                e.printStackTrace();
            }
        } catch(IllegalArgumentException e) {
                errorMessage("invalid input");
        }
        String letzterZustand = ps.getProzessSchrittZustandsAutomat().getZustaende().get(
                ps.getProzessSchrittZustandsAutomat().getZustaende().size() -1);
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
