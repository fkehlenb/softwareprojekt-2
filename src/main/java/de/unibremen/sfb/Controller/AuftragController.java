package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.ProzessKettenVorlage;
import de.unibremen.sfb.Model.AuftragsLog;
import de.unibremen.sfb.Model.ProzessSchritt;
import java.util.Set;

public class AuftragController {

    public Auftrag auftrag;

    /**
     * Gibt die ID des Auftrags aus.
     *
     * @return Die ID
     */
    public int getID() { return 0; }

    /**
     * Gibt die ProzessKettenVorlage, von der dieser Auftrag instanziiert wurde, aus.
     *
     * @return Die ProzessKettenVorlage
     */
    public ProzessKettenVorlage getPKV() { return null; }

    /**
     * Gibt das bisherige Protokoll dieses Auftrags zurück
     *
     * @return Das Protokoll
     */
    public AuftragsLog getLog() { return null; }

    /**
     * Setzt das Protokoll dieses Auftrags
     *
     * @param al Das neue Protokoll
     */
    public void setLog(AuftragsLog al) { }

    /**
     * Gibt den aktuellen Zustand des Auftrags zurück.
     * Mögliche Werte: Instanziiert, Freigegeben, Gestartet, Abgebrochen, Durchgeführt
     */
    //returnt eigentlich enum (Zustände)!
    public void getPKZ() { }

    /**
     * Setzt den Zustand des Auftrags.
     * Mögliche Werte: Instanziiert, Freigegeben, Gestartet, Abgebrochen, Durchgeführt
     */
    public void setPKZ() {}

    /**
     * Gibt die aktuelle Priorität dieses Auftrags zurück
     */
    //returnt eigentlich enum
    public void getPrio() {}

    /**
     * Setzt die Priorität dieses Auftrags neu
     */
    public void setPrio() {}

    /**
     * Gibt die Prozesssschritte zurück, aus denen dieser Auftrag besteht.
     * @return Gibt alle ProzessSchritte zurück
     */
    public Set<ProzessSchritt> getPS() { return null; }
}
