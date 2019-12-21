package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Archiv;
import de.unibremen.sfb.Model.Auftrag;
import java.time.LocalDateTime;

/**
 * this class manages the interaction with models of archives
 */
public class ArchivController {

    /**
     * The Archiv managed by this ArchivController
     */
    public Archiv archiv;

    /**
     * Sets the Auftrag which is archived with this archive.
     *
     * @param a The new Auftrag
     */
    public void setAuftrag(Auftrag a) {}

    /**
     * returns the Auftrag which is archived with this archive
     *
     * @return the Auftrag
     */
    public Auftrag getAuftrag() { return null; }

    /**
     * Sets the time at which the Auftrag was archived
     *
     * @param d the new timestamp
     */
    public void setDatum(LocalDateTime d) {}

    /**
     * Returns the time at which the Auftrag was archived
     *
     * @return The timestamp
     */
    public LocalDateTime getDatum() { return null; }
}
