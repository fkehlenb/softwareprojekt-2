package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

/** Conditional data class, used to make sure certain conditions apply */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bedingung {

    @Id @GeneratedValue
    private int id;

    /** Process step parameters that the material must have */
    @OneToMany
    private List<ProzessSchrittParameter> prozessSchrittParameter;

    /** Qualitative and quantitative descriptors that material needs to have */
    @OneToMany
    private List<QualitativeEigenschaft> eigenschaften;
}
