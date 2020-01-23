package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

/** Data class for the process step automaton templates */
@Data
public class ProzessSchrittZustandsAutomatVorlage {

    /** The states the process step automatons can be in */
    @NonNull
    public List<String> zustaende;

    /** The user who created this template */
    @NonNull
    public User creator;

    public ProzessSchrittZustandsAutomatVorlage(User user) {
        this.creator = user;
        this.zustaende = new ArrayList<>();
        zustaende.add("Angenommen");
        zustaende.add("In Bearbeitung");
        zustaende.add("Bearbeitet");
        zustaende.add("Weitergeleitet");
    }
}
