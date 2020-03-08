package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
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

class ProzessSchrittZustandsAutomatVorlageDAOTest {
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;
    @Mock
    ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateProzessSchrittZustandsAutomatVorlageException {
        prozessSchrittZustandsAutomatVorlageDAO.persist(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testUpdate() throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        when(prozessSchrittZustandsAutomatVorlage.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(prozessSchrittZustandsAutomatVorlage);
        when(em.contains(prozessSchrittZustandsAutomatVorlage)).thenReturn(true);
        prozessSchrittZustandsAutomatVorlageDAO.update(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testRemove() throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        when(prozessSchrittZustandsAutomatVorlage.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(prozessSchrittZustandsAutomatVorlage);
        when(em.contains(prozessSchrittZustandsAutomatVorlage)).thenReturn(true);
        prozessSchrittZustandsAutomatVorlageDAO.remove(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testGetAll() {
        List<ProzessSchrittZustandsAutomatVorlage> result = prozessSchrittZustandsAutomatVorlageDAO.getAll();
        Assertions.assertEquals(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), result);
    }

    @Test
    void testGet() {
        Class<ProzessSchrittZustandsAutomatVorlage> result = prozessSchrittZustandsAutomatVorlageDAO.get();
        Assertions.assertEquals(ProzessSchrittZustandsAutomatVorlage.class, result);
    }

    @Test
    void testGetById() throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        ProzessSchrittZustandsAutomatVorlage result = prozessSchrittZustandsAutomatVorlageDAO.getById(0);
        Assertions.assertEquals(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), result);
    }
}

