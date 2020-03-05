package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
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

class QlEViewTest {
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    QuantitativeEigenschaftService quantitativeEigenschaftService;
    @Mock
    List<QualitativeEigenschaft> availableQualitativeEigenschaften;
    @Mock
    List<QuantitativeEigenschaft> availableQuantitativeEigenschaften;
    @Mock
    Logger log;
    @InjectMocks
    QlEView qlEView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddQualitative() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));
        qlEView.addQualitative();
    }

    @Test
    void testAddQuantitative() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        qlEView.addQuantitative();
    }

    @Test
    void testEditQualitative() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(qualitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QualitativeEigenschaft(0, "name"));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        qlEView.editQualitative(0);
    }

    @Test
    void testEditQuantitative() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));
        when(quantitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QuantitativeEigenschaft(0, "name"));

        qlEView.editQuantitative(0);
    }

    @Test
    void testRemoveQualitative() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(qualitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QualitativeEigenschaft(0, "name"));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        qlEView.removeQualitative(0);
    }

    @Test
    void testRemoveQuantitative() {
        when(qualitativeEigenschaftService.getAllQualitativeEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(quantitativeEigenschaftService.getAllQuantitativeEigenschaften()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));
        when(quantitativeEigenschaftService.getQlEById(anyInt())).thenReturn(new QuantitativeEigenschaft(0, "name"));

        qlEView.removeQuantitative(0);
    }

    @Test
    void testOnRowEditCancelled() {
        qlEView.onRowEditCancelled();
    }

    @Test
    void testSetQualitativeEigenschaftService() {
        qlEView.setQualitativeEigenschaftService(new QualitativeEigenschaftService());
    }

    @Test
    void testSetQuantitativeEigenschaftService() {
        qlEView.setQuantitativeEigenschaftService(new QuantitativeEigenschaftService());
    }

    @Test
    void testSetAvailableQualitativeEigenschaften() {
        qlEView.setAvailableQualitativeEigenschaften(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetAvailableQuantitativeEigenschaften() {
        qlEView.setAvailableQuantitativeEigenschaften(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetSelectedQualitativeName() {
        qlEView.setSelectedQualitativeName("selectedQualitativeName");
    }

    @Test
    void testSetSelectedQuantitativeName() {
        qlEView.setSelectedQuantitativeName("selectedQuantitativeName");
    }

    @Test
    void testSetValue() {
        qlEView.setValue("value");
    }

    @Test
    void testSetMeasurement() {
        qlEView.setMeasurement("measurement");
    }
}

