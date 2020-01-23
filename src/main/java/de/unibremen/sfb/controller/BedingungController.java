package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

/**
 * this class manages the interaction with models of conditions
 */
public class BedingungController {

    /**
     * the BedingungController managed by this class
     */
    public BedingungController controller;

    /**
     * sets the process step parameters for this condition
     * @param psz process step parameter
     */
    public void setPSZ(Set<ProzessSchrittParameter> psz) {}

    /**
     * returns the process step parameter for this condition
     * @return a set containing all process step parameters for this condition
     */
    public Set<ProzessSchrittParameter> getPSZ() { return null; }

    /**
     * sets the properties for this condition
     * @param eigenschaft a set containing all properties for this condition
     */
    public void setEigenschaften(Set<QualitativeEigenschaft> eigenschaft) {}

    /**
     * returns the properties for this condition
     * @return a set containing all properties for this condition
     */
    public Set<QualitativeEigenschaft> getEigenschaften() { return null; }

    /**
     * sets the process step parameters and properties for this condition
     * @param bedingung a set containing the new parameters and conditions
     */
    public void setBedingung(Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> bedingung) {}

    /**
     * returns all process step parameters and properties for this condition
     * @return a set containing all parameters and properties
     */
    public Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> getBedingung() { return null; }
}
