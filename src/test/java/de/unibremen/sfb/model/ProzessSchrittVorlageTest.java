package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittVorlageTest {
    @Mock
    List<TraegerArt> eingabeTraeger;
    @Mock
    List<TraegerArt> ausgabeTraeger;
    @Mock
    List<ExperimentierStation> stationen;
    @Mock
    List<Bedingung> bedingungen;
    @Mock
    ProzessSchrittZustandsAutomatVorlage zustandsAutomatVorlage;
    @InjectMocks
    ProzessSchrittVorlage prozessSchrittVorlage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = prozessSchrittVorlage.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetValidData() {
        prozessSchrittVorlage.setValidData(true);
    }

    @Test
    void testSetPsVID() {
        prozessSchrittVorlage.setPsVID(0);
    }

    @Test
    void testSetDauer() {
        prozessSchrittVorlage.setDauer("dauer");
    }

//    @Test
//    void testSetEingabeTraeger() {
//        prozessSchrittVorlage.setEingabeTraeger(Arrays.<TraegerArt>asList(new TraegerArt("art")));
//    }

//    @Test
//    void testSetAusgabeTraeger() {
//        prozessSchrittVorlage.setAusgabeTraeger(Arrays.<TraegerArt>asList(new TraegerArt("art")));
//    }

    @Test
    void testSetName() {
        prozessSchrittVorlage.setName("name");
    }
//
//    @Test
//    void testSetPsArt() {
//        prozessSchrittVorlage.setPsArt("psArt");
//    }

//    @Test
//    void testSetStationen() {
//        prozessSchrittVorlage.setStationen(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
//    }

//    @Test
//    void testSetBedingungen() {
//        prozessSchrittVorlage.setBedingungen(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)));
//    }

    @Test
    void testSetZustandsAutomatVorlage() {
        prozessSchrittVorlage.setZustandsAutomatVorlage(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testEquals() {
        boolean result = prozessSchrittVorlage.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = prozessSchrittVorlage.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = prozessSchrittVorlage.hashCode();
        Assertions.assertEquals(0, result);
    }
}