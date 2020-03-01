package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittV2;

import java.util.ArrayList;
import java.util.List;

public class ProzessSchrittV2DAO extends ObjectDAO<ProzessSchrittV2> {

    /**
     * Add a new process step to the database
     *
     * @param ps - the process step to add
     * @throws DuplicateProzessSchrittException if it already exists in the database
     */
    public void persist(ProzessSchrittV2 ps) throws DuplicateProzessSchrittException {
        if (ps != null) {
            synchronized (ProzessSchrittV2.class) {
                if (em.contains(em.find(get(), ps.getId()))) {
                    throw new DuplicateProzessSchrittException();
                }
                em.persist(ps);
            }
        }
    }

    /**
     * Update a process step in the database
     *
     * @param ps - the process step to update
     * @throws ProzessSchrittNotFoundException if it cannot be found in the database
     */
    public void update(ProzessSchrittV2 ps) throws ProzessSchrittNotFoundException {
        if (ps != null) {
            if (!em.contains(em.find(get(), ps.getId()))) {
                throw new ProzessSchrittNotFoundException();
            }
            em.merge(ps);
        }
    }

    /**
     * Remove a process step from the database
     *
     * @param ps - the process step to remove
     * @throws ProzessSchrittNotFoundException if it cannot be found in the database
     */
    public void remove(ProzessSchrittV2 ps) throws ProzessSchrittNotFoundException {
        if (ps != null) {
            if (!em.contains(em.find(get(), ps.getId()))) {
                throw new ProzessSchrittNotFoundException();
            }
            ps.setValidData(false);
            update(ps);
        }
    }

    /**
     * Get a process step by id
     *
     * @param id - the id of the process step to fetch from the database
     * @return the requested process step
     * @throws ProzessSchrittNotFoundException if it cannot be found in the database
     */
    public ProzessSchrittV2 getObjById(int id) throws ProzessSchrittNotFoundException {
        try {
            ProzessSchrittV2 ps = em.find(get(), id);
            if (!ps.isValidData()) {
                throw new ProzessSchrittNotFoundException();
            }
            return ps;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProzessSchrittNotFoundException();
        }
    }

    /**
     * Get all process steps from the database
     *
     * @return a list of all process steps from the database, or an empty arraylist
     */
    public List<ProzessSchrittV2> getAll() {
        List<ProzessSchrittV2> prozessSchrittV2s = new ArrayList<>();
        try {
            prozessSchrittV2s = em.createQuery("select ps from ProzessSchrittV2 ps where ps.isValidData=true", get()).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prozessSchrittV2s;
    }

    /**
     * @return the class
     */
    public Class<ProzessSchrittV2> get() {
        return ProzessSchrittV2.class;
    }
}
