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

class ProzessSchrittZustandsAutomatVorlageTest {
    @Mock
    List<String> zustaende;
    @InjectMocks
    ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = prozessSchrittZustandsAutomatVorlage.toString();
        Assertions.assertEquals("ProzessSchrittZustandsAutomatVorlage{name='null'}", result);
    }

    @Test
    void testSetValidData() {
        prozessSchrittZustandsAutomatVorlage.setValidData(true);
    }

    @Test
    void testSetId() {
        prozessSchrittZustandsAutomatVorlage.setId(0);
    }

    @Test
    void testSetZustaende() {
        prozessSchrittZustandsAutomatVorlage.setZustaende(Arrays.<String>asList("String"));
    }

    @Test
    void testSetName() {
        prozessSchrittZustandsAutomatVorlage.setName("name");
    }
}

