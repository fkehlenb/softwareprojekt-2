package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.*;

import java.time.LocalDateTime;
import java.util.Set;

import de.unibremen.sfb.persistence.ProbeDAO;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 * this class manages the interaction with models of samples (Proben)
 */
public class ProbeController {

    /**
     * the Probe managed by a controller instance
     */
    public Probe probe;

    @Inject
    private ProbeDAO probeDAO;

    /**
     * Sets the ID of this Probe.
     * Proben-IDs are of the format: [A-Z][0-9][0-9].[0-9]+(.[0-9]+)+
     *
     * @param id the new ID //TODO testen ob id richtiges format!
     */
    public void setID(String id) {
        /*try {
            // probeDAO.getProbeById(id); //TODO sollte Probenid nicht String sein?
        }
        catch(ProbeNotFoundException e) {
            //probe.setProbenID(id); //TODO Probenid string?
            try {
                probeDAO.update(probe);
            }
            catch(ProbeNotFoundException b) {

            }
        }**/
    }

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
    public void addComment(Pair<LocalDateTime,String> p) {
        Kommentar temp = getComment();
        probe.setKommentar(new Kommentar(p.getLeft(), p.getRight()));
        try {
            probeDAO.update(probe);
        }
        catch(ProbeNotFoundException e) {
            probe.setKommentar(temp);
            e.printStackTrace();
        }
    }

    /**
     * Returns the Probenkommentar (comment about this Probe) to this Probe.
     * Probenkommentare consist of the timestampt and the comment text
     *
     * @return A pair consisting of the timestamp and the comment text
     */
    public Kommentar getComment() { return probe.getKommentar(); }

    /**
     * Sets the Zustand (state) of this Probe
     * Possible values are: Kaputt(broken), Verloren(lost), Vorhanden(available)
     *
     * @param pz the new state of this Probe
     */
    public void setZustand(ProbenZustand pz) {
        ProbenZustand temp = getZustand();
        probe.setZustand(pz);
        try {
            probeDAO.update(probe);
        }
        catch(ProbeNotFoundException e) {
            probe.setZustand(temp);
            e.printStackTrace();
        }
    }

    /**
     * returns the current Zustand (state) of this Probe
     * Possible values are: Kaputt(broken), Verloren(lost), Vorhanden(available).
     * @return the current Zustand
     */
    public ProbenZustand getZustand() { return probe.getZustand(); }

    /**
     * Sets the Standort (location) at which the Probe currently is.
     *
     * @param s the new location
     */
    public void setStandort(Standort s) {
        if(s!=null) {
            Standort temp = getStandort();
            probe.setStandort(s);
            try {
                probeDAO.update(probe);
            }
            catch(ProbeNotFoundException e) {
                probe.setStandort(temp);
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the Standort (location) at which the Probe currently is
     *
     * @return the location
     */
    public Standort getStandort() { return probe.getStandort(); }

    /**
     * Sets an Archiv (archive) for this Probe (and thus archives it)
     * @param a the new archive
     */
    public void setArchiv(Archiv a) {
        //TODO warum hier archiv? warum nicht bei auftrag?
    }

    /**
     * Returns the Archiv (archive) for this Probe
     * @return the archive
     */
    public Archiv getArchiv() { return null; }

    /**
     * returns all properties this sample has
     * @return a set containing all properties of this sample
     *
     */
    public Set<QualitativeEigenschaft> getEigenschaften() {
        return probe.getQualitativeEigenschaften();
    }

    /**
     *sets the properties of this sample
     * @param eigenschaft a set containing all properties this sample is supposed to have
     */
    public void setEigenschaften(Set<QualitativeEigenschaft> eigenschaft) {
        if(eigenschaft != null) {
            Set<QualitativeEigenschaft> temp = getEigenschaften();
            probe.setQualitativeEigenschaften(eigenschaft);
            try {
                probeDAO.update(probe);
            }
            catch(ProbeNotFoundException e) {
                probe.setQualitativeEigenschaften(temp);
                e.printStackTrace();
            }
        }
    }

    /**
     * Return the JSON Representation of a Probe
     * @param probe the probe which should be converted
     * @return json of the probe
     */
    public String getJSON(Probe probe) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.toJson(probe);
    }

    /**
     * creates a new sample
     * @param id the id for the new sample
     */
    public void createNewProbe(String id, Standort s) {
        Probe p = new Probe(0, ProbenZustand.VORHANDEN, s); //id
        try {
            probeDAO.persist(p);
        }
        catch(DuplicateProbeException f) {
            f.printStackTrace();
        }
    }

    /**
     * sets a comment for a sample
     * @param p the sample for which the new comment is
     * @param k the comment
     */
    public void setKommentarForProbe(Probe p, Kommentar k) {
        if(p!=null && k!=null) {
            Kommentar temp = p.getKommentar();
            p.setKommentar(k);
            try {
                probeDAO.update(p);
            } catch (ProbeNotFoundException e) {
                p.setKommentar(temp);
                e.printStackTrace();
            }
        }
    }

    /**
     * sets the state of a sample
     * @param p the sample
     * @param z the new state
     */
    public void setZustandForProbe(Probe p, ProbenZustand z) {
        if(p!=null && z!=null) {
            ProbenZustand temp = p.getZustand();
            p.setZustand(z);
            try {
                probeDAO.update(p);
            } catch (ProbeNotFoundException e) {
                p.setZustand(temp);
                e.printStackTrace();
            }
        }
    }

    /**
     * retrieves the sample with the id from the database
     * @param id the id of the requested sample
     * @return a sample, or null, if the sample does not exist
     */
    public Probe getProbeById(int id) { //TODO sollte die id nicht String sein?
        try {
            return probeDAO.getObjById(id);
        }
        catch(ProbeNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * retrieves the comment of a sample
     * @param p the sample
     * @return the comment
     */
    public Kommentar getKommentarForProbe(Probe p) {
        return p.getKommentar();
    }
}
