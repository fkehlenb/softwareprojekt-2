package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;

/** Data class for process step state automatons */
@Data
public class ProzessSchrittZustandsAutomat {

    /** The state the automaton is currently in */
    @NonNull
    public String current;

    /** The process step state automaton template the automaton was created from (containing all possible states) */
    @NonNull
    public ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage;

    public ProzessSchrittZustandsAutomat(ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage) {
        this.prozessSchrittZustandsAutomatVorlage = prozessSchrittZustandsAutomatVorlage;
        this.current = "Angenommen";
    }

}
