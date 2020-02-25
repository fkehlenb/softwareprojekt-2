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
    ZustandsService zustandsService;

    @Inject
    ProzessSchrittVorlageDAO psvDAO;

    /**
     * Diese Methode simuliert eine korrekte Persistenz.
     * Wenn ExperimentierStation.benutzer und ProzessSchrittVorlage.bedingungen auf Eager gestellt sind
     * ist es fuer Hibernate unmoeglich. Siehe: org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags
     * https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
     * <p>
     * Es ist daher keine Loesung alles auf Fetchtype EAGER zu stellen.
     * <p>
     * AddPSV.xhtml benoetigt aber das man psv.bedingung als Value nutzt. Es muss also fuer views  moeglich sein
     * das in einem View die Collection eines Objectes aufgerufen werden kann
     * <p>
     * Bitte gebe uns eine andere Alernative als, das wir FetchType.EAGER benutzen muessen.
     * <p>
     * In psv.xhtml muessen Aufrufe wie diese moeglich sein
     * <f:facet name="output"><h:outputText value="#{psv.bedingungen}"/></f:facet>
     * <p>
     * Bei Fetch Type Eager, wuerde Hibernate fuer eine PSV zwei Eager Queries aufrufen, dies wird aber nicht unterstuetzt
     * <p>
     * Es muss fuer uns moeglich sein, ohne Eager eine Collection (bedingungen oder es) aufrufen zu koennen
     * <p>
     * Um dieses Problem zu Reproduzieren zu koenne, kann in init in psvErstellen zwischen dieser Methode und der Dao gewechselt werden
     *
     * @return
     */
    public List<ProzessSchrittVorlage> erstelleStandartVorlagen() {


        var p = new ProzessSchrittParameter(420, "Test", new ArrayList<>());
        var us = List.of(new User(5, "Default", "Logistik", "l@g.c", "01234",
                "pk,", "123", true, LocalDateTime.now(),
                List.of(Role.TECHNOLOGE), "DEUTSCH"));
        var es = List.of(new ExperimentierStation(4, new Standort(1, "Test"), "Fehlerfrei",
                ExperimentierStationZustand.VERFUEGBAR, us));
        var bs = List.of(new Bedingung(9, "Test B", List.of(new ProzessSchrittParameter(6, "PsP 1",
                List.of(new QualitativeEigenschaft(8, "gestresst"))), p), 66));
        // Es ist nicht moeglich, es und bs eager in der naechsten Zeile

        // ProzessSchrittVorlage Setup
        Set<ProzessSchrittZustandsAutomatVorlage> ergebnis = new HashSet<>();
        List<String> zustaende = new ArrayList();
        zustaende.add("Angenommen");
        zustaende.add("In Brearbeitung");
        zustaende.add("Bearbeitet");
        zustaende.add("Weitergeleitet");
        ProzessSchrittZustandsAutomatVorlage sVorlage = new ProzessSchrittZustandsAutomatVorlage(zustaende, "Standart");
        ProzessSchrittZustandsAutomatVorlage v = new ProzessSchrittZustandsAutomatVorlage(
                zustandsService.getPsZustaende(), "Test pszvav");
        var a = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "ANGENOMMEN", sVorlage);

        var psv0 = new ProzessSchrittVorlage(42, "8", "ERMITTELND", es, bs, v );
        var psv1 = new ProzessSchrittVorlage(55, "6", "FAERBEND", es, bs, v);

        // Traeger Config
        var glass = new TraegerArt("Glass");
        var eT = new TraegerArt("Eingebetet");
        var gT = new TraegerArt("Einzelen");

        psv0.setAusgabeTraeger(List.of(glass, eT));
        psv0.setEingabeTraeger(List.of(gT, glass));

        var l = new ArrayList<ProzessSchrittVorlage>();
        l.add(psv0);
        l.add(psv1);
        return l;
    }

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
