package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
@Slf4j
@Getter
@Data
/**
 * Service fuer ProzessSchrittVorlagen
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class AuftragService implements Serializable {
    private List<Auftrag> auftrage;
    private

    @Inject
    AuftragDAO auftragDAO;

    @Inject
    ProbenService probenService;

    private Auftrag auftrag; // FIXME Why Thow
    /**
     * returns the ID of this Auftrag
     *
     * @return the ID
     */
    public int getID() {
        return auftrag.getPkID();
    }

    /**
     * return the ProzessKettenVorlage which was used to instantiate this Auftrag
     *
     * @return the ProzessKettenVorlage
     */
    public ProzessKettenVorlage getPKV() {
        return auftrag.getVorlage();
    }

    /**
     * return the protocol of this Auftrag that was created thus far
     *
     * @return the protocol
     */
    public AuftragsLog getLog() {
        return auftrag.getLog();
    }


    /**
     * sets the protocol of this Auftrag
     *
     * @param al the new protocol
     */
    public void setLog(AuftragsLog al) {
        auftrag.setLog(al);
    }

    /**
     * returns the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgefuehrt (carried out)
     * @return the current Zustand
     */
    public Enum<ProzessKettenZustandsAutomat> getPKZ() {
        return auftrag.getProzessKettenZustandsAutomat();
    }

    /**
     * sets the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgefuehrt (carried out)
     */
//        public List<Probe> getProbenByStandort(Standort s) {
//        return proben.stream()
//                .filter(e -> e.getStandort().equals(s))
//                .collect(Collectors.toList());
//    }

//    public void setPKZ(Enum<ProzessKettenZustandsAutomat> pkz) {
//        auftrag.setProzessKettenZustandsAutomat(pkz);
//    }
//
//    /**
//     * returns the current Prioritaet (priority) of this Auftrag
//     * @return the current Prioritaet
//     */
//    public Enum<AuftragsPrioritaet> getPrio() {
//        return auftrag.getPriority();
//    }
//
//    /**
//     * sets the current Prioritaet (priority) of this Auftrag
//     */
//    public void setPrio(Enum<AuftragsPrioritaet> prio) {
//        auftrag.setPriority(prio);
//    }
//
//    /**
//     * returns the ProzessSchritte which the Auftrag consists of
//     * @return a Set containing all ProzessSchritt
//     */
//    public List<ProzessSchritt> getPS() {
//        return auftrag.getProzessSchritte();
//    }
//
//
    /**
     * Setze den Zustand von Auftrag a auf p und persistiere
     * @param a Der Auftrag
     * @param p Der Zustand
     */
    public void zustandswechsel(Auftrag a, ProzessKettenZustandsAutomat p) {
        a.setProzessKettenZustandsAutomat(p);
        upate(a);
    }


    // Hier beginnt der neue Service

    @PostConstruct
    public void init() {
        auftrage = getAuftrage();
    }

    public List<Auftrag> getAuftrage() {
        return auftragDAO.getAll();
    }

    public void upate(Auftrag auftrag) {
        try {
            auftragDAO.update(auftrag);
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void add(Auftrag auftrag) {
        auftrage.add(auftrag);
        try {
            auftragDAO.persist(auftrag);
        } catch (DuplicateAuftragException e) {
            e.printStackTrace();
        }
    }

    /**
     * Bearbeiten der ProzessKettenVorlage
     *
     * @param auftrag
     * @throws ProzessKettenVorlageNotFoundException
     */
    public void edit(Auftrag auftrag) throws ProzessKettenVorlageNotFoundException {
        var old = auftrage.stream().filter(p -> auftrag.getPkID() == p.getPkID()).findFirst().orElse(null);

        if (Collections.replaceAll(auftrage, old, auftrag)) {
            log.info("Succesful edit " + auftrag);
        } else {
            log.info("Failed to edit " + auftrag);
        }
    }

    /**
     * Loeschen von ProzessKettenVorlagen
     * @param auftrags die Vorlagen
     */
    public void delete(List<ProzessKettenVorlage> auftrags) {
        for (ProzessKettenVorlage auftrag :
                auftrags) {
            auftrage.remove(auftrag);
        }
    }

    public String toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(getAuftrage());
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

    public Auftrag getAuftrag(String value) {

        try {
            return auftragDAO.getObjById(Integer.parseInt(value));
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Bestimme was der naechste Prozessschritt ist, der noch nicht ausgefuehrt wurde
     * Es ist wichtig das der aktuell durchgefuehrte Schritt nicht den Zustand angenommen hat
     * @param a Auftrag
     * @return  Der naechste ProzessSchritt
     */
//    public ProzessSchritt getNextPS(Auftrag a) {
//        return a.getProzessSchritte().stream()
//                .filter((p) -> "Angenommen".equals(p.getProzessSchrittVorlage().getZustandsAutomat().getCurrent()))
//                .findFirst()
//                .orElse(null);
//    }

    /**
     * Weise einen Auftrag Proben zu
     * Vorgehen:
     *  - Fuer jeden ProzessSchritt
     *    - Falls die Art erstellend ist, so koennen diese an der Station erstellt werden
     *    - Ansonsten
     *      - Gucke ob sich die Bedingen zu dem vorhergen ProzessSchritt veraendert haben
     *        - Wenn nicht ueberneme die vorehrigen Proben
     *        - Weise TODO andere Proben dann ins archiv
     *      - Gucke welche existierenden freie Proben den Bediungen und Eigenschaften entsprechcen
     *      - Teile dem ProzessSchritt diese Proben zu
     * @param auftrag der Auftrag
     * @return der Auftrag mit den neuen Proben
     */
    public Auftrag probenZuweisen(Auftrag auftrag) {
//        for (ProzessSchritt ps :
//                auftrag.getProzessSchritte()) {
//            var proben = switch( ps.getProzessSchrittVorlage().getPsArt()) {
//                case "ERZEUGEND" :
//                    yield fori
//            }
//        }
        return null;
    }

    /**
     * Erstelle Proben die einer Bedingung entsprechen, dies koenne wir fuer erzeugende Prozessschritte nutzen
     * @param b Die Bedingung
     * @param s der Standort wo die Proben sind, normalerweise die Station and der sie erstellt werden
     * @return
     */
    private List<Probe> erzeugeProbenNachBeding(Bedingung b, Standort s) {
        var result = new ArrayList<Probe>();
        for (int i = 0; i < b.getGewuenschteAnzahl(); i++) {
            var p = new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, s);
            p.setBedingungen(List.of(b));
            result.add(p);
            }
        // TODO persist
        return result;
        }
    }


