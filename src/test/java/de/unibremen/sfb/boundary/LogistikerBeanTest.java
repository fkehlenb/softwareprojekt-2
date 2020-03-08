package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.service.*;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.*;

class LogistikerBeanTest {
    @Mock
    List<Probe> proben;
    @Mock
    List<Auftrag> auftrage;
    @Mock
    ProbeDAO probeDAO;
    @Mock
    ProbenService probenService;
    @Mock
    AuftragService auftragService;
    @Mock
    ProzessKettenVorlageService prozessKettenVorlageService;
    @Mock
    AuftragView auftragView;
    @Mock
    TraegerService traegerService;
    @Mock
    TraegerArtService traegerArtService;
    @Mock
    StandortService standortService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    List<Traeger> traegers;
    @Mock
    TraegerArt traegerArt;
    @Mock
    List<TraegerArt> traegerArts;
    @Mock
    Standort traegerLocation;
    @Mock
    List<Probe> archiviert;
    @Mock
    List<Probe> selectedProbe;
    @Mock
    User logistiker;
    @Mock
    Logger log;
    @Mock
    List<Probe> probes;
    @Mock
    List<Auftrag> auftrags;
    @InjectMocks
    LogistikerBean logistikerBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(probenService.getAllArchived()).thenReturn(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)));
        when(probenService.getAll()).thenReturn(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)));
        when(auftragService.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));
        when(traegerService.getAll()).thenReturn(Arrays.<Traeger>asList(null));
        when(traegerArtService.getAll()).thenReturn(Arrays.<TraegerArt>asList(new TraegerArt("art")));

        logistikerBean.init();
    }

    @Test
    void testGetTraegerList() {
        when(traegerService.getAll()).thenReturn(Arrays.<Traeger>asList(null));

        List<Traeger> result = logistikerBean.getTraegerList();
        Assertions.assertEquals(Arrays.<Traeger>asList(null), result);
    }



    @Test
    void testOnRowEditUpdateTraeger() throws TraegerNotFoundException {
        when(traegerService.getTraegerById(anyInt())).thenReturn(new Traeger(0, "art", Arrays.<Probe>asList(new Probe("probenID", 0, null, null)), new Standort(0, "ort")));
        when(traegerService.getAll()).thenReturn(Arrays.<Traeger>asList(null));
        when(traegerLocation.getOrt()).thenReturn("getOrtResponse");

        logistikerBean.onRowEditUpdateTraeger(0);
    }



    @Test
    void testDeleteTraeger() throws TraegerNotFoundException {
        when(traegerService.getTraegerById(anyInt())).thenReturn(new Traeger(0, "art", Arrays.<Probe>asList(new Probe("probenID", 0, null, null)), new Standort(0, "ort")));
        when(traegerService.getAll()).thenReturn(Arrays.<Traeger>asList(null));

        logistikerBean.deleteTraeger(0);
    }

    @Test
    void testGetAllArchviert() {
        when(probenService.getAllArchived()).thenReturn(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)));

        List<Probe> result = logistikerBean.getAllArchviert();
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)), result);
    }

    @Test
    void testAddProbe() throws ProbeNotFoundException, DuplicateProbeException, StandortNotFoundException {
        when(probenService.erstelleProbe(any(), anyString(), anyInt())).thenReturn(new Probe("probenID", 0, null, new Standort(0, "ort")));
        when(standortService.findByLocation(anyString())).thenReturn(new Standort(0, "ort"));

        logistikerBean.addProbe();
    }

    @Test
    void testStartAuftrag() throws AuftragNotFoundException {
        when(auftragService.getObjById(anyInt())).thenReturn(new Auftrag());

        logistikerBean.startAuftrag(0);
    }

    @Test
    void testRefuseAuftrag() throws AuftragNotFoundException {
        when(auftragService.getObjById(anyInt())).thenReturn(new Auftrag());

        logistikerBean.refuseAuftrag(0);
    }

    @Test
    void testErrorMessageAnPkA() throws AuftragNotFoundException {
        logistikerBean.errorMessageAnPkA(new Auftrag());
    }

    @Test
    void testSetProben() {
        logistikerBean.setProben(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)));
    }

    @Test
    void testSetAuftrage() {
        logistikerBean.setAuftrage(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetProbeDAO() {
        logistikerBean.setProbeDAO(new ProbeDAO());
    }

    @Test
    void testSetProbenService() {
        logistikerBean.setProbenService(new ProbenService());
    }

    @Test
    void testSetAuftragService() {
        logistikerBean.setAuftragService(new AuftragService());
    }

    @Test
    void testSetProzessKettenVorlageService() {
        logistikerBean.setProzessKettenVorlageService(new ProzessKettenVorlageService());
    }

    @Test
    void testSetAuftragView() {
        logistikerBean.setAuftragView(new AuftragView());
    }

    @Test
    void testSetTraegerService() {
        logistikerBean.setTraegerService(new TraegerService());
    }

    @Test
    void testSetTraegerArtService() {
        logistikerBean.setTraegerArtService(new TraegerArtService());
    }

    @Test
    void testSetStandortService() {
        logistikerBean.setStandortService(new StandortService());
    }

    @Test
    void testSetExperimentierStationService() {
        logistikerBean.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetTraegers() {
        logistikerBean.setTraegers(Arrays.<Traeger>asList(null));
    }

    @Test
    void testSetTraegerArt() {
        logistikerBean.setTraegerArt("art");
    }

    @Test
    void testSetTraegerArts() {
        logistikerBean.setTraegerArts(Arrays.<String>asList("art"));
    }

    @Test
    void testSetTraegerLocation() {
        logistikerBean.setTraegerLocation(new Standort(0, "ort"));
    }

    @Test
    void testSetErrorMessage() {
        logistikerBean.setErrorMessage("errorMessage");
    }

    @Test
    void testSetArchiviert() {
        logistikerBean.setArchiviert(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)));
    }

    @Test
    void testSetProbenID() {
        logistikerBean.setProbenID("probenID");
    }

    @Test
    void testSetSelectedProbe() {
        logistikerBean.setSelectedProbe(Arrays.<Probe>asList(new Probe("probenID", 0, null, null)));
    }

    @Test
    void testSetAnzahl() {
        logistikerBean.setAnzahl(0);
    }
}

