package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.TransportAuftrag;
import de.unibremen.sfb.model.TransportAuftragZustand;

import java.util.HashSet;
import java.util.Set;

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
     * @param e the new Zustand of this TransportAuftrag
     */
    public void setZustand(TransportAuftragZustand e) {}

    /**
     * Returns the current Zustand (state) of this TransportAuftrag)
     *
     * @return the current Zustand
     */
    public TransportAuftragZustand getZustand() { return null; }

    public ExperimentierStation getDestination() { return null; }

    public Set<String> getDefaults() {
        Set<String> returns = new HashSet<>();
        returns.add("Abgeholt");
        returns.add("Abgegeben");
        return returns;
    }
}
