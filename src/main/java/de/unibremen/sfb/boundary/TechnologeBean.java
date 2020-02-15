package de.unibremen.sfb.boundary;

import de.unibremen.sfb.controller.ProbeController;
import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.persistence.UserDAO;
import de.unibremen.sfb.service.*;
import lombok.Getter;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.management.Query;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
@Named
@SessionScoped
@Getter
public class TechnologeBean implements Serializable {

    /**
     * the user managed by this bean
     */
    private User technologe;

    @Inject
    private ExperimentierStationService esService;

    @Inject
    private ProbeService probeService;

    @Inject
    private UserService userService;

    @Inject
    private AuftragService auftragService;

    @Inject
    private ProbeController probeController;

    private ProzessSchritt singleJob;

    /**
     * returns the experimentation stations this user is assigned to
     * @return a set containing all stations this user is assigned to
     */
    public List<ExperimentierStation> getStationen() { return technologe.getStationen(); }

    /**
     * returns the assignments currently available for this user
     * @return a set containing all availabe jobs
     */
    public Set<ProzessSchritt> getAuftrag() {
        Set<ProzessSchritt> res = new HashSet<>();
        //mit named query aus prozessSchritt für jede station des users?

        return res;
    }

    /**
     * sets the state of a job
     * @param a the job
     * @param zustand the new state
     */
    public void setAuftragsZustand(Auftrag a, Enum<ProzessKettenZustandsAutomat> zustand) {
        if(a == null || zustand == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                auftragService.setAuftragsZustand(a, zustand);
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
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
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
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
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sorts a list of jobs by their priority
     * @param prio a set containing all jobs to be sorted
     */
    public Set<ProzessSchritt> prioSort(Set<Auftrag> prio) {
        return null;
    }

    /**
     * creates a new sample (happens in "urformende" process chains)
     * @param id the sample id of the new sample
     */
    public void createUrformend(String id) {}

    /**
     * adds a comment to a process step
     * @param ps the process step
     * @param c the comment
     */
    public void addComment(ProzessSchritt ps, String c) {
        if(ps == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            Kommentar k = new Kommentar(LocalDateTime.now(), c);
            for(Probe p : ps.getTraeger().getProben()) {
                addProbenComment(p, c);
            }
        }
    }

    /**
     * edits a comment which belongs to a process step
     * @param ps the process step the comments belongs to
     * @param c the comment
     */
    public void editComment(ProzessSchritt ps, String c) {
        if(ps == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            for(Probe p : ps.getTraeger().getProben()) {
                editProbenComment(p, c);
            }
        }
    }

    /**
     * deletes a comment belonging to a process step
     * @param ps the process step
     * @param c the comment
     */
    public void deleteComment(ProzessSchritt ps, String c) {
        if(ps == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            for(Probe p : ps.getTraeger().getProben()) {
                deleteProbenComment(p, c);
            }
        }
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
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * edits a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void editProbenComment(Probe p, String c) {
        if(p == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.editProbenComment(p, c);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * deletes a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void deleteProbenComment(Probe p, String c) {
        if(p == null || c == null) {
            errorMessage("invalid input");
        }
        else {
            try {
                probeService.deleteProbenComment(p, c);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     * @return a set containing all those samples
     */
    public Set<Probe> viewToBeUploaded() { return null; }

    /**
     * uploads a sample
     * @param p the sample
     */
    public void upload(Probe p) {}

    /**
     * reports a sample as lost
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {
        probeController.setZustandForProbe(p, ProbenZustand.VERLOREN);
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
            }
        }

    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {}

    /**
     * the empty constructor
     */
    public TechnologeBean() {}

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

    public List<Probe> getSamples() { return null; }

    public List<ProzessSchritt> getJobs() {
        return null;
    }

    public String singleview(ProzessSchritt a) {
        singleJob = a;
        return "singleview.xhtml";
    }
}
