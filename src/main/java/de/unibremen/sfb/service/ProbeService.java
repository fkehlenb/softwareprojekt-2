package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

public class ProbeService implements Serializable {

    /**
     * probeDAO for the managment of samples in the database
     */
    @Inject
    private ProbeDAO probeDAO;

    /**
     * adds a comment to a sample
     * @param p the sample
     * @param c the new comment
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void addProbenComment(Probe p, String c) throws ProbeNotFoundException {
        Kommentar k = new Kommentar(LocalDateTime.now(), c);
        if(p.getKommentar() != null) {
            p.getKommentar().add(k);
        }
        else {
            List<Kommentar> ks = new LinkedList<>();
            ks.add(k);
            p.setKommentar(ks);
        }
        probeDAO.update(p);
    }

    /**
     * edits a comment of a sample
     * @param p the sample
     * @param c the new comment text
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void editProbenComment(Probe p, Kommentar k, String c) throws ProbeNotFoundException {
        if(p.getKommentar()!=null && p.getKommentar().contains(k)) {
            k.setText(c);
        }
        probeDAO.update(p);
    }

    /**
     * deletes a comment of a sample
     * @param p the sample
     * @param k the comment to be deleted //TODO macht das sinn so?
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void deleteProbenComment(Probe p, Kommentar k) throws ProbeNotFoundException {
        //TODO macht das alles überhaupt sinn so? idk... vor allem probedao update. nicht kommentardao update? (gibt es nicht, lel)
        p.getKommentar().remove(k);
        probeDAO.update(p);
    }

    /**
     * find a sample in the database by its id
     * @param id the id
     * @return the sample
     * @throws ProbeNotFoundException there is no sample with this id
     */
    public Probe getProbeById(String id) throws ProbeNotFoundException {
        return probeDAO.getObjById(id); //TODO in DAO id als string
    }

    /**
     * adds a new sample to the database
     * @param id the id of the new sample
     * @param k a comment (optional)
     * @param pz the current status
     * @param s the location
     * @param qe a list of  (optional)
     * @param t the carrier the sample is currently in (optional)
     * @throws DuplicateProbeException there is already a sample with this id
     */
    public void addNewSample(String id, Kommentar k, ProbenZustand pz, Standort s, List<QualitativeEigenschaft> qe, Traeger t) throws DuplicateProbeException {
        if(!id.matches("[A-Z][0-9][0-9].[0-9]+(.[0-9]+)+")) {
            throw new IllegalArgumentException();
        }
        Probe p = new Probe(id, pz, s);
        List<Kommentar> ks = new LinkedList<>();
        ks.add(k);
        p.setKommentar(ks);
        p.setQualitativeEigenschaften(qe);
        p.setCurrentTraeger(t);
        probeDAO.persist(p);
    }

    public String KommentarToString(Probe p) {
        String res = "";
        for(Kommentar k : p.getKommentar()) {
            res += k.getText() + "\n";
        }
        return res;
    }
}
