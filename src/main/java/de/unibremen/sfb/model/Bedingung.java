package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/** Conditional data class, used to make sure certain conditions apply */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Bedingung {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    @Id
    @NonNull
    private int id;

    @NonNull
    private String name;

    /** Process step parameters that the material must have */
    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<ProzessSchrittParameter> prozessSchrittParameter;

    // Die gewunschte Anzahl an Proben die dieser Bedingung entsprechen
    @NonNull
    private int gewuenschteAnzahl;

    @Override
    public String toString() {
        return "Bed: " + name;
    }
}
