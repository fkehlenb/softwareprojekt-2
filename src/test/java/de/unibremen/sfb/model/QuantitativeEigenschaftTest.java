package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
class QuantitativeEigenschaftTest {
    @Mock
    Number wert;
    @InjectMocks
    QuantitativeEigenschaft quantitativeEigenschaft;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        quantitativeEigenschaft.setValidData(true);
    }

    @Test
    void testSetWert() {
        quantitativeEigenschaft.setWert(null);
    }

    @Test
    void testSetEinheit() {
        quantitativeEigenschaft.setEinheit("einheit");
    }

    @Test
    void testSetId() {
        quantitativeEigenschaft.setId(0);
    }

    @Test
    void testSetName() {
        quantitativeEigenschaft.setName("name");
    }

    @Test
    void testEquals() {
        boolean result = quantitativeEigenschaft.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = quantitativeEigenschaft.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = quantitativeEigenschaft.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}