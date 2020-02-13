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
    private AuftragDAO auftragDAO;

    @Inject
    private ExperimentierStationDAO esDAO;

    @Inject
    private ProbeDAO probeDAO;

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
            Enum<ProzessKettenZustandsAutomat> temp = a.getProzessKettenZustandsAutomat();
            a.setProzessKettenZustandsAutomat(zustand);
            try {
                auftragDAO.update(a);
            } catch (AuftragNotFoundException e) {
                a.setProzessKettenZustandsAutomat(temp);
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
            Enum<ExperimentierStationZustand> temp = es.getStatus();
            es.setStatus(ExperimentierStationZustand.KAPUTT);
            try {
                esDAO.update(es);
            } catch (ExperimentierStationNotFoundException e) {
                e.printStackTrace();
                es.setStatus(temp);
            }
        }
    }

    /**
     * assigns this user to a job
     * @param a the job
     */
    public void assignToAuftrag(Auftrag a) {
        if(a == null) {
            errorMessage("invalid input");
        }
        else {
            User temp = a.getAssigned();
            a.setAssigned(technologe);
            try {
                auftragDAO.update(a);
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
                a.setAssigned(temp);
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
            Kommentar k = p.getKommentar();
            p.setKommentar(new Kommentar(LocalDateTime.now(), c));
            try {
                probeDAO.update(p);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
                p.setKommentar(k);
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
            Kommentar k = p.getKommentar();
            p.setKommentar(k);
            try {
                probeDAO.update(p);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
                p.setKommentar(k);
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
            Kommentar k = p.getKommentar();
            p.setKommentar(null);
            try {
                probeDAO.update(p);
            }
            catch(ProbeNotFoundException e) {
                e.printStackTrace();
                p.setKommentar(k);
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
        Probe p = null;
        try {
            p = probeDAO.getObjById(0); //TODO in DAO id als string
        }
        catch(ProbeNotFoundException e) {
            e.printStackTrace();
        }
        reportLostProbe(p);
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
