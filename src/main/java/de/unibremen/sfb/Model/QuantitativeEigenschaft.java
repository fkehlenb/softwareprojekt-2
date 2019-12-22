package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;
import si.uom.SI;

/** The quantitative descriptor data class */
@Data
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** The numeric quantitative value  */
    @NonNull
    public Number wert;

    /** The quantitative descriptor value's measuring unit */
    @NonNull
    public SI einheit;

    QuantitativeEigenschaft() {
        super("");
    }
}
