package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Singleton
/**
 * Service fuer ProzessSchrittVorlagen
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class ProzessSchrittVorlageService implements Serializable {
    private List<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = getProzessSchrittVorlagen();
    }

    @Inject
    ProzessSchrittVorlageDAO psvDAO;

    // FIXME Add Default
    private List<ProzessSchrittVorlage> erstelleStandartVorlagen() {
        return new ArrayList<>();
    }

    public List<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return psvDAO.getAll();
    }


    public void addVorlage(ProzessSchrittVorlage prozessSchrittVorlage) {
        this.vorlagen.add(prozessSchrittVorlage);
    }

    public void persist(ProzessSchrittVorlage psv) {
        try {
            psvDAO.persist(psv);
        } catch (DuplicateProzessSchrittVorlageException e) {
            e.printStackTrace();
        }
    }
    public ProzessSchrittVorlage ByID(int id) throws ProzessSchrittVorlageNotFoundException {
        try {
            log.info("Trying to find a PSP by ID");
            return psvDAO.getObjById(id);
        } catch (Exception e) {
            log.info("Error ProzessSchrittVorlageNotFoundException in PSVErstellenBean");
            return null;
        }
    }
    public void edit(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException {
        try {
            log.info("Trying try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
            psvDAO.update(psv);
        } catch (Exception e) {
            log.info("Error try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
        }
    }

}
