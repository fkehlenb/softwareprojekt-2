package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

/** Data class for the qualitative descriptors */
@Data
@Entity
public class QualitativeEigenschaft {

    /** Qualitative descriptor name */
    @Id
    public String name;
}
