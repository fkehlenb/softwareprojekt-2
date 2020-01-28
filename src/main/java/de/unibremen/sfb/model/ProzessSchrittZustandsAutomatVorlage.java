package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/** Data class for the process step automaton templates */
@Getter
@Setter
@Entity
public class ProzessSchrittZustandsAutomatVorlage {

    @Id @GeneratedValue
    private int id;

    /** The states the process step automatons can be in */
    @NonNull
    @ElementCollection
    private List<String> zustaende;

    /** The user who created this template */
    @ManyToOne
    public User creator;

    public ProzessSchrittZustandsAutomatVorlage() {
        this.zustaende = new ArrayList<>();
        zustaende.add("Angenommen");
        zustaende.add("In Bearbeitung");
        zustaende.add("Bearbeitet");
        zustaende.add("Weitergeleitet");
    }
}
