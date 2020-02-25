package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Data
@Singleton

/**
 * Service fuer ProzessSchrittVorlagen
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class ProzessSchrittVorlageService implements Serializable {
    public List<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = getProzessSchrittVorlagen();
    }


    @Inject
    ProzessSchrittVorlageDAO psvDAO;



    public List<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return psvDAO.getAll();
    }

    /**
     * Persistieren der ProzessSchrittVorlage
     *
     * @param psv die Vorlage
     */
    public void persist(ProzessSchrittVorlage psv) {
        try {
            psvDAO.persist(psv);
        } catch (DuplicateProzessSchrittVorlageException e) {
            e.printStackTrace();
        }
        vorlagen.add(psv);
    }

    public ProzessSchrittVorlage ByID(int id) throws ProzessSchrittVorlageNotFoundException {
        try {
            log.info("Trying to find a PSP by ID");
            return psvDAO.getObjById(id);
        } catch (Exception e) {
            log.info("Error ProzessSchrittVorlageNotFoundException in PSVView");
            return null;
        }

    }

    /**
     * Bearbeiten der ProzessSchrittVorlage
     *
     * @param psv
     * @throws ProzessSchrittVorlageNotFoundException
     */
    public void edit(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException {
        try {
            log.info("Trying try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
            psvDAO.update(psv);
        } catch (Exception e) {
            log.info("Error try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
        }

        //var old = vorlagen.stream().filter(p -> psv.getPsVID() == p.getPsVID()).findFirst().orElse(null);
        //if (Collections.replaceAll(vorlagen, old, psv)) {
        //    log.info("Succesful edit " + psv);
        //} else {
        //    log.info("Failed to edit " + psv);
       // }
    }

    /**
     * Loeschen von ProzessSchrittVorlagen
     * @param psvs die Vorlagen
     */
    public void delete(List<ProzessSchrittVorlage> psvs) {
        for (ProzessSchrittVorlage psv :
                psvs) {

            vorlagen.remove(psv);

        }
    }

    /**
     * Hole die PSV durch ihre ID
     * @param id die ID von psv
     * @return die PSV
     * @throws ProzessSchrittVorlageNotFoundException
     */
    public ProzessSchrittVorlage getByID(int id) throws ProzessSchrittVorlageNotFoundException{
        return psvDAO.getObjById(id);
    }

}
