package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
/** Achive data class */
public class Archiv {

    /** The job that has been archived */
    @NonNull
    public Auftrag auftrag;

    /** The date the job has been archived */
    public LocalDateTime datum;
}
