package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.Archiv;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.ProbenZustand;
import de.unibremen.sfb.model.Standort;
import org.apache.commons.lang3.tuple.Pair;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * this class manages the interaction with models of samples (Proben)
 */
public class ProbeController implements Serializable {

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
    public void setID(String id) {
        probe.setProbenID(id);
    }

    /**
     * Returns the ID of this Probe
     * Probens IDs are of the format: [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+
     *
     * @return the Proben-ID
     */
    public String getID() {
        return probe.getProbenID();
    }

    /**
     * Adds a ProbenKommentar (comment about this Probe) to this Probe.
     * Probenkommentare consist of the timestamp and the comment text.
     *
     * @param p A pair consisting of the timestamp and the comment text.
     */
    public void addComment(Pair<LocalDateTime,String> p) {
        //TODO
    }

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
    public void setZustand(ProbenZustand pz) {
        probe.setZustand(pz);
    }

    /**
     * returns the current Zustand (state) of this Probe
     * Possible values are: Kaputt(broken), Verloren(lost), Vorhanden(available).
     * @return the current Zustand
     */
    public Enum<ProbenZustand> getZustand() {
        return probe.getZustand();
    }

    /**
     * Sets the Standort (location) at which the Probe currently is.
     *
     * @param s the new location
     */
    public void setStandort(Standort s) {
        probe.setStandort(s);
    }

    /**
     * Returns the Standort (location) at which the Probe currently is
     *
     * @return the location
     */
    public Standort getStandort() {
        return probe.getStandort();
    }

    /**
     * Sets an Archiv (archive) for this Probe (and thus archives it)
     * @param a the new archive
     */
    public void setArchiv(Archiv a) {
        //TODO
    }

    /**
     * Returns the Archiv (archive) for this Probe
     * @return the archive
     */
    public Archiv getArchiv() { return null; }


    /**
     * Return the JSON Representation of a Probe
     * @param probe the probe which should be converted
     * @return json of the probe
     */
    public String getJSON(Probe probe) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(probe);
    }

    public void setZustandForProbe(Probe p, ProbenZustand pz) {

    }

}
