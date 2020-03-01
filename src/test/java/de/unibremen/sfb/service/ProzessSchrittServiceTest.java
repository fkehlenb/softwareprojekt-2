package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittServiceTest {
    @Mock
    List<ProzessSchritt> psListe;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    ProzessSchrittDAO prozessSchrittDAO;
    @Mock
    ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;
    @Mock
    ProzessSchrittLogService pslService;
    @Mock
    AuftragService auftragService;
    @Mock
    Logger log;
    @InjectMocks
    ProzessSchrittService prozessSchrittService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(prozessSchrittDAO.getAll()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), null, null)));

        prozessSchrittService.init();
    }

    @Test
    void testGetSchritteByUser() {
        when(experimentierStationService.getESByUser(any())).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        List<ProzessSchritt> result = prozessSchrittService.getSchritteByUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 28), "language"));
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), null, null)), result);
    }

    @Test
    void testGetPotentialStepsByUser() {
        when(experimentierStationService.getESByUser(any())).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        List<ProzessSchritt> result = prozessSchrittService.getPotentialStepsByUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 28), "language"));
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), null, null)), result);
    }

    @Test
    void testFindStation() {
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        ExperimentierStation result = prozessSchrittService.findStation(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))));
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    @Test
    void testSetZustand() {
        try {
            when(pslService.newLog(anyString())).thenReturn(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 28), "zustandsAutomat"));
        } catch (DuplicateProzessSchrittLogException e) {
            e.printStackTrace();
        }

        try {
            prozessSchrittService.setZustand(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))), "zustand");
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
        } catch (ProzessSchrittLogNotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateProzessSchrittLogException e) {
            e.printStackTrace();
        } catch (ProzessSchrittZustandsAutomatNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAuftrag() {
        when(auftragService.getAuftrage()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        Auftrag result = prozessSchrittService.getAuftrag(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))));
        Assertions.assertEquals(new Auftrag(), result);
    }

    @Test
    void testGetAll() {
        when(prozessSchrittDAO.getAll()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), null, null)));

        List<ProzessSchritt> result = prozessSchrittService.getAll();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), null, null)), result);
    }

    @Test
    void testToJson() {
        String result = prozessSchrittService.toJson();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testAdd() {
        try {
            prozessSchrittService.add(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(null), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))));
        } catch (DuplicateProzessSchrittException e) {
            e.printStackTrace();
        }
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme