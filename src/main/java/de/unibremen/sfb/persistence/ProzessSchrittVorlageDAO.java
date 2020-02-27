package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittVorlage;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the process chain step templates in the database
 */
public class ProzessSchrittVorlageDAO extends ObjectDAO<ProzessSchrittVorlage> {

    /**
     * Add a process chain step template to the database
     *
     * @param psv - the process chain step template to add to the database
     * @throws DuplicateProzessSchrittVorlageException if the process chain step template already exists in the database
     */
    public void persist(ProzessSchrittVorlage psv) throws DuplicateProzessSchrittVorlageException {
        if (psv != null) {
            synchronized (ProzessSchrittVorlage.class) {
                if (em.contains(psv)) {
                    throw new DuplicateProzessSchrittVorlageException();
                }
                em.persist(psv);
            }
        }
    }

    /**
     * Update a process chain step template in the database
     *
     * @param psv - the process chain step template to update in the database
     */
    public void update(ProzessSchrittVorlage psv) {
        if (psv != null) {
            em.merge(psv);
        }
    }

    /**
     * Remove a process chain step template from the database
     *
     * @param psv - the process chain step template to remove from the database
     * @throws ProzessSchrittVorlageNotFoundException if the process chain step template couldn't be found
     */
    public void remove(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException {
        if (psv != null) {
            if (!em.contains(psv)) {
                throw new ProzessSchrittVorlageNotFoundException();
            }
            psv.setValidData(false);
            update(psv);
        }
    }


    /**
     * Get a process chain step template from the database
     *
     * @return the requested process chain step
     */
    public Class<ProzessSchrittVorlage> get() {
        return ProzessSchrittVorlage.class;
    }

    /**
     * Get a process chain step template from the database using its defined id
     *
     * @param id - the id of the requested process chain step template
     * @return the process chain step template matching the given id
     * @throws ProzessSchrittVorlageNotFoundException if the process chain step template couldn't be found
     */
    public ProzessSchrittVorlage getObjById(int id) throws ProzessSchrittVorlageNotFoundException {
        try {
            ProzessSchrittVorlage psv = em.find(get(), id);
            if (psv == null || !psv.isValidData()) {
                throw new ProzessSchrittVorlageNotFoundException();
            }
            return psv;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ProzessSchrittVorlageNotFoundException();
        }
    }

    /**
     * Return a List quantitative descriptor object from the database
     * @return all PSV from the db
     * @throws IllegalArgumentException if the quantitative descriptor couldn't be found in the database
     */
    public List<ProzessSchrittVorlage> getAll() {
        try {
            return em.createQuery("SELECT p FROM ProzessSchrittVorlage p WHERE p.isValidData=true", get()).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
