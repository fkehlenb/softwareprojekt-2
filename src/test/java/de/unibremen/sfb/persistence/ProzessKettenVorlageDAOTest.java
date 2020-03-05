package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessKettenVorlageDAOTest {
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessKettenVorlageDAO prozessKettenVorlageDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateProzessKettenVorlageException {
        prozessKettenVorlageDAO.persist(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0))));
    }

    @Test
    void testUpdate() throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageDAO.update(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0))));
    }

    @Test
    void testRemove() throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageDAO.remove(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0))));
    }

    @Test
    void testGetAll() {
        List<ProzessKettenVorlage> result = prozessKettenVorlageDAO.getAll();
        Assertions.assertEquals(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0)))), result);
    }

    @Test
    void testGet() {
        Class<ProzessKettenVorlage> result = prozessKettenVorlageDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetObjById() throws ProzessKettenVorlageNotFoundException {
        ProzessKettenVorlage result = prozessKettenVorlageDAO.getObjById(0);
        Assertions.assertEquals(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0))), result);
    }
}

