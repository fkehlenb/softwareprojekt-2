package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class ProzessSchrittParameterConverterTest {
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @InjectMocks
    ProzessSchrittParameterConverter prozessSchrittParameterConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testToString() {
        String result = prozessSchrittParameterConverter.toString();
        Assertions.assertEquals("ProzessSchrittParameterConverter{prozessSchrittParameterConverterprozessSchrittParameterService}", result);
    }

    @Test
    void testGetAsString() {
        String result = prozessSchrittParameterConverter.getAsString(null, null, new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name"))));
        Assertions.assertEquals("name", result);
    }

}

