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

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** Process step parameters */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProzessSchrittParameter> prozessSchrittParameters;

    /** The experimenting station the process step is being carried out at */
    @ManyToOne
    private ExperimentierStation experimentierStation;

    /** Process step template id */
    @Id
    @Generated
    @NonNull
    private int psVID;

    /** Duration of the process step template */
    @NonNull
    private String dauer;

    /** Accepted container input types */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TraegerArt> ausgabeTraeger;

    /** Process step template name */
    @NonNull
    private String name;
    /** The process step type */
    @NonNull
    private String psArt;

    /** The experimenting stations accepted in the process step template */
    @ManyToOne
    @NonNull
    private  ExperimentierStation stationen;

    /** The process step predicates */
    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<ProzessSchrittParameter> parameter;

    /** The state Automaton for the process step */
    @NonNull
    @ManyToOne
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatVorlage;

    @Override
    public String toString() {
        return "PSV" + this.psVID;
    }
}
