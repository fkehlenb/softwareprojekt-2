package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;

/** Experimenting stations data class */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "ExperimentierStation.findAllInLocation",
                query = "SELECT es FROM ExperimentierStation es WHERE es.standort = :standort AND es.isValidData = true"),
        @NamedQuery(name = "ExperimentierStation.getByStatus",
                query = "SELECT es FROM ExperimentierStation es WHERE es.status = :status AND es.isValidData = true"),
        @NamedQuery(name = "ExperimentierStation.getAll", query = "SELECT es FROM ExperimentierStation es WHERE es.isValidData = true")//,
        //@NamedQuery(name = "ExperimentierStation.getByUser", query = "SELECT es FROM ExperimentierStation es WHERE  es.benutzer = ?"  )
})
@RequiredArgsConstructor
@NoArgsConstructor
public class ExperimentierStation {

    /** On delete set to invalid */
    private boolean isValidData = true;

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
    private Enum<ExperimentierStationZustand> status;

    //TODO
    /** The queue currently being processed at the experimenting station */
//    @NonNull
//    private Queue<ProzessSchritt> nextPS;

    /** Conditions for using an experimenting station */
    @OneToMany
    private List<Bedingung> bedingungen;

    @NonNull
    @ManyToMany(cascade=CascadeType.MERGE, fetch = FetchType.EAGER)
    private List<User> benutzer;

    @OneToOne
    private ProzessSchritt currentPS;

    @Override
    public String toString() {
        return Integer.toString(esID);
    }


}
