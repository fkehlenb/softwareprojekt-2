package de.unibremen.sfb.controller;

import com.github.javafaker.Faker;
import de.unibremen.sfb.exception.DuplicateKommentarException;
import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.KommentarDAO;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.persistence.TraegerDAO;
import de.unibremen.sfb.persistence.UserDAO;
import de.unibremen.sfb.service.RoleService;
import de.unibremen.sfb.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

import org.apache.shiro.authc.credential.PasswordMatcher;

/**
 * This Class is run when the Server is deployed. Its purpose is to pupulate all web Views
 * At the same time this works as a integration Test.
 * <p>
 * If there are persistence Problems they will be noticed here
 */
@Startup
@Singleton
@Transactional
@Slf4j
public class InitialDataFiller {
    int limit = 20;
    private List<Standort> standorte;
    private ProzessSchritt ps;
    private ProzessKettenVorlage pkv;
    private ProzessSchrittZustandsAutomatVorlage pszaVorlage;

    private List<String> psZustaende = new ArrayList<>();
    private List<String> pkZustaende = new ArrayList<>();

    private List<TraegerArt> tas = new ArrayList<>();

    @Inject
    private UserDAO userDAO;

    @Inject
    UserService userService;

    @Inject
    RoleService roleService;

    @Inject
    private ProbeDAO probeDAO;

    @Inject
    private KommentarDAO kommentarDAO;

    @Inject
    private TraegerDAO traegerDAO;

    @PersistenceContext
    private EntityManager em;

    private User testUser;
    private ArrayList<ExperimentierStation> experimentierStations;
    private ProzessSchrittZustandsAutomatVorlage sVorlage;
    private User tTechnologe;

