package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.ProbenZustand;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the samples in the database
 */
@Slf4j
public class ProbeDAO extends ObjectDAO<Probe> {

    /**
     * Add a sample to the database
     *
     * @param p - the sample to add to the database
     * @throws DuplicateProbeException if the sample already exists in the database
     */
    public void persist(Probe p) throws DuplicateProbeException {
        if (p != null) {
            synchronized (Probe.class) {
                if (em.contains(em.find(get(), p.getProbenID()))) {
                    throw new DuplicateProbeException();
                }
                em.persist(p);
            }
        }
    }

    /**
     * Update a sample in the database
     *
     * @param p - the sample to update in the database
     * @throws ProbeNotFoundException if the sample couldn't be found in the database
     */
    public void update(Probe p) throws ProbeNotFoundException {
        if (p != null) {
            if (!em.contains(em.find(get(), p.getProbenID()))) {
                throw new ProbeNotFoundException();
            }
            em.merge(p);
        }
    }

    /**
     * Remove a sample from the database
     *
     * @param p - the sample to be removed from the database
     * @throws ProbeNotFoundException if the sample couldn't be found in the database
     */
    public void remove(Probe p) throws ProbeNotFoundException {
        if (p != null) {
            if (!em.contains(em.find(get(), p.getProbenID()))) {
                throw new ProbeNotFoundException();
            }
            p.setValidData(false);
            update(p);
        }
    }

    /**
     * @return the class of sample
     */
    public Class<Probe> get() {
        return Probe.class;
    }

    /**
     * Use a sample id to get a specific sample
     *
     * @param id - the id of the requested sample
     * @return the sample which's id matches the given one
     * @throws ProbeNotFoundException if the sample couldn't be found in the database
     */
    public Probe getObjById(String id) throws ProbeNotFoundException {
        try {
            Probe p = em.find(get(), id);
            if (p == null || !p.isValidData()) {
                throw new ProbeNotFoundException();
            }
            return p;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new ProbeNotFoundException();
        }
    }

    /**
     * Get samples by location
     *
     * @param s - the location which's samples to get
     * @return the samples in a specific location
     * @throws ProbeNotFoundException if the sample couldn't be found in the database
     */
    public List<Probe> getProbenByLocation(Standort s) throws ProbeNotFoundException {
        try {
            return em.createNamedQuery("Probe.getByLocation", get()).setParameter("standort", s).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * Get the samples in a container
     *
     * @param t - the container which's samples to get
     * @return the samples from the container
     * @throws ProbeNotFoundException if the sample couldn't be found in the database
     */
    public List<Probe> getProbenByTraeger(Traeger t) throws ProbeNotFoundException {
        try {
            return em.createNamedQuery("Probe.getByTraeger", get()).setParameter("traeger", t).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * counts the samples currently saved in the database
     *
     * @return the amount of samples
     */
    public int getProbenCount() {
        List<Probe> proben = em.createQuery("select p from Probe p where p.isValidData = true", get()).getResultList();
        int sum = 0;
        for (Probe p : proben){
            sum+=p.getAnzahl();
        }
        return sum;
    }

    /**
     * Get all archived samples from the database
     *
     * @return a list of all archived samples in the database or an empty arraylist
     */
    public List<Probe> getAllArchived() {
        List<Probe> probes = new ArrayList<>();
        try {
            List<Probe> probes1 = new ArrayList<>();
            probes1 = em.createQuery("select p from Probe p where p.isValidData = true", get()).getResultList();
            for (Probe p : probes1) {
                if (p.getZustand() == ProbenZustand.ARCHIVIERT) {
                    probes.add(p);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return probes;
    }

    /**
     * Get all samples from the database
     *
     * @return a list of all samples or an empty arraylist
     */
    public List<Probe> getAll() {
        List<Probe> probes = new ArrayList<>();
        try {
            probes = em.createQuery("select p from Probe p where p.isValidData = true", get()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return probes;
    }
}
