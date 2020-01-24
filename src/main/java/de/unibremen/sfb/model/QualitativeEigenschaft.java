package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

/** Data class for the qualitative descriptors */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class QualitativeEigenschaft {

    /** Qualitative descriptor name */
    @Id @NonNull
    private String name;
}
