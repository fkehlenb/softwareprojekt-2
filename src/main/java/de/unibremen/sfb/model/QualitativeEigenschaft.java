package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Data class for the qualitative descriptors
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NamedQueries({
        @NamedQuery(name = "QualitativeEigenschaft.getAll", query = "SELECT q FROM QualitativeEigenschaft q WHERE q.isValidData = true")
})

public class QualitativeEigenschaft {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    /**
     * ID descriptor
     */
    @NonNull
    @Id
    private int id;
    /**
     * Qualitative descriptor name
     */
    @NonNull
    private String name;

    @Override
    public String toString() {
        return "QualitativeEigenschaft " +
                name;
    }
}
