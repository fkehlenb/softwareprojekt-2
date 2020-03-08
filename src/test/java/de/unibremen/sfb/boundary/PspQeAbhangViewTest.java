package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PspQeAbhangViewTest {
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    QuantitativeEigenschaftService quantitativeEigenschaftService;
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    List<ProzessSchrittParameter> prozessSchrittParameters;
    @Mock
    Logger log;
    @InjectMocks
    PspQeAbhangView pspQeAbhangView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testLinkQIE() {
        String result = pspQeAbhangView.linkQIE();
        Assertions.assertEquals("qEin?faces-redirect=true", result);
    }

    @Test
    void testSetProzessSchrittParameterService() {
        pspQeAbhangView.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetQuantitativeEigenschaftService() {
        pspQeAbhangView.setQuantitativeEigenschaftService(new QuantitativeEigenschaftService());
    }

    @Test
    void testSetQualitativeEigenschaftService() {
        pspQeAbhangView.setQualitativeEigenschaftService(new QualitativeEigenschaftService());
    }

    @Test
    void testSetIdqEin() {
        pspQeAbhangView.setIdqEin("idqEin");
    }

    @Test
    void testSetProzessSchrittParameters() {
        pspQeAbhangView.setProzessSchrittParameters(prozessSchrittParameters);
    }
}

