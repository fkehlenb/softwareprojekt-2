package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittParameterException;
import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittParameterDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateProzessSchrittParameterException {
        prozessSchrittParameterDAO.persist(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name"))));
    }

    @Test
    void testUpdate() throws ProzessSchrittParameterNotFoundException {
        prozessSchrittParameterDAO.update(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name"))));
    }

    @Test
    void testRemove() throws ProzessSchrittParameterNotFoundException {
        prozessSchrittParameterDAO.remove(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name"))));
    }

    @Test
    void testGet() {
        Class<ProzessSchrittParameter> result = prozessSchrittParameterDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetAll() {
        List<ProzessSchrittParameter> result = prozessSchrittParameterDAO.getAll();
        Assertions.assertEquals(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), result);
    }

    @Test
    void testGetPSPByID() throws ProzessSchrittParameterNotFoundException {
        ProzessSchrittParameter result = prozessSchrittParameterDAO.getPSPByID(0);
        Assertions.assertEquals(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name"))), result);
    }
}

