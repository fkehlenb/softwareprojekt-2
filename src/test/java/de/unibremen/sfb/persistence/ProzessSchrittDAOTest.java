package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittDAO prozessSchrittDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateProzessSchrittException {
        prozessSchrittDAO.persist(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 35), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
    }

    @Test
    void testUpdate() throws ProzessSchrittNotFoundException {
        prozessSchrittDAO.update(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 35), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
    }

    @Test
    void testRemove() throws ProzessSchrittNotFoundException {
        prozessSchrittDAO.remove(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 35), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
    }

    @Test
    void testGet() {
        Class<ProzessSchritt> result = prozessSchrittDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetObjById() throws ProzessSchrittNotFoundException {
        ProzessSchritt result = prozessSchrittDAO.getObjById(0);
        Assertions.assertEquals(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 35), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>()), result);
    }

    @Test
    void testGetAll() {
        List<ProzessSchritt> result = prozessSchrittDAO.getAll();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 35), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>())), result);
    }

    @Test
    void testGetAllAvailable() {
        List<ProzessSchritt> result = prozessSchrittDAO.getAllAvailable();
        Assertions.assertEquals(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")), "duration", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), "attribute", Arrays.<ProzessSchrittLog>asList(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 35), "zustandsAutomat")), "name", true, 0,new ArrayList<>(),new ArrayList<>())), result);
    }
}

