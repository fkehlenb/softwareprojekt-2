package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import de.unibremen.sfb.persistence.TransportAuftragDAO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service fuer AuftragService
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */
@Slf4j
@Getter
@Setter
@Transactional
public class AuftragService implements Serializable {

    /** Job DAO */
    @Inject
    private AuftragDAO auftragDAO;

    /** Add a new job to the database
     * @param a - the job to add
     * @throws DuplicateAuftragException on failure */
    public void add(Auftrag a) throws DuplicateAuftragException{
        auftragDAO.persist(a);
    }

    /** Update a job in the database
     * @param a - the job to update
     * @throws AuftragNotFoundException on failure */
    public void update(Auftrag a) throws AuftragNotFoundException{
        auftragDAO.update(a);
    }

    /** Remove a job from the database
     * @param a - the job to remove
     * @throws AuftragNotFoundException on failure */
    public void remove(Auftrag a) throws AuftragNotFoundException{
        auftragDAO.remove(a);
    }

    /** Get a list of all jobs in the database
     * @return a list of all jobs or an empty arraylist */
    public List<Auftrag> getAll(){
        return auftragDAO.getAll();
    }

    /** Get a job using its id
     * @param id - the id of the job
     * @return the job with a matching id
     * @throws AuftragNotFoundException on failure */
    public Auftrag getObjById(int id) throws AuftragNotFoundException{
        return auftragDAO.getObjById(id);
    }

    /** Serialize a job to json */
    public void json(){

    }
}
