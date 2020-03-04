package de.unibremen.sfb.model;

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

class ProbeTest {
    @Mock
    List<Kommentar> kommentar;
    @Mock
    Enum<ProbenZustand> zustand;
    @Mock
    Standort standort;
    @Mock
    List<ProzessSchrittParameter> bedingungen;
    @Mock
    List<QualitativeEigenschaft> qualitativeEigenschaften;
    @Mock
    Traeger currentTraeger;
    @InjectMocks
    Probe probe;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        probe.setValidData(true);
    }

    @Test
    void testSetProbenID() {
        probe.setProbenID("probenID");
    }

    @Test
    void testSetKommentar() {
        probe.setKommentar(Arrays.<Kommentar>asList(new Kommentar(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 10), "text")));
    }

    @Test
    void testSetZustand() {
        probe.setZustand(null);
    }

    @Test
    void testSetStandort() {
        probe.setStandort(new Standort(0, "ort"));
    }

//    @Test
//    void testSetBedingungen() {
//        probe.setBedingungen(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)));
//    }

//FIXME
/*    @Test
    void testSetCurrentTraeger() {
        probe.setCurrentTraeger(new Traeger(0, new TraegerArt("art"), List.of(probe)));
    }*/

    @Test
    void testEquals() {
        boolean result = probe.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = probe.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = probe.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = probe.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}