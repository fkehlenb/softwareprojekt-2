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
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Startup
@Singleton
@Transactional
@Slf4j
public class InitialDataFiller {
    int limit = 5;
    private  List<Standort>  standorte;
 

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        log.info("Storing inital Data");


        for (Standort s :
                createDefaulStandort()) {
            log.info("Trying to  persist Standort " + s.toString());
//            em.persist(s);

        }

        // Experimentierstaation
        for (ExperimentierStation s :
                createDefaultStation()) {
            log.info("Trying to persist Station " + s.getName());
//            em.persist(s);
        }

        // User
        for (User user :
                createDefaultUsers()) {
            log.info("Trying to persist User" + user.getNachname());
//            em.persist(user);
        }


    }


    private List<Standort> createDefaulStandort() {

        standorte = new ArrayList<>();

        for (int i = 0; i < limit; i++) {
            Standort s =  new Standort(UUID.randomUUID().hashCode(), "Station " + i) ;
            log.info("Persisiting Experimentierstation " + i);
            standorte.add(s);
        }
        return  standorte;
    }


    private Set<ExperimentierStation> createDefaultStation() {
        Set<ExperimentierStation> ergebnis = new HashSet<ExperimentierStation>();

        for (int i = 0; i < limit; i++) {
            Faker faker = new Faker();
            ergebnis.add(new ExperimentierStation(UUID.randomUUID().hashCode(), standorte.get(i),
                    faker.lordOfTheRings().location(), ExperimentierStationZustand.VERFUEGBAR, createDefaultUsers()));
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
}