    @PostConstruct
    public void init() {

        if (userDAO.getAll().isEmpty()) {
            log.info("Storing inital Data");

            // User
            for (User user :
                    createDefaultUsers()) {
                log.info("Trying to persist User " + user.getNachname());
                em.persist(user);
            }

            // FIXME DAO
            psZustaende.add("Erstellt");
            psZustaende.add("Angenommen");
            psZustaende.add("In Bearbeitung");
            psZustaende.add("Bearbeitet");
            psZustaende.add("Weitergeleitet");

            pkZustaende.add("ERSTELLT");
            pkZustaende.add("INSTANZIERT");
            pkZustaende.add("BEENDET");
            pkZustaende.add("ARCHIVIERT");

            // Standort, requires Users
            for (Standort s :
                    createDefaulStandort()) {
                log.info("Trying to  persist Standort " + s.toString());
                em.persist(s);
            }

            // Persistiere Traeger Arten
            tas = erstelleTraeger();
            for (TraegerArt t :
                    tas) {
                log.info("Persisting Traegerart " + t.getArt());
                em.persist(t);
            }

            for (ProzessSchrittZustandsAutomatVorlage p :
                    erstelleStandartVorlagen()) {
                log.info("Persisting: Voralge " + p.getName());
                em.persist(p);
            }

            // Experimentierstaation, requires Standort
            for (ExperimentierStation s :
                    createDefaultStation()) {
                log.info("Trying to persist Station " + s.getName());
                em.persist(s);
            }

            /*
              persis von QualitativeEigenschaft durch die Variebln qualitativeEigenschaftList
              */
            List<QualitativeEigenschaft> qualitativeEigenschaftList = getQualitativeEigenschaften();
            for (QualitativeEigenschaft qE : qualitativeEigenschaftList
            ) {
                log.info("Trying to Persist Qualitaet Eingenschaft " + qE.getName());
                em.persist(qE);
            }

            em.persist(new QuantitativeEigenschaft("Lustige Eigenschaft", 420, "seconds"));

            // PS Parameter
            List<ProzessSchrittParameter> parameters = new ArrayList<>();
            parameters.add(new ProzessSchrittParameter(UUID.randomUUID().hashCode(), "Getestet", qualitativeEigenschaftList));

            for (ProzessSchrittParameter psp : parameters
            ) {
                log.info("Trying ti persist ProzessSchrittParameter " + psp.getName());
                em.persist(psp);
            }

            // Erstelle Test Bedingung
            Bedingung b = new Bedingung(UUID.randomUUID().hashCode(), "Test Bedingung", parameters, 44);
            log.info("Trying to persist Bedingung: " + b.toString());
            em.persist(b);

            // Erstelle die Liste aus den Parametern
            List<ProzessSchrittVorlage> psvListe = getProzessSchrittVorlages(b);
            for (ProzessSchrittVorlage pSV :
                    psvListe) {
                log.info("Trying to persist ProzessSchrittVorlage " + pSV.toString());
                em.persist(pSV);
            }
            pkv = new ProzessKettenVorlage(UUID.randomUUID().hashCode(), psvListe);
            log.info("Try to persist ProzessSchrittVorlage " + pkv.getPkvID());
            em.persist(pkv);

            // PSZAV Setup
            pszaVorlage = new ProzessSchrittZustandsAutomatVorlage(996699,
                    psZustaende, "Standart");
            log.info("Try to persist ProzessSchrittZustandsAutomatVorlage " + pszaVorlage.toString());
            em.persist(pszaVorlage);

            // Setup PSZA
            ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(
                    UUID.randomUUID().hashCode(), "ERSTELLT", pszaVorlage);
            log.info("Try to persist ProzessSchrittZustandsAutomat " + prozessSchrittZustandsAutomat.toString());
            em.persist(prozessSchrittZustandsAutomat);
//            erstelleAuftrag(psvListe);

        } else {
            log.info("Data already exists");
        }

    }

//    private void erstelleAuftrag(List<ProzessSchrittVorlage> psvListe) {
//        for (int i = 0; i < limit; i++) {
//            // Auftrag Setup
//            AuftragsLog aLog = new AuftragsLog(LocalDateTime.now());
//            aLog.setErstellt(LocalDateTime.now());
//            em.persist(aLog);
//            log.info("Try to persist AuftragsLog " + aLog.toString());
////            Auftrag pk = new Auftrag(UUID.randomUUID().hashCode(), pkv, AuftragsPrioritaet.HOCH, erstelePS(psvListe),
////                    aLog, ProzessKettenZustandsAutomat.INSTANZIIERT);
//
//            // Erstelle neuen PSZA
//            var automat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "AKZEPTIERT", pszaVorlage);
//            em.persist(automat);
//
//            // PS Setup
//            ArrayList<ProzessSchrittLog> logs = new ArrayList<>();
//            logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
//            for (ProzessSchrittLog pSL :
//                    logs) {
//                log.info("Try to persist logs " + pSL.getGestartet().toString());
//                em.persist(pSL);
//            }
//            // PS aufuellen
////            log.info("Try to persist TEST ProzessKette " + pk.getPkID());
////            em.persist(pk);
//        }
//    }

//    private List<ProzessSchritt> erstelePS(List<ProzessSchrittVorlage> psvListe) {
//        var r = new ArrayList<ProzessSchritt>();
//        for (int i = 0; i < limit * 0.5; i++) {
//            var a = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "Erstellt", pszaVorlage);
//            em.persist(a);
//            var logs = new ArrayList<ProzessSchrittLog>();
//            var l = new ProzessSchrittLog(LocalDateTime.now(), a.getCurrent());
//            //var ps = new ProzessSchritt(UUID.randomUUID().hashCode(),ps);
//            var psLogs = List.of(new ProzessSchrittLog(LocalDateTime.now(), "Gestartet"),
//                    new ProzessSchrittLog(LocalDateTime.now(), "Veraendert"));
//            for (ProzessSchrittLog psl :
//                    psLogs) {
//                em.persist(psl);
//            }
//            //ps.setProzessSchrittLog(psLogs);
//            log.info("Try to persist TEST ProzessSchritt " + ps.getId());
//
//            var transportAuftrag = new TransportAuftrag(LocalDateTime.now(), TransportAuftragZustand.ERSTELLT);
//            em.persist(transportAuftrag);
//            log.info("Persisting Transport Auftag " + transportAuftrag.getZustandsAutomat());
//            em.persist(transportAuftrag);
//            ps.setTransportAuftrag(transportAuftrag);
//            em.persist(ps);
//            r.add(ps);
//
//            // Weise den PS auch Stationen zu.
//            // Wenn es einen akutellen Schritt gibt, dann werden die weiteren Schritte in Warteschlange eingreiht.
//            ExperimentierStation es;
//            try {
//                es = ps.getProzessSchrittVorlage().getStationen().get(0);
//                if (es.getCurrentPS() == null) {
//                    es.setCurrentPS(ps);
//                    ps.setZugewieseneProben(erstelleProben(es.getStandort()));
//                } else {
//                    es.getNextPS().add(ps);
//                }
//                em.persist(es);
//            } catch (NullPointerException e) {
//                log.info("Es gibt keine Stationen für diesen Schritt");
//            }
//
//        }
//        return r;
//    }

    /**
     * Erstelle Standorte
     *
     * @return erstelle Standorte
     */
    private List<Standort> createDefaulStandort() {

        standorte = new ArrayList<>();
        standorte.add(new Standort(UUID.randomUUID().hashCode(),"Lager"));
        for (int i = 0; i < limit; i++) {
            Standort s = new Standort(UUID.randomUUID().hashCode(), "Station " + i);
            log.info("Persisiting Experimentierstation " + i);
            standorte.add(s);
        }


        return standorte;
    }

    /**
     * Erstelle Experimentierstationen
     * Depends on createDefaultUser
     *
     * @return The List of Created Stations
     */
    private List<ExperimentierStation> createDefaultStation() {
        experimentierStations = new ArrayList<>();
        Faker faker = new Faker();
        for (int i = 0; i < limit; i++) {
            experimentierStations.add(new ExperimentierStation(UUID.randomUUID().hashCode(), standorte.get(i),
                    faker.lordOfTheRings().location(), ExperimentierStationZustand.VERFUEGBAR, new ArrayList<Bedingung>(), List.of(tTechnologe)));
        }
        return experimentierStations;
    }

