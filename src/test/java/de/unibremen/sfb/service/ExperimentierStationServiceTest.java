package de.unibremen.sfb.service;

import de.unibremen.sfb.controller.InitialDataFiller;
import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


public class ExperimentierStationServiceTest {
   private List<ExperimentierStation> stationen;
    private ExperimentierStation es;

    @Inject
    ExperimentierStationService experimentierStationService;

    @Inject
    StandortService standortService;

    @Inject
    UserService userService;

    void addES() {
        var s = new Standort(UUID.randomUUID().hashCode(), "Hallo Test");
        standortService.addStandort(s);

        List<User> users = userService.getUsers();
        es = new ExperimentierStation(UUID.randomUUID().hashCode(), s, "Test Station" ,
                ExperimentierStationZustand.VERFUEGBAR, new ArrayList<ProzessSchritt>(), users);

        try {
            experimentierStationService.addES(es);
        } catch (DuplicateExperimentierStationException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void serviceCheck() {
        assertNotNull(userService);
        assertNotNull(experimentierStationService);
    }

    void loescheES() {
        try {
            experimentierStationService.loescheES(es);
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findByName() throws ExperimentierStationNotFoundException {
        assertEquals(experimentierStationService.getStationByName(es.getName()), es);
    }

    @Test
    void cycle() {
        addES();
        loescheES();
    }


    @Test
    void getById() throws ExperimentierStationNotFoundException {
        assertEquals(experimentierStationService.getById(es.getEsID()), es);

    }

    @Test
    void getAll() {
        experimentierStationService.getAll();
    }
}