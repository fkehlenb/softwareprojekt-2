package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.TraegerArt;
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
     * @param ta A Set with all possible TraegerArten
     */
    public void setTraegerArten(Set<String> ta) {}

    /**
     * Returns the TraegerArten (types of carriers) which a Traeger can be
     *
     * @return A Set with all possible TraegerArten
     */
    public Set<String> getTraegerArten() { return null; }
}
