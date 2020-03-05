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

    @Inject
    AuftragService auftragService;

    @Inject
    ExperimentierStationService experimentierStationService;

    @Inject
    ProzessSchrittService prozessSchrittService;

    // TODO immer wieder neu laden mit der unteren id
    /**
     * the user managed by this bean
     */
    private User technologe;

    List<Auftrag> auftragList;

    @Inject
    private ExperimentierStationService esService;

    @Inject
    private ProbenService probeService;

    @Inject
    private UserService userService;

    @Inject
    private ProzessSchrittService psService;

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
     * Hole alle Verfügbaren Schritte
     * @return
     */
    public List<ProzessSchritt> getSchritte() {
        List<ProzessSchritt> r = null;
        try {
            r = prozessSchrittService.getSchritte();
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            log.error("Could find any Steps");
            return  new ArrayList<>();
        }
        assert r != null;
        return  r;
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
     * reports an experimentation station as broken
     *
     * @param es the station
     */
    public void reportBroken(ExperimentierStation es) {
        try {
            es.setStatus(ExperimentierStationZustand.KAPUTT);
            experimentierStationService.updateES(es);
            log.info("ExperimentierStation " + es.getEsID() + "was reported as broken.");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("an error occurred trying to report ExperimentierStation " + es.getEsID() + " as broken: " + e.getMessage());
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
          r =  probeService.viewToBeUploaded();
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
}
