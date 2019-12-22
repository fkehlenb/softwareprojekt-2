package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.QuantitativeEigenschaft;
import si.uom.SI;

/**
 * this class manages the interaction with models of quantity properties
 */
public class QuantitativeEigenschaftController extends QualitativeEigenschaftController {

    public QuantitativeEigenschaft eigenschaft;

    /**
     * sets the value for this quantity property
     * @param wert the new value
     */
    public void setWert(Number wert) {}

    /**
     * returns the value of this quantitiy property
     * @return the value
     */
    public Number getWert() { return null; }

    /**
     * sets the unit the value is in
     * @param einheit the new unit
     */
    public void setEinheit(SI einheit) {}

    /**
     * returns the unit the value is in
     * @return the unit
     */
    public SI getEinheit() { return null; }
}
