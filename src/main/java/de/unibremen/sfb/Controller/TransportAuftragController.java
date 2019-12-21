package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.TransportAuftrag;
import de.unibremen.sfb.Model.TransportAuftragZustand;

/**
 * this class manages the interaction with models of tranport assignments (TransportAuftrag)
 */
public class TransportAuftragController {

    /**
     * the TransportAuftrag managed by an instance of this controller
     */
    public TransportAuftrag ta;

    /**
     * Sets the current Zustand (status) of this TransportAuftrag
     *
     * @param taz the new Zustand of this TransportAuftrag
     */
    public void setZustand(TransportAuftragZustand taz) {}

    /**
     * Returns the current Zustand (state) of this TransportAuftrag)
     *
     * @return the current Zustand
     */
    public TransportAuftragZustand getZustand() { return null; }
}
