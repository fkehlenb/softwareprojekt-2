package de.unibremen.sfb.Model;

import lombok.Data;

import java.time.LocalDateTime;

/** Data class for the process chain step log */
@Data
public class ProzessSchrittLog {

    /** The process step log's start time/date */
    public LocalDateTime gestartet;

    /** The process step log's end time/date */
    public LocalDateTime geendet;

    /** The process step's current state */
    public String zustandsAutomat;
}
