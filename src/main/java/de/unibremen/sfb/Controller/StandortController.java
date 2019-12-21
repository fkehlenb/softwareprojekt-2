package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Standort;
import de.unibremen.sfb.Model.Traeger;
import java.util.Set;

/**
 * this class manages the interaction with models of locations (Standort)
 */
public class StandortController {

    /**
     * the Standort managed by an instance of this controller
     */
    public Standort standort;

    /**
     * Sets the current Standort (location)
     *
     * @param s The new Standort
     */
    public void setStandort(String s) {}

    /**
     * Returns the current Standort
     *
     * @return The current Standort
     */
    public String getStandort() { return null; }

    /**
     * Returns the Träger (carrier) which are currently at this Standort
     *
     * @return A Set with all Träger
     */
    public Set<Traeger> getTrager() { return null; }
}
