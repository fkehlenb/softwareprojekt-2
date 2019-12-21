package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

/** Job log data class */
@Data
public class AuftragsLog {

    /** Start date/time */
    public LocalDate start;

    /** End date/time */
    public LocalDate beendet;

    /** When the job was archived */
    public LocalDate archiviert;
}
