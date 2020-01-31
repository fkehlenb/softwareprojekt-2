package de.unibremen.sfb.persistence;

import com.github.javafaker.Faker;
import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.model.*;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

class ObjectDAOCreationTest
{

    @Inject
    private StandortDAO standortDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private ExperimentierStationDAO experimentierStationDAO;

    @Inject
    private User testUser;

    public void createMockDate() {
        // Standort

        createDefaulStandort();


        // Experimentierstaation
        for (ExperimentierStation s:
             createDefaultStation()) {
            try {
                experimentierStationDAO.persist(s);
            } catch (DuplicateExperimentierStationException e) {
                e.printStackTrace();
            }
        }



    }

    private Set<Standort> createDefaulStandort() {
        // FIXME Load from DB
        Standort s = new Standort("Test Standort");
        Set<Standort> ergebnis = new HashSet<>();
        try {
            standortDAO.persist(s);
        } catch (DuplicateStandortException e) {
            e.printStackTrace();
        }
        ergebnis.add(s);

        return ergebnis;
    }

    private Set<ExperimentierStation> createDefaultStation() {
        Set<ExperimentierStation> ergebnis = new HashSet<ExperimentierStation>();

        for (int i = 0; i < 20; i++) {
            Faker faker = new Faker();
            ergebnis.add(new ExperimentierStation(faker.random().nextInt(0, 500), new Standort(faker.lordOfTheRings().location()),
                    faker.lordOfTheRings().character(), ExperimentierStationZustand.VERFUEGBAR, createDefaultUsers()));
        }
        return ergebnis;
    }

    private Set<User> createDefaultUsers() {
        // User Setup
        Set<Role> a = new HashSet<>();
        testUser = new User(0, "Default", "Loser", "l@g.c", "110",
                "kev,", "12345678".getBytes(), true, LocalDateTime.now(),
                a, new ArrayList<Auftrag>(), "DEUTSCH");
        Set users = new HashSet<>();
        try {
            userDAO.persist(testUser);
        } catch (DuplicateUserException e) {
            e.printStackTrace();
        }
        users.add(testUser);
        return users;
    }

}