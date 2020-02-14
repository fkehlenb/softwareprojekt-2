package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

/** The process chain step parameter's data class  */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class ProzessSchrittParameter {

    @Id
    @NonNull
    private int id;

    /** The process step parameter's name */
    @NonNull
    private String name;

    /** The qualitative/quantitative descriptors creating this process step parameter */
    @NonNull
    @ManyToMany
    private List<QualitativeEigenschaft> qualitativeEigenschaften;
}
