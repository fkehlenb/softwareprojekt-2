package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class ProzessSchrittZustandsAutomatDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateProzessSchrittZustandsAutomatException {
        prozessSchrittZustandsAutomatDAO.persist(new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")));
    }

    @Test
    void testUpdate() throws ProzessSchrittZustandsAutomatNotFoundException {
        prozessSchrittZustandsAutomatDAO.update(new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")));
    }

    @Test
    void testRemove() throws ProzessSchrittZustandsAutomatNotFoundException {
        prozessSchrittZustandsAutomatDAO.remove(new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")));
    }

    @Test
    void testGet() {
        Class<ProzessSchrittZustandsAutomat> result = prozessSchrittZustandsAutomatDAO.get();
        Assertions.assertEquals(null, result);
    }
}

