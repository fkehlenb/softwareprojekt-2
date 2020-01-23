package de.unibremen.sfb.model;

import lombok.Data;

import javax.persistence.OneToMany;
import java.util.Set;

/** Conditional data class, used to make sure certain conditions apply */
@Data
public class Bedingung {

    /** Process step parameters that the material must have */
    @OneToMany
    public Set<ProzessSchrittParameter> prozessSchrittParameter;

    /** Qualitative and quantitative descriptors that material needs to have */
    @OneToMany
    public Set<QualitativeEigenschaft> eigenschaften;
}
