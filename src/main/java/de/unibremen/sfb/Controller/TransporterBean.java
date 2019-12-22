package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;

import java.util.Set;

/**
 * this bean manages the interaction of the gui with the backend system (for users who are transporters)
 */
public class TransporterBean {

    //TODO bean properties
    /**
     * the user managed by this bean
     */
    public User transporter;

    /**
     * returns all jobs available to the transporter
     * @return a set containing all those jobs
     */
    public Set<TransportAuftrag> getAuftragList() { return null; }

    /**
     * sets the status of the job this transporter is currently working on
     */
    public void setAuftragStatus() {}

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
