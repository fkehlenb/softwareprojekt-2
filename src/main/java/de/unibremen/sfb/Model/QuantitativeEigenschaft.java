package de.unibremen.sfb.Model;

import lombok.Data;
import si.uom.SI;

@Data
/** The quantitative descriptor data class */
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** The numeric quantitative value  */
    public Number wert;

    /** The quantitative descriptor value's measuring unit */
    public SI einheit;
}
