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
    List<QualitativeEigenschaft> eigenschaften;
    @Mock
    List<String> atribute;
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
    void testSetAnzahl() {
        probe.setAnzahl(0);
    }

    @Test
    void testSetLost() {
        probe.setLost(0);
    }

    @Test
    void testSetKommentar() {
        probe.setKommentar(Arrays.<Kommentar>asList(new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 47, 10), "text")));
    }

    @Test
    void testSetZustand() {
        probe.setZustand(null);
    }

    @Test
    void testSetStandort() {
        probe.setStandort(new Standort(0, "ort"));
    }

    @Test
    void testSetEigenschaften() {
        probe.setEigenschaften(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetAtribute() {
        probe.setAtribute(Arrays.<String>asList("String"));
    }

    @Test
    void testSetCurrentTraeger() {
        probe.setCurrentTraeger(new Traeger(0, "art", Arrays.<Probe>asList(new Probe()), new Standort(0, "ort")));
    }

    @Test
    void testEquals() {
        boolean result = probe.equals("o");
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
        Assertions.assertEquals("Probe(isValidData=true, probenID=null, anzahl=0, lost=0, kommentar=kommentar, zustand=zustand, standort=standort, eigenschaften=eigenschaften, atribute=atribute, currentTraeger=currentTraeger)", result);
    }
}

