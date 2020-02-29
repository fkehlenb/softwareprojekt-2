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
    void testPersist() {
        try {
            archivDAO.persist(new Archiv(new Auftrag()));
        } catch (DuplicateArchivException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try {
            archivDAO.update(new Archiv(new Auftrag()));
        } catch (ArchivNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemove() {
        try {
            archivDAO.remove(new Archiv(new Auftrag()));
        } catch (ArchivNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGet() {
        Class<Archiv> result = archivDAO.get();
        Assertions.assertEquals(archivDAO.getClass(), result);
    }

    @Test
    void testGetObjById() {
        Archiv result = null;
        try {
            result = archivDAO.getObjById(0);
        } catch (ArchivNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new Archiv(new Auftrag()), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme