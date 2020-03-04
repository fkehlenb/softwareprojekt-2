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
    Faker f = new Faker(new Locale("de"));

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

    @PersistenceContext(name = "swp2")
    private EntityManager em;

    private User testUser;
    private List<ExperimentierStation> experimentierStations;
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

            //  Persistiere Traeger Arten
            tas = erstelleTraeger();
            for (TraegerArt t :
                    tas) {
                log.info("Persisting Traegerart " + t.getArt());
                em.persist(t);
            }

            em.persist(erstelleStandartVorlage());

            //       Experimentierstaation, requires Standort;
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

            //    PS Parameter
            List<ProzessSchrittParameter> parameters = new ArrayList<>();
            for (int i = 0; i < limit; i++) {
                parameters.add(new ProzessSchrittParameter(UUID.randomUUID().hashCode(), f.gameOfThrones().dragon(), qualitativeEigenschaftList));
            }

            for (ProzessSchrittParameter psp : parameters
            ) {
                log.info("Trying ti persist ProzessSchrittParameter " + psp.getName());
                em.persist(psp);
            }

            // PSZAV Setup
            pszaVorlage = new ProzessSchrittZustandsAutomatVorlage(996699,
                    psZustaende, "Standart");
            log.info("Try to persist ProzessSchrittZustandsAutomatVorlage " + pszaVorlage.toString());
            em.persist(pszaVorlage);

            // Erstelle die Liste aus den Parametern
            List<ProzessSchrittVorlage> psvListe = getProzessSchrittVorlage(parameters);
            for (ProzessSchrittVorlage pSV :
                    psvListe) {
                log.info("Trying to persist ProzessSchrittVorlage " + pSV.toString());
                em.persist(pSV); //FINDME
            }
            pkv = new ProzessKettenVorlage(UUID.randomUUID().hashCode(), f.gameOfThrones().house(), psvListe);
            log.info("Try to persist ProzessSchrittVorlage " + pkv.getPkvID());
            em.persist(pkv);


            // Setup PSZA
            List z = new ArrayList();
            for (String s :
                    pszaVorlage.getZustaende()) {
                z.add(s);
            }
            ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(
                    UUID.randomUUID().hashCode(), "ERSTELLT", z);
            log.info("Try to persist ProzessSchrittZustandsAutomat " + prozessSchrittZustandsAutomat.toString());
            em.persist(prozessSchrittZustandsAutomat);
            erstelleAuftrag(psvListe);

        } else {
            log.info("Data already exists");
        }
    }

    private void erstelleAuftrag(List<ProzessSchrittVorlage> psvListe) {
        for (int i = 0; i < 1; i++) {
            //  Auftrag Setup
            AuftragsLog aLog = new AuftragsLog(LocalDateTime.now());
            aLog.setErstellt(LocalDateTime.now());
            em.persist(aLog);
            log.info("Try to persist AuftragsLog " + aLog.toString());
            Auftrag pk = new Auftrag(UUID.randomUUID().hashCode(), f.gameOfThrones().character(), AuftragsPrioritaet.HOCH, erstelePS(psvListe),
                    aLog, ProzessKettenZustandsAutomat.GESTARTET);

            // Erstelle neuen PSZA
            List<String> z = new ArrayList<>();
            for (String s :
                    pszaVorlage.getZustaende()) {
                z.add(s);
            }
            var automat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "AKZEPTIERT", z);
            em.persist(automat);

            // PS Setup
            List<ProzessSchrittLog> logs = new ArrayList<>();
            logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
            for (ProzessSchrittLog pSL :
                    logs) {
                log.info("Try to persist logs " + pSL.getGestartet().toString());
                em.persist(pSL);
            }
            // PS aufuellen
            log.info("Try to persist TEST ProzessKette " + pk.getPkID());
            em.persist(pk);
        }


    }

    private List<ProzessSchritt> erstelePS(List<ProzessSchrittVorlage> psvListe) {
        var r = new ArrayList<ProzessSchritt>();
        for (int i = 0; i < limit * 0.5; i++) {
            List<String> z = new ArrayList();
            for (String s :
                    pszaVorlage.getZustaende()) {
                z.add(s);
            }
            var a = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "Erstellt", new ArrayList<>());
            em.persist(a);
            var psv = psvListe.get(i);
            var psLogs = List.of(new ProzessSchrittLog(LocalDateTime.now(), "Gestartet"),
                    new ProzessSchrittLog(LocalDateTime.now(), "Veraendert"));
            for (ProzessSchrittLog psl :
                    psLogs) {
                em.persist(psl);
            }

            List<ProzessSchrittParameter> y= new ArrayList<>();
            List<ProzessSchrittParameter> prozessSchrittParameterList = new ArrayList<>();
            for (ProzessSchrittParameter psp :
                    psv.getProzessSchrittParameters()) {
                y.add(psp);
            }
            var ps = new ProzessSchritt(UUID.randomUUID().hashCode(), a, psv.getDauer(), y,
                    psv.getExperimentierStation(), "Test Atribut" + 1, psLogs, "PSV: " + i, true, f.random().nextInt(0, 9999));

            log.info("Try to persist TEST ProzessSchritt " + ps.getId());

            var transportAuftrag = new TransportAuftrag(LocalDateTime.now(), TransportAuftragZustand.ERSTELLT);
            em.persist(transportAuftrag);
            log.info("Persisting Transport Auftag " + transportAuftrag.getZustandsAutomat());
            ps.setTransportAuftrag(transportAuftrag);
            em.persist(ps);
            r.add(ps);

            // Weise den PS auch Stationen zu.
            // Wenn es einen akutellen Schritt gibt, dann werden die weiteren Schritte in Warteschlange eingreiht.
//            ExperimentierStation es;
//            try {
//                es = ps.getExperimentierStation();
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

        }
        return r;
    }

    /**
     * Erstelle Standorte
     *
     * @return erstelle Standorte
     */
    private List<Standort> createDefaulStandort() {

        standorte = new ArrayList<>();
        standorte.add(new Standort(UUID.randomUUID().hashCode(), "Lager"));
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
                    faker.lordOfTheRings().location(), ExperimentierStationZustand.VERFUEGBAR, new ArrayList<>(), List.of(tTechnologe)));
        }
        return experimentierStations;
    }

    /**
     * Erstelle Standart Nutzer
     * FIXME Password need to be in external Config
     *
     * @return A user for every RoleProzessSchrittZustandsAutomat.zustaende
     */
    private List<User> createDefaultUsers() {
        try {
            PasswordMatcher matcher = new PasswordMatcher();

            var uList = userService.getRoles();

            // User Setup
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

    public List<ProzessSchrittVorlage> getProzessSchrittVorlage(List<ProzessSchrittParameter> parameters) {
        var result = new ArrayList<ProzessSchrittVorlage>();
        for (int i = 0; i < limit; i++) {

            // ProzessSchrittVorlage Setup
            ProzessSchrittZustandsAutomatVorlage v = new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(),
                    psZustaende, "Test pszvav");
            em.persist(v);
            List z = new ArrayList();
            Collections.addAll(z, pszaVorlage.getZustaende());
            z.addAll(pszaVorlage.getZustaende());
            Faker faker = new Faker();
            result.add(new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), List.of(parameters.get(i)), experimentierStations.get(1), "42:00", faker.gameOfThrones().dragon(),
                    v, true, f.random().nextInt(0, 999)));
        }
        return result;
    }

    public List<QualitativeEigenschaft> getQualitativeEigenschaften() {
        // Eigenschaften
        Faker faker = new Faker();
        List<QualitativeEigenschaft> qualEigenschaften = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            QualitativeEigenschaft q1 = new QualitativeEigenschaft(UUID.randomUUID().hashCode(), faker.funnyName().name());
            qualEigenschaften.add(q1);
        }

        return qualEigenschaften;
    }

    public List<TraegerArt> erstelleTraeger() {
        return List.of(new TraegerArt("Glass"), new TraegerArt("Eingebetet"), new TraegerArt("Einzelen"));
    }

    public ProzessSchrittZustandsAutomatVorlage erstelleStandartVorlage() {
        Set<ProzessSchrittZustandsAutomatVorlage> ergebnis = new HashSet<>();
        List<String> zustaende = new ArrayList<>();
        zustaende.add("Angenommen");
        zustaende.add("In Brearbeitung");
        zustaende.add("Bearbeitet");
        zustaende.add("Weitergeleitet");

        sVorlage = new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(), zustaende, "Standart");
        return sVorlage;
    }

    /**
     * erstellt proben für einen prozessschritt
     *
     * @return a list of samples
     */
    public Traeger erstelleProben(Standort s) {
        List<Probe> r = new ArrayList<>();
        for(int i=0; i<limit; i++){
            Probe p1 = new Probe("FDGHJ"+i, 9, ProbenZustand.VORHANDEN, s);
            p1.setKommentar(erstelleKommentare());
            try {
                probeDAO.persist(p1);
            } catch (DuplicateProbeException e) {
                e.printStackTrace();
            }
            r.add(p1);
        }
        Traeger t = new Traeger(UUID.randomUUID().hashCode(), tas.get(0), r,standorte.get(1) );
        return t;
    }

    public List<Kommentar> erstelleKommentare() {
        List<Kommentar> r = new ArrayList<>();
        for (int i = 0; i < limit / 10; i++) {
            Kommentar k = new Kommentar(LocalDateTime.now(), "hallo" + i);
            try {
                kommentarDAO.persist(k);
            } catch (DuplicateKommentarException e) {
                e.printStackTrace();
            }
            r.add(k);
        }
        return r;
    }

}
