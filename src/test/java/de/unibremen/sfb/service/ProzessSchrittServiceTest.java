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
    @Mock
    ProzessSchrittLog prozessSchrittLog;
    @InjectMocks
    ProzessSchrittService prozessSchrittService;
    @Mock
    List<ProzessSchritt> prozessSchritts;
    @Mock
    ProzessSchritt prozessSchritt;
    @Mock
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;
    @Mock
    List<ExperimentierStation> experimentierStations;
    @Mock
    User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void testInit() {
//        when(prozessSchrittDAO.getAll()).thenReturn(prozessSchritts);
//        prozessSchrittService.init();
//    }
//
//
//
//    // @Test Landa Expericon to see
//    void testGetPotentialStepsByUser() {
//        when(experimentierStationService.getESByUser(any())).thenReturn(experimentierStations);
//        List<ProzessSchritt> result = prozessSchrittService.getPotentialStepsByUser(user);
//        Assertions.assertEquals(prozessSchritts, result);
//    }
//
//    //@Test
//    void testFindStation() {
//        when(experimentierStationService.getAll()).thenReturn(experimentierStations);
//        ExperimentierStation result = prozessSchrittService.findStation(prozessSchritt);
//        Assertions.assertEquals(new ExperimentierStation(), result);
//    }


    @Test
    void testGetAll() {
        when(prozessSchrittDAO.getAll()).thenReturn(prozessSchritts);
        List<ProzessSchritt> result = prozessSchrittService.getAll();
        Assertions.assertEquals(prozessSchritts, result);
    }

//    @Test
//    void testToJson() {
//        String result = prozessSchrittService.toJson();
//        Assertions.assertEquals("JSON IS Broken", result);
//    }
//
//    @Test
//    void testAdd() throws DuplicateProzessSchrittException {
//        prozessSchrittService.add(prozessSchritt);
//        verify(prozessSchrittDAO).persist(prozessSchritt);
//    }
}