package de.unibremen.sfb.persistence;

import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatZustaende;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;

import static org.mockito.Mockito.*;

class ProzessSchrittZustandsAutomatZustaendeDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittZustandsAutomatZustaendeDAO prozessSchrittZustandsAutomatZustaendeDAO;
    @Mock
    ProzessSchrittZustandsAutomatZustaende prozessSchrittZustandsAutomatZustaende;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() {
        when(prozessSchrittZustandsAutomatZustaende.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(prozessSchrittZustandsAutomatZustaende);
        when(em.contains(prozessSchrittZustandsAutomatZustaende)).thenReturn(true);
        prozessSchrittZustandsAutomatZustaendeDAO.persist(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
    }

    @Test
    void testUpdate() {
        when(prozessSchrittZustandsAutomatZustaende.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(prozessSchrittZustandsAutomatZustaende);
        when(em.contains(prozessSchrittZustandsAutomatZustaende)).thenReturn(true);
        prozessSchrittZustandsAutomatZustaendeDAO.update(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
    }

    @Test
    void testRemove() {
        prozessSchrittZustandsAutomatZustaendeDAO.remove(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
    }

    @Test
    void testGetById() {
        ProzessSchrittZustandsAutomatZustaende result = prozessSchrittZustandsAutomatZustaendeDAO.getById(0);
        Assertions.assertEquals(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")), result);
    }
}

