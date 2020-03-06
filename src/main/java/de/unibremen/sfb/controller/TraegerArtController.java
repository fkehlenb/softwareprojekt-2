package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.TraegerArt;

import java.util.Set;

/**
 * this class manages the interaction with models of carrier types (TraegerArt)
 */
public class TraegerArtController {

    /**
     * the TraegerArt managed by an instance of this controller
     */
    public TraegerArt traegerart;

    /**
     * Sets the TraegerArten (types of carriers) which a Traeger can be
     *
     * @param a A Set with all possible TraegerArten
     */
    public void setTraegerArten(Set<String> a) {}

    /**
     * Returns the TraegerArten (types of carriers) which a Traeger can be
     *
     * @return A Set with all possible TraegerArten
     */
    public Set<String> getTraegerArten() { return null; }
}
