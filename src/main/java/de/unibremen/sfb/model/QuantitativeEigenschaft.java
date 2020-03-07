package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;

/** The quantitative descriptor data class */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class QuantitativeEigenschaft extends QualitativeEigenschaft {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** The numeric quantitative value  */
    @NonNull
    private Number wert;

    /**
     * The quantitative descriptor value's measuring unit
     */
    @NonNull
    private String einheit;

    public QuantitativeEigenschaft(int id, String name, Number wert, String einheit){
        super(id,name);
        this.wert=wert;
        this.einheit=einheit;
    }

    public QuantitativeEigenschaft(String name, Number wert, String einheit){
        super();
        this.wert=wert;
        this.einheit=einheit;
    }

    @Override
    public String toString() {
        return "QuantitativeEigenschaft{" +
                "wert=" + wert +
                ", einheit='" + einheit + '\'' +
                '}';
    }
}
