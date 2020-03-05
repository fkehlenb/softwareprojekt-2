package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTraegerArtException;
import de.unibremen.sfb.exception.TraegerArtNotFoundException;
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

class TraegerArtDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    TraegerArtDAO traegerArtDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateTraegerArtException {
        traegerArtDAO.persist(new TraegerArt("art"));
    }

    @Test
    void testUpdate() throws TraegerArtNotFoundException {
        traegerArtDAO.update(new TraegerArt("art"));
    }

    @Test
    void testRemove() throws TraegerArtNotFoundException {
        traegerArtDAO.remove(new TraegerArt("art"));
    }

    @Test
    void testGet() {
        Class<TraegerArt> result = traegerArtDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetAll() {
        List<TraegerArt> result = traegerArtDAO.getAll();
        Assertions.assertEquals(Arrays.<TraegerArt>asList(new TraegerArt("art")), result);
    }

    @Test
    void testGetByName() throws TraegerArtNotFoundException {
        TraegerArt result = traegerArtDAO.getByName("taName");
        Assertions.assertEquals(new TraegerArt("art"), result);
    }

    @Test
    void testGetById() throws TraegerArtNotFoundException {
        TraegerArt result = traegerArtDAO.getById(0);
        Assertions.assertEquals(new TraegerArt("art"), result);
    }
}
