package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/** Data class for the process step automaton templates */
@Data
@Entity
public class ProzessSchrittZustandsAutomatVorlage {

    /** The states the process step automatons can be in */
    @NonNull
    @OneToMany
    public Set<String> zustaende;

    /** The user who created this template */
    @NonNull
    @Id
    @OneToOne
    public User creator;
}
