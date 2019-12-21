package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Probe;
import de.unibremen.sfb.Model.ProbenZustand;
import de.unibremen.sfb.Model.Standort;
import de.unibremen.sfb.Model.Archiv;
import java.time.LocalDateTime;
import org.apache.commons.lang3.tuple.Pair;

/**
 * this class manages the interaction with models of samples (Proben)
 */
public class ProbeController {

    /**
     * the Probe managed by a controller instance
     */
    public Probe probe;

    /**
     * Sets the ID of this Probe.
     * Proben-IDs are of the format: [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+
     *
     * @param id the new ID
     */
    public void setID(String id) {}

    /**
     * Returns the ID of this Probe
     * Probens IDs are of the format: [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+
     *
     * @return the Proben-ID
     */
    public String getID() { return null; }

    /**
     * Adds a ProbenKommentar (comment about this Probe) to this Probe.
     * Probenkommentare consist of the timestamp and the comment text.
     *
     * @param p A pair consisting of the timestamp and the comment text.
     */
    public void addComment(Pair<LocalDateTime,String> p) {}

    /**
     * Returns the Probenkommentar (comment about this Probe) to this Probe.
     * Probenkommentare consist of the timestampt and the comment text
     *
     * @return A pair consisting of the timestamp and the comment text
     */
    public Pair<LocalDateTime,String> getComment() { return null; }

    /**
     * Sets the Zustand (state) of this Probe
     * Possible values are: Kaputt(broken), Verloren(lost), Vorhanden(available)
     *
     * @param pz the new state of this Probe
     */
    public void setZustand(ProbenZustand pz) { }

    /**
     * returns the current Zustand (state) of this Probe
     * Possible values are: Kaputt(broken), Verloren(lost), Vorhanden(available).
     * @return the current Zustand
     */
    public ProbenZustand getZustand() { return null; }

    /**
     * Sets the Standort (location) at which the Probe currently is.
     *
     * @param s the new location
     */
    public void setStandort(Standort s) {}

    /**
     * Returns the Standort (location) at which the Probe currently is
     *
     * @return the location
     */
    public Standort getStandort() { return null; }

    /**
     * Sets an Archiv (archive) for this Probe (and thus archives it)
     * @param a the new archive
     */
    public void setArchiv(Archiv a) {}

    /**
     * Returns the Archiv (archive) for this Probe
     * @return the archive
     */
    public Archiv getArchiv() { return null; }
}
