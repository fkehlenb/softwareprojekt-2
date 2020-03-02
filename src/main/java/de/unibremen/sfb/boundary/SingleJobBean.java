package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProbenService;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.ProzessSchrittService;
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

    @PostConstruct
    public void init() {
        verProzessSchrittParameters = prozessSchrittParameterService.getParameterList();
        // FIXME Zustandswehcsel
    }

    public String singlejob(ProzessSchritt ps) {
        this.ps = ps;
        return "singlejob.xhtml";
    }



    /**
     * adds a comment to a process step
     */
    public void addComment() {
        if(ps == null) {
            errorMessage("invalid input");
        }
        else {
//            for (Probe p : ps.getZugewieseneProben()) {
//                try {
//                    probeService.addProbenComment(p, kommentarForAll);
//                } catch (ProbeNotFoundException | DuplicateKommentarException e) {
//                    e.printStackTrace();
//                    log.info("the sample " + p.getProbenID() + " could not be found while trying to add comment " + kommentarForAll);
//                }
//                catch(IllegalArgumentException e) {
//                    errorMessage("invalid input");
//                }
//            }
            message("added comment to all samples");
        }
        kommentarForAll = "";
    }

//    public List<Probe> getProben() {
//        return psService.getProben(ps);
//    }

    /**
     * weise den Proben des akutellen ProzessSchrittes Proben zu
     */
    public void zuweisen() {
//        for (Probe p :
//                ps.getZugewieseneProben()) {
//            var list = p.getParameter();
//            list.addAll(ausProzessSchrittParameters);
//            p.setParameter(list);
//            try {
//                probeService.update(p);
//            } catch (ProbeNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * returns the ProzessSchrittParameter of the ProzessSchritt this bean manages
     * @return a list containing the prozessSchrittParameter
     */
    public List<ProzessSchrittParameter> getParameter() {
        List<ProzessSchrittParameter> r = new LinkedList<>();
        for(Bedingung b : ps.getProzessSchrittVorlage().getBedingungen()) {
            r.addAll(b.getProzessSchrittParameter());
        }
        return r;
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
//    public ExperimentierStation findStation() {
//        return psService.findStation(ps);
//    }

    /**
     * sets the state of a ProzessSchritt on further than it was
     */
//    public void setJobZustand() {
//        try {
////            psService.oneFurther(ps);
//        }
//        catch(ProzessSchrittNotFoundException | ProzessSchrittLogNotFoundException | DuplicateProzessSchrittLogException | ExperimentierStationNotFoundException | ProzessSchrittZustandsAutomatNotFoundException e) {
//                e.printStackTrace();
////                log.info("an error occurred trying to update the state of " + ps.getPsID() + ": " + e.getMessage());
//        }
//        catch(IllegalArgumentException e) {
//                errorMessage("invalid input");
//        }
//        String letzterZustand = ps.getProzessSchrittVorlage().getZustandsAutomatVorlage().getZustaende().get(ps.getProzessSchrittVorlage().
//                getZustandsAutomatVorlage().getZustaende().size()-1);
//        if(letzterZustand.equals(ps.getProzessSchrittZustandsAutomat().getCurrent())){
//            facesNotification("prozessSchritt wurde beendet! ");
//        }
//        else{
//            facesNotification("ProzessSchritt gewechselt auf: " + ps.getProzessSchrittZustandsAutomat().getCurrent());
//        }
//
//
//    }

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
