package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for the process step automaton templates */
@Getter
@Setter
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
public class ProzessSchrittZustandsAutomatVorlage {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    @Id
    @NonNull
    private int id;

    /** The states the process step automatons can be in */
    @NonNull
     @ElementCollection(fetch = FetchType.LAZY)
     private List<String> zustaende;

    //Leo Möglische Loesung
    //@OneToMany
    //private List<Zustand> zustaende;

    @NonNull
    private String name;

    @Override
    public String toString() {
        return "ProzessSchrittZustandsAutomatVorlage{" +
                "name='" + name + '\'' +
                '}';
    }
}
