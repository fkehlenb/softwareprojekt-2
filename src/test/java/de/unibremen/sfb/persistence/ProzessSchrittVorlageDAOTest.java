package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
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

class ProzessSchrittVorlageDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittVorlageDAO prozessSchrittVorlageDAO;
    @Mock
    ProzessKettenVorlage prozessKettenVorlage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateProzessSchrittVorlageException {
        prozessSchrittVorlageDAO.persist(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0));
    }

    @Test
    void testUpdate() throws ProzessSchrittVorlageNotFoundException {
        when(prozessKettenVorlage.getPkvID()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(prozessKettenVorlage);
        when(em.contains(prozessKettenVorlage)).thenReturn(true);
        prozessSchrittVorlageDAO.update(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0));
    }

    @Test
    void testRemove() throws ProzessSchrittVorlageNotFoundException {
        when(prozessKettenVorlage.getPkvID()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(prozessKettenVorlage);
        when(em.contains(prozessKettenVorlage)).thenReturn(true);
        prozessSchrittVorlageDAO.remove(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0));
    }

    @Test
    void testGet() {
        Class<ProzessSchrittVorlage> result = prozessSchrittVorlageDAO.get();
        Assertions.assertEquals(ProzessSchrittVorlage.class, result);
    }

    @Test
    void testGetObjById() throws ProzessSchrittVorlageNotFoundException {
        ProzessSchrittVorlage result = prozessSchrittVorlageDAO.getObjById(0);
        Assertions.assertEquals(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0), result);
    }

    @Test
    void testGetAll() {
        List<ProzessSchrittVorlage> result = prozessSchrittVorlageDAO.getAll();
        Assertions.assertEquals(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0)), result);
    }
}

