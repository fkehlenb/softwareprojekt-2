package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class ProzessSchrittLogDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ProzessSchrittLogDAO prozessSchrittLogDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateProzessSchrittLogException {
        prozessSchrittLogDAO.persist(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 39), "zustandsAutomat"));
    }

    @Test
    void testUpdate() throws ProzessSchrittLogNotFoundException {
        prozessSchrittLogDAO.update(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 39), "zustandsAutomat"));
    }

    @Test
    void testRemove() throws ProzessSchrittLogNotFoundException {
        prozessSchrittLogDAO.remove(new ProzessSchrittLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 39), "zustandsAutomat"));
    }

    @Test
    void testGet() {
        Class<ProzessSchrittLog> result = prozessSchrittLogDAO.get();
        Assertions.assertEquals(null, result);
    }
}

