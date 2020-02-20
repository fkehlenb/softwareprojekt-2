package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/** Data class for the process chain step log */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchrittLog {

    @Id @GeneratedValue
    private int id;

    /** The process step log's start time/date */
    @NonNull
    private LocalDateTime gestartet;

    /** The process step log's end time/date */
    private LocalDateTime geendet;

    /** The process step's current state */
    @NonNull
    private String zustandsAutomat;
}
