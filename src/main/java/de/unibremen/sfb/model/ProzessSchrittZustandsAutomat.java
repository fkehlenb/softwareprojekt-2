package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/** Data class for process step state automatons */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchrittZustandsAutomat {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    /** The state the automaton is currently in */
    @NonNull
    private String current;

    /** The process step state automaton template the automaton was created from (containing all possible states) */
    @NonNull
    @OneToOne
    private ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage;
}
