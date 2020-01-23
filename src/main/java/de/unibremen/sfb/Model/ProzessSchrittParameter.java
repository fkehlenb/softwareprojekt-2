package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;

/** The process chain step parameter's data class  */
@Data
public class ProzessSchrittParameter {

    /** The process step parameter's name */
    @NonNull
    public String name;

    /** The qualitative/quantitative descriptors creating this process step parameter */
    @NonNull
    public Set<QualitativeEigenschaft> qualitativeEigenschaften;

    public ProzessSchrittParameter(String name, HashSet<QualitativeEigenschaft> qualitativeEigenschaften) {
        this.name = name;
        this.qualitativeEigenschaften = qualitativeEigenschaften;
    }
}
