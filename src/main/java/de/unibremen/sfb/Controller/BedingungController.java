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
     * //TODO was
     * @param psz process step parameter
     */
    public void setPSZ(Set<ProzessSchrittParameter> psz) {}

    public Set<ProzessSchrittParameter> getPSZ() { return null; }

    public void setEigenschaften(Set<QualitativeEigenschaft> eigenschaft) {}

    public Set<QualitativeEigenschaft> getEigenschaften() { return null; }

    public void setBedingung(Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> bedingung) {}

    public Pair<Set<ProzessSchrittParameter>,Set<QualitativeEigenschaft>> getBedingung() { return null; }
}
