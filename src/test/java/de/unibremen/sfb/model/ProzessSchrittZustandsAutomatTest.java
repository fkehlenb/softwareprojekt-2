package de.unibremen.sfb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittZustandsAutomatTest {
    @Mock
    List<String> zustaende;
    @InjectMocks
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        prozessSchrittZustandsAutomat.setValidData(true);
    }

    @Test
    void testSetId() {
        prozessSchrittZustandsAutomat.setId(0);
    }

    @Test
    void testSetCurrent() {
        prozessSchrittZustandsAutomat.setCurrent("current");
    }

    @Test
    void testSetName() {
        prozessSchrittZustandsAutomat.setName("name");
    }

    @Test
    void testSetZustaende() {
        prozessSchrittZustandsAutomat.setZustaende(Arrays.<String>asList("String"));
    }
}

