package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.Probe;
import de.unibremen.sfb.Model.User;

import java.util.Set;

/**
 * this bean manages the interaction of the gui with the backend system (for users who are transporters)
 */
public class TransporterBean {

    //TODO das ist so noch keine bean!! der rest auch nicht
    /**
     * the user managed by this bean
     */
    public User transporter;

    /**
     * returns all jobs available to the transporter //TODO available oder accepted?
     * @return a set containing all those jobs
     */
    public Set<Auftrag> getAuftragList() { return null; }

    /**
     * reports a sample as lost
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {}

    /**
     * reports a sample as lost via its id
     * @param id the id of the sample
     */
    public void reportLostProbe(int id) {}
}
