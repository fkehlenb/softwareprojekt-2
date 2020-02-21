package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/** Conditional data class, used to make sure certain conditions apply */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bedingung {

    @Id
    @NonNull
    private int id;

    @NonNull
    private String name;

    /** Process step parameters that the material must have */
    @OneToMany(fetch = FetchType.EAGER)
    @NonNull
    private List<ProzessSchrittParameter> prozessSchrittParameter;

    // Die gewunschte Anzahl an Proben die dieser Bedingung entsprechen
    private int gewuenschteAnzahl;

    @Override
    public String toString() {
        return "Bed: " + name;
    }
}
