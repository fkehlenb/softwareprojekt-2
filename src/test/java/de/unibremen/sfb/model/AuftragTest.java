package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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
    Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;
    @Mock
    List<Traeger> traeger;
    @InjectMocks
    Auftrag auftrag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = auftrag.toString();
        Assertions.assertEquals("0", result);
    }

    @Test
    void testEquals() {
        boolean result = auftrag.equals("obj");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = auftrag.hashCode();
        Assertions.assertEquals(0, result);
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
        auftrag.setProzessSchritte(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
    }

    @Test
    void testSetLog() {
        auftrag.setLog(new AuftragsLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 45, 55)));
    }

    @Test
    void testSetProzessKettenZustandsAutomat() {
        auftrag.setProzessKettenZustandsAutomat(null);
    }

    @Test
    void testSetTraeger() {
        auftrag.setTraeger(Arrays.<Traeger>asList(new Traeger(0, null, Arrays.<Probe>asList(null), null)));
    }

    @Test
    void testSetErrorMessage() {
        auftrag.setErrorMessage("ErrorMessage");
    }
}

