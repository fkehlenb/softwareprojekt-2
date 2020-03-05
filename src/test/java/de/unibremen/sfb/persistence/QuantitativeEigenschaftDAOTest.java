package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateQuantitativeEigenschaftException;
import de.unibremen.sfb.exception.QuantitativeEingenschaftNotFoundException;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
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

class QuantitativeEigenschaftDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    QuantitativeEigenschaftDAO quantitativeEigenschaftDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateQuantitativeEigenschaftException {
        quantitativeEigenschaftDAO.persist(new QuantitativeEigenschaft(0, "name"));
    }

    @Test
    void testUpdate() throws QuantitativeEingenschaftNotFoundException {
        quantitativeEigenschaftDAO.update(new QuantitativeEigenschaft(0, "name"));
    }

    @Test
    void testRemove() throws QuantitativeEingenschaftNotFoundException {
        quantitativeEigenschaftDAO.remove(new QuantitativeEigenschaft(0, "name"));
    }

    @Test
    void testGetAll() {
        List<QuantitativeEigenschaft> result = quantitativeEigenschaftDAO.getAll();
        Assertions.assertEquals(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testFindQnEById() {
        QuantitativeEigenschaft result = quantitativeEigenschaftDAO.findQnEById(0);
        Assertions.assertEquals(new QuantitativeEigenschaft(0, "name"), result);
    }

    @Test
    void testGet() {
        Class<QuantitativeEigenschaft> result = quantitativeEigenschaftDAO.get();
        Assertions.assertEquals(null, result);
    }
}

