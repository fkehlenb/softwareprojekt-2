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

class ProzessKettenVorlageTest {
    @Mock
    List<ProzessSchrittVorlage> prozessSchrittVorlagen;
    @InjectMocks
    ProzessKettenVorlage prozessKettenVorlage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = prozessKettenVorlage.toString();
        Assertions.assertEquals("null", result);
    }

    @Test
    void testSetValidData() {
        prozessKettenVorlage.setValidData(true);
    }

    @Test
    void testSetPkvID() {
        prozessKettenVorlage.setPkvID(0);
    }

    @Test
    void testSetName() {
        prozessKettenVorlage.setName("name");
    }

    @Test
    void testSetProzessSchrittVorlagen() {
        prozessKettenVorlage.setProzessSchrittVorlagen(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0)));
    }
}

