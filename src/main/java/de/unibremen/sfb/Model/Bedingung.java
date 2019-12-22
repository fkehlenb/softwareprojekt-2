package de.unibremen.sfb.Model;

import lombok.Data;

import java.util.Set;

/** Conditional data class, used to make sure certain conditions apply */
@Data
public class Bedingung {

    /** Process step parameters that the material must have */
    public Set<ProzessSchrittParameter> prozessSchrittParameter;

    /** Qualitative and quantitative descriptors that material needs to have */
    public Set<QualitativeEigenschaft> eigenschaften;
}
