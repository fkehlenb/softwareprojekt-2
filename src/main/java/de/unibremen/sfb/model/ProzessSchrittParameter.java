package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

/** The process chain step parameter's data class  */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchrittParameter {

    @Id @GeneratedValue
    private int id;

    /** The process step parameter's name */
    @NonNull
    private String name;

    /** The qualitative/quantitative descriptors creating this process step parameter */
    @NonNull
    @ManyToMany
    private Set<QualitativeEigenschaft> qualitativeEigenschaften;
}
