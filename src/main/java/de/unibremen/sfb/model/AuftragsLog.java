package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

/** Job log data class */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class AuftragsLog {

    /** On delete set to invalid */
    private boolean isValidData = true;

    @Id @GeneratedValue
    private int id;

    /** Creation Date */
    @NonNull
    private LocalDateTime erstellt;

    /** Start date/time */
    private LocalDateTime start;

    /** End date/time */
    private LocalDateTime beendet;

    /** When the job was archived */
    private LocalDateTime archiviert;

}
