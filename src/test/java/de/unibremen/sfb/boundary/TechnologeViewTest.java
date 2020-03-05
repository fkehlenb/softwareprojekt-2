package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TechnologeViewTest {
    @Mock
    AuftragService auftragService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    ProzessSchrittService prozessSchrittService;
    @Mock
    User technologe;
    @Mock
    List<Auftrag> auftragList;
    @Mock
    ExperimentierStationService esService;
    @Mock
    ProbenService probeService;
    @Mock
    UserService userService;
    @Mock
    ProzessSchrittService psService;
    @Mock
    Logger log;
    @InjectMocks
    TechnologeView technologeView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() throws UserNotFoundException {
        when(userService.getCurrentUser()).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "language"));

        technologeView.init();
    }

    @Test
    void testGetStationen() {
        when(experimentierStationService.getESByUser(any())).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
        when(esService.getESByUser(any())).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        List<ExperimentierStation> result = technologeView.getStationen();
        Assertions.assertEquals(Arrays.<ExperimentierStation>asList(new ExperimentierStation()), result);
    }

    @Test
    void testGetSchritte() throws UserNotFoundException {
        when(prozessSchrittService.getSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
        when(psService.getSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>())));

        List<ProzessSchritt> result = technologeView.getSchritte();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>())), result);
    }

    @Test
    void testFindStandort() {
        when(experimentierStationService.findStation(any())).thenReturn(new ExperimentierStation());
        when(esService.findStation(any())).thenReturn(new ExperimentierStation());

        ExperimentierStation result = technologeView.findStandort(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    @Test
    void testReportBroken() {
        technologeView.reportBroken(new ExperimentierStation());
    }

    @Test
    void testCreateUrformend() {
        technologeView.createUrformend("id");
    }

//    @Test
//    void testViewToBeUploaded() {
//        when(probeService.viewToBeUploaded()).thenReturn(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))));
//
//        List<Probe> result = technologeView.viewToBeUploaded();
//        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), result);
//    }

    @Test
    void testUpload() {
        technologeView.upload(new Probe("probenID", 0, null, new Standort(0, "ort")));
    }

    @Test
    void testReportLostProbe() {
        technologeView.reportLostProbe(new Probe("probenID", 0, null, new Standort(0, "ort")));
    }

    @Test
    void testGetPriority() throws ProzessSchrittNotFoundException {
        when(auftragService.getAuftrag(any())).thenReturn(new Auftrag());
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(psService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));

        AuftragsPrioritaet result = technologeView.getPriority(0);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testKommentarToString() {
        when(probeService.KommentarToString(any())).thenReturn("KommentarToStringResponse");

        String result = technologeView.KommentarToString(new Probe("probenID", 0, null, new Standort(0, "ort")));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetAuftragService() {
        technologeView.setAuftragService(new AuftragService());
    }

    @Test
    void testSetExperimentierStationService() {
        technologeView.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetProzessSchrittService() {
        technologeView.setProzessSchrittService(new ProzessSchrittService());
    }

    @Test
    void testSetTechnologe() {
        technologeView.setTechnologe(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "language"));
    }

    @Test
    void testSetAuftragList() {
        technologeView.setAuftragList(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetEsService() {
        technologeView.setEsService(new ExperimentierStationService());
    }

    @Test
    void testSetProbeService() {
        technologeView.setProbeService(new ProbenService());
    }

    @Test
    void testSetUserService() {
        technologeView.setUserService(new UserService());
    }

    @Test
    void testSetPsService() {
        technologeView.setPsService(new ProzessSchrittService());
    }
}

