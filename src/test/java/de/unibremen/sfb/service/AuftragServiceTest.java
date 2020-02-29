package de.unibremen.sfb.service;

import de.unibremen.sfb.controller.InitialDataFiller;
import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AuftragServiceTest {
    @Mock
    List<Auftrag> auftrage;
    @Mock
    AuftragDAO auftragDAO;
    @Mock
    ProbenService probenService;
    @Mock
    ProzessKettenVorlageService prozessKettenVorlageService;
    @Mock
    AuftragsLogsService auftragsLogsService;
    @Mock
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;
    @Mock
    ProzessSchrittLogService prozessSchrittLogService;
    @Mock
    ProzessSchrittDAO prozessSchrittDAO;
    @Mock
    Auftrag auftrag;
    @Mock
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;
    @Mock
    Logger log;
    @Mock
    ProzessSchrittLog prozessSchrittLog;
    @Mock
    ProzessKettenVorlage prozessKettenVorlage;
    @InjectMocks
    AuftragService auftragService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetID() {
        when(auftrag.getPkID()).thenReturn(0);

        int result = auftragService.getID();
        Assertions.assertEquals(0, result);

    }

    @Test
    void testGetPKV() {
        when(auftrag.getVorlage()).thenReturn(prozessKettenVorlage);

        ProzessKettenVorlage result = auftragService.getPKV();
        Assertions.assertEquals(prozessKettenVorlage, result);
    }



    @Test
    void testSetLog() {
        auftragService.setLog(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9)));
    }

    @Test
    void testGetPKZ() {
        when(auftrag.getProzessKettenZustandsAutomat()).thenReturn(null);

        Enum<ProzessKettenZustandsAutomat> result = auftragService.getPKZ();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testSetPrio() {
        auftragService.setPrio(AuftragsPrioritaet.KEINE);
    }

    @Test
    void testGetPS() {
        when(auftrag.getProzessSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(prozessSchrittLog), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")),
                prozessSchrittZustandsAutomat )));

        List<ProzessSchritt> result = auftragService.getPS();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(prozessSchrittLog), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")),
                prozessSchrittZustandsAutomat)).toString(), result.toString());
    }

    @Test
    void testZustandswechsel() {
        try {
            auftragService.zustandswechsel(new Auftrag(), ProzessKettenZustandsAutomat.INSTANZIIERT);
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInit() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        auftragService.init();
    }

    @Test
    void testGetAuftrage() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        List<Auftrag> result = auftragService.getAuftrage();
        Assertions.assertEquals(Arrays.<Auftrag>asList(new Auftrag()), result);
    }

    @Test
    void testUpdate() {
        try {
            auftragService.update(new Auftrag());
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAdd() {
        try {
            auftragService.add(new Auftrag());
        } catch (DuplicateAuftragException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEdit() {
        when(auftrag.getPkID()).thenReturn(0);

        try {
            auftragService.edit(new Auftrag());
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDelete() {
        try {
            auftragService.delete(Arrays.<Auftrag>asList(new Auftrag()));
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testToJson() {
        String result = auftragService.toJson();
        Assertions.assertEquals("NO JSON", result);
    }

    @Test
    void testGetAuftrag() {
        try {
            when(auftragDAO.getObjById(anyInt())).thenReturn(new Auftrag());
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }

        Auftrag result = null;
        try {
            result = auftragService.getAuftrag(0);
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new Auftrag(), result);
    }


    @Test
    void testAssignToAuftrag() {
        try {
            auftragService.assignToAuftrag(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9), "language"), new Auftrag());
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }

    //@Test Methode probenZuweisen() ist auskommentiert.
    //TODO
    void testProbenZuweisen() {
        Auftrag result = auftragService.probenZuweisen(new Auftrag());
        Assertions.assertEquals(new Auftrag(), result);
    }


   // @Test
    void testErstelleAuftrag() {
        when(auftrag.getPkID()).thenReturn(0);
        Auftrag result = auftragService.erstelleAuftrag(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))), AuftragsPrioritaet.KEINE);
        Assertions.assertEquals(any(), result);
    }

    @Test
    void testSetAuftrage() {
        auftragService.setAuftrage(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetAuftragDAO() {
        auftragService.setAuftragDAO(new AuftragDAO());
    }

    @Test
    void testSetProbenService() {
        auftragService.setProbenService(new ProbenService());
    }

    @Test
    void testSetProzessKettenVorlageService() {
        auftragService.setProzessKettenVorlageService(new ProzessKettenVorlageService());
    }

    @Test
    void testSetAuftragsLogsService() {
        auftragService.setAuftragsLogsService(new AuftragsLogsService());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatService() {
        auftragService.setProzessSchrittZustandsAutomatService(new ProzessSchrittZustandsAutomatService());
    }

    @Test
    void testSetProzessSchrittLogService() {
        auftragService.setProzessSchrittLogService(new ProzessSchrittLogService());
    }

    @Test
    void testSetProzessSchrittDAO() {
        auftragService.setProzessSchrittDAO(new ProzessSchrittDAO());
    }

    @Test
    void testSetAuftrag() {
        auftragService.setAuftrag(new Auftrag());
    }

    @Test
    void testEquals() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        boolean result = auftragService.equals(auftragService);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = auftragService.canEqual(auftragService);
        Assertions.assertEquals(true, result);
    }

    @Test
    void testToString() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        String result = auftragService.toString();
        Assertions.assertEquals("AuftragService(auftrage=[Auftrag: 0], auftragDAO=auftragDAO, probenService=probenService, prozessKettenVorlageService=prozessKettenVorlageService, auftragsLogsService=auftragsLogsService, prozessSchrittZustandsAutomatService=prozessSchrittZustandsAutomatService, prozessSchrittLogService=prozessSchrittLogService, prozessSchrittDAO=prozessSchrittDAO, auftrag=auftrag)", result);
    }
}