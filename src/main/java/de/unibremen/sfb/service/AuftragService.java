package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Hash;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Slf4j
@Getter
@Data
/**
 * Service fuer AuftragService
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class AuftragService implements Serializable {
    private List<Auftrag> auftrage;
    private

    @Inject
    AuftragDAO auftragDAO;

    @Inject
    ProbenService probenService;

    private Auftrag auftrag;

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
     * Abgebrochen (canceled), Durchgefuehrt (carried out)
     *
     * @return the current Zustand
     */
    public Enum<ProzessKettenZustandsAutomat> getPKZ() {
        return auftrag.getProzessKettenZustandsAutomat();
    }

    /**
     * sets the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     * Abgebrochen (canceled), Durchgefuehrt (carried out)
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
     * sets the current Prioritaet (priority) of this Auftrag
     */
    public void setPrio(AuftragsPrioritaet prio) {
        auftrag.setPriority(prio);
    }

    /**
     * returns the ProzessSchritte which the Auftrag consists of
     *
     * @return a Set containing all ProzessSchritt
     */
    public List<ProzessSchritt> getPS() {
        return auftrag.getProzessSchritte();
    }

    /**
     * Setze den Zustand von Auftrag a auf p und persistiere
     *
     * @param a Der Auftrag
     * @param p Der Zustand
     */
    public void zustandswechsel(Auftrag a, ProzessKettenZustandsAutomat p) throws AuftragNotFoundException {
        a.setProzessKettenZustandsAutomat(p);
        update(a);
    }


    // Hier beginnt der neue Service

    @PostConstruct
    public void init() {
        auftrage = getAuftrage();
    }

    public List<Auftrag> getAuftrage() {
        return auftragDAO.getAll();
        // FIXME DAO Persistence LEo
    }

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
    public List<Auftrag> erstelleStandartAuftrag() {


        var p = new ProzessSchrittParameter(420, "Test", new ArrayList<>());
        var us = List.of(new User(5, "Default", "Logistik", "l@g.c", "01234",
                "pk,", "123", true, LocalDateTime.now(),
                List.of(Role.TECHNOLOGE), "DEUTSCH"));
        var es = List.of(new ExperimentierStation(4, new Standort(1, "Test"), "Fehlerfrei",
                ExperimentierStationZustand.VERFUEGBAR,new ArrayList<>(), us));
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
        ProzessSchrittZustandsAutomatVorlage sVorlage = new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(),
                zustaende, "Standart");
        ProzessSchrittZustandsAutomatVorlage v = new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(),
                List.of("Erstellt", "Kapput") , "Test pszvav");
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

        var pslogs = new ArrayList<ProzessSchrittLog>();
        var ps = new ProzessSchritt(UUID.randomUUID().hashCode(), List.of(new ProzessSchrittLog(LocalDateTime.now(), "string")),  psv1, new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(),
                "FUCK", new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(), List.of("String"), "String")));

        var pkv = new ProzessKettenVorlage(UUID.randomUUID().hashCode(), List.of(psv1, psv0));

        return List.of(new Auftrag(UUID.randomUUID().hashCode(), pkv, AuftragsPrioritaet.HOCH, List.of(ps),
                new AuftragsLog(LocalDateTime.now()), ProzessKettenZustandsAutomat.GESTARTET));
    }


    /**
     * Update an existing job in the database
     *
     * @param auftrag - the job to update
     * @throws AuftragNotFoundException on failure
     */
    public void update(Auftrag auftrag) throws AuftragNotFoundException {
        auftragDAO.update(auftrag);
    }


    /**
     * Add a new job to the database
     *
     * @param auftrag - the job to add
     * @throws DuplicateAuftragException on failure
     */
    public void add(Auftrag auftrag) throws DuplicateAuftragException {
        auftragDAO.persist(auftrag);
        auftrage.add(auftrag);
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
         String result = jsonb.toJson(auftrage);
        //String result = "JSON IS BROKEN FIXME LEO";
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

    public Auftrag getAuftrag(int value) throws AuftragNotFoundException {
        return auftragDAO.getObjById(value);
    }

    /**
     * sets the status of a job
     * @param a the job
     * @param zustand the new status
     * @throws AuftragNotFoundException the job couldn't be found in the database
     */
    public void setAuftragsZustand(Auftrag a, Enum<ProzessKettenZustandsAutomat> zustand) throws AuftragNotFoundException {
        a.setProzessKettenZustandsAutomat(zustand); //TODO wenn update in db fehlschlägt: Zustand zurücksetzen?
        auftragDAO.update(a);
    }

    /**
     * assigns a user to a job
     * @param t the user to be assigned
     * @param a the job to which they will be assigned
     * @throws AuftragNotFoundException the job couldn't be found in the database
     */
    public void assignToAuftrag(User t, Auftrag a) throws AuftragNotFoundException {
        //a.setAssigned(t); //TODO
        auftragDAO.update(a);
    }

    /**
     * Bestimme was der naechste Prozessschritt ist, der noch nicht ausgefuehrt wurde
     * Es ist wichtig das der aktuell durchgefuehrte Schritt nicht den Zustand angenommen hat
     *
     * @param a Auftrag
     * @return Der naechste ProzessSchritt
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
     * - Fuer jeden ProzessSchritt
     * - Falls die Art erstellend ist, so koennen diese an der Station erstellt werden
     * - Ansonsten
     * - Gucke ob sich die Bedingen zu dem vorhergen ProzessSchritt veraendert haben
     * - Wenn nicht ueberneme die vorehrigen Proben
     * - Weise TODO andere Proben dann ins archiv
     * - Gucke welche existierenden freie Proben den Bediungen und Eigenschaften entsprechcen
     * - Teile dem ProzessSchritt diese Proben zu
     *
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
     *
     * @param b Die Bedingung
     * @param s der Standort wo die Proben sind, normalerweise die Station and der sie erstellt werden
     * @return die liste mit proben die erzeugt wurden
     */ //TODO warum nicht in ProbeService?
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

    /**
     * Hole Alle ProzessSchritte die als Transport Zustand ERSTELLT haben
     * @return alle ps fuer den Transport
     */
    public List<ProzessSchritt> getTransportSchritt() {
        var s = new HashSet<ProzessSchritt>();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> p.getTransportAuftrag().getZustandsAutomat()==TransportAuftragZustand.ERSTELLT)
                    .collect(Collectors.toSet()));
        }
        return s.isEmpty()?new ArrayList<>():List.copyOf(s);
    }
}
