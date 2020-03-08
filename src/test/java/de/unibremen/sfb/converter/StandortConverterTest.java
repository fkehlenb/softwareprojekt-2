package de.unibremen.sfb.converter;

import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.StandortService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class StandortConverterTest {
    @Mock
    StandortService standortService;
    @InjectMocks
    StandortConverter standortConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAsString() {
        String result = standortConverter.getAsString(null, null, new Standort(0, "ort"));
        Assertions.assertEquals("0", result);
    }


}

