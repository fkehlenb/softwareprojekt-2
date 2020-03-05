package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PSPViewTest {
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    QuantitativeEigenschaftService quantitativeEigenschaftService;
    @Mock
    List<ProzessSchrittParameter> availableProzessSchrittParameter;
    @Mock
    List<QualitativeEigenschaft> availableQualitativeEigenschaften;
    @Mock
    List<QualitativeEigenschaft> selectedQualitativeEigenschaften;
    @Mock
    List<QuantitativeEigenschaft> availableQuantitativeEigenschaften;
    @Mock
    List<QuantitativeEigenschaft> selectedQuantitativeEigenschaften;
    @Mock
    Logger log;
    @InjectMocks
    PSPView pSPView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatePSP() {
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(null));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        pSPView.createPSP();
    }

    @Test
    void testEditPSP() throws ProzessSchrittParameterNotFoundException {
        when(prozessSchrittParameterService.getPSPByID(anyInt())).thenReturn(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(null));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        pSPView.editPSP(0);
    }

    @Test
    void testRemovePSP() throws ProzessSchrittParameterNotFoundException {
        when(prozessSchrittParameterService.getPSPByID(anyInt())).thenReturn(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(null));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        pSPView.removePSP(0);
    }


    @Test
    void testSetProzessSchrittParameterService() {
        pSPView.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetQualitativeEigenschaftService() {
        pSPView.setQualitativeEigenschaftService(new QualitativeEigenschaftService());
    }

    @Test
    void testSetQuantitativeEigenschaftService() {
        pSPView.setQuantitativeEigenschaftService(new QuantitativeEigenschaftService());
    }

    @Test
    void testSetAvailableProzessSchrittParameter() {
        pSPView.setAvailableProzessSchrittParameter(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
    }

    @Test
    void testSetAvailableQualitativeEigenschaften() {
        pSPView.setAvailableQualitativeEigenschaften(Arrays.<QualitativeEigenschaft>asList(null));
    }

    @Test
    void testSetSelectedQualitativeEigenschaften() {
        pSPView.setSelectedQualitativeEigenschaften(Arrays.<QualitativeEigenschaft>asList(null));
    }

    @Test
    void testSetAvailableQuantitativeEigenschaften() {
        pSPView.setAvailableQuantitativeEigenschaften(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetSelectedQuantitativeEigenschaften() {
        pSPView.setSelectedQuantitativeEigenschaften(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetSelectedName() {
        pSPView.setSelectedName("selectedName");
    }
}

