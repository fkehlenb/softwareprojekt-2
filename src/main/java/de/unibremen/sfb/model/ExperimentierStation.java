package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Queue;
import java.util.Set;

/** Experimenting stations data class */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "ExperimentierStation.findAllInLocation",
                query = "SELECT es FROM ExperimentierStation es WHERE es.standort = :standort"),
        @NamedQuery(name = "ExperimentierStation.getByStatus",
                query = "SELECT es FROM ExperimentierStation es WHERE es.status = :status")
})
public class ExperimentierStation {

    /** The station's id */
    @NonNull
    @Id
    public int esID;

    /** The station's location */
    @NonNull
    @ManyToOne
    public Standort standort;

    /** The station's status */
    @NonNull
    @OneToMany
    public Enum<ExperimentierStationZustand> status;

    /** The queue currently being processed at the experimenting station */
    @NonNull
    @OneToMany
    public Queue<ProzessSchritt> nextPS;

    /** Conditions for using an experimenting station */
    @OneToMany
    public Set<Bedingung> bedingungen;
}
