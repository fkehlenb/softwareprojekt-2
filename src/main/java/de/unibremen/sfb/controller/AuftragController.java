package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction with models of assignments (Auftraege)
 */
public class AuftragController {

    public Auftrag auftrag;

    @Inject
    private AuftragDAO auftragDAO;

    /**
     * returns the ID of this Auftrag
     *
     * @return the ID
     */
    public int getID() { return 0; }

    /**
     * return the ProzessKettenVorlage which was used to instantiate this Auftrag
     *
     * @return the ProzessKettenVorlage
     */
    public ProzessKettenVorlage getPKV() { return null; }

    /**
     * return the protocol of this Auftrag that was created thus far
     *
     * @return the protocol
     */
    public AuftragsLog getLog() { return null; }


    /**
     * sets the protocol of this Auftrag
     *
     * @param al the new protocol
     */
    public void setLog(AuftragsLog al) { }

    /**
     * returns the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgeführt (carried out)
     * @return the current Zustand
     */
    public ProzessKettenZustandsAutomat getPKZ() { return null; }

    /**
     * sets the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgeführt (carried out)
     */
    public void setPKZ() {}

    /**
     * sets the current state for a job
     * @param a the job for which the state is to be updated
     * @param z the new state
     */
    public void setPKZforAuftrag(Auftrag a, ProzessKettenZustandsAutomat z) {
        a.setProzessKettenZustandsAutomat(z);
        try {
            auftragDAO.update(a);
        }
        catch(AuftragNotFoundException e) {

        }
    }

    /**
     * returns the current Prioritaet (priority) of this Auftrag
     * @return the current Prioritaet
     */
    public AuftragsPrioritaet getPrio() { return null; }

    /**
     * sets the current Priorität (priority) of this Auftrag
     */
    public void setPrio() {}

    /**
     * returns the ProzessSchritte which the Auftrag consists of
     * @return a Set containing all ProzessSchritt
     */
    public Set<ProzessSchritt> getPS() { return null; }

    /**
     * sorts the list of jobs by their priority
     * @param l1 the unsorted list of jobs
     * @return the sorted list of jobs
     */
    public List<Auftrag> mergeSort(List<Auftrag> l1) {
        if (l1.size() > 1) {
            final List<Auftrag> l2 = new LinkedList<>();
            split(l1, l2);
            return merge(mergeSort(l1), mergeSort(l2));
        }
        else {
            return l1;
        }
    }

    /**
     * splits the first list in half by adding elements to the second, empty list, until both have the same size
      * @param l1 the first list: full
     * @param l2 the second list: empty
     */
    private void split(List<Auftrag> l1, List<Auftrag> l2) {
        while(l1.size() > l2.size()) {
            l2.add(l1.remove(0));
        }
    }

    /**
     * merges two (sorted) lists together by comparing their priorities
     * @param l1 the first list of jobs
     * @param l2 the second list of jobs
     * @return a sorted, merged list of jobs
     */
    private List<Auftrag> merge(List<Auftrag> l1, List<Auftrag> l2) {
        final List<Auftrag> result = new LinkedList<>();
        while(!l1.isEmpty() || !l2.isEmpty()) {
            if(l2.isEmpty() || !l1.isEmpty() && l1.get(0).getPriority().compareTo(l2.get(0).getPriority()) >= 0) {
                result.add(l1.remove(0));
            }
            else {
                result.add(l2.remove(0));
            }
        }
        return result;
    }

    /**
     * assigns a user to a job
     * @param a the job
     * @param u the user
     * @return 0, if the user was assigned; -1, if an error occured (eg the job already has an assigned user)
     */
    public int setUserForAuftrag(Auftrag a, User u) {
        if(a.getAssigned() == null) {
            a.setAssigned(u);
            try {
                auftragDAO.update(a);
                return 0;
            }
            catch(AuftragNotFoundException e) {
                return -1;
            }
        }
        else {
            return -1;
        }
    }
}
