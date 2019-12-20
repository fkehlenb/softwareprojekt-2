package de.unibremen.sfb.Model;

import lombok.Data;

import java.util.List;

@Data
/* Data class for the qualitative descriptors */
public class QualitativeEigenschaft {

    /** Descriptor name */
    public String name;

    /** The samples that have this qualitative descriptor */
    public List<Probe> proben;

    /** The process parameters made out of the qualitative descriptor */
    public List<ProzessParameter> parameter;
}
