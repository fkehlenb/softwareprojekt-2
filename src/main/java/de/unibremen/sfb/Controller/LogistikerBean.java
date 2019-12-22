package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;
import org.apache.commons.lang3.tuple.Pair;

import java.io.Serializable;
import java.util.Set;

/**
 * this class manages the interaction between the gui and the backend system for users who are logistic experts
 */
public class LogistikerBean implements Serializable {

    /**
     * the user managed by this bean
     */
    public User logistiker;

    /**
     * returns all carriers currently existing
     * @return a set containing all carriers
     */
    public Set<Traeger> getTraegerList() { return null; }

    /**
     * creates a new carrier
     * @return the new carrier
     */
    public Traeger createTraeger() { return null; }

    /**
     * deletes a carrier
     * @param t the carrier to be deleted
     */
    public void deleteTraeger(Traeger t) {}

    /**
     * returns all jobs currently awaiting processing by the logistic
     * @return a set containing all jobs currently awaiting processing
     */
    public Set<Auftrag> getAuftrag() { return null; }

    /**
     * assigns a sample to a job
     * @param a the job
     * @param p the sample
     */
    public void zuorndnenProbe(Auftrag a, Probe p) {}

    /**
     * assigns a carrier to a job
     * @param a the job
     * @param t the carrier
     */
    public void zuordnenTraeger(Auftrag a, Traeger t) {}

    /**
     * returns all samples currently existing
     * @return a set containing all samples
     */
    public Set<Probe> getProben() { return null; }

    /**
     * returns all samples currently existing by whether or not they are archived
     * @param archiviert True: display archived samples. False: display samples which are not archived
     * @return a set containing all samples which fullfill the condition
     */
    public Set<Probe> getProben(boolean archiviert) { return null; }

    /**
     * returns the current location of a sample
     * @param p the sample
     * @return the location
     */
    public Standort getProbenStandort(Probe p) { return null; }

    /**
     * starts a job
     * @param a the job to be started
     */
    public void startAuftrag(Auftrag a) {}

    /**
     * refuses a job (signals to the process chain administrator that this job cannot be started in the current form)
     * @param a the job
     * @param message the message to the process chain administrator
     */
    public void refuseAuftrag(Auftrag a, String message) {}

    /**
     * returns an error message
     * @return the error message
     */
    public String errorMessage() { return null; }

    /**
     * assigns himself to a job so that no other logistic expert starts working on it
     * @param a the job
     */
    public void zuordnen(Auftrag a) {}

    /**
     * get's specific samples and their amount
     * @param a the job
     * @return the Pair of samples with the amount.
     */
    public Set<Pair<QualitativeEigenschaft,Integer>> getAngeforderteProben(Auftrag a){return null;}

    /**
     * the empty constructor
     */
    public LogistikerBean() {}

    /**
     * returns the logistic expert managed by this bean
     * @return the user
     */
    public User getLogistiker() { return logistiker; }

    /**
     * sets the logistic expert managed by this bean
     * @param logistiker the user
     */
    public void setLogistiker(User logistiker) { this.logistiker = logistiker; }
}
