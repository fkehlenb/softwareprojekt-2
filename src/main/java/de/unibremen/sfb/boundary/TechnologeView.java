package de.unibremen.sfb.boundary;

import de.unibremen.sfb.controller.ProbeController;
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
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
@Named
@SessionScoped
@Getter
@Setter
@Slf4j
public class TechnologeView implements Serializable {

    /**
     * the user managed by this bean
     */
    private User technologe;

    /**
     * saves whether the user wants to see only samples that need info to be uploaded, or everything
     */
    private Boolean viewUploaded;

    private ProzessSchritt singleJob;

    private Kommentar singleKommentar;

    private LazyDataModel<Probe> lazyProben;


    @Inject
    private ExperimentierStationService esService;

    @Inject
    private ProbenService probeService;

    @Inject
    private UserService userService;

    @Inject
    private AuftragService auftragService;

    @Inject
    private ProbeController probeController;

    @Inject
    private ProzessSchrittService psService;



    /**
     * loads the initial data from the database
     */
    @PostConstruct
    public void init() {
        technologe = userService.getCurrentUser(); //TODO evtl updated das nicht richtig?

        lazyProben = new LazyProbenDataModel();

        viewUploaded = false;
    }

    /**
     * returns the experimentation stations this user is assigned to
     * @return a list containing all stations this user is assigned to
     */
    public List<ExperimentierStation> getStationen() {
        return esService.getESByUser(technologe);
    }

    /**
     * returns the assignments currently available for this user
     * @return a set containing all availabe jobs
     */
    public Set<ProzessSchritt> getAuftrag() {
        //alle einträge in queues von experimentierstationen denene der user zugeordnet ist
        return new HashSet<>();
    }

    /**
     * sets the state of a ProzessSchritt on further than it was
     * @param a the ProzessSchritt
     */
    public void setJobZustand(ProzessSchritt a) {
        if(a == null) {
            errorMessage("invalid input");
        }
        else {
            int i = 0;
            while(!a.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i).equals(a.getProzessSchrittZustandsAutomat().getCurrent())) {
                i++;
            }
            try {
                psService.setZustand(a, a.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i+1));
                log.info("set state of ProzessSchritt " + a.getPsID() + " to " + a.getProzessSchrittZustandsAutomat().getCurrent());
            }
            catch(ProzessSchrittNotFoundException|ProzessSchrittLogNotFoundException|DuplicateProzessSchrittLogException e) {
                e.printStackTrace();
                log.info("an error occurred trying to update the state of " + a.getPsID() + ": " + e.getMessage());
            }
        }
    }

    /**
     * reports an experimentation station as broken
     * @param es the station
     */
    public void reportBroken(ExperimentierStation es) {
        if(es == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                esService.setZustand(es, ExperimentierStationZustand.KAPUTT);
                log.info("ExperimentierStation " + es.getEsID() + "was reported as broken.");
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to report ExperimentierStation " + es.getEsID() + " as broken: " + e.getMessage());
            }
        }
    }

    /**
     * assigns this user to a job
     * @param a the job
     */
    public void assignToAuftrag(Auftrag a) {
        if(a==null) {
            errorMessage("invalid input");
        }
        else {
            try {
                auftragService.assignToAuftrag(technologe, a);
                log.info("user " + technologe.getId() + " was assigned to Auftrag " + a.getPkID());
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to assign user " + technologe.getId() + "to Auftrag " + a.getPkID() + ": " + e.getMessage());
            }
        }
    }

    /**
     * assigns this user to a prozessSchritt
     * @param ps the prozessschritt
     */
    public void assignToAuftrag(ProzessSchritt ps) {
        //find auftrag this prozessschritt belongs to
        //then assignToAuftrag(a);
    }

    /**
     * creates a new sample (happens in "urformende" process chains)
     * @param id the sample id of the new sample
     */
    public void createUrformend(String id) {
        //probeService.addNewSample(id);
        //TODO kann das der technologe wirklich selber?
    }

    /**
     * adds a comment to a sample
     * @param p the sample
     * @param c the comment
     */
    public void addProbenComment(Probe p, String c) {
        if(p == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.addProbenComment(p, c);
                log.info("the comment " + c + " was added to the sample " + p.getProbenID());
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to add comment " + c + " to sample " + p.getProbenID() + " : " +e.getMessage());
            }
        }
    }

    /**
     * edits a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     * @param k the comment class
     */
    public void editProbenComment(Probe p, Kommentar k, String c) {
        if(p == null || c == null || k == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.editProbenComment(p, k, c);
                log.info("the comment " + k.getId() + " of probe " + p.getProbenID() + " was edited to " + c);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to update comment " + k.getId() + " of sample " + p.getProbenID() + " : " + e.getMessage());
            }
        }
    }

    /**
     * deletes a comment belonging to a sample
     * @param p the sample
     * @param k the comment
     */
    public void deleteProbenComment(Probe p, Kommentar k) {
        if(p == null || k == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.deleteProbenComment(p, k);
                log.info("comment " + k.getId() + " of probe " + p.getProbenID() + " was deleted");
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to delete comment " + k.getId() + " of sample " + p.getProbenID() + " : " +e.getMessage());
            }
        }
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     * @return a set containing all those samples
     */
    public List<Probe> viewToBeUploaded() {
        List<Probe> res = new LinkedList<>();
        for(ProzessSchritt ps : getJobs()) {
            if(!ps.isUploaded()) {
                res.addAll(ps.getZugewieseneProben());
            }
        }
        return res;
    }

    /**
     * uploads a sample
     * @param p the sample
     */
    public void upload(Probe p) {

    }

    /**
     * reports a sample as lost
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {
        probeController.setZustandForProbe(p, ProbenZustand.VERLOREN);
        log.info("sample " + p.getProbenID() + " was reported as missing");
    }

    /**
     * reports a sample as lost by its id
     * @param id the id of the sample to be reported
     */
    public void reportLostProbe(String id) {
        if(id==null) {
            errorMessage("invalid input");
        }
        else {
            try {
                reportLostProbe(probeService.getProbeById(id));
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to report a sample as lost: sample " + id + "could not be found in the database");
            }
        }
    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

    /**
     * the empty constructor
     */
    public TechnologeView() {}

    /**
     * returns the technologist managed by this bean
     * @return the user
     */
    public User getTechnologe() {
        return technologe;
    }

    /**
     * sets the technologist managed by this bean
     * @param technologe the new user
     */
    public void setTechnologe(User technologe) {
        this.technologe = technologe;

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
        //r.sort(Comparator.comparing(o -> psService.getAuftrag(o).getPriority())); //FIXME
        return psService.getSchritteByUser(technologe);
        //also nur ein technologe pro Station, und
        //kann erst auftrag annehmen, wenn er an dieser station nichts zu tun
        //nur ein auftrag pro station, und ein technologe pro station

    }

    public List<Probe> getSamples() {
        System.out.println(viewUploaded);
        if(viewUploaded) {
            return viewToBeUploaded();
        }
        return probeService.getProbenByUser(technologe);
    }

    public String KommentarToString(Probe p) {
        return probeService.KommentarToString(p);
    }
}
