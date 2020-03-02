package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/** Data class for the process step templates */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchrittVorlage {

    /** Process step template id */
    @Id
    @NonNull
    private int psVID;

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** Process step parameters */
    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<ProzessSchrittParameter> prozessSchrittParameters;

    /** The experimenting station the process step is being carried out at */
    @ManyToOne
    @NonNull
    private ExperimentierStation experimentierStation;

    /** Duration of the process step template */
    @NonNull
    private String dauer;

    /** Process step template name */
    @NonNull
    private String name;

    /** The state Automaton for the process step */
    @NonNull
    @ManyToOne
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatVorlage;
}
