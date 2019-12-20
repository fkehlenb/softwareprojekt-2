package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Archiv;
import de.unibremen.sfb.Model.Auftrag;
import java.time.LocalDateTime;

public class ArchivController {

    public Archiv archiv;

    /**
     * Setzt den Auftrag, der mit dem Archiv archiviert wird.
     *
     * @param a Der neue Auftrag
     */
    public void setAuftrag(Auftrag a) {}

    /**
     * Gibt den Auftrag, der mit diesem Archiv archivivert wird.
     *
     * @return Der Auftrag
     */
    public Auftrag getAuftrag() { return null; }

    /**
     *  Diese Methode setzt die Zeit der Archivierung
     *
     * @param d Die neue Zeit
     */
    public void setDatum(LocalDateTime d) {}

    /**
     * Die Methode gibt die Zeit der Archivierung aus
     *
     * @return Die Zeit
     */
    public LocalDateTime getDatum() { return null; }
}
