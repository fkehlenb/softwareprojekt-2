package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Experimenting stations data class
 */
@Getter
@Setter
@Entity
@NamedQueries({
        @NamedQuery(name = "ExperimentierStation.findAllInLocation",
                query = "SELECT es FROM ExperimentierStation es WHERE es.standort = :standort AND es.isValidData = true"),
        @NamedQuery(name = "ExperimentierStation.getByStatus",
                query = "SELECT es FROM ExperimentierStation es WHERE es.status = :status AND es.isValidData = true"),
        @NamedQuery(name = "ExperimentierStation.getAll", query = "SELECT es FROM ExperimentierStation es WHERE es.isValidData = true")
})
@RequiredArgsConstructor
@NoArgsConstructor
public class ExperimentierStation implements Serializable {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    /**
     * The station's id
     */
    @NonNull
    @Id
    private int esID;

    /**
     * The station's location
     */
    @NonNull
    @ManyToOne
    private Standort standort;

    @NonNull
    private String name;

    /**
     * The station's status
     */
    @NonNull
    private Enum<ExperimentierStationZustand> status;

    /** The queue currently being processed at the experimenting station */
    @OneToMany(fetch = FetchType.LAZY)
   private List<ProzessSchritt> nextPS = new ArrayList<ProzessSchritt>();

    /** Parameters required to use this station */
    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<ProzessSchrittParameter> requirements;

    @NonNull
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<User> benutzer;

    @ManyToOne
    private ProzessSchritt currentPS;

    @Override
    public String toString() {
        return standort.getOrt();
    }


}
