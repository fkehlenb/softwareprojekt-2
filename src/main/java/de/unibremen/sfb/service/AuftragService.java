package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import lombok.Data;
import lombok.Getter;
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
@Data
@Transactional
public class AuftragService implements Serializable {
    private List<Auftrag> auftrage;
    private

    @Inject
    AuftragDAO auftragDAO;

    @Inject
    ProbenService probenService;

    @Inject
    ProzessKettenVorlageService prozessKettenVorlageService;

    @Inject
    AuftragsLogsService auftragsLogsService;

    @Inject
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    @Inject
    ProzessSchrittLogService prozessSchrittLogService;

    @Inject
    ProzessSchrittDAO prozessSchrittDAO;

    @Inject
    StandortService standortService;

    private Auftrag auftrag;
    @Inject
    private ProbeDAO probeDao;

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
     *
     * @param prio the prio whih sould be set
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
     * @throws AuftragNotFoundException falls es den Auftrag nicht gibt
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
     * @param auftrag der Auftrag der Bearbeitet wird
     * @throws ProzessKettenVorlageNotFoundException falls die Vorlage nicht gefunden wird
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
     *
     * @param auftrags die Vorlagen
     * @throws AuftragNotFoundException falls es diesen Auftrag nicht gibt
     */
    public void delete(List<Auftrag> auftrags) throws AuftragNotFoundException {
        for (Auftrag auftrag :
                auftrags) {
            auftrage.remove(auftrag);
            auftragDAO.remove(auftrag);
        }
    }

    /**
     * Converts all Auftraege tojson
     *
     * @return the json as a Sting
     */
    public String toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);
        Jsonb jsonb = JsonbBuilder.create(config);
