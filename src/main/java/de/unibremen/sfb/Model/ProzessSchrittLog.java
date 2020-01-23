package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

/** Data class for the process chain step log */
@Data
public class ProzessSchrittLog {

    /** The process step log's start time/date */
    @NonNull
    public LocalDateTime gestartet;

    /** The process step log's end time/date */
    public LocalDateTime geendet;

    /** The process step's current state */
    @NonNull
    public String zustandsAutomat;

    public ProzessSchrittLog(String zustand){
        this.zustandsAutomat = zustand;
        this.gestartet = LocalDateTime.now();
    }
}
