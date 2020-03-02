package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateKommentarException;
import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.KommentarNotFoundException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.KommentarDAO;
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
    Probe probe;
    @Mock
    List<Probe> probes;
    @Mock
    Kommentar kom;
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    BedingungService bedingungService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    QualitativeEigenschaft qualitativeEigenschaft;
    @Mock
    List<QualitativeEigenschaft> qualitativeEigenschafts;
    @Mock
    List<ProzessSchrittParameter> prozessSchrittParameters;
    @Mock
    List<Bedingung> bedingungs;
    @Mock
    List<ExperimentierStation> experimentierStations;
    @Mock
    Standort standort;
    @Mock
    User user;
    @Mock
    KommentarDAO kommentarDAO;
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;


    @InjectMocks
    ProbenService probenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(qualitativeEigenschaftService.getEigenschaften()).thenReturn(qualitativeEigenschafts);
        when(bedingungService.getBs()).thenReturn(bedingungs);
        when(prozessSchrittParameterService.getParameterList()).thenReturn(prozessSchrittParameters);
        probenService.init();
    }


    //@Test To see
    void testGetProbenByStandort() {
        List<Probe> result = probenService.getProbenByStandort(standort);
        Assertions.assertEquals(proben, result);
    }

    //@Test To See
    void testGetProbenByPredicate() {
        List<Probe> result = probenService.getProbenByPredicate(bedingungs.get(0));
        Assertions.assertEquals(proben, result);
    }

    //@Test
//    void testGetProbenByUser() {
//        when(experimentierStationService.getESByUser(user)).thenReturn(experimentierStations);
//        when(probenService.getProbenByUser(user)).thenReturn(probes);
//        List<Probe> result = probenService.getProbenByUser(user);
//        Assertions.assertEquals(probes, result);
//    }

    @Test
    void testAddProbenComment() throws ProbeNotFoundException, DuplicateKommentarException {
        probenService.addProbenComment(probe, "c");
        verify(probeDAO).update(probe);
    }

    @Test
    void testEditProbenComment() throws ProbeNotFoundException, KommentarNotFoundException {

        probenService.editProbenComment(probe, kom, "hola");
        verify(probeDAO).update(probe);
    }

    @Test
    void testDeleteProbenComment() throws ProbeNotFoundException, KommentarNotFoundException {
        probenService.deleteProbenComment(probe, kom);
        verify(kommentarDAO).remove(kom);
        verify(probeDAO).update(probe);
    }

    @Test
    void testGetProbeById() throws ProbeNotFoundException {
        when(probeDAO.getObjById(anyString())).thenReturn(probe);
        Probe result = null;
        result = probenService.getProbeById("id");
        verify(probeDAO).getObjById("id");

    }

    @Test
    void testKommentarToString() {
        String result = probenService.KommentarToString(probe);
        Assertions.assertEquals("", result);
    }

    @Test
    void testSetZustandForProbe() throws ProbeNotFoundException {
           // probenService.setZustandForProbe(probe);
    }

    //@Test
//    void testAddNewSample() throws DuplicateProbeException {
//
//            probenService.addNewSample("id", new Kommentar(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 9), "text"),
//                    ProbenZustand.KAPUTT,
//                    new Standort(0, "ort"),
//                    prozessSchrittParameters,
//                    new Traeger(0, new TraegerArt("art"),
//                            new Standort(0, "ort")));
//
//    }

    @Test
    void testGetProbenTotalCount() {
        when(probeDAO.getProbenCount()).thenReturn(0);

        int result = probenService.getProbenTotalCount();
        Assertions.assertEquals(0, result);
    }

   //@Test
    void testGetProbenListe() {
        when(probeDAO.getProben(anyInt(), anyInt())).thenReturn(Arrays.<Probe>asList(probe));
        List<Probe> result = probenService.getProbenListe(0, 0);
        Assertions.assertEquals(Arrays.<Probe>asList(probe), result);
    }

    @Test
    void testGetAllArchived() {
        when(probeDAO.getAllArchived()).thenReturn(proben);
        List<Probe> result = probenService.getAllArchived();
        Assertions.assertEquals(proben, result);
    }

    @Test
    void testPersist() throws DuplicateProbeException {
        probenService.persist(proben.get(0));
        verify(probeDAO).persist(proben.get(0));
    }

    @Test
    void testUpdate() throws ProbeNotFoundException {
        probenService.update(proben.get(0));
        verify(probeDAO).update(proben.get(0));

    }

    @Test
    void testRemove() throws ProbeNotFoundException {
        probenService.remove(proben.get(0));
        verify(probeDAO).remove(proben.get(0));
    }

    @Test
    void testGetAll() {
        when(probeDAO.getAll()).thenReturn(proben);
        List<Probe> result = probenService.getAll();
        Assertions.assertEquals(proben, result);
    }
}