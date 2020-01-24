package de.unibremen.sfb.model;

import lombok.*;
import si.uom.SI;

import javax.persistence.*;

/** The quantitative descriptor data class */
@Getter
@Setter
@Entity
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** The numeric quantitative value  */
    @NonNull
    private Number wert;

    /** The quantitative descriptor value's measuring unit */
    //TODO
//    @NonNull
//    @Basic
//    @OneToOne
//    private SI einheit;
}
