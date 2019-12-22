package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

/** Data class for the qualitative descriptors */
@Data
public class QualitativeEigenschaft {

    /** Qualitative descriptor name */
    @NonNull
    public String name;
}
