package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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
        this.vorlagen = erstelleStandartVorlagen();
    }

    @Inject
    ProzessSchrittVorlageDAO psvDAO;

    /**
     * Diese Methode simuliert eine korrekte Persistenz.
     * Wenn ExperimentierStation.benutzer und ProzessSchrittVorlage.bedingungen auf Eager gestellt sind
     * ist es fuer Hibernate unmoeglich. Siehe: org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags
     * https://stackoverflow.com/questions/4334970/hibernate-throws-multiplebagfetchexception-cannot-simultaneously-fetch-multipl
     *
     * Es ist daher keine Loesung alles auf Fetchtype EAGER zu stellen.
     *
     * AddPSV.xhtml benoetigt aber das man psv.bedingung als Value nutzt. Es muss also fuer views  moeglich sein
     * das in einem View die Collection eines Objectes aufgerufen werden kann
     *
     * Bitte gebe uns eine andere Alernative als, das wir FetchType.EAGER benutzen muessen.
     *
     * In addPSV.xhtml muessen Aufrufe wie diese moeglich sein
     * <f:facet name="output"><h:outputText value="#{psv.bedingungen}"/></f:facet>
     *
     * Bei Fetch Type Eager, wuerde Hibernate fuer eine PSV zwei Eager Queries aufrufen, dies wird aber nicht unterstuetzt
     *
     * Es muss fuer uns moeglich sein, ohne Eager eine Collection (bedingungen oder es) aufrufen zu koennen
     *
     * Um dieses Problem zu Reproduzieren zu koenne, kann in init in psvErstellen zwischen dieser Methode und der Dao gewechselt werden
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
                List.of(new QualitativeEigenschaft(8, "gestresst"))), p),66));
        // Es ist nicht moeglich, es und bs eager in der naechsten Zeile
        var psv = new ProzessSchrittVorlage(42, Duration.ofHours(8),"ERMITTELND", es,bs);

        // Traeger Config
        var glass = new TraegerArt("Glass");
        var eT = new TraegerArt("Eingebetet");
        var gT = new TraegerArt("Einzelen");

        psv.setAusgabeTraeger(List.of(glass, eT));
        psv.setEingabeTraeger(List.of(gT, glass));

        return List.of(psv);
    }

    public List<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return psvDAO.getAll();
    }

    /**
     * Persistieren der ProzessSchrittVorlage
     * @param psv die Vorlage
     */
    public void persist(ProzessSchrittVorlage psv) {
//        try {
//            psvDAO.persist(psv);
//        } catch (DuplicateProzessSchrittVorlageException e) {
//            e.printStackTrace();
//        }
        vorlagen.add(psv);
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

    /**
     * Bearbeiten der ProzessSchrittVorlage
     * @param psv
     * @throws ProzessSchrittVorlageNotFoundException
     */
    public void edit(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException {
//        try {
//            log.info("Trying try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
//            psvDAO.update(psv);
//        } catch (Exception e) {
//            log.info("Error try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
//        }
//    }
        var old = vorlagen.stream().filter(p -> psv.getPsVID() == p.getPsVID()).findFirst().orElse(null);

        (Collections.replaceAll(vorlagen, old, psv) ? log.info("Succesful edit " + psv) : log.info("Failed to edit " + psv);
    }

    /**
     * Loeschen der ProzessSchrittVorlage
     * @param psv
     */
    public void delete(ProzessSchrittVorlage psv) {
       vorlagen.remove(psv);
    }
}
