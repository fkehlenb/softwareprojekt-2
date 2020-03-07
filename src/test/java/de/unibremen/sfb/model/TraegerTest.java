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

class TraegerTest {
    @Mock
    TraegerArt art;
    @Mock
    List<Probe> proben;
    @Mock
    Standort standort;
    @InjectMocks
    Traeger traeger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        traeger.setValidData(true);
    }

    @Test
    void testSetId() {
        traeger.setId(0);
    }

    @Test
    void testSetArt() {
        traeger.setArt("art");
    }

    @Test
    void testSetProben() {
        traeger.setProben(Arrays.<Probe>asList(new Probe()));
    }

    @Test
    void testSetStandort() {
        traeger.setStandort(new Standort(0, "ort"));
    }

    @Test
    void testEquals() {
        boolean result = traeger.equals("o");
        Assertions.assertEquals(false, result);
    }


    @Test
    void testToString() {
        String result = traeger.toString();
        Assertions.assertEquals("Traeger(isValidData=true, id=0, art=art, proben=proben, standort=standort)", result);
    }
}

