package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Duration;
import java.util.Set;

/** Data class for the process step templates */
@Data
@Entity
public class ProzessSchrittVorlage {

    /** Process step template id */
    @NonNull
    @Id
    public int psVID;

    /** Duration of the process step template */
    @NonNull
    public Duration dauer;

    /** Accepted container input types */
    @OneToMany
    public Set<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    @OneToMany
    public Set<TraegerArt> ausgabeTraeger;

    /** The process step type */
    @NonNull
    public ProzessSchrittArt psArt;

    /** The experimenting stations accepted in the process step template */
    @NonNull
    @OneToMany
    public Set<ExperimentierStation> stationen;

    /** The process step template's state automaton template */
    @NonNull
    @OneToOne
    public ProzessSchrittZustandsAutomatVorlage zustandsAutomat;

    /** The user who created this template */
    @NonNull
    @OneToOne
    public User creator;
}
