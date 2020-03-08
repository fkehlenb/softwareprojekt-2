package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.model.Auftrag;
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

class AuftragDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    AuftragDAO auftragDAO;
    @Mock
    Auftrag auftrag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateAuftragException {
        auftragDAO.persist(new Auftrag());
    }

    @Test
    void testUpdate() throws AuftragNotFoundException {
        when(auftrag.getPkID()).thenReturn(0);
        when(em.find(any(), any())).thenReturn(auftrag);
        when(em.contains(auftrag)).thenReturn(true);
        auftragDAO.update(new Auftrag());
    }

    @Test
    void testRemove() throws AuftragNotFoundException {
        when(auftrag.getPkID()).thenReturn(0);
        when(em.find(any(), any())).thenReturn(auftrag);
        when(em.contains(auftrag)).thenReturn(true);
        auftragDAO.remove(new Auftrag());
    }

    @Test
    void testGet() {
        Class<Auftrag> result = auftragDAO.get();
        Assertions.assertEquals(Auftrag.class, result);
    }

    @Test
    void testGetObjById() throws AuftragNotFoundException {
        Auftrag result = auftragDAO.getObjById(0);
        Assertions.assertEquals(new Auftrag(), result);
    }

    @Test
    void testGetAll() {
        List<Auftrag> result = auftragDAO.getAll();
        Assertions.assertEquals(Arrays.<Auftrag>asList(new Auftrag()), result);
    }
}

