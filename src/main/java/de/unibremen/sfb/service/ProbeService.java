package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Kommentar;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.persistence.ProbeDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;

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
        p.setKommentar(new Kommentar(LocalDateTime.now(), c));
        probeDAO.update(p);
    }

    /**
     * edits a comment of a sample
     * @param p the sample
     * @param c the new comment text
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void editProbenComment(Probe p, String c) throws ProbeNotFoundException {
        p.getKommentar().setText(c);
        probeDAO.update(p);
    }

    /**
     * deletes a comment of a sample
     * @param p the sample
     * @param c the comment to be deleted //TODO macht das sinn so?
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void deleteProbenComment(Probe p, String c) throws ProbeNotFoundException {
        p.setKommentar(null);
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
}
