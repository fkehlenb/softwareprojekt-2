package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;

import java.io.Serializable;
import java.util.Set;

/**
 * this bean manages the interaction of the gui with the backend system (for users who are transporters)
 */
public class TransporterBean implements Serializable {

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

    /**
     * the empty constructor
     */
    public TransporterBean(){}

    /**
     * returns the transporter managed by this bean
     * @return the user
     */
    public User getTransporter() { return transporter; }

    /**
     * sets the transporter managed by this bean
     * @param t the user
     */
    public void setTransporter(User t) { transporter = t; }
}
