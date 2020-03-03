package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Service fuer ProzessSchritt
 * Anwendungsfall: Bearbeiten eines ProzessSchrittes oder Hinzufügen eines neuen
 */
@Slf4j
@Transactional
public class ProzessSchrittService implements Serializable {

    /** Process step dao */
    @Inject
    private ProzessSchrittDAO prozessSchrittDAO;

    /** Create a new process step
     * @param ps - the process step to add
     * @throws DuplicateProzessSchrittException failure */
    public void createPS(ProzessSchritt ps) throws DuplicateProzessSchrittException{
        prozessSchrittDAO.persist(ps);
    }

    /** Edit a process step
     * @param ps - the process step to edit
     * @throws ProzessSchrittNotFoundException on failure */
    public void editPS(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        prozessSchrittDAO.update(ps);
    }

    /** Remove a process step
     * @param ps - the process step to remove
     * @throws ProzessSchrittNotFoundException on failure */
    public void removePS(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        prozessSchrittDAO.remove(ps);
    }

    /** Get a process step using its id
     * @param id - the process step id
     * @return the requested process step
     * @throws ProzessSchrittNotFoundException on failure */
    public ProzessSchritt getObjById(int id) throws ProzessSchrittNotFoundException{
        return prozessSchrittDAO.getObjById(id);
    }

    /** Get all process steps from the database
     * @return a list of all process steps or an empty arraylist */
    public List<ProzessSchritt> getAll(){
        return prozessSchrittDAO.getAll();
    }

    /** Get all process steps that are not assigned
     * @return a list of all process steps not yet assigned or an empty arraylist */
    public List<ProzessSchritt> getAllAvailable(){
        return prozessSchrittDAO.getAllAvailable();
    }
}

