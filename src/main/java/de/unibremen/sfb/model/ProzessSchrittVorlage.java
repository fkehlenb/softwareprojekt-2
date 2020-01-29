package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

/** Data class for the process step templates */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchrittVorlage {

    /** Process step template id */
    @Id
    @Generated
    @NonNull
    private int psVID;

    /** Duration of the process step template */
    @NonNull
    private Duration dauer;

    /** Accepted container input types */
    @OneToMany
    private List<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    @OneToMany
    private List<TraegerArt> ausgabeTraeger;

    /** The process step type */
    @NonNull
    private String psArt;

    /** The experimenting stations accepted in the process step template */
    @NonNull
    @OneToMany
    private Set<ExperimentierStation> stationen;

    /** The process step template's state automaton template */
    @NonNull
    @OneToOne
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomat;

    /** The user who created this template */
    @OneToOne
    private User creator;

    /** The process step parameters */
    @NonNull
    @ManyToMany
    private Set<ProzessSchrittParameter> prozessSchrittParameter;
}
