package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
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

class UpdatePSPViewTest {
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    QuantitativeEigenschaftService quantitativeEigenschaftService;
    @Mock
    ProzessSchrittParameter prozessSchrittParameter;
    @Mock
    List<QualitativeEigenschaft> qualitativeEigenschaften;
    @Mock
    Logger log;
    @InjectMocks
    UpdatePSPView updatePSPView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdatePSP() throws ProzessSchrittParameterNotFoundException {
        when(prozessSchrittParameterService.getPSPByID(anyInt())).thenReturn(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));

        String result = updatePSPView.updatePSP();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testDeleteGewaltQein() {
        when(qualitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QualitativeEigenschaft(0, "name"));

        List<QualitativeEigenschaft> result = updatePSPView.deleteGewaltQein("id");
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(null), result);
    }

    @Test
    void testListqlE() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(null));

        List<QualitativeEigenschaft> result = updatePSPView.listqlE();
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(null), result);
    }

    @Test
    void testListqtE() {
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        List<QuantitativeEigenschaft> result = updatePSPView.listqtE();
        Assertions.assertEquals(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testSelect() {
        when(qualitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QualitativeEigenschaft(0, "name"));
        when(quantitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QuantitativeEigenschaft(0, "name"));

        updatePSPView.select("idqE");
    }

    @Test
    void testSetProzessSchrittParameterService() {
        updatePSPView.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetQualitativeEigenschaftService() {
        updatePSPView.setQualitativeEigenschaftService(new QualitativeEigenschaftService());
    }

    @Test
    void testSetQuantitativeEigenschaftService() {
        updatePSPView.setQuantitativeEigenschaftService(new QuantitativeEigenschaftService());
    }

    @Test
    void testSetProzessSchrittParameter() {
        updatePSPView.setProzessSchrittParameter(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
    }

    @Test
    void testSetId() {
        updatePSPView.setId("id");
    }

    @Test
    void testSetName() {
        updatePSPView.setName("name");
    }

    @Test
    void testSetQualitativeEigenschaften() {
        updatePSPView.setQualitativeEigenschaften(Arrays.<QualitativeEigenschaft>asList(null));
    }
}

