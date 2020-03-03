package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Id
    @NonNull
    private int id;

    /** The state the automaton is currently in */
    @NonNull
    private String current;

    /** State automaton name */
    private String name;

    /**
     * The states the process step automatons can be in
     */
    @NonNull
    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> zustaende;
}
