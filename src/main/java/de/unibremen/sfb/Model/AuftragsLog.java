package de.unibremen.sfb.Model;

import lombok.Data;

import java.time.LocalDateTime;

/** Job log data class */
@Data
public class AuftragsLog {

    /** Start date/time */
    public LocalDateTime start;

    /** End date/time */
    public LocalDateTime beendet;

    /** When the job was archived */
    public LocalDateTime archiviert;
}
