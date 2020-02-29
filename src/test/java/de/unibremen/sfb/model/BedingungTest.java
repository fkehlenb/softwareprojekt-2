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

class BedingungTest {
    @Mock
    List<ProzessSchrittParameter> prozessSchrittParameter;
    @InjectMocks
    Bedingung bedingung;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = bedingung.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetValidData() {
        bedingung.setValidData(true);
    }

    @Test
    void testSetId() {
        bedingung.setId(0);
    }

    @Test
    void testSetName() {
        bedingung.setName("name");
    }

    @Test
    void testSetProzessSchrittParameter() {
        bedingung.setProzessSchrittParameter(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))));
    }

    @Test
    void testSetGewuenschteAnzahl() {
        bedingung.setGewuenschteAnzahl(0);
    }

    @Test
    void testEquals() {
        boolean result = bedingung.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = bedingung.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = bedingung.hashCode();
        Assertions.assertEquals(0, result);
    }
}