package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ExperimentierStationServiceTest {
    @Mock
    List<ExperimentierStation> esSet;
    @Mock
    User user;
    @Mock
    ExperimentierStationDAO esDao;
    @Mock
    ProzessSchritt prozessSchritt;
    @Mock
    ExperimentierStation experimentierStation;
    @Mock
    List<ExperimentierStation> experimentierStations;
    @Mock
    ExperimentierStationDAO experimentierStationDAO;
    @InjectMocks
    ExperimentierStationService experimentierStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(esDao.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        experimentierStationService.init();
    }

    @Test
    void testGetESListe() {
        when(esDao.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        List<ExperimentierStation> result = experimentierStationService.getESListe();
        Assertions.assertEquals(Arrays.<ExperimentierStation>asList(new ExperimentierStation()), result);
    }

    @Test
    void testAddES() {
        try {
            experimentierStationService.addES(new ExperimentierStation());
        } catch (DuplicateExperimentierStationException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoescheES() {
        try {
            experimentierStationService.loescheES(new ExperimentierStation());
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindByName() {
        when(esDao.getAll()).thenReturn(experimentierStations);
        ExperimentierStation result = experimentierStationService.findByName("anyString()");
        verify(esDao).getAll();
    }

    @Test
    void testGetStationByName() {
        try {
            when(esDao.getByName(anyString())).thenReturn(new ExperimentierStation());
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }

        ExperimentierStation result = null;
        try {
            result = experimentierStationService.getStationByName("name");
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    @Test
    void testSetZustand() {
        try {
            experimentierStationService.setZustand(new ExperimentierStation(), ExperimentierStationZustand.VERFUEGBAR);
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    void testGetAll() {
        when(esDao.getAll()).thenReturn(experimentierStations);

        List<ExperimentierStation> result = experimentierStationService.getAll();
        Assertions.assertEquals(experimentierStations, result);
    }

    @Test
    void testUpdateES() {
        try {
            experimentierStationService.updateES(new ExperimentierStation());
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }
    }

    //@Test To See Landa Ausdrück
    void testGetESByUser() {

        List<ExperimentierStation> result = experimentierStationService.getESByUser(user);
        verify(experimentierStationDAO, atLeastOnce()).getAll();
    }

    //@Test Landa Ausdruck
    void testGetAllESByBedingung() {
        when(esDao.getAll()).thenReturn(experimentierStations);
        List<ExperimentierStation> result = experimentierStationService.getAllESByBedingung(null);
        Assertions.assertEquals(Arrays.<ExperimentierStation>asList(new ExperimentierStation()), result);
    }

    //@Test
//    void testSetCurrentPS() {
//        try {
//            experimentierStationService.setCurrentPS(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 52), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))), new ExperimentierStation());
//        } catch (ExperimentierStationNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    //@Test
    void testDeleteCurrent() throws ExperimentierStationNotFoundException {

        experimentierStationService.deleteCurrent(prozessSchritt, experimentierStation);
        verify(esDao).remove(experimentierStation);
    }
}