package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.*;

/**
 * this class manages the interaction with models of process chain templates (ProzessKettenVorlage)
 */
@Singleton
@Slf4j
@Getter
public class ProzessKettenVorlageService implements Serializable {
    private ArrayList<ProzessKettenVorlage> pkVorlagen;
    private List<ProzessSchrittVorlage> psVorlagen;

    @Inject
    ProzessKettenVorlageDAO pkvDAO;

    @Inject
    ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    ProzessKettenVorlageService prozessKettenVorlageService;

    @PostConstruct
    public void init() {
        psVorlagen = prozessSchrittVorlageService.getVorlagen();
        pkVorlagen = getPkVorlagen();
    }

    private List<ProzessKettenVorlage> erstellePKV() {
        return List.of(new ProzessKettenVorlage(UUID.randomUUID().hashCode(), psVorlagen));
    }

    public ProzessKettenVorlage getPKV(int id) {
        ProzessKettenVorlage result = null;
        try {
            result = pkvDAO.getObjById(id);
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<ProzessKettenVorlage> getProzessKettenVorlagen() {
        try {
            return pkvDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Persistieren der ProzessKettenVorlage
     *
     * @param pkv die Vorlage
     * @throws DuplicateProzessKettenVorlageException falls es sie schon gibt
     */
    public void persist(ProzessKettenVorlage pkv) throws DuplicateProzessKettenVorlageException {
        pkvDAO.persist(pkv);
//        pkVorlagen.add(pkv);
    }

    public ProzessKettenVorlage ByID(int id) throws ProzessKettenVorlageNotFoundException {
        try {
            log.info("Trying to find a PSP by ID");
            return pkvDAO.getObjById(id);
        } catch (Exception e) {
            log.info("Error ProzessKettenVorlageNotFoundException in PKVErstellenBean");
            return null;
        }

    }

    /**
     * Bearbeiten der ProzessKettenVorlage
     *
     * @param pkv die zu bearbeitende Experimentier Station
     * @throws ProzessKettenVorlageNotFoundException falls nicht gefunden
     */
    public void edit(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
//        var old = pkVorlagen.stream().filter(p -> pkv.getPkID() == p.getPkID()).findFirst().orElse(null);
//
//        if (Collections.replaceAll(pkVorlagen, old, pkv)) {
//            log.info("Succesful edit " + pkv);
//        } else {
//            log.info("Failed to edit " + pkv);
//        }
// }
        try {
            log.info("Trying try to update a PKV" + pkv + "Class=ProzessKettenVorlageService");
            pkvDAO.update(pkv);
        } catch (Exception e) {
            log.info("Error try to update a PKV" + pkv + "Class=ProzessKettenVorlageService");
        }
    }

    /**
     * Loeschen von ProzessKettenVorlagen
     *
     * @param pkvs die Vorlagen
     */
    @SneakyThrows
    public void delete(List<ProzessKettenVorlage> pkvs) throws DuplicateProzessKettenVorlageException {
        for (ProzessKettenVorlage pkv :
                pkvs) {
            pkvDAO.remove(pkv);
        }
    }


    public List<ProzessKettenVorlage> getPKVs() {
        try {
            return pkvDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
