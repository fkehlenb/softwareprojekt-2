package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittParameter;
import de.unibremen.sfb.Model.QualitativeEigenschaft;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

/**
 * this class manages the interaction with models of conditions
 */
public class BedingungController {

    /**
     * //TODO was
     */
    public ModuleLayer.Controller controller;

    /**
     * sets the process step parameters for this condition //TODO
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
     * TODO
     * @param bedingung TODO
     */
    public void setBedingung(Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> bedingung) {}

    public Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> getBedingung() { return null; }
}
