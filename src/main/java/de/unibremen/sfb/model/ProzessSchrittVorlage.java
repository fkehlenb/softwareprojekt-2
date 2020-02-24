package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import javax.ws.rs.ext.ParamConverter;
import java.time.Duration;
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

    /** Process step template id */
    @Id
    @Generated
    @NonNull
    private int psVID;

    /** Duration of the process step template */
    @NonNull
    private String dauer;

    /** Accepted container input types */
    @ManyToMany
    private List<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    @ManyToMany
    private List<TraegerArt> ausgabeTraeger;

    /** The process step type */
    @NonNull
    private String psArt;

    /** The experimenting stations accepted in the process step template */
    @ManyToMany
    @NonNull
    private  List<ExperimentierStation> stationen;

    /** The process step predicates */
    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<Bedingung> bedingungen;

    /** The state Automaton for the process step */
    @NonNull
    @ManyToOne
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatVorlage;

    @Override
    public String toString() {
        return "PSV" + this.psVID;
    }
}
