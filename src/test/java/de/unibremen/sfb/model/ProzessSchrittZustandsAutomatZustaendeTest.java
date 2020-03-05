package de.unibremen.sfb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittZustandsAutomatZustaendeTest {
    @Mock
    List<String> zustaende;
    @InjectMocks
    ProzessSchrittZustandsAutomatZustaende prozessSchrittZustandsAutomatZustaende;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetId() {
        prozessSchrittZustandsAutomatZustaende.setId(0);
    }

    @Test
    void testSetZustaende() {
        prozessSchrittZustandsAutomatZustaende.setZustaende(Arrays.<String>asList("String"));
    }
}

