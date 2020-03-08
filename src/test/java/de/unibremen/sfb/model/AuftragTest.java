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

class AuftragTest {
    //Field priority of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    @Mock
    List<ProzessSchritt> prozessSchritte;
    @Mock
    AuftragsLog log;
    @Mock
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;
    @Mock
    Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;
    @Mock
    List<Traeger> traeger;
    @Mock
    List<Probe> probes;
    @Mock
    List<ProzessSchritt> prozessSchritts;
    @InjectMocks
    Auftrag auftrag;
    @Mock
    Standort standort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = auftrag.toString();
        Assertions.assertEquals("Auftrag: 0", result);
    }

    @Test
    void testEquals() {
        boolean result = auftrag.equals("obj");
        Assertions.assertEquals(false, result);
    }


    @Test
    void testSetValidData() {
        auftrag.setValidData(true);
    }

    @Test
    void testSetPkID() {
        auftrag.setPkID(0);
    }

    @Test
    void testSetName() {
        auftrag.setName("name");
    }

    @Test
    void testSetPriority() {
        auftrag.setPriority(AuftragsPrioritaet.KEINE);
    }

    @Test
    void testSetProzessSchritte() {
        auftrag.setProzessSchritte(prozessSchritts);
    }

    @Test
    void testSetLog() {
        auftrag.setLog(new AuftragsLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 45, 55)));
    }

    @Test
    void testSetProzessKettenZustandsAutomat() {
        auftrag.setProzessKettenZustandsAutomat(ProzessKettenZustandsAutomat.INSTANZIIERT);
    }

    @Test
    void testSetTraeger() {
        auftrag.setTraeger(Arrays.<Traeger>asList(new Traeger(0, "Glass", probes, standort)));
    }

    @Test
    void testSetErrorMessage() {
        auftrag.setErrorMessage("ErrorMessage");
    }
}