    /**
     * Erstelle Standart Nutzer
     * FIXME Password need to be in external Config
     *
     * @return A user for every Role
     */
    private List<User> createDefaultUsers() {
        try {
            Faker f = new Faker(new Locale("de"));
            PasswordMatcher matcher = new PasswordMatcher();

            var uList = userService.getRoles();

            // User Setup
            List<Role> a = new ArrayList<>();
            testUser = new User(UUID.randomUUID().hashCode(), "Default", "Technologe", "l@g.c", f.phoneNumber().cellPhone(),
                    "t", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                    "en");
            roleService.applyRoles(List.of("technologe"), "t");
            tTechnologe = testUser;
            List users = new ArrayList<>();

            // Add to user Lost
            users.add(testUser);

            testUser = new User(UUID.randomUUID().hashCode(), "Default", "PKAdmin", "l@g.c", f.phoneNumber().cellPhone(),
                    "pk", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                    "de");
            roleService.applyRoles(List.of("pkAdmin"), "pk");
            // Add to user Lost


            testUser = new User(UUID.randomUUID().hashCode(), "Default", "Admin", "l@g.c", f.phoneNumber().cellPhone(),
                    "admin", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                    "en");
            roleService.applyRoles(List.of("admin", "transport", "pkAdmin", "logistik", "technologe"), "admin");
            // Add to user Lost


            users.add(testUser);

            testUser = new User(UUID.randomUUID().hashCode(), "Default", "Transport", "l@g.c", f.phoneNumber().cellPhone(),
                    "tr", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                    "de");
            roleService.applyRoles(List.of("transport"), "tr");

            // Add to user Lost
            users.add(testUser);

            testUser = new User(UUID.randomUUID().hashCode(), "Default", "Logistik", "l@g.c", f.phoneNumber().cellPhone(),
                    "l", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                    "de");
            roleService.applyRoles(List.of("logistik"), "l");
            // Add to user Lost
            users.add(testUser);

            // Add to user Lost
            users.add(testUser);
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public List<ProzessSchrittVorlage> getProzessSchrittVorlages(Bedingung b) {
        // PSVA
        List<ProzessSchrittVorlage> psListe = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            psListe.add(getProzessSchrittVorlage(b));
        }
        return psListe;
    }

    public ProzessSchrittVorlage getProzessSchrittVorlage(Bedingung b) {
        // ProzessSchrittVorlage Setup
        ProzessSchrittZustandsAutomatVorlage v = new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(),
                psZustaende, "Test pszvav");
        em.persist(v);
        var a = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "ANGENOMMEN", sVorlage);
        em.persist(a);
        Faker faker = new Faker();
        List<Bedingung> bs = new ArrayList<>();
        bs.add(b);
        return  new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), "42",faker.gameOfThrones().character(),
                "Ermittlend", experimentierStations, bs, v);
    }

    public List<QualitativeEigenschaft> getQualitativeEigenschaften() {
        // Eigenschaften
        Faker faker = new Faker();
        ArrayList<QualitativeEigenschaft> qualEigenschaften = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            QualitativeEigenschaft q1 = new QualitativeEigenschaft(UUID.randomUUID().hashCode(), faker.funnyName().name());
            qualEigenschaften.add(q1);
        }

        return qualEigenschaften;
    }

    public List<TraegerArt> erstelleTraeger() {
        return List.of(new TraegerArt("Glass"), new TraegerArt("Eingebetet"), new TraegerArt("Einzelen"));
    }

    // FIXME Add Default
    public Set<ProzessSchrittZustandsAutomatVorlage> erstelleStandartVorlagen() {
        Set<ProzessSchrittZustandsAutomatVorlage> ergebnis = new HashSet<>();
        List<String> zustaende = new ArrayList<>();
        zustaende.add("Angenommen");
        zustaende.add("In Brearbeitung");
        zustaende.add("Bearbeitet");
        zustaende.add("Weitergeleitet");

        sVorlage = new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(), zustaende, "Standart");
        ergebnis.add(sVorlage);
        return ergebnis;
    }

    /**
     * erstellt proben für einen prozessschritt
     * @return a list of samples
     */
    public List<Probe> erstelleProben(Standort s) {
        List<Probe> r = new ArrayList<>();
        Traeger t = new Traeger(666, tas.get(0), s);
        try {
            traegerDAO.persist(t);
        }
        catch(DuplicateTraegerException e) {
            e.printStackTrace();
        }
        for(int i=0; i<limit; i++){
            Probe p1 = new Probe("FDGHJ"+i, 9, ProbenZustand.VORHANDEN, s);
            p1.setCurrentTraeger(t);
            p1.setKommentar(erstelleKommentare());
            r.add(p1);
            try {
                probeDAO.persist(p1);
            }
            catch(DuplicateProbeException e) {
                e.printStackTrace();
            }
        }
        return r;
    }

    public List<Kommentar> erstelleKommentare() {
        List<Kommentar> r = new ArrayList<>();
        for(int i=0; i<limit; i++) {
            Kommentar k = new Kommentar(LocalDateTime.now(), "hallo"+i);
            try {
                kommentarDAO.persist(k);
            }
            catch(DuplicateKommentarException e) {
                e.printStackTrace();
            }
            r.add(k);
        }
        return r;
    }

}
