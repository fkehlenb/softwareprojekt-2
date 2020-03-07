package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.util.Assert;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
@Named
@ViewScoped
@Getter
@Setter
@Slf4j
public class TechnologeView implements Serializable {

    /** Job Service */
    @Inject
    private AuftragService auftragService;

    /** Experimenting station service */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /** Process Step Service */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    // TODO immer wieder neu laden mit der unteren id
    /**
     * the user managed by this bean
     */
    private User technologe;

    /** List of all his jobs */
    private List<Auftrag> auftragList;

    /** Experimenting station service TODO DUPLICATE */
    @Inject
    private ExperimentierStationService esService;

    /** Sample Service */
    @Inject
    private ProbenService probeService;

    /** User Service */
    @Inject
    private UserService userService;

    /** Process Step Service TODO DUPLICATE */
    @Inject
    private ProzessSchrittService psService;

    private ProzessSchritt parameterschritt;

    /**
     * loads the initial data from the database
     */
    @PostConstruct
    public void init() {
        try {
            technologe = userService.getCurrentUser();
        } catch (Exception e) {
            e.printStackTrace();
            errorMessage("Couldn't grab current user! Error " + e.getMessage());
        }
    }


    /**
     * returns the experimentation stations this user is assigned to
     *
     * @return a list containing all stations this user is assigned to
     */
    public List<ExperimentierStation> getStationen() {
        return esService.getESByUser(technologe);
    }

    /**
     * Hole alle current schritte der stationen, denen der technologe zugeordnet ist
     *
     * @return liste mit current schritten
     */
    //TODO ONLY THE ONES WITH AUFTRAG GESTARTET
    public List<ProzessSchritt> getSchritte() {
        List<ProzessSchritt> r = null;
        try {
            r = prozessSchrittService.getSchritte();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            log.error("Could find any Steps");
            return new ArrayList<>();
        }
        assert r != null;
        return r;
    }

    /**
     * verfügbare schritte in den experimentierstationen des technologen
     * @return liste
     */
    public List<ProzessSchritt> getJobs() {
        return prozessSchrittService.getJobs(technologe);
    }

    /**
     * finds the station a process step is currently at
     * the step belongs to a station this user is at
     *
     * @param ps the step
     * @return the station
     */
    public ExperimentierStation findStandort(ProzessSchritt ps) { //TODO integrate into my xhtmls
        try {
            return experimentierStationService.findStation(ps);
        } catch (IllegalArgumentException e) {
            errorMessage("invalid input");
            return null;
        }
    }

    /**
     * Report experimenting station as broken
     *
     * @param es - the experimenting station to report as broken
     */
    public void reportBroken(ExperimentierStation es) {
        try {
            if (!es.getStatus().equals(ExperimentierStationZustand.KAPUTT)) {
                es.setStatus(ExperimentierStationZustand.KAPUTT);
                esService.updateES(es);
                log.info("Reported station as broken! ID " + es.getEsID());
                facesNotification("Reported experimenting station as broken!");
            } else {
                errorMessage("Station already reported broken!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't set experimenting station status to broken! Error " + e.getMessage());
            errorMessage("Couldn't report station as broken!");
        }
    }

    /**
     * creates a new sample (happens in "urformende" process chains)
     *
     * @param id the sample id of the new sample
     */
    public void createUrformend(String id) {
        //probeService.addNewSample(id);
        //TODO wie soll ich das integrieren?
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     *
     * @return a set containing all those samples
     * // FIXME Keine PS
     */
    public List<Probe> viewToBeUploaded() {
        List<Probe> r = null;
        try {
            r = probeService.viewToBeUploaded();
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<Probe>();
        }
        log.info("Probe die Hochgeladen werden muessen wurden geladen");
        return r;
    }

    /**
     * uploads a sample
     *
     * @param p the sample
     */
    public void upload(Probe p) {

    }

    /**
     * reports a sample as lost
     *
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {
        try {
            probeService.setZustandForProbe(p, ProbenZustand.VERLOREN);
            log.info("sample " + p.getProbenID() + " was reported as missing");
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("sample " + p.getProbenID() + " could not be found when trying to report as missing");
        } catch (IllegalArgumentException e) {
            errorMessage("invalid input");
        }
    }

    /**
     * finds the priority of a process step
     *
     * @param id of the step
     * @return the priority of the Auftrag the process step belongs to
     */
    public AuftragsPrioritaet getPriority(int id) {
        ProzessSchritt ps = null;
        try {
            ps = prozessSchrittService.getObjById(id);
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
        }
        Auftrag r = null;
        r = auftragService.getAuftrag(ps);
        return r.getPriority();
    }


    /**
     * creates and sends an error message
     *
     * @param e error messsage
     */
    private void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

    /**
     * the empty constructor
     */
    public TechnologeView() {
    }

    public String KommentarToString(Probe p) {
        return probeService.KommentarToString(p);
    }


    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
