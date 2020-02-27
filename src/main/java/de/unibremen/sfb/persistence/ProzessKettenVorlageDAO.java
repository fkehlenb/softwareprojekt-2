package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the job templates in the database
 */
@Slf4j
public class ProzessKettenVorlageDAO extends ObjectDAO<ProzessKettenVorlage> {

    /**
     * Add a job template to the database
     *
     * @param pkv - the job template to add to the database
     * @throws DuplicateProzessKettenVorlageException if the job template already exists in the database
     */
    public void persist(ProzessKettenVorlage pkv) throws DuplicateProzessKettenVorlageException {
        if (pkv != null) {
            synchronized (ProzessKettenVorlage.class) {
                if (em.contains(pkv)) {
                    throw new DuplicateProzessKettenVorlageException();
                }
                em.persist(pkv);
            }
        }
    }

    /**
     * Update an existing job template in the database
     *
     * @param pkv - the job template to update in the database
     * @throws ProzessKettenVorlageNotFoundException if the job couldn't be found
     */
    public void update(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
        if (pkv != null) {
            if (!em.contains(pkv)) {
                throw new ProzessKettenVorlageNotFoundException();
            }
            em.merge(pkv);
        }
    }

    /**
     * Remove an existing job template from the database
     *
     * @param pkv - the job template to remove from the database
     * @throws ProzessKettenVorlageNotFoundException if the job template couldn't be found
     */
    public void remove(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
        if (pkv != null) {
            if (!em.contains(pkv)) {
                throw new ProzessKettenVorlageNotFoundException();
            }
            pkv.setValidData(false);
            update(pkv);
        }
    }


    public List<ProzessKettenVorlage> getAll() throws Exception {
        try {
            return em.createNamedQuery("PKV.getAll", get()).getResultList();
        }
        catch (EntityNotFoundException e){
            return new ArrayList<>();
        }
    }

    /**
     * @return the class of process chain templates
     */
    public Class<ProzessKettenVorlage> get() {
        return ProzessKettenVorlage.class;
    }

    /**
     * Get a specific job template from the database matching a specific id
     *
     * @param id - the id whose job template to fetch from the database
     * @return the job template matching the given id
     * @throws ProzessKettenVorlageNotFoundException if the job template couldn't be found
     */
    public ProzessKettenVorlage getObjById(int id) throws ProzessKettenVorlageNotFoundException {
        ProzessKettenVorlage pkv = em.find(get(), id);
        if (pkv == null || !pkv.isValidData()) {
            throw new ProzessKettenVorlageNotFoundException();
        }
        return pkv;
    }

}
