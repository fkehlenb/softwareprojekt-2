package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.AuftragsLogNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragsLogException;
import de.unibremen.sfb.model.AuftragsLog;
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

class AuftragsLogDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    AuftragsLogDAO auftragsLogDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateAuftragsLogException {
        auftragsLogDAO.persist(new AuftragsLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 11)));
    }

    @Test
    void testUpdate() throws AuftragsLogNotFoundException {
        auftragsLogDAO.update(new AuftragsLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 11)));
    }

    @Test
    void testRemove() throws AuftragsLogNotFoundException {
        auftragsLogDAO.remove(new AuftragsLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 11)));
    }

    @Test
    void testGet() {
        Class<AuftragsLog> result = auftragsLogDAO.get();
        Assertions.assertEquals(null, result);
    }
}

