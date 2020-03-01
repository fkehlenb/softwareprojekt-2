package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProzessSchrittV2 {

    /**
     * Valid Bit
     */
    private boolean isValidData = true;

    /**
     * ID
     */
    @Id
    @NonNull
    private int id;

    /**
     * Process step name
     */
    @NonNull
    private String name;

    /**
     * Process step experimenting station
     */
    @NonNull
    @ManyToOne
    private ExperimentierStation experimentierStation;

    /**
     * Process step duration
     */
    @NonNull
    private String duration;

    /**
     * Process step state automaton
     */
    @NonNull
    @ManyToOne
    private ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;

    /**
     * Process step parameter name
     */
    @NonNull
    @ManyToOne
    private ProzessSchrittParameter prozessSchrittParameter;

    /** Process step log */
    @NonNull
    @ManyToOne
    private ProzessSchrittLog prozessSchrittLog;

    /** Attributes */
    @NonNull
    private String attributes;
}
