package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateKommentarException;
import de.unibremen.sfb.exception.KommentarNotFoundException;
import de.unibremen.sfb.model.Kommentar;
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

class KommentarDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    KommentarDAO kommentarDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateKommentarException {
        kommentarDAO.persist(new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 20), "text"));
    }

    @Test
    void testUpdate() throws KommentarNotFoundException {
        kommentarDAO.update(new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 20), "text"));
    }

    @Test
    void testGet() {
        Class<Kommentar> result = kommentarDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testRemove() throws KommentarNotFoundException {
        kommentarDAO.remove(new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 52, 20), "text"));
    }
}

