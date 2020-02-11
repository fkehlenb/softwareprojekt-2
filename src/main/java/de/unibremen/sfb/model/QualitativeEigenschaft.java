package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/** Data class for the qualitative descriptors */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "QualitativeEigenschaft.getAll", query = "SELECT q FROM QualitativeEigenschaft q")
})
public class QualitativeEigenschaft {

    /** Qualitative descriptor name */
    @Id @NonNull
    private String name;
}
