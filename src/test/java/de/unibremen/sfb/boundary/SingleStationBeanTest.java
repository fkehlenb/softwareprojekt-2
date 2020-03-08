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

import java.util.ArrayList;
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
    @Mock
    List<Probe> probes;
    @Mock
    Auftrag auftrag;
    @Mock
    ProzessSchritt prozessSchritt;
    @InjectMocks
    SingleStationBean singleStationBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSingleStation() {
        String result = singleStationBean.singleStation(new ExperimentierStation());
        Assertions.assertEquals("singlestation.xhtml", result);
    }


    @Test
    void testGetProben() {
        when(station.getCurrentPS()).thenReturn(prozessSchritt);
        when(auftragService.getAuftrag(any())).thenReturn(auftrag);
        List<Probe> result = singleStationBean.getProben();
        Assertions.assertEquals(new ArrayList<Probe>().getClass(), result.getClass());
    }
}

