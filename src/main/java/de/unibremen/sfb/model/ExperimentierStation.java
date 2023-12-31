package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Experimenting stations data class */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "ExperimentierStation.findAllInLocation",
                query = "SELECT es FROM ExperimentierStation es WHERE es.standort = :standort"),
        @NamedQuery(name = "ExperimentierStation.getByStatus",
                query = "SELECT es FROM ExperimentierStation es WHERE es.status = :status"),
        @NamedQuery(name = "ExperimentierStation.getAll", query = "SELECT es FROM ExperimentierStation es")
})
@RequiredArgsConstructor
@NoArgsConstructor
public class ExperimentierStation {

    /** The station's id */
    @NonNull
    @Id
    private int esID;

    /** The station's location */
    @NonNull
    @ManyToOne
    private Standort standort;

    @NonNull
    private String name;

    /** The station's status */
    @NonNull
    private ExperimentierStationZustand status;

    //TODO
    /** The queue currently being processed at the experimenting station */
    //@NonNull
    //private Queue<ProzessSchritt> nextPS; //FIXME was passiert hiermit?

    /** Conditions for using an experimenting station */
    @OneToMany
    private List<Bedingung> bedingungen;

    @NonNull
    @ManyToMany
    private List<User> benutzer;
}
