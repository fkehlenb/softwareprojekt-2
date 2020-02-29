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
        List<QualitativeEigenschaft> result = prozessSchrittParameterService.getEigenschaften(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(null), result);
    }

    @Test
    void testLoscheParameter() {
        prozessSchrittParameterService.loscheParameter(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
    }

    @Test
    void testGetPSPByID() {
        try {
            when(prozessSchrittParameterDAO.getPSPByID(anyInt())).thenReturn(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
        } catch (ProzessSchrittParameterNotFoundException e) {
            e.printStackTrace();
        }

        ProzessSchrittParameter result = prozessSchrittParameterService.getPSPByID(0);
        Assertions.assertEquals(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)), result);
    }

    @Test
    void testFindByName() {
        ProzessSchrittParameter result = prozessSchrittParameterService.findByName("name");
        Assertions.assertEquals(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)), result);
    }

    @Test
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
        prozessSchrittParameterService.addProcessSP(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
    }

    @Test
    void testGetAll() {
        when(prozessSchrittParameterDAO.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));

        List<ProzessSchrittParameter> result = prozessSchrittParameterService.getAll();
        Assertions.assertEquals(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))), result);
    }

    @Test
    void testUpdate() {
        prozessSchrittParameterService.update(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null)));
    }

    @Test
    void testFindByQei() {
        when(prozessSchrittParameterDAO.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));

        List<ProzessSchrittParameter> result = prozessSchrittParameterService.findByQei(0);
        Assertions.assertEquals(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))), result);
    }

    @Test
    void testSetParameterList() {
        prozessSchrittParameterService.setParameterList(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
    }

    @Test
    void testSetProzessSchrittParameterDAO() {
        prozessSchrittParameterService.setProzessSchrittParameterDAO(new ProzessSchrittParameterDAO());
    }

    @Test
    void testEquals() {
        boolean result = prozessSchrittParameterService.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = prozessSchrittParameterService.canEqual("other");
        Assertions.assertEquals(true, result);
    }
}