package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.ProbenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.mockito.Mockito.*;

class ProbeMeldenViewTest {
    @Mock
    ProbenService probenService;
    @Mock
    Logger log;
    @InjectMocks
    ProbeMeldenView probeMeldenView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testReportLost() throws ProbeNotFoundException {
        when(probenService.getProbeById(anyString())).thenReturn(new Probe("probenID", 0, null, new Standort(0, "ort")));

        probeMeldenView.reportLost();
    }

    @Test
    void testAlleLost() throws ProbeNotFoundException {
        when(probenService.getProbeById(anyString())).thenReturn(new Probe("probenID", 0, null, new Standort(0, "ort")));

        boolean result = probeMeldenView.alleLost();
        Assertions.assertEquals(true, result);
    }

    @Test
    void testReportLostProbe() {
        probeMeldenView.reportLostProbe(new Probe("probenID", 0, null, null), 0);
    }

    @Test
    void testReportLostProbe2() throws ProbeNotFoundException {
        when(probenService.getProbeById(anyString())).thenReturn(new Probe("probenID", 0, null, new Standort(0, "ort")));

        probeMeldenView.reportLostProbe("id", 0);
    }

    @Test
    void testReportLostProbe3() throws ProbeNotFoundException {
        when(probenService.getProbeById(anyString())).thenReturn(new Probe("probenID", 0, null, new Standort(0, "ort")));

        probeMeldenView.reportLostProbe();
    }

    @Test
    void testErrorMessage() {
        probeMeldenView.errorMessage("e");
    }

    @Test
    void testSetProbeMeldenID() {
        probeMeldenView.setProbeMeldenID("probeMeldenID");
    }

    @Test
    void testSetProbenAnzahl() {
        probeMeldenView.setProbenAnzahl(0);
    }

    @Test
    void testSetProbenService() {
        probeMeldenView.setProbenService(new ProbenService());
    }
}

