package de.unibremen.sfb.service;

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
    Logger log;
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
        when(auftrag.getVorlage()).thenReturn(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))));

        ProzessKettenVorlage result = auftragService.getPKV();
        Assertions.assertEquals(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))), result);
    }

    @Test
    void testGetLog() {
        when(auftrag.getLog()).thenReturn(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9)));

        AuftragsLog result = auftragService.getLog();
        Assertions.assertEquals(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9)), result);
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
        when(auftrag.getProzessSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))));

        List<ProzessSchritt> result = auftragService.getPS();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))), result);
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
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
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
    void  testSetAuftragsZustand() {
        try {
            auftragService.setAuftragsZustand(new Auftrag(), null);
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTransportSchritt() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));
        when(auftrag.getProzessSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))));

        List<ProzessSchritt> result = auftragService.getTransportSchritt();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 9), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))), result);
    }

    @Test
    void testErstelleAuftrag() {
        when(auftrag.getPkID()).thenReturn(0);

        int result = auftragService.erstelleAuftrag(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))), AuftragsPrioritaet.KEINE);
        Assertions.assertEquals(0, result);
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

        boolean result = auftragService.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = auftragService.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        int result = auftragService.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        when(auftragDAO.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        String result = auftragService.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}