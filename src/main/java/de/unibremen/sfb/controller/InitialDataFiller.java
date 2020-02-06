package de.unibremen.sfb.controller;

import com.github.javafaker.Faker;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.StandortDAO;
import de.unibremen.sfb.persistence.UserDAO;
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

/**
 * This Class is run when the Server is deployed. Its purpose is to pupulate all web Views
 * At the same time this works as a integration Test.
 *
 * If there are persistence Problems they will be noticed here
 */
@Startup
@Singleton
@Transactional
@Slf4j
public class InitialDataFiller {
    int limit = 5;
    private  List<Standort>  standorte;
    private ProzessSchritt ps;
    private Auftrag pk;
    private ProzessKettenVorlage pkv;

    @Inject
    UserDAO userDAO;

    @Inject
    StandortDAO standortDAO;

    @Inject
    ZustandsService zustandsService;

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


        } else {
            log.info("Data already exists");
        }

    }

    /**
     * Erstelle Standorte
     * @return erstelle Standorte
     */
    private List<Standort> createDefaulStandort() {

        standorte = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            Standort s =  new Standort(UUID.randomUUID().hashCode(), "Station " + i) ;
            log.info("Persisiting Experimentierstation " + i);
            standorte.add(s);
        }


        return  standorte;
    }

    /**
     * Erstelle Experimentierstationen
     * Depends on createDefaultUser
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
     * @return A user for every Role
     */
    private List<User> createDefaultUsers() {
        Faker f = new Faker(new Locale("de"));

        // User Setup
        List<Role> a = new ArrayList<>();
        a.add(Role.TECHNOLOGE);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Technologe", "l@g.c", f.phoneNumber().cellPhone(),
                "t,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");
        List users = new ArrayList<>();

        // Add to user Lost
        users.add(testUser);


        a.clear();
        a.add(Role.PKADMIN);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "PKAdmin", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");
        // Add to user Lost

        users.add(testUser);

        a.clear();
        a.add(Role.TRANSPORT);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Transport", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");

        // Add to user Lost
        users.add(testUser);

        a.clear();
        a.add(Role.LOGISTIKER);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Logistik", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");

        // Add to user Lost
        users.add(testUser);

        a.clear();
        a.add(Role.ADMIN);
        testUser = new User(UUID.randomUUID().hashCode(), "Default", "Logistik", "l@g.c", f.phoneNumber().cellPhone(),
                "pk,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");
        // Add to user Lost
        users.add(testUser);


        return users;
    }


    private void setUpAuftrag() {
        List<QualitativeEigenschaft> e = getQualitativeEigenschaften();

        // PS Parameter
        List<ProzessSchrittParameter> parameters = new ArrayList<>();
        parameters.add(new ProzessSchrittParameter(UUID.randomUUID().hashCode(), "Getestet", e));

        // Erstelle die Liste aus den Parametern
        List<ProzessSchrittVorlage> psListe = getProzessSchrittVorlages(parameters);
        pkv = new ProzessKettenVorlage(99, psListe, testUser);

        // Auftrag Setup
        AuftragsLog aLog = new AuftragsLog(LocalDateTime.now());
        aLog.setErstellt(LocalDateTime.now()); ;
        pk = new Auftrag(420, pkv, AuftragsPrioritaet.HOCH, new ArrayList<ProzessSchritt>(), aLog , ProzessKettenZustandsAutomat.INSTANZIIERT);


        // PSAV Setup
        ProzessSchrittZustandsAutomatVorlage psza = new ProzessSchrittZustandsAutomatVorlage(zustandsService.getPsZustaende(),
                zustandsService.getPsZustaende().get(0));

        // PS Setup
        ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat("ERSTELLT", psza);
        HashSet<ProzessSchrittLog> logs = new HashSet<>();
        logs.add(new ProzessSchrittLog(LocalDateTime.now(), "INSTANZIERT"));
        ps = new ProzessSchritt(42, prozessSchrittZustandsAutomat, logs , getProzessSchrittVorlage(parameters));

        // PS aufuellen
        pk.getProzessSchritte().add(ps);
    }

    private List<ProzessSchrittVorlage> getProzessSchrittVorlages(List<ProzessSchrittParameter> parameters) {
        // PSVA
        List<ProzessSchrittVorlage> psListe = new ArrayList<>();
        psListe.add(getProzessSchrittVorlage(parameters));
        return psListe;
    }

    private ProzessSchrittVorlage getProzessSchrittVorlage(List<ProzessSchrittParameter> parameters) {
        // ProzessSchrittVorlage Setup
        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(99, Duration.ofMinutes(42),
                "Ermittlend", experimentierStations, new ProzessSchrittZustandsAutomatVorlage(),parameters);
        return  psv;
    }

    private List<QualitativeEigenschaft> getQualitativeEigenschaften() {
        // Eigenschaften
        qualEigenschaften = new ArrayList<>();
        qualEigenschaften.add(new QualitativeEigenschaft("Test Eigenschaft"));
        return  qualEigenschaften;
    }
}
