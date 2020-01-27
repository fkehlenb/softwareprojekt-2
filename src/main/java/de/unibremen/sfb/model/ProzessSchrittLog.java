package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

/** Data class for the process chain step log */
@Data
@RequiredArgsConstructor
public class ProzessSchrittLog {

    /** The process step log's start time/date */
    @NonNull
    public LocalDateTime gestartet;

    /** The process step log's end time/date */
    public LocalDateTime geendet;

    /** The process step's current state */
    @NonNull
    public String zustandsAutomat;

}
