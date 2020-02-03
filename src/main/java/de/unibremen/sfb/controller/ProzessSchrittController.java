package de.unibremen.sfb.controller;


import de.unibremen.sfb.model.*;

import javax.inject.Inject;
import java.util.Set;

/**
 * this class manages the interaction with models of process chains (ProzessKetten)
 */
public class ProzessSchrittController {

    /**
     * the ProzessSchritt managed by an instance of this controller
     */
    public ProzessSchritt ps;

    @Inject
    AuftragController auftragController;

    /**
     * Sets the Auftrag wo which this ProzessSchritt belongs
     *
     * @param a The new Auftrag to which this ProzessSchritt should belong
     */
    public void setAuftrag(Auftrag a){
        if(a!=null) {
            auftragController.addPStoAuftrag(a, ps);
        }
    }

    /**
     * Returns the Auftrag to which this ProzessSchritt belongs
     *
     * @return The Auftrag
     */
    public Auftrag getAuftrag(){
        return null;
    }

    /**
     * Returns if the data to this ProzessSchritt (process step) was already uploaded by a user
     * @return True if the data was uploaded, False if not
     */
    public boolean uploaded() { return false; }

    /**
     * Sets if the data to this ProzessSchritt was uploaded
     */
    public void setUploaded() {}

    /**
     * Returns the ExperimentierStationen at which this ProzessSchritt can be executed
     *
     * @return A set with all possible ExperimentierStationen
     */
    public Set<ExperimentierStation> getES(){
        return ps.getProzessSchrittVorlage().getStationen();
    }

    /**
     * Returns the ProzessKettenVorlage to which this ProzessSchritt belongs
     *
     * @return the ProzessKettenVorlage
     */
    public ProzessKettenVorlage getPSV() { return null; }

    /**
     * Sets the protocol for this ProzessSchritt
     *
     * @param psl The new protocol
     */
    public void setPSLog(ProzessSchrittLog psl) {
        //log set oder so?
    }

    /**
     * Returns the protocol of this ProzessSchritt which was created up until this point.
     *
     * @return The protocol thus far
     */
    public ProzessSchrittLog getPSLog() { return null; }

    /**
     * Sets a TransportAuftrag (transportation assignment) for this ProzessSchritt
     * @param ta the TransportAuftrag
     */
    public void setTransportAuftrag(TransportAuftrag ta) {}

    /**
     * Returns the TransportAuftrag (transportation assignment), if there is one for this ProzessSchritt
     * @return the TransportAuftrag
     */
    public TransportAuftrag getTransportAuftrag() { return ps.getTransportAuftrag(); }

    /**
     * returns the Traeger which is currently assigned to a prozessSchritt
     * @param ps the prozessSchritt
     * @return the Traeger
     */
    public Traeger getTraeger(ProzessSchritt ps) {
        return ps.getTraeger();
    }
}
