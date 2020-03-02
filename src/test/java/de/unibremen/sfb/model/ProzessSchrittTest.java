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

class ProzessSchrittTest {
    @Mock
    TransportAuftrag transportAuftrag;
    @Mock
    List<ProzessSchrittLog> prozessSchrittLog;
    @Mock
    ProzessSchrittVorlage prozessSchrittVorlage;
    @Mock
    List<Probe> zugewieseneProben;
    @Mock
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;
    @InjectMocks
    ProzessSchritt prozessSchritt;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        prozessSchritt.setValidData(true);
    }

//    @Test
//    void testSetPsID() {
//        prozessSchritt.setPsID(0);
//    }

//    @Test
//    void testSetUploaded() {
//        prozessSchritt.setUploaded(true);
//    }

    @Test
    void testSetTransportAuftrag() {
        prozessSchritt.setTransportAuftrag(new TransportAuftrag());
    }

//    @Test
//    void testSetProzessSchrittLog() {
//        prozessSchritt.setProzessSchrittLog(Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 22), "zustandsAutomat")));
//    }

    @Test
    void testSetProzessSchrittVorlage() {
        prozessSchritt.setProzessSchrittVorlage(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

//    @Test
//    void testSetZugewieseneProben() {
//        prozessSchritt.setZugewieseneProben(Arrays.<Probe>asList(new Probe()));
//    }

    @Test
    void testSetProzessSchrittZustandsAutomat() {
        prozessSchritt.setProzessSchrittZustandsAutomat(new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

    @Test
    void testEquals() {
        boolean result = prozessSchritt.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = prozessSchritt.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = prozessSchritt.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = prozessSchritt.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}