package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import java.util.Queue;
import java.util.Set;

/** Experimenting stations data class */
@Data
public class ExperimentierStation {

    /** The station's id */
    @NonNull
    public int esID;

    /** The station's location */
    @NonNull
    public Standort standort;

    /** The station's status */
    @NonNull
    public Enum<ExperimentierStationZustand> status;

    /** The queue currently being processed at the experimenting station */
    @NonNull
    public Queue<ProzessSchritt> nextPS;

    /** Conditions for using an experimenting station */
    public Set<Bedingung> bedingungen;
}
