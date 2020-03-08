package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.model.TraegerArt;
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

class TraegerDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    TraegerDAO traegerDAO;
    @Mock
    Traeger traeger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateTraegerException {
        traegerDAO.persist(traeger);
    }


    @Test
    void testRemove() throws TraegerNotFoundException {
        traegerDAO.remove(new Traeger(0, "art", Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), new Standort(0, "ort")));
    }

    @Test
    void testGet() {
        Class<Traeger> result = traegerDAO.get();
        Assertions.assertEquals(Traeger.class, result);
    }

    @Test
    void testGetObjById() throws TraegerNotFoundException {
        Traeger result = traegerDAO.getObjById(0);
        Assertions.assertEquals(traeger, result);
    }

    @Test
    void testGetAll() {
        List<Traeger> result = traegerDAO.getAll();
        Assertions.assertEquals("[]", result);
    }
}

