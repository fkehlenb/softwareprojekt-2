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

    @Test
    void testInit() {
        when(prozessSchrittDAO.getAll()).thenReturn(prozessSchritts);
        prozessSchrittService.init();
    }

    @Test
    void testGetSchritteByUser() {
        when(experimentierStationService.getESByUser(any())).thenReturn(experimentierStations);
        List<ProzessSchritt> result = prozessSchrittService.getSchritteByUser(user);
        Assertions.assertEquals(prozessSchritts, result);
    }

    // @Test Landa Expericon to see
    void testGetPotentialStepsByUser() {
        when(experimentierStationService.getESByUser(any())).thenReturn(experimentierStations);
        List<ProzessSchritt> result = prozessSchrittService.getPotentialStepsByUser(user);
        Assertions.assertEquals(prozessSchritts, result);
    }

    //@Test
    void testFindStation() {
        when(experimentierStationService.getAll()).thenReturn(experimentierStations);
        ExperimentierStation result = prozessSchrittService.findStation(prozessSchritt);
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    //@Test
    void testSetZustand() throws ProzessSchrittZustandsAutomatNotFoundException, ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException, DuplicateProzessSchrittLogException, ExperimentierStationNotFoundException {
        when(pslService.newLog(anyString())).thenReturn(prozessSchrittLog);
        prozessSchrittService.setZustand(prozessSchritt, "zustand");
        verify(prozessSchrittZustandsAutomatDAO).update(prozessSchrittZustandsAutomat);
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