//        String result = jsonb.toJson(auftrage);
        String result = "NO JSON";
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

    public Auftrag getAuftrag(int value) throws AuftragNotFoundException {
        return auftragDAO.getObjById(value);
    }

    /**
     * sets the status of a job
     *
     * @param a       the job
     * @param zustand the new status
     * @throws AuftragNotFoundException the job couldn't be found in the database
     */
    public void setAuftragsZustand(Auftrag a, Enum<ProzessKettenZustandsAutomat> zustand) throws AuftragNotFoundException {
        a.setProzessKettenZustandsAutomat(zustand); //TODO wenn update in db fehlschlägt: Zustand zurücksetzen?
        auftragDAO.update(a);
    }

    /**
     * assigns a user to a job
     *
     * @param t the user to be assigned
     * @param a the job to which they will be assigned
     * @throws AuftragNotFoundException the job couldn't be found in the database
     */
    public void assignToAuftrag(User t, Auftrag a) throws AuftragNotFoundException {
        //a.setAssigned(t); //TODO
        auftragDAO.update(a);
    }

    /*
      Bestimme was der naechste Prozessschritt ist, der noch nicht ausgefuehrt wurde
      Es ist wichtig das der aktuell durchgefuehrte Schritt nicht den Zustand angenommen hat

      @param a Auftrag
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
     * Wir kennen den Auftrag, vieleicht gibt es eine Liste von Proben die wir dem Auftrag zuweisen wollen
     * oder die Proben muessen erst erstellt werden
     * Dies finden wir raus, in dem wir prüfen, ob es der Erste PS die PS Art erstellend ist
     *   falls er erstellen ist, muessen wir die Proben erzeugen
     *   ansonsten nehmen wir die proben und weisen dem auftrag proben zu
     *
     * @param auftrag der Auftrag
     * @return der Auftrag mit den neuen Proben
     */
    public Auftrag probenZuweisen(@org.jetbrains.annotations.NotNull Auftrag auftrag, List<Probe> proben, String startID) throws AuftragNotFoundException, DuplicateProbeException {
        Standort lager = null;
        int i = 0;
        for (Bedingung b :
                auftrag.getProzessSchritte().get(0).getProzessSchrittVorlage().getBedingungen()) {
            try {
                lager = standortService.findByLocation("lager");
            } catch (StandortNotFoundException e) {
                e.printStackTrace();
            }
            if (auftrag.getProzessSchritte().get(0).getProzessSchrittVorlage().getPsArt().equals("ERZEUGEND")) {
                auftrag.setZugewieseneProben(erzeugeProbenNachBeding(b, lager, startID + i++));
            } else {
                auftrag.setZugewieseneProben(proben);
                // FIXME only once
                // fixme schleife korrekt?
            }
        }
        auftragDAO.update(auftrag);
        return auftrag;
    }

    /**
     * Erstelle Proben die einer Bedingung entsprechen, dies koenne wir fuer erzeugende Prozessschritte nutzen
     *
     * @param b       Die Bedingung
     * @param s       der Standort wo die Proben sind, normalerweise die Station and der sie erstellt werden
     * @param startID die Proben ID vom Logstiker / pkAdmin festgelegt
     * @return die liste mit proben die erzeugt wurden
     */
    private List<Probe> erzeugeProbenNachBeding(Bedingung b, Standort s, String startID) throws DuplicateProbeException {
        var result = new ArrayList<Probe>();
            var p = new Probe(startID, b.getGewuenschteAnzahl(), ProbenZustand.VORHANDEN, s);
            p.setBedingungen(List.of(b));
            result.add(p);
            probeDao.persist(p);
        return result;
    }

    /**
     * Hole Alle ProzessSchritte die als Transport Zustand ERSTELLT haben
     *
     * @return alle ps fuer den Transport
     */
    public List<ProzessSchritt> getTransportSchritt() {
        var s = new HashSet<ProzessSchritt>();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> p.getTransportAuftrag().getZustandsAutomat() == TransportAuftragZustand.ERSTELLT)
                    .collect(Collectors.toSet()));
        }
        return s.isEmpty() ? new ArrayList<>() : List.copyOf(s);
    }


    public int erstelleAuftrag(ProzessKettenVorlage ausPKV, AuftragsPrioritaet ausPrio) {
        // Auftrags Log
        AuftragsLog aLog = new AuftragsLog(LocalDateTime.now());
        aLog.setErstellt(LocalDateTime.now());
        try {
            log.info("Try to persist AuftragsLog " + aLog.toString());
            auftragsLogsService.add(aLog);
        } catch (DuplicateAuftragsLogException e) {
            log.error(e.getMessage());
            e.printStackTrace();
            log.error(e.getMessage());
        }

        var pk = new Auftrag(UUID.randomUUID().hashCode(), ausPKV, ausPrio, erstelePS(ausPKV.getProzessSchrittVorlagen()),
                aLog, ProzessKettenZustandsAutomat.INSTANZIIERT);
        try {
            add(pk);
        } catch (DuplicateAuftragException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return pk.getPkID();
    }

    private List<ProzessSchritt> erstelePS(List<ProzessSchrittVorlage> psvListe) {
        var r = new ArrayList<ProzessSchritt>();

        for (ProzessSchrittVorlage psv :
                psvListe) {
            var psAutomat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "Erstellt", psv.getZustandsAutomatVorlage());
            try {
                log.info("Try to persist pkAutomat " + psAutomat.getId());
                prozessSchrittZustandsAutomatService.add(psAutomat);
            } catch (DuplicateProzessSchrittZustandsAutomatException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }

            // Logs
            var l = new ProzessSchrittLog(LocalDateTime.now(), psAutomat.getCurrent());
            try {
                prozessSchrittLogService.add(l);
            } catch (DuplicateProzessSchrittLogException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }

            // Transport Auftrag // FIXME

            // PS erstellen
            var ps = new ProzessSchritt(UUID.randomUUID().hashCode(), List.of(l), psv, psAutomat);
            try {
                prozessSchrittDAO.persist(ps);
            } catch (DuplicateProzessSchrittException e) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
            r.add(ps);
        }
        return r;
    }
}
