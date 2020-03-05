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

class ExperimentierStationTest {
    @Mock
    Standort standort;
    @Mock
    Enum<ExperimentierStationZustand> status;
    @Mock
    List<ProzessSchritt> nextPS;
    @Mock
    List<ProzessSchrittParameter> requirements;
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
        Assertions.assertEquals("getOrtResponse", result);
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

    @Test
    void testSetNextPS() {
        experimentierStation.setNextPS(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 46, 40), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
    }

    @Test
    void testSetRequirements() {
        experimentierStation.setRequirements(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))));
    }

    @Test
    void testSetBenutzer() {
        experimentierStation.setBenutzer(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 46, 40), "language")));
    }

    @Test
    void testSetCurrentPS() {
        experimentierStation.setCurrentPS(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 46, 40), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
    }
}

