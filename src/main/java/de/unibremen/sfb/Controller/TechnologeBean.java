package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;

import java.util.Set;

/**
 * this class manages the interaction of the gui with the backend system (for users who are technologists)
 */
public class TechnologeBean {

    /**
     * the user managed by this bean
     */
    public User technologe;

    /**
     * returns the experimentation stations this user is assigned to //TODO ?
     * @return a set containing all stations this user is assigned to
     */
    public Set<ExperimentierStation> getStationen() { return null; }

    /**
     * returns the assignments currently available for this user
     * @return a set containing all availabe jobs
     */
    public Set<Auftrag> getAuftrag() { return null; }

    /**
     * sets the state of a job
     * @param a the job //TODO with the new state?
     */
    public void setAuftragsZustand(Auftrag a) {}

    /**
     * reports an experimentation station as broken
     * @param es the station
     */
    public void reportBroken(ExperimentierStation es) {}

    /**
     * assigns this user to a job
     * @param a the job
     */
    public void assignToAuftrag(Auftrag a) {}

    /**
     * sorts a list of jobs by their priority
     * @param prio a set containing all jobs to be sorted
     */
    public void prioSort(Set<Auftrag> prio) {}

    /**
     * creates a "urformende" process chain (meaning a process chain which creates new samples) //TODO wirklich hier
     */
    public void createUrformend() {}

    /**
     * adds a comment to a process step
     * @param ps the process step
     * @param c the comment
     */
    public void addComment(ProzessSchritt ps, String c) {}

    /**
     * edits a comment which belongs to a process step
     * @param ps the process step the comments belongs to
     * @param c the comment
     */
    public void editComment(ProzessSchritt ps, String c) {}

    /**
     * deletes a comment belonging to a process step
     * @param ps the process step
     * @param c the comment
     */
    public void deleteComment(ProzessSchritt ps, String c) {}

    /**
     * adds a comment to a sample
     * @param p the sample
     * @param c the comment
     */
    public void addProbenComment(Probe p, String c) {}

    /**
     * edits a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void editProbenComment(Probe p, String c) {}

    /**
     * deletes a comment belonging to a sample
     * @param p the sample
     * @param c the comment
     */
    public void deleteProbenComment(Probe p, String c) {}

    /**
     * returns all samples to which the user has not yet uploaded data //TODO was
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
    public void reportLostProbe(Probe p) {}

    /**
     * reports a sample as lost by its id
     * @param id the id of the sample to be reported
     */
    public void reportLostProbe(int id) {}

    /**
     * //TODO warum returnt das das nicht?
     * @param e error messsage
     */
    public void errorMessage(String e) {}



}
