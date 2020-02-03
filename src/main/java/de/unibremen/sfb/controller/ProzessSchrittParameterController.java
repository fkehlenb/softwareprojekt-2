package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;

import javax.inject.Inject;
import java.util.Set;

/**
 * this class manages the interaction with models of process chain parameters (ProzessKettenParaneter)
 */
public class ProzessSchrittParameterController {

    /**
     * the ProzessSchrittParameter managed by an instance of this controller
     */
    public ProzessSchrittParameter psp;

    @Inject
    private ProzessSchrittParameterDAO pspDAO;

    /**
     * Sets the name of this ProzessSchrittParameter.
     *
     * @param s The new name
     */
    public void setName(String s) {
        if(!s.equals("")) {
            String temp = psp.getName();
            psp.setName(s);
            try {
                pspDAO.update(psp);
            }
            catch(ProzessSchrittParameterNotFoundException e) {
                psp.setName(temp);
            }
        }
    }

    /**
     * Returns the name of this ProzessSchrittParameter
     *
     * @return The name
     */
    public String getName() { return psp.getName(); }

    /**
     * returns all properties this process step parameter corresponds to
     * @return a set containing all properties of this process step paramter
     */
    public Set<QualitativeEigenschaft> getEigenschaften() { return psp.getQualitativeEigenschaften(); }

    /**
     * sets the properties this process step parameter corresponds to
     * @param eigenschaft a set containing all properties this process step parameter is supposed to have
     */
    public void setEigenschaften(Set<QualitativeEigenschaft> eigenschaft) {
        if(eigenschaft!=null) {
            Set<QualitativeEigenschaft> temp = getEigenschaften();
            psp.setQualitativeEigenschaften(eigenschaft);
            try {
                pspDAO.update(psp);
            }
            catch(ProzessSchrittParameterNotFoundException e) {
                psp.setQualitativeEigenschaften(temp);
            }
        }
    }

    /**
     * Exports this parameter to JSON
     */
    public void JSONExport() {}
}
