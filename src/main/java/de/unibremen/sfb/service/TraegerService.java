package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.persistence.TraegerDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 * Container service
 */
public class TraegerService implements Serializable {

    /**
     * Container DAO
     */
    @Inject
    private TraegerDAO traegerDAO;

    /**
     * Add a new container
     *
     * @param t - the container to add
     * @throws DuplicateTraegerException on failure
     */
    public void persist(Traeger t) throws DuplicateTraegerException {
        traegerDAO.persist(t);
    }

    /**
     * Update a container
     *
     * @param t - the container to update
     * @throws TraegerNotFoundException on failure
     */
    public void update(Traeger t) throws TraegerNotFoundException {
        traegerDAO.update(t);
    }

    /**
     * Remove a container
     *
     * @param t - the cntainer to remove
     * @throws TraegerNotFoundException on failure
     */
    public void remove(Traeger t) throws TraegerNotFoundException {
        traegerDAO.remove(t);
    }

    /**
     * Get a container using its id
     *
     * @param id - the container id
     * @return the requested container
     * @throws TraegerNotFoundException on failure
     */
    public Traeger getTraegerById(int id) throws TraegerNotFoundException {
        return traegerDAO.getObjById(id);
    }

    /**
     * Get a list of all containers in the database
     *
     * @return a list of all containers in the database
     */
    public List<Traeger> getAll() {
        return traegerDAO.getAll();
    }
}
