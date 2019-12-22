package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

/** Data class for the process step automaton templates */
@Data
public class ProzessSchrittZustandsAutomatVorlage {

    /** The states the process step automatons can be in */
    @NonNull
    public Set<String> zustaende;
}
