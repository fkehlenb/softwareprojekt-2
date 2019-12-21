package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittArt;
import java.util.Set;

/**
 * this class manages the interaction with models of process chain types (ProzessKettenArt)
 */
public class ProzessSchrittArtController {

    /**
     * the ProzessSchrittArt managed by an instance of this controller
     */
    public ProzessSchrittArt psa;

    /**
     * Sets the ProzessSchrittArten which a ProzessSchritt can be.
     *
     * @param psa A set containing the ProzessSchrittArten
     */
    public void setProzessSchrittArt(Set<String> psa) {}

    /**
     * Returns the currently existing ProzessSchrittArten
     *
     * @return A set with the currently existing ProzessSchrittArten
     */
    public Set<String> getProzessSchrittArt() { return null; }
}
