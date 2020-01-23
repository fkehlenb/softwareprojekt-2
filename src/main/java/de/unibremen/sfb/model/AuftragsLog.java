package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

/** Job log data class */
@Data
public class AuftragsLog {

    /** Creation DAte */
    @NonNull
    public LocalDateTime erstellt;

    /** Start date/time */
    public LocalDateTime start;

    /** End date/time */
    public LocalDateTime beendet;

    /** When the job was archived */
    public LocalDateTime archiviert;

    public AuftragsLog() {
        this.erstellt = LocalDateTime.now();
    }
}
