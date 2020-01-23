package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

/** Data class for the qualitative descriptors */
@Data
public class QualitativeEigenschaft {

    /** Qualitative descriptor name */
    public String name;

    public QualitativeEigenschaft(String name) {
        this.name = name;
    }
}
