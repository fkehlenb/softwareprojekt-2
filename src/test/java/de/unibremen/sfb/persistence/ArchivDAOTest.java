package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.ArchivNotFoundException;
import de.unibremen.sfb.exception.DuplicateArchivException;
import de.unibremen.sfb.model.Archiv;
import de.unibremen.sfb.model.Auftrag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;

import static org.mockito.Mockito.*;

class ArchivDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    ArchivDAO archivDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateArchivException {
        archivDAO.persist(new Archiv(new Auftrag()));
    }

    @Test
    void testUpdate() throws ArchivNotFoundException {
        archivDAO.update(new Archiv(new Auftrag()));
    }

    @Test
    void testRemove() throws ArchivNotFoundException {
        archivDAO.remove(new Archiv(new Auftrag()));
    }

    @Test
    void testGet() {
        Class<Archiv> result = archivDAO.get();
        Assertions.assertEquals(  de.unibremen.sfb.model.Archiv.class, result);
    }

    @Test
    void testGetObjById() throws ArchivNotFoundException {
        Archiv result = archivDAO.getObjById(0);
        Assertions.assertEquals(new Archiv(new Auftrag()), result);
    }
}

