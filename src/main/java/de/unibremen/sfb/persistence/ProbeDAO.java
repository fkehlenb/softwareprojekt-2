package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;

import java.util.List;

/**
 * This class handles the samples in the database
 */
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
                if (em.contains(p)) {
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
            if (!em.contains(p)) {
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
            if (!em.contains(p)) {
                throw new ProbeNotFoundException();
            }
            em.remove(p);
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
    public Probe getObjById(int id) throws ProbeNotFoundException {
        try {
            Probe p = em.find(get(), id);
            if (p == null) {
                throw new ProbeNotFoundException();
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProbeNotFoundException();
        }
    }

    /**
     * Use a sample id (String type) to get a specific sample
     *
     * @param id the id of the requested sample
     * @return the sample which's id matches the given one
     * @throws ProbeNotFoundException if the sample couldn't be found in the database
     */
    public Probe getObjById(String id) throws ProbeNotFoundException {
        try {
            Probe p = em.find(get(), id);
            if (p == null) {
                throw new ProbeNotFoundException();
            }
            return p;
        } catch (Exception e) {
            e.printStackTrace();
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
            List<Probe> proben = em.createNamedQuery("Probe.getByLocation", get()).setParameter("standort", s).getResultList();
            if (proben.isEmpty()) {
                throw new ProbeNotFoundException();
            }
            return proben;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProbeNotFoundException();
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
        List<Probe> proben = em.createNamedQuery("Probe.getByTraeger", get()).setParameter("traeger", t).getResultList();
        if (proben.isEmpty()) {
            throw new ProbeNotFoundException();
        }
        return proben;
    }
}
