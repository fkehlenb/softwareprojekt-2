package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.Archiv;
import de.unibremen.sfb.model.Auftrag;
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
    public void setAuftrag(Auftrag a) {
        archiv.setAuftrag(a);
    }

    /**
     * returns the Auftrag which is archived with this archive
     *
     * @return the Auftrag
     */
    public Auftrag getAuftrag() {
        return archiv.getAuftrag();
    }

    /**
     * Sets the time at which the Auftrag was archived
     *
     * @param d the new timestamp
     */
    public void setDatum(LocalDateTime d) {
        archiv.setDatum(d);
    }

    /**
     * Returns the time at which the Auftrag was archived
     *
     * @return The timestamp
     */
    public LocalDateTime getDatum() {
        return archiv.getDatum();
    }
}
