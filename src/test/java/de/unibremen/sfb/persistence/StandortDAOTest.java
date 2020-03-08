package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
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

class StandortDAOTest {
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    StandortDAO standortDAO;
    @Mock
    Standort standort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateStandortException {
        standortDAO.persist(new Standort(0, "ort"));
    }

    @Test
    void testUpdate() throws StandortNotFoundException {
        when(standort.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(standort);
        when(em.contains(standort)).thenReturn(true);
        standortDAO.update(new Standort(0, "ort"));
    }

    @Test
    void testRemove() throws StandortNotFoundException {
        when(standort.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(standort);
        when(em.contains(standort)).thenReturn(true);
        standortDAO.remove(new Standort(0, "ort"));
    }

    @Test
    void testGet() {
        Class<Standort> result = standortDAO.get();
        Assertions.assertEquals(Standort.class, result);
    }

    @Test
    void testGetObjById() throws StandortNotFoundException {
        Standort result = standortDAO.getObjById(0);
        Assertions.assertEquals(new Standort(0, "ort"), result);
    }

    @Test
    void testGetByOrt() throws StandortNotFoundException {
        Standort result = standortDAO.getByOrt("l");
        Assertions.assertEquals(new Standort(0, "ort"), result);
    }

    @Test
    void testGetAll() {
        List<Standort> result = standortDAO.getAll();
        Assertions.assertEquals(Arrays.<Standort>asList(new Standort(0, "ort")), result);
    }
}

