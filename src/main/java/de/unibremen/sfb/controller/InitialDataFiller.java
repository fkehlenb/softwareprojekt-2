package de.unibremen.sfb.controller;

import com.github.javafaker.Faker;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.StandortDAO;
import de.unibremen.sfb.persistence.UserDAO;
import de.unibremen.sfb.service.TraegerArtService;
import de.unibremen.sfb.service.ZustandsService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.Duration;
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
    int limit = 5;
    private List<Standort> standorte;
    private ProzessSchritt ps;
    private Auftrag pk;
    private ProzessKettenVorlage pkv;
    private List<QualitativeEigenschaft> qualitativeEigenschaftList;

    @Inject
    private UserDAO userDAO;

    @Inject
    private StandortDAO standortDAO;

    @Inject
    private ZustandsService zustandsService;

    @Inject
    private AuftragDAO auftragDAO;

    @Inject
    private TraegerArtService traegerArtService;


    @PersistenceContext
    private EntityManager em;

    private User testUser;
    private ArrayList<QualitativeEigenschaft> qualEigenschaften;
    private ArrayList<ExperimentierStation> experimentierStations;

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

            // Standort, requires Users
            for (Standort s :
                    createDefaulStandort()) {
                log.info("Trying to  persist Standort " + s.toString());
                em.persist(s);
            }


            // Experimentierstaation, requires Standort
            for (ExperimentierStation s :
                    createDefaultStation()) {
                log.info("Trying to persist Station " + s.getName());
                em.persist(s);
            }
            // Experimentierstaation, requires Standort
            for (ExperimentierStation s :
                    createDefaultStation()) {
                log.info("Trying to persist Station " + s.getName());
                em.persist(s);
            }
            /**
             * persis von QualitativeEigenschaft durch die Variebln qualitativeEigenschaftList
             * */
            qualitativeEigenschaftList = getQualitativeEigenschaften();
            for (QualitativeEigenschaft qE : qualitativeEigenschaftList
            ) {
                log.info("Trying to Persist Qualitaet Eingenschaft " + qE.getName());
                em.persist(qE);
            }
            // PS Parameter
            List<ProzessSchrittParameter> parameters = new ArrayList<>();
            parameters.add(new ProzessSchrittParameter(UUID.randomUUID().hashCode(), "Getestet", qualitativeEigenschaftList));

            for (ProzessSchrittParameter psp : parameters
            ) {
                log.info("Trying ti persist ProzessSchrittParameter " + psp.getName());
                em.persist(psp);
            }

            // Erstelle Test Bedingung
            Bedingung b = new Bedingung(UUID.randomUUID().hashCode(), "Test Bedingung" ,parameters,44);
            log.info("Trying to persist Bedingung: " + b.toString());
            em.persist(b);

            // Erstelle die Liste aus den Parametern
            List<ProzessSchrittVorlage> psListe = getProzessSchrittVorlages(parameters);
            for (ProzessSchrittVorlage pSV :
                    psListe) {
                log.info("Trying to persist ProzessSchrittVorlage " + pSV.toString());
                em.persist(pSV);
            }
            pkv = new ProzessKettenVorlage(99, psListe);
            log.info("Try to persist ProzessSchrittVorlage " + pkv.getPkID());
            em.persist(pkv);

            // Auftrag Setup
            AuftragsLog aLog = new AuftragsLog(LocalDateTime.now());
            aLog.setErstellt(LocalDateTime.now());
            em.persist(aLog);
            log.info("Try to persist AuftragsLog " + aLog.toString());
            pk = new Auftrag(420, pkv, AuftragsPrioritaet.HOCH, new ArrayList<ProzessSchritt>(), aLog, ProzessKettenZustandsAutomat.INSTANZIIERT);


            // PSAV Setup
            ProzessSchrittZustandsAutomatVorlage psza = new ProzessSchrittZustandsAutomatVorlage(zustandsService.getPsZustaende(),
                    zustandsService.getPsZustaende().get(0));
            log.info("Try to persist ProzessSchrittZustandsAutomatVorlage " + psza.toString());
            em.persist(psza);
            // PS Setup
            ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat("ERSTELLT", psza);
            log.info("Try to persist ProzessSchrittZustandsAutomat " + prozessSchrittZustandsAutomat.toString());
            em.persist(prozessSchrittZustandsAutomat);

            ArrayList<ProzessSchrittLog> logs = new ArrayList<>();
            logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
            for (ProzessSchrittLog pSL :
                    logs) {
                log.info("Try to persist TESTlogs " + pSL.getGestartet().toString());
                em.persist(pSL);
            }


            ps = new ProzessSchritt(42, prozessSchrittZustandsAutomat, logs, psListe.get(0));
            var psLogs = List.of(new ProzessSchrittLog(LocalDateTime.now(), "Gestartet"),
                    new ProzessSchrittLog(LocalDateTime.now(), "Veraendert") );
            for (ProzessSchrittLog psl :
                    psLogs) {
                em.persist(psl);
            }
            ps.setProzessSchrittLog(psLogs);
            log.info("Try to persist TEST ProzessSchritt " + ps.getPsID());
            em.persist(ps);


            // Persistiere Traeger Arten
            for (TraegerArt t :
                    traegerArtService.getVerTraeger()) {
                em.persist(t);
            }

            // PS aufuellen
            pk.getProzessSchritte().add(ps);
            log.info("Try to persist TEST ProzessKette " + pk.getPkID());
            em.persist(pk);


        } else {
            log.info("Data already exists");
        }

    }

    /**
     * Erstelle Standorte
     *
     * @return erstelle Standorte
     */
    private List<Standort> createDefaulStandort() {

        standorte = new ArrayList<>();

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
     * @return
     */
    private List<ExperimentierStation> createDefaultStation() {
        experimentierStations = new ArrayList<ExperimentierStation>();
        List<User> users = new ArrayList();
        users.add(testUser);

        for (int i = 0; i < limit; i++) {
            Faker faker = new Faker();
            experimentierStations.add(new ExperimentierStation(UUID.randomUUID().hashCode(), standorte.get(i),
                    faker.lordOfTheRings().location(), ExperimentierStationZustand.VERFUEGBAR, users));
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
        Faker f = new Faker(new Locale("de"));
        PasswordMatcher matcher = new PasswordMatcher();

        // User Setup
        List<Role> a = new ArrayList<>();
        a.add(Role.TECHNOLOGE);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Technologe", "l@g.c", f.phoneNumber().cellPhone(),
                "t,", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                a, "DEUTSCH");
        List users = new ArrayList<>();

        // Add to user Lost
        users.add(testUser);


        a.clear();
        a.add(Role.PKADMIN);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "PKAdmin", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                a, "DEUTSCH");
        // Add to user Lost

        users.add(testUser);

        a.clear();
        a.add(Role.TRANSPORT);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Transport", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                a, "DEUTSCH");

        // Add to user Lost
        users.add(testUser);

        a.clear();
        a.add(Role.LOGISTIKER);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Logistik", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                a, "DEUTSCH");

        // Add to user Lost
        users.add(testUser);

        a.clear();
        a.add(Role.ADMIN);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Logistik", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", matcher.getPasswordService().encryptPassword("12345678"), true, LocalDateTime.now(),
                a, "DEUTSCH");
        // Add to user Lost
        users.add(testUser);


        return users;
    }


    private void setUpAuftrag() {


    }

    public List<ProzessSchrittVorlage> getProzessSchrittVorlages(List<ProzessSchrittParameter> parameters) {
        // PSVA
        List<ProzessSchrittVorlage> psListe = new ArrayList<>();
        psListe.add(getProzessSchrittVorlage(parameters));
        return psListe;
    }

    public ProzessSchrittVorlage getProzessSchrittVorlage(List<ProzessSchrittParameter> parameters) {
        // ProzessSchrittVorlage Setup
        ProzessSchrittZustandsAutomatVorlage v = new ProzessSchrittZustandsAutomatVorlage(
                zustandsService.getPsZustaende(), "Test pszvav");
        em.persist(v);
        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(99, "42",
                "Ermittlend", experimentierStations, new ArrayList<>());
        return psv;
    }

    public List<QualitativeEigenschaft> getQualitativeEigenschaften() {
        // Eigenschaften
        qualEigenschaften = new ArrayList<>();
        QualitativeEigenschaft q1 = new QualitativeEigenschaft(UUID.randomUUID().hashCode(), "Test Eigenschaft");
        QualitativeEigenschaft q2 = new QualitativeEigenschaft(UUID.randomUUID().hashCode(), "2te Eigenschaft");
        qualEigenschaften.add(q1);
        qualEigenschaften.add(q2);
        return qualEigenschaften;
    }
}
