package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/** Conditional data class, used to make sure certain conditions apply */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bedingung {

    @Id
    private int id;

    @NotEmpty
    private String name;

    /** Process step parameters that the material must have */
    @OneToMany
    private List<ProzessSchrittParameter> prozessSchrittParameter;

    // Die gewunschte Anzahl an Proben die dieser Bedingung entsprechen
    private int gewuenschteAnzahl;
}
