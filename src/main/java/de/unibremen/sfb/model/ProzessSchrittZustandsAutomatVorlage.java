package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for the process step automaton templates */
@Getter
@RequiredArgsConstructor
@Entity
@NoArgsConstructor()
public class ProzessSchrittZustandsAutomatVorlage {

    @Id @GeneratedValue
    private int id;

    /** The states the process step automatons can be in */
    @NonNull
    @ElementCollection
    private List<String> zustaende;

//    /** The user who created this template */
//    @ManyToOne
//    public User creator;

    @NonNull
    private String name;
}
