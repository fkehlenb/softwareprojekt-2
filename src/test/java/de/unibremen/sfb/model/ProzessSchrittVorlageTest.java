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
    List<ProzessSchrittParameter> prozessSchrittParameters;
    @Mock
    ExperimentierStation experimentierStation;
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
        Assertions.assertEquals(null, result);
    }

    @Test
    void testSetPsVID() {
        prozessSchrittVorlage.setPsVID(0);
    }

    @Test
    void testSetValidData() {
        prozessSchrittVorlage.setValidData(true);
    }

    @Test
    void testSetProzessSchrittParameters() {
        prozessSchrittVorlage.setProzessSchrittParameters(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))));
    }

    @Test
    void testSetExperimentierStation() {
        prozessSchrittVorlage.setExperimentierStation(new ExperimentierStation());
    }

    @Test
    void testSetDauer() {
        prozessSchrittVorlage.setDauer("dauer");
    }

    @Test
    void testSetName() {
        prozessSchrittVorlage.setName("name");
    }

    @Test
    void testSetZustandsAutomatVorlage() {
        prozessSchrittVorlage.setZustandsAutomatVorlage(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testSetUrformend() {
        prozessSchrittVorlage.setUrformend(true);
    }

    @Test
    void testSetAmountCreated() {
        prozessSchrittVorlage.setAmountCreated(0);
    }
}

