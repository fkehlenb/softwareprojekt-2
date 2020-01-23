package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;
import java.util.Set;

/** Data class for the process step templates */
@Data
public class ProzessSchrittVorlage {

    /** Process step template id */
    @NonNull
    public int psVID;

    /** Duration of the process step template */
    @NonNull
    public Duration dauer;

    /** Accepted container input types */
    public Set<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    public Set<TraegerArt> ausgabeTraeger;

    /** The process step type */
    @NonNull
    public ProzessSchrittArt psArt;

    /** The experimenting stations accepted in the process step template */
    @NonNull
    public Set<ExperimentierStation> stationen;

    /** The process step template's state automaton template */
    @NonNull
    public ProzessSchrittZustandsAutomatVorlage zustandsAutomat;

    /** The user who created this template */
    @NonNull
    public User creator;

    public ProzessSchrittParameter prozessSchrittParameter;

    public ProzessSchrittVorlage(int psVID, Duration dauer, ProzessSchrittArt psArt, Set<ExperimentierStation> stationen,
                                 ProzessSchrittZustandsAutomatVorlage zustandsAutomat, User creator,
                                 ProzessSchrittParameter prozessSchrittParameter) {
        this.psVID = psVID;
        this.dauer = dauer;
        this.psArt = psArt;
        this.stationen = stationen;
        this.zustandsAutomat = zustandsAutomat;
        this.creator = creator;
        this.prozessSchrittParameter = prozessSchrittParameter;
    }
}
