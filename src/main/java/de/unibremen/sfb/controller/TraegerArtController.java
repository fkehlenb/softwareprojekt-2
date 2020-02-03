package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.TraegerArtNotFoundException;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.persistence.TraegerArtDAO;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction with models of carrier types (TraegerArt)
 */
public class TraegerArtController {

    /**
     * the TraegerArt managed by an instance of this controller
     */
    public TraegerArt traegerart;

    @Inject
    private TraegerArtDAO traegerArtDAO;

    /**
     * Sets the TraegerArten (types of carriers) which a Traeger can be
     *
     * @param a A Set with all possible TraegerArten
     */
    public void setTraegerArten(Set<String> a) {
        if(a!=null) {
            Set<String> temp = getTraegerArten();
            traegerart.setArten(a);
            try{
                traegerArtDAO.update(traegerart);
            }
            catch(TraegerArtNotFoundException e) {
                traegerart.setArten(temp);
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the TraegerArten (types of carriers) which a Traeger can be
     *
     * @return A Set with all possible TraegerArten
     */
    public Set<String> getTraegerArten() { return traegerart.getArten(); }
}
