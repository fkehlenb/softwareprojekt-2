package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.ProzessSchritt;

public class ExperimentierStationController {

    public ExperimentierStation experimenteristation;

    /**
     * gibt die ID dieser Experimentierstation zurück
     *
     * @return die ID
     */
    public int getESID() { return 0; }

    /**
     * setzt den Standort dieser Experimentierstation
     *
     * @return der neue Standort
     */
    public String getESStandort() { return null; }

    /**
     * setzt den Status der Experimentierstation.
     * Mögliche Werte: Verfügbar, Besetzt, Kaputt
     */
    //eigentlich enum übergeben
    public void setStatus() {}

    /**
     * gibt den aktuellen Status der Experimentierstation zurück.
     * Mögliche Werte: Verfügbar, Besetzt, Kaputt
     */
    //eigentlich enum returnt
    public void getStatus() {}

    /**
     * Fügt einen neuen ProzessSchritt, der an dieser Station durchgeführt werden soll.
     *
     * @param ps Der neue ProzessSchritt
     */
    public void addNextPS(ProzessSchritt ps) {}

    /**
     * Gibt den nächsten ProzessSchritt aus, der ausgeführt werden soll.
     *
     * @return Der nächste durchzuführende ProzessSchritt
     */
    public ProzessSchritt peekNextPS() { return null; }

    /**
     *
     * @param ps
     */
    public void popNextPS(ProzessSchritt ps) {}
}
