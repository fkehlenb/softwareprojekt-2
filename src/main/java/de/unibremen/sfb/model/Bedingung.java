package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/** Conditional data class, used to make sure certain conditions apply */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bedingung {

    /** On delete set to invalid */
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    /** Process step parameters that the material must have */
    @OneToMany
    private List<ProzessSchrittParameter> prozessSchrittParameter;

    private int gewuenschteAnzahl;
}
