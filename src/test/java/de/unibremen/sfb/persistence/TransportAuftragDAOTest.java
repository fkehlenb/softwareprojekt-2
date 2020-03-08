package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTransportAuftragException;
import de.unibremen.sfb.exception.TransportAuftragNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.TransportAuftrag;
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

class TransportAuftragDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    TransportAuftragDAO transportAuftragDAO;
    @Mock
    TransportAuftrag transportAuftrag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateTransportAuftragException {
        transportAuftragDAO.persist(new TransportAuftrag(LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 41), null, new Standort(0, "ort"), new Standort(0, "ort")));
    }

    @Test
    void testUpdate() throws TransportAuftragNotFoundException {
        transportAuftragDAO.update(new TransportAuftrag(LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 41), null, new Standort(0, "ort"), new Standort(0, "ort")));
    }

    @Test
    void testRemove() throws TransportAuftragNotFoundException {
        transportAuftragDAO.remove(new TransportAuftrag(LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 41), null, new Standort(0, "ort"), new Standort(0, "ort")));
    }

    @Test
    void testGet() {
        Class<TransportAuftrag> result = transportAuftragDAO.get();
        Assertions.assertEquals(TransportAuftrag.class, result);
    }

    @Test
    void testGetTransportAuftragById() throws TransportAuftragNotFoundException {
        TransportAuftrag result = transportAuftragDAO.getTransportAuftragById(0);
        Assertions.assertEquals(new TransportAuftrag(LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 41), null, new Standort(0, "ort"), new Standort(0, "ort")), result);
    }
}

