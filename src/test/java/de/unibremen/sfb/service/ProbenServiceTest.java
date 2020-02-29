package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProbenServiceTest {
    @Mock
    List<Probe> proben;
    @Mock
    ProbeDAO probeDAO;
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    BedingungService bedingungService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @InjectMocks
    ProbenService probenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(qualitativeEigenschaftService.getEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(bedingungService.getBs()).thenReturn(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0)));

        probenService.init();
    }

    @Test
    void testGetProbenByEigenschaft() {
        List<Probe> result = probenService.getProbenByEigenschaft(new QualitativeEigenschaft(0, "name"));
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetProbenByStandort() {
        List<Probe> result = probenService.getProbenByStandort(new Standort(0, "ort"));
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetProbenByPredicate() {
        List<Probe> result = probenService.getProbenByPredicate(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetProbenByUser() {
        when(experimentierStationService.getESByUser(any())).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        List<Probe> result = probenService.getProbenByUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 9), "language"));
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }

    @Test
    void testAddProbenComment() {
        try {
            probenService.addProbenComment(new Probe("probenID", null, new Standort(0, "ort")), "c");
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEditProbenComment() {
        try {
            probenService.editProbenComment(new Probe("probenID", null, new Standort(0, "ort")), new Kommentar(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 9), "text"), "c");
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDeleteProbenComment() {
        try {
            probenService.deleteProbenComment(new Probe("probenID", null, new Standort(0, "ort")), new Kommentar(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 9), "text"));
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetProbeById() {
        try {
            when(probeDAO.getObjById(anyString())).thenReturn(new Probe("probenID", null, new Standort(0, "ort")));
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }

        Probe result = null;
        try {
            result = probenService.getProbeById("id");
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new Probe("probenID", null, new Standort(0, "ort")), result);
    }

    @Test
    void testKommentarToString() {
        String result = probenService.KommentarToString(new Probe("probenID", null, new Standort(0, "ort")));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetZustandForProbe() {
        try {
            probenService.setZustandForProbe(new Probe("probenID", null, new Standort(0, "ort")), ProbenZustand.KAPUTT);
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddNewSample() {
        try {
            probenService.addNewSample("id", new Kommentar(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 9), "text"), ProbenZustand.KAPUTT, new Standort(0, "ort"), Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")), new Traeger(0, new TraegerArt("art"), new Standort(0, "ort")));
        } catch (DuplicateProbeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetProbenTotalCount() {
        when(probeDAO.getProbenCount()).thenReturn(0);

        int result = probenService.getProbenTotalCount();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetProbenListe() {
        when(probeDAO.getProben(anyInt(), anyInt())).thenReturn(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))));

        List<Probe> result = probenService.getProbenListe(0, 0);
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetAllArchived() {
        when(probeDAO.getAllArchived()).thenReturn(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))));

        List<Probe> result = probenService.getAllArchived();
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }

    @Test
    void testPersist() {
        try {
            probenService.persist(new Probe("probenID", null, new Standort(0, "ort")));
        } catch (DuplicateProbeException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try {
            probenService.update(new Probe("probenID", null, new Standort(0, "ort")));
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemove() {
        try {
            probenService.remove(new Probe("probenID", null, new Standort(0, "ort")));
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAll() {
        when(probeDAO.getAll()).thenReturn(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))));

        List<Probe> result = probenService.getAll();
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", null, new Standort(0, "ort"))), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme