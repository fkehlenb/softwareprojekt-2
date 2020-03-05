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

class ExperimentierStationTest {
    @Mock
    Standort standort;
    @Mock
    Enum<ExperimentierStationZustand> status;
    @Mock
    List<ProzessSchritt> nextPS;
    @Mock
    List<ProzessSchrittParameter> bedingungen;
    @Mock
    List<User> benutzer;
    @Mock
    ProzessSchritt currentPS;
    @InjectMocks
    ExperimentierStation experimentierStation;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        when(standort.getOrt()).thenReturn("getOrtResponse");

        String result = experimentierStation.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetValidData() {
        experimentierStation.setValidData(true);
    }

    @Test
    void testSetEsID() {
        experimentierStation.setEsID(0);
    }

    @Test
    void testSetStandort() {
        experimentierStation.setStandort(new Standort(0, "ort"));
    }

    @Test
    void testSetName() {
        experimentierStation.setName("name");
    }

    @Test
    void testSetStatus() {
        experimentierStation.setStatus(null);
    }

//    @Test
//    void testSetNextPS() {
//        experimentierStation.setNextPS(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 25), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")))));
//    }

//    @Test
//    void testSetBedingungen() {
//        experimentierStation.setRequirements(Arrays.<ProzessSchrittParameter>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)));
//    }

    @Test
    void testSetBenutzer() {
        experimentierStation.setBenutzer(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 25), "language")));
    }

//    @Test
//    void testSetCurrentPS() {
//        experimentierStation.setCurrentPS(new ProzessSchritt(0, Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 25), "zustandsAutomat")), new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))));
//    }

/*
    @Test
    void testEquals() {
        boolean result = experimentierStation.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = experimentierStation.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = experimentierStation.hashCode();
        Assertions.assertEquals(0, result);
    }
*/
}