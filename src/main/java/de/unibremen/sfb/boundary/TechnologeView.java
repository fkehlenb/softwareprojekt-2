package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.LazyDataModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
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
    ProzessSchrittService prozessSchrittService;

    // TODO immer wieder neu laden mit der unteren id
    /**
     * the user managed by this bean
     */
    private User technologe;

    private LazyDataModel<Probe> lazyProben;

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

        lazyProben = new LazyProbenDataModel();
    }

    /**
     * returns the jobs this user is currently assigned to
     * ProzessSchritte, not Auftrag, as the Technologe is
     * not supposed to see entire job chains, just what they
     * have to do
     *
     * @return a list containing all the jobs
     */
    public List<ProzessSchritt> getJobs() {
        List<ProzessSchritt> r = psService.getSchritteByUser(technologe);
        r.sort(Comparator.comparing(o -> psService.getAuftrag(o).getPriority()));
        return r;

        //FIXME Aaron @karlaaron: Nullpointer wird hier geworfen
        //(ProzessSchrittService.java:114)
        //also nur ein technologe pro Station, und
        //kann erst auftrag annehmen, wenn er an dieser station nichts zu tun
        //nur ein auftrag pro station, und ein technologe pro station
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
     * returns the assignments currently available for this user
     *
     * @return a set containing all availabe jobs
     */
    public List<ProzessSchritt> getAuftrag() {
        //alle einträge in queues von experimentierstationen denene der user zugeordnet ist
        try {
            return prozessSchrittService.getSchritteByUser(userService.getCurrentUser());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<ProzessSchritt>();
    }

    /**
     * reports an experimentation station as broken
     *
     * @param es the station
     */
    public void reportBroken(ExperimentierStation es) {
        try {
            esService.setZustand(es, ExperimentierStationZustand.KAPUTT);
            log.info("ExperimentierStation " + es.getEsID() + "was reported as broken.");
        } catch (ExperimentierStationNotFoundException e) {
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
        //TODO kann das der technologe wirklich selber?
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     *
     * @return a set containing all those samples
     */
    public List<Probe> viewToBeUploaded() { //TODO nur die wo experimente abgeschlossen
        List<Probe> res = new LinkedList<>();
        for(ProzessSchritt ps : getJobs()) {
            if(!ps.isUploaded() && ps.getProzessSchrittZustandsAutomat().getCurrent().equals("Bearbeitet")) {
                res.addAll(ps.getZugewieseneProben());
            }
        }
        return res;
    }

    /**
     * uploads a sample
     *
     * @param p the sample
     */
    public void upload(Probe p) {

    }

    /**
     * creates and sends an error message
     *
     * @param e error messsage
     */
    public void errorMessage(String e) {
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
