package de.unibremen.sfb.controller;

import com.github.javafaker.Faker;
import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.model.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.*;

@Startup
@Singleton
@Slf4j
public class InitialDataFiller {

    List<User> users;

    @PersistenceContext(name = "sfb")
    private EntityManager em;

    @PostConstruct
    public void init() {



        log.info("Storing inital Data");

        // User
        for (User user :
                createDefaultUsers()) {
            em.persist(user);
            log.info("Trying to persist User" + user.getNachname());
        }


        for (Standort s :
                createDefaulStandort()) {
            em.persist(s);
            log.info("Trying to  persist Standort " + s.toString());
        }

        // Experimentierstaation
        for (ExperimentierStation s :
                createDefaultStation()) {
            em.persist(s);
            log.info("Trying to persist Station " + s.getName());
        }


    }


    private List<Standort> createDefaulStandort() {
        // FIXME Load from DB
        Standort s = new Standort(UUID.randomUUID().hashCode(),"Test Standort");
        List<Standort> ergebnis = new ArrayList<>();
        ergebnis.add(s);
        log.info("Setting up Default Standort");
        return ergebnis;
    }


    private Set<ExperimentierStation> createDefaultStation() {
        Set<ExperimentierStation> ergebnis = new HashSet<ExperimentierStation>();

        int limit = 5;
        List<Standort>  standorte = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            Standort s =  new Standort(UUID.randomUUID().hashCode(), "Station " + i) ;
            standorte.add(s);
            log.info("Persisiting Experimentierstation " + i);
            em.persist(s);
        }

        for (int i = 0; i < limit; i++) {
            Faker faker = new Faker();
            ergebnis.add(new ExperimentierStation(UUID.randomUUID().hashCode(), standorte.get(i),
                    faker.lordOfTheRings().location(), ExperimentierStationZustand.VERFUEGBAR, users));
        }
        return ergebnis;
    }

    private List<User> createDefaultUsers() {
        Faker f = new Faker(new Locale("de"));

        // User Setup
        List<Role> a = new ArrayList<>();
        a.add(Role.TECHNOLOGE);
        User testUser = new User(UUID.randomUUID().hashCode(), "Default", "Technologe", "l@g.c", f.phoneNumber().cellPhone(),
                "t,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");
        users = new ArrayList<>();

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
}
