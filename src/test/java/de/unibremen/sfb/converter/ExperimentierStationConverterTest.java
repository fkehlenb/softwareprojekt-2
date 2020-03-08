package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.service.ExperimentierStationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class ExperimentierStationConverterTest {
    @Mock
    ExperimentierStationService experimentierStationService;
    @InjectMocks
    ExperimentierStationConverter experimentierStationConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = experimentierStationConverter.toString();
        Assertions.assertEquals("ExperimentierStationConverter{experimentierStationConverterexperimentierStationService}", result);
    }




}

