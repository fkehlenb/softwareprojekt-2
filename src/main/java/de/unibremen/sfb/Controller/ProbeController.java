package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Probe;
import de.unibremen.sfb.Model.Standort;
import de.unibremen.sfb.Model.Archiv;
import java.time.LocalDateTime;
import org.apache.commons.lang3.tuple.Pair;

public class ProbeController {
    public Probe probe;

    /**
     * Setzt die Id dieser Probe
     * Proben-IDs sind von der Form: [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+
     *
     * @param id Die neue ID
     */
    public void setID(String id) {}

    /**
     * Gibt die ID dieser Probe zurück.
     * Proben-IDs sind von der Form: [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+
     *
     * @return Die Proben-ID
     */
    public String getID() { return null; }

    /**
     * Fügt dieser Probe einen Kommentar hinzu.
     * Probenkommentare bestehen aus dem Zeitstempel und dem Kommentartext.
     *
     * @param p Ein Paar, das den Zeitstempel und den Kommentartext enthält.
     */
    public void addComment(Pair<LocalDateTime,String> p) {}

    /**
     * Gibt den Probenkommentar dieser Probe zurück.
     * Probenkommentare bestehen aus dem Zeitstempel un dem Kommentartext.
     *
     * @return Ein Paar aus Zeitstempel und Kommentartext
     */
    public Pair<LocalDateTime,String> getComment() { return null; }

    /**
     * Setzt den Zustand dieser Probe.
     * Mögliche Zustände sind: Kaputt, Verloren, Vorhanden
     *
     * param: Der neue Zustand dieser Probe.
     */
    //hier enum übergeben
    public void setZustand() {}

    /**
     * Gibt den akutellen Zustand dieser Probe zurück.
     * Mögliche Zustände sind: Kaputt, Verloren, Vorhanden.
     */
    //hier enum returnt
    public void getZustand() {}

    /**
     * Setzt den Standort, an dem sich die Probe aktuell befindet.
     *
     * @param s Der neue Standort.
     */
    public void setStandort(Standort s) {}

    /**
     * Gibt den Standort zurück, an dem die Probe sich aktuell befindet.
     *
     * @return Der Standort.
     */
    public Standort getStandort() { return null; }

    /**
     * //TODO
     * @param a
     */
    public void setArchiv(Archiv a) {}

    public Archiv getArchiv() { return null; }
}
