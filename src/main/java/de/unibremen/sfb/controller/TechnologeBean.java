package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProbeDAO;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
@Named
@RequestScoped
public class TechnologeBean implements Serializable {

    /**
     * the user managed by this bean
     */
    private User technologe;

    @Inject
    private AuftragController auftragController;

    @Inject
    private ExperimentierStationController esController;

    @Inject
    private ProbeController probeController;

    @Inject
    private ProzessSchrittController prozessSchrittController;

    @Inject
    private TraegerController traegerController;

    /**
     * returns the experimentation stations this user is assigned to
     * @return a set containing all stations this user is assigned to
     */
    public Set<ExperimentierStation> getStationen() {
        Standort b = new Standort();
        b.setId(0);
        b.setOrt("asdjvb");
        Set<ExperimentierStation> r = new HashSet<>();
        ExperimentierStation s = new ExperimentierStation();
        s.setEsID(1);
        s.setName("sakdjv");
        s.setStandort(b);
        s.setStatus(ExperimentierStationZustand.VERFUEGBAR);
        ExperimentierStation t = new ExperimentierStation();
        t.setEsID(2);
        t.setName("ipajwirf");
        t.setStandort(b);
        t.setStatus(ExperimentierStationZustand.VERFUEGBAR);

        r.add(s);
        r.add(t);
        return r; //technologe.getStationen(); //TODO nur für testzwecke
    } //TODO List oder Set?

    /**
     * return the jobs this technologe is currently assigned to
     * @return a list containing all jobs
     */
    public List<Auftrag> getJobs() {
        //return technologe.getAuftraege();
        List<Auftrag> r = new LinkedList<>();
        Auftrag a = new Auftrag();
        a.setPkID(1);
        a.setPriority(AuftragsPrioritaet.ETWAS);
        r.add(a);
        Auftrag b = new Auftrag();
        b.setPriority(AuftragsPrioritaet.SEHR_HOCH);
        b.setPkID(6);
        r.add(b); //TODO sind nur test
        return r;
    }

    /**
     * return all samples of the jobs the technologe is currently assigned to
     * @return a list containing all samples
     */
    public List<Probe> getSamples() {
        return null;
        //brauche Methode für prozessSchritte.
        //eigentlich mehr sinn getJobs umzuschreiben

        //dann kann ich daraus die träger, von denen die proben
    }

    /**
     * returns the assignments currently available for this user
     * @return a set containing all availabe jobs
     */
    public Set<Auftrag> getAuftrag() {
        Set<ExperimentierStation> stations = getStationen();
        Set<Auftrag> result = new HashSet<>();
        for(ExperimentierStation s : stations) {
           // Queue<ProzessSchritt> q = esController.getQueue(s);
            // auftrag from prozessschritt, this in queue
        } //TODO
        return result;
    }

    /**
     * sets the state of a job
     * @param a the job
     * @param zustand the new state
     */
    public void setAuftragsZustand(Auftrag a, ProzessKettenZustandsAutomat zustand) {
        auftragController.setPKZforAuftrag(a, zustand);
    }
    //TODO hier Auftrag im sinne von Technologen Arbeits Auftrag? Also Zustand ProzessSchritt

    /**
     * reports an experimentation station as broken
     * @param es the station
     */
    public void reportBroken(ExperimentierStation es) {
        esController.reportBroken(es);
    }

    /**
     * assigns this user to a job
     * @param a the job
     */
    public void assignToAuftrag(Auftrag a) {
        int assign = auftragController.setUserForAuftrag(a, technologe);
        if(assign != 0) {
            errorMessage("Assigning User has failed"); //TODO Sprache
        }
    }

    /**
     * sorts a list of jobs by their priority
     * @param prio a set containing all jobs to be sorted
     */
    public List<Auftrag> prioSort(List<Auftrag> prio) {
        prio = auftragController.mergeSort(prio);
        return prio;
    }

    /**
     * creates a new sample (happens in "urformende" process chains)
     * @param id the sample id of the new sample
     */
    public void createUrformend(String id) {
        probeController.createNewProbe(id, null); //TODO
        //TODO keine Eigenschaften für neue Probe?
    }

    /**
     * adds a comment to all samples of a process step
     * @param ps the process step
     * @param c the comment
     */
    public void addComment(ProzessSchritt ps, String c) {
        if(ps == null || c.equals("")) {
            errorMessage("Input for addComment failed");
        }
        else {
            List<Probe> samples = traegerController.getProben(prozessSchrittController.getTraeger(ps));
            for (Probe p : samples) {
                addProbenComment(p, c);
            }
        }
    }

    /**
     * edits a comment which belongs to all samples of a process step
     * @param ps the process step the comments belongs to
     * @param c the comment
     */
    public void editComment(ProzessSchritt ps, String c) {
        if(ps == null || c.equals("")) {
            errorMessage("Input for editComment failed");
        }
        else {
            List<Probe> samples = traegerController.getProben(prozessSchrittController.getTraeger(ps));
            for(Probe p : samples) {
                editProbenComment(p, c);
            }
        }
    }

    /**
     * deletes a comment belonging to the samples of a process step
     * @param ps the process step
     * @param c the comment
     */
    public void deleteComment(ProzessSchritt ps, String c) {
        if(ps == null || c.equals("")) {
            errorMessage("Input for deleteComment failed");
        }
        else {
            List<Probe> samples = traegerController.getProben(prozessSchrittController.getTraeger(ps));
            for(Probe p : samples) {
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
        Kommentar k = new Kommentar(LocalDateTime.now(), c);
        probeController.setKommentarForProbe(p, k);
    }

    /**
     * edits a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void editProbenComment(Probe p, String c) {
        if(p==null || c.equals("")) {
            errorMessage("the inputs are not valid");
        }
        else {
            Kommentar findK = probeController.getKommentarForProbe(p);
            if (findK != null) {
                findK.setText(c);
                probeController.setKommentarForProbe(p, findK);
            } else {
                addProbenComment(p, c);
            }
        }
    }

    /**
     * deletes a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void deleteProbenComment(Probe p, String c) {
        if(p==null || c.equals("")) {
            errorMessage("The inputs are not valid");
        }
        else {
            Kommentar k = p.getKommentar();
            if (k != null && k.getText().equals(c)) {
                probeController.setKommentarForProbe(p, null);
            } else {
                errorMessage("There was an error while trying to delete the comment");
            }
        }
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     * @return a set containing all those samples
     */
    public Set<Probe> viewToBeUploaded() {
        return null;
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
    }

    /**
     * reports a sample as lost by its id
     * @param id the id of the sample to be reported
     */
    public void reportLostProbe(int id) {
        Probe p = probeController.getProbeById(id);
        if(p!=null) {
            reportLostProbe(p);
        }
        else {
            errorMessage("the sample could not be found in the database");
        }
    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {

    }

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
}
