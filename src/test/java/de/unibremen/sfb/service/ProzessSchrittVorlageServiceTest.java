package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
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

class ProzessSchrittVorlageServiceTest {
    @Mock
    List<ProzessSchrittVorlage> vorlagen;
    @Mock
    ProzessSchrittVorlageDAO psvDAO;
    @Mock
    AuftragService auftragService;
    @Mock
    Logger log;
    @InjectMocks
    ProzessSchrittVorlageService prozessSchrittVorlageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(psvDAO.getAll()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)));

        prozessSchrittVorlageService.init();
    }

    @Test
    void testGetProzessSchrittVorlagen() {
        when(psvDAO.getAll()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)));

        List<ProzessSchrittVorlage> result = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        Assertions.assertEquals(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)), result);
    }

    @Test
    void testPersist() {
        prozessSchrittVorlageService.persist(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null));
    }

    @Test
    void testByID() {
        try {
            when(psvDAO.getObjById(anyInt())).thenReturn(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }

        ProzessSchrittVorlage result = null;
        try {
            result = prozessSchrittVorlageService.ByID(0);
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), result);
    }

    @Test
    void testEdit() {
        try {
            prozessSchrittVorlageService.edit(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDelete() {
        prozessSchrittVorlageService.delete(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)));
    }

    @Test
    void testGetByID() {
        try {
            when(psvDAO.getObjById(anyInt())).thenReturn(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }

        ProzessSchrittVorlage result = null;
        try {
            result = prozessSchrittVorlageService.getByID(0);
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), result);
    }

    @Test
    void testDarftBearbeiten() {
        when(auftragService.getAuftrage()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        List<ProzessSchritt> result = prozessSchrittVorlageService.darftBearbeiten();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 32), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))), result);
    }

    @Test
    void testAkzeptiertePSV() {
        when(auftragService.getAuftrage()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        List<ProzessSchrittVorlage> result = prozessSchrittVorlageService.akzeptiertePSV();
        Assertions.assertEquals(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)), result);
    }

    @Test
    void testSetVorlagen() {
        prozessSchrittVorlageService.setVorlagen(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)));
    }

    @Test
    void testSetPsvDAO() {
        prozessSchrittVorlageService.setPsvDAO(new ProzessSchrittVorlageDAO());
    }

    @Test
    void testSetAuftragService() {
        prozessSchrittVorlageService.setAuftragService(new AuftragService());
    }

    @Test
    void testEquals() {
        boolean result = prozessSchrittVorlageService.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = prozessSchrittVorlageService.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = prozessSchrittVorlageService.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = prozessSchrittVorlageService.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}