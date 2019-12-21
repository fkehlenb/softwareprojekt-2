package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittParameter;
import de.unibremen.sfb.Model.QualitativeEigenschaft;

import java.util.Set;

/**
 * this class manages the interaction with models of process chain parameters (ProzessKettenParaneter)
 */
public class ProzessSchrittParameterController {

    /**
     * the ProzessSchrittParameter managed by an instance of this controller
     */
    public ProzessSchrittParameter psp;

    /**
     * Sets the name of this ProzessSchrittParameter.
     *
     * @param s The new name
     */
    public void setName(String s) {}

    /**
     * Returns the name of this ProzessSchrittParameter
     *
     * @return The name
     */
    public String getName() { return null; }

    /**
     * returns all properties this process step parameter has //TODO ?
     * @return a set containing all properties of this process step paramter
     */
    public Set<QualitativeEigenschaft> getEigenschaften() { return null; }

    /**
     * sets the properties of this process step parameter
     * @param eigenschaft a set containing all properties this process step parameter is supposed to have
     */
    public void setEigenschaften(Set<QualitativeEigenschaft> eigenschaft) {}

    /**
     * //TODO
     */
    public void JSONExport() {}
}
