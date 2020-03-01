package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittV2;
import de.unibremen.sfb.persistence.ProzessSchrittV2DAO;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import java.util.List;

public class ProzessSchrittV2Service implements Serializable {

    /**
     * Process step dao
     */
    @Inject
    private ProzessSchrittV2DAO prozessSchrittV2DAO;

    /**
     * Add a new process step
     *
     * @param ps - the process step to add
     * @throws DuplicateProzessSchrittException on failure
     */
    public void add(ProzessSchrittV2 ps) throws DuplicateProzessSchrittException {
        prozessSchrittV2DAO.persist(ps);
    }

    /**
     * Update a process step
     *
     * @param ps - the process step to update
     * @throws ProzessSchrittNotFoundException on failure
     */
    public void update(ProzessSchrittV2 ps) throws ProzessSchrittNotFoundException {
        prozessSchrittV2DAO.update(ps);
    }

    /**
     * Remove a process step
     *
     * @param ps - the process step to remove
     * @throws ProzessSchrittNotFoundException on failure
     */
    public void remove(ProzessSchrittV2 ps) throws ProzessSchrittNotFoundException {
        prozessSchrittV2DAO.remove(ps);
    }

    /**
     * Get a process step using its id
     *
     * @param id - the process step id
     * @return the process step with a matching id
     * @throws ProzessSchrittNotFoundException on failure
     */
    public ProzessSchrittV2 getObjById(int id) throws ProzessSchrittNotFoundException {
        return prozessSchrittV2DAO.getObjById(id);
    }

    /**
     * Get all process steps from the database
     *
     * @return a list of all process steps from the databse
     */
    public List<ProzessSchrittV2> getAll() {
        return prozessSchrittV2DAO.getAll();
    }
}
