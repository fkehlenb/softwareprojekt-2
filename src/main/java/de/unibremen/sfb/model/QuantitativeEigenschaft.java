package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;

/** The quantitative descriptor data class */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** The numeric quantitative value  */
    @NonNull
    private Number wert;

    /**
     * The quantitative descriptor value's measuring unit
     */
    //TODO
//    @NonNull
//    @OneToOne
//    private SI einheit;
    public QuantitativeEigenschaft(String name, Number wert) {
        super(name);
        this.wert = wert;
    }

}
