package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

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
    public void setPSZ(List<ProzessSchrittParameter> psz) {
        controller.setPSZ(psz);
    }

    /**
     * returns the process step parameter for this condition
     * @return a set containing all process step parameters for this condition
     */
    public List<ProzessSchrittParameter> getPSZ() {
        return controller.getPSZ();
    }

    /**
     * sets the properties for this condition
     * @param eigenschaft a set containing all properties for this condition
     */
    public void setEigenschaften(List<QualitativeEigenschaft> eigenschaft) {
        controller.setEigenschaften(eigenschaft);
    }

    /**
     * returns the properties for this condition
     * @return a set containing all properties for this condition
     */
    public List<QualitativeEigenschaft> getEigenschaften() {
        return controller.getEigenschaften();
    }

    /**
     * sets the process step parameters and properties for this condition
     * @param bedingung a set containing the new parameters and conditions
     */
    public void setBedingung(Pair<List<ProzessSchrittParameter>,List<QualitativeEigenschaft>> bedingung) {
        controller.setBedingung(bedingung);
    }

    /**
     * returns all process step parameters and properties for this condition
     * @return a set containing all parameters and properties
     */
    public Pair<List<ProzessSchrittParameter>,List<QualitativeEigenschaft>> getBedingung() {
        return controller.getBedingung();
    }
}
