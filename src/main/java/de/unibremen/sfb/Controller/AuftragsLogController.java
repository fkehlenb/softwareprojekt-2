package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.AuftragsLog;
import java.time.LocalDateTime;

public class AuftragsLogController {

    public AuftragsLog log;

    /**
     * Setzt die Startzeit dieses Auftrags.
     *
     * @param t die neue Startzeit
     */
    public void setStart(LocalDateTime t) {}

    /**
     * gibt die Startzeit dieses Auftrags zurück
     *
     * @return die Startzeit
     */
    public LocalDateTime getStart() { return null; }

    /**
     * setzt die Endezeit dieses Auftrags
     *
     * @param t die neue Endzeit
     */
    public void setBeendet(LocalDateTime t) {}

    /**
     * gibt die Endzeit dieses Auftrags zurück
     *
     * @return die Endzeit
     */
    public LocalDateTime getBeendet() { return null; }

    /**
     * setzt die Zeit, zu der dieser Auftrag archiviert wurde
     *
     * @param t die neue Zeit
     */
    public void setArchiviert(LocalDateTime t) {}

    /**
     * gibt die Zeit zurück, zu der dieser Auftrag archiviert wurde
     *
     * @return der Zeitpunkt der Archivierung
     */
    public LocalDateTime getArchiviert() { return null; }
}
