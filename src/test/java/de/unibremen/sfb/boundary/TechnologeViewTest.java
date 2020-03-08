package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.AuftragNotFoundException;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TechnologeViewTest {
    @Mock
    List<ExperimentierStation> experimentierStations;
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
    @Mock
    List<Probe> probes;
    @Mock
    ExperimentierStation experimentierStation;
    @InjectMocks
    TechnologeView technologeView;
    @Mock
    Probe probe;

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
        when(experimentierStationService.getESByUser(any())).thenReturn(experimentierStations);
        when(esService.getESByUser(any())).thenReturn(experimentierStations);
        when(experimentierStations.get(anyInt())).thenReturn(experimentierStation);
        List<ExperimentierStation> result = technologeView.getStationen();
        Assertions.assertEquals(experimentierStations.size(), result.size());
    }

    @Test
    void testGetSchritte() throws UserNotFoundException {
        when(prozessSchrittService.getSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0)));
        when(psService.getSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0)));

        List<ProzessSchritt> result = technologeView.getSchritte();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0)), result);
    }

    @Test
    void testFindStandort() {
        when(experimentierStationService.findStation(any())).thenReturn(experimentierStation);
        when(esService.findStation(any())).thenReturn(experimentierStation);

        ExperimentierStation result = technologeView.findStandort(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0));
        Assertions.assertEquals(experimentierStation, result);
    }

    @Test
    void testCreateUrformend() {
        technologeView.createUrformend("id");
    }

    @Test
    void testViewToBeUploaded() throws AuftragNotFoundException {
        when(probeService.viewToBeUploaded()).thenReturn(probes);
        List<Probe> result = technologeView.viewToBeUploaded();
        Assertions.assertEquals(probes, result);
    }


    @Test
    void testUpload() {
        technologeView.upload(probe);
    }

    @Test
    void testReportLostProbe() {
        technologeView.reportLostProbe(probe);
    }

    @Test
    void testGetPriority() throws ProzessSchrittNotFoundException {
        when(auftragService.getAuftrag(any())).thenReturn(new Auftrag());
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0));
        when(psService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 39, 42), "zustandsAutomat")), "name", true, 0));

        AuftragsPrioritaet result = technologeView.getPriority(0);
        Assertions.assertEquals(null, result);
    }

    @Test
    void testKommentarToString() {
        when(probeService.KommentarToString(any())).thenReturn("KommentarToStringResponse");
        String result = technologeView.KommentarToString(probe);
        Assertions.assertEquals("KommentarToStringResponse", result);
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
    void testSetProbeService() {
        technologeView.setProbeService(new ProbenService());
    }

    @Test
    void testSetUserService() {
        technologeView.setUserService(new UserService());
    }

}

