package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

@Slf4j
@Getter
@Setter
public class ProzessKettenVorlageService implements Serializable {

    /**
     * Process chain template dao
     */
    @Inject
    private ProzessKettenVorlageDAO prozessKettenVorlageDAO;

    /**
     * Add a new process chain template to the database
     *
     * @param pkv - the process chain template to add
     * @throws DuplicateProzessKettenVorlageException on failure
     */
    public void persist(ProzessKettenVorlage pkv) throws DuplicateProzessKettenVorlageException {
        prozessKettenVorlageDAO.persist(pkv);
    }

    /**
     * Update a process chain template in the database
     *
     * @param pkv - the process chain template to update
     * @throws ProzessKettenVorlageNotFoundException on failure
     */
    public void update(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageDAO.update(pkv);
    }

    /**
     * Remove a process chain template from the database
     *
     * @param pkv - the process chain template to remove
     * @throws ProzessKettenVorlageNotFoundException on failure
     */
    public void remove(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageDAO.remove(pkv);
    }

    /**
     * Get a process chain template using its id
     *
     * @param id - the process chain template id
     * @return the process chain template with a matching id
     * @throws ProzessKettenVorlageNotFoundException on failure
     */
    public ProzessKettenVorlage getObjById(int id) throws ProzessKettenVorlageNotFoundException {
        return prozessKettenVorlageDAO.getObjById(id);
    }

    /**
     * Get all process chain templates from the database
     *
     * @return a list of all process chain templates or an empty arraylist
     */
    public List<ProzessKettenVorlage> getAll() {
        return prozessKettenVorlageDAO.getAll();
    }
}
