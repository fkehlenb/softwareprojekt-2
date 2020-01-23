package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;
import si.uom.SI;

import javax.persistence.Entity;
import javax.persistence.Id;

/** The quantitative descriptor data class */
@Data
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** The numeric quantitative value  */
    @NonNull
    public Number wert;

    /** The quantitative descriptor value's measuring unit */
    @NonNull
    public SI einheit;
}
