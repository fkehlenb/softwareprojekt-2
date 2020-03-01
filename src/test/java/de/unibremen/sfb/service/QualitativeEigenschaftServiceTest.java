package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.QualitativeEigenschaftDAO;
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

class QualitativeEigenschaftServiceTest {
    @Mock
    List<QualitativeEigenschaft> eigenschaften;
    @Mock
    QualitativeEigenschaftDAO qeDAO;
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    Logger log;
    @InjectMocks
    QualitativeEigenschaftService qualitativeEigenschaftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(qeDAO.getAll()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));

        qualitativeEigenschaftService.init();
    }

    @Test
    void testAddQualitativeEigenschaft() {
        qualitativeEigenschaftService.addQualitativeEigenschaft(new QualitativeEigenschaft(0, "name"));
    }

    @Test
    void testGetAllQualitativeEigenschaften() {
        when(qeDAO.getAllQlEminusQnE()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));

        List<QualitativeEigenschaft> result = qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testRemove() {
        qualitativeEigenschaftService.remove(new QualitativeEigenschaft(0, "name"));
    }

    @Test
    void testEdit() {
        qualitativeEigenschaftService.edit(new QualitativeEigenschaft(0, "name"));
    }

    @Test
    void testGetReferences() {
        when(prozessSchrittParameterService.getParameterList()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))));

        List<ProzessSchrittParameter> result = qualitativeEigenschaftService.getReferences(new QualitativeEigenschaft(0, "name"));
        Assertions.assertEquals(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), result);
    }

    @Test
    void testGetQlEById() {
        when(qeDAO.getQlEById(anyInt())).thenReturn(new QualitativeEigenschaft(0, "name"));

        QualitativeEigenschaft result = qualitativeEigenschaftService.getQlEById(0);
        Assertions.assertEquals(new QualitativeEigenschaft(0, "name"), result);
    }

    @Test
    void testSetEigenschaften() {
        qualitativeEigenschaftService.setEigenschaften(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetQeDAO() {
        qualitativeEigenschaftService.setQeDAO(new QualitativeEigenschaftDAO());
    }

    @Test
    void testSetProzessSchrittParameterService() {
        qualitativeEigenschaftService.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme