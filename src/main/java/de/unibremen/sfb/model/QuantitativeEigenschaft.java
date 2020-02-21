package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/** The quantitative descriptor data class */
@Getter
@Setter
@Entity
@NoArgsConstructor
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** On delete set to invalid */
    private boolean isValidData = true;

    /** The numeric quantitative value  */
    @NonNull
    private Number wert;

    /**
     * The quantitative descriptor value's measuring unit
     */
    @NonNull
    private String einheit;


//    private SI einheit;
    public QuantitativeEigenschaft(String name, Number wert) {
        super(UUID.randomUUID().hashCode() ,name);
        this.wert = wert;
    }

}
