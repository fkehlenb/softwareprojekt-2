package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;

import java.io.Serializable;
import java.util.Set;

/**
 * this class manages the interaction between the gui and the backend system for users who are process chain administrators
 */
public class PKAdminBean implements Serializable {
    /**
     * the user managed by this bean
     */
    public User pkadmin;

    /**
     * returns all existing jobs
     * @return a set containing all existing jobs
     */
    public Set<Auftrag> getAuftragList() { return null; }

    /**
     * returns all existing process steps
     * @return a set containing all existing process steps
     */
    public Set<ProzessSchritt> getProzessSchrittList() { return null; }

    /**
     * adds a process chain template
     * @param pkv the new process chain template
     */
    public void addProzessKettenVorlage(ProzessKettenVorlage pkv) {}

    /**
     * edits a process chain template
     * @param pkv the process chain template to be edited
     */
    public void editProzessKettenVorlage(ProzessKettenVorlage pkv) {}

    /**
     * deletes a process chain template
     * @param pkv the process chain template to be deleted
     */
    public void deleteProzessKettenVorlage(ProzessKettenVorlage pkv) {}

    /**
     * adds a process step to a job. the job may not be started already
     * @param ps the process step //woher weiß er welcher job
     */
    public void addPSToAuftrag(ProzessSchritt ps) {}

    /**
     * adds a process step template to a process chain template
     * @param pkv the process chain template
     * @param psv the proces step template
     */
    public void addProzessSchrittVorlage(ProzessKettenVorlage pkv, ProzessSchrittVorlage psv) {}

    /**
     * edits a process step template in a process chain template
     * @param pkv the process chain template
     * @param psv the process step template
     */
    public void editProzessSchrittVorlage(ProzessKettenVorlage pkv, ProzessSchrittVorlage psv) {}

    /**
     * deletes a process step template from a process chain template
     * @param pkv the process chain template
     * @param psv the process step template
     */
    public void deleteProzessSchrittVorlage(ProzessKettenVorlage pkv, ProzessSchrittVorlage psv) {}

    /**
     * adds a sample to the system
     * @param p the new sample
     */
    public void addProbe (Probe p) {}

    /**
     * deletes a sample
     * @param p the sample to be deleted
     */
    public void deleteProbe(Probe p) {}

    /**
     * adds a carrier to the system
     * @param t the new carrier
     */
    public void addTraeger(Traeger t) {}

    /**
     * edits a carrier
     * @param t the carrier to be edited
     */
    public void editTraeger(Traeger t) {}

    /**
     * deletes a carrier
     * @param t the carrier to be deleted
     */
    public void deleteTraeger(Traeger t) {}

    /**
     * creates a job from a process chain template
     * @param pkv the process chain template
     */
    public void createAuftrag(ProzessKettenVorlage pkv) {}

    /**
     * deletes a job
     * @param auftrag the job to be deleted
     */
    public void deleteAuftrag(Auftrag auftrag) { }

    /**
     * edits a job
     * @param a the job to be edited
     */
    public void editAuftrag(Auftrag a) {}

    /**
     * sets a condition for a process step
     * @param ps the process step
     * @param b the condition
     */
    public void setBedingung(ProzessSchritt ps, Bedingung b) {}

    /**
     * edits a condition for a process step
     * @param ps the process step
     * @param b the condition
     */
    public void editBedingung(ProzessSchritt ps, Bedingung b) {}

    /**
     * removes a condition from a process step
     * @param ps the process step
     * @param b the condition
     */
    public void removeBedingung(ProzessSchritt ps, Bedingung b) {}

    /**
     * sets the experimentation station a process step is supposed to be executed at
     * @param es the station
     * @param ps the process step
     */
    public void setExperimentierStationZuPS(ExperimentierStation es, ProzessSchritt ps) {}

    /**
     * edits a experimentation station at which a process step is supped to be executed
     * @param es the station
     * @param ps the process step
     */
    public void editExperimentierStationZuPS(ExperimentierStation es, ProzessSchritt ps) {}

    /**
     * sets the priority a job
     * @param a the job
     */
    public void setPrio(Auftrag a) {}

    /**
     * edits the priority of a job
     * @param a the job
     */
    public void editPrio(Auftrag a) {}

    /**
     * deletes a process step
     * @param ps the process step
     */
    public void deleteProzessSchritt(ProzessSchritt ps) {}

    /**
     * creates a template for a process step state automaton
     * @param pszav the new process step state automaton template
     */
    public void createZustandsAutomat(ProzessSchrittZustandsAutomatVorlage pszav) {}

    /**
     * removes a process step state automaton template
     * @param pszav the process step state automaton template to be deleted
     */
    public void removeZustandsAutomat(ProzessSchrittZustandsAutomatVorlage pszav) {}

    /**
     * edits a process step state automaton template
     * @param pszav the process step state automaton template to be edited
     */
    public void editZustandsAutomat(ProzessSchrittZustandsAutomatVorlage pszav) {}

    /**
     * opens a job for the logistic expert to assign samples
     * @param a the job
     */
    public void setFreigegeben(Auftrag a) {}

    /**
     * stops a job
     * @param a the job to be stopped
     */
    public void stopAuftrag(Auftrag a) {}

    /**
     * displays the usage of an experimentation station
     */
    public void viewESUsage() {}

    /**
     * displays the usage of an experimentation station
     * @param es the station of which the usage is to be displayed
     */
    public void viewESUsage(ExperimentierStation es) {}

    /**
     * exports something to json? // TODO ps angeben
     */
    public void exportJSON() {}

    /**
     * the empty constructor
     */
    public PKAdminBean() {}

    /**
     * returns the process chain administrator managed by this bean
     * @return the user
     */
    public User getPkadmin() { return pkadmin; }

    /**
     * sets the process chain administrator managed by this bean
     * @param pkadmin the user
     */
    public void setPkadmin(User pkadmin) { this.pkadmin = pkadmin; }

    /**
     * Export a chain
     */

}