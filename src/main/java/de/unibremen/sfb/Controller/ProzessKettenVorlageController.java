package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessKettenVorlage;
import de.unibremen.sfb.Model.ProzessSchrittVorlage;
import java.util.Set;

/**
 * this class manages the interaction with models of process chain templates (ProzessKettenVorlage)
 */
public class ProzessKettenVorlageController {

    /**
     * the ProzessKettenVorlage managed by an instance of this controller
     */
    public ProzessKettenVorlage pkv;

    /**
     * Sets the ID of this ProzessKettenVorlage.
     *
     * @param id the new ID
     */
    public void setID(int id) {}

    /**
     * Returns the ID of this ProzessKettenVorlage.
     *
     * @return the ID
     */
    public int getID() { return 0; }

    /**
     * Sets the ProzessSchrittVorlagen this ProzessKettenVorlage consists of.
     *
     * @param psv A set containing the ProzessSchrittVorlagen
     */
    public void setPSV(Set<ProzessSchrittVorlage> psv) {}

    /**
     * Returns the ProzessSchrittVorlagen this ProzessKettenVorlage consists of.
     *
     * @return A set containing the ProzessSchrittVorlagen
     */
    public Set<ProzessSchrittVorlage> getPSV() { return null; }
}
