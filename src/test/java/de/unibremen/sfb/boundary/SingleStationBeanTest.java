package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProbenService;
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

class SingleStationBeanTest {
    @Mock
    ExperimentierStation station;
    @Mock
    ProbenService probenService;
    @Mock
    ExperimentierStationService esService;
    @Mock
    TechnologeView technologeView;
    @Mock
    AuftragService auftragService;
    @Mock
    Logger log;
    @InjectMocks
    SingleStationBean singleStationBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSingleStation() {
        String result = singleStationBean.singleStation(new ExperimentierStation());
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testReportBroken() {
        when(station.getEsID()).thenReturn(0);

        singleStationBean.reportBroken();
    }

    @Test
    void testGetProben() {
        when(station.getCurrentPS()).thenReturn(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 38, 55), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(auftragService.getAuftrag(any())).thenReturn(new Auftrag());

        List<Probe> result = singleStationBean.getProben();
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), result);
    }
}

