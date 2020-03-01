package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittParameterServiceTest {
    @Mock
    List<ProzessSchrittParameter> parameterList;
    @Mock
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;
    @Mock
    Logger log;
    @Mock
    ProzessSchrittParameter prozessSchrittParameter;
    @Mock
    List<QualitativeEigenschaft> qualitativeEigenschafts;
    @Mock
    List<ProzessSchrittParameter> prozessSchrittParameters;
    @InjectMocks
    ProzessSchrittParameterService prozessSchrittParameterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(prozessSchrittParameterDAO.getAll()).thenReturn(parameterList);
        prozessSchrittParameterService.init();
    }

    @Test
    void testGetEigenschaften() {
        when(prozessSchrittParameter.getQualitativeEigenschaften()).thenReturn(qualitativeEigenschafts);
        List<QualitativeEigenschaft> result = prozessSchrittParameterService.getEigenschaften(prozessSchrittParameter);
        Assertions.assertEquals(prozessSchrittParameter.getQualitativeEigenschaften(), result);
    }

    @Test
    void testLoscheParameter() throws ProzessSchrittParameterNotFoundException {
        prozessSchrittParameterService.loscheParameter(prozessSchrittParameter);
        verify(prozessSchrittParameterDAO).remove(prozessSchrittParameter);
    }

    @Test
    void testGetPSPByID() throws ProzessSchrittParameterNotFoundException {

        when(prozessSchrittParameterDAO.getPSPByID(anyInt())).thenReturn(prozessSchrittParameter);
        ProzessSchrittParameter result = prozessSchrittParameterService.getPSPByID(0);
        Assertions.assertEquals(prozessSchrittParameter, result);
    }

    @Test
    void testFindByName() {
        ProzessSchrittParameter result = prozessSchrittParameterService.findByName("name");
    }

    //@Test To see
    void testToJSON() {
        File result = null;
        try {
            result = prozessSchrittParameterService.toJSON(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new File(getClass().getResource("/de/unibremen/sfb/service/PleaseReplaceMeWithTestFile.txt").getFile()), result);
    }

    @Test
    void testAddProcessSP() {
        prozessSchrittParameterService.addProcessSP(prozessSchrittParameter);
    }

    @Test
    void testGetAll() {
        when(prozessSchrittParameterDAO.getAll()).thenReturn(prozessSchrittParameters);
        List<ProzessSchrittParameter> result = prozessSchrittParameterService.getAll();
        Assertions.assertEquals(prozessSchrittParameters, result);
    }

    @Test
    void testUpdate() {
        prozessSchrittParameterService.update(prozessSchrittParameter);
    }

    //@Test
    void testFindByQei() {
        when(prozessSchrittParameterDAO.getAll()).thenReturn(prozessSchrittParameters);
        List<ProzessSchrittParameter> result = prozessSchrittParameterService.findByQei(0);
        Assertions.assertEquals(prozessSchrittParameters, result);
    }

    @Test
    void testSetParameterList() {
        prozessSchrittParameterService.setParameterList(prozessSchrittParameters);
    }

    @Test
    void testSetProzessSchrittParameterDAO() {
        prozessSchrittParameterService.setProzessSchrittParameterDAO(new ProzessSchrittParameterDAO());
    }

    @Test
    void testEquals() {
        boolean result = prozessSchrittParameterService.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = prozessSchrittParameterService.canEqual("other");
        Assertions.assertEquals(false, result);
    }
}