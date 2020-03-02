package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateTraegerArtException;
import de.unibremen.sfb.exception.TraegerArtNotFoundException;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.persistence.TraegerArtDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class TraegerArtServiceTest {
    @Mock
    List<TraegerArt> verTraeger;
    @Mock
    TraegerArtDAO traegerArtDAO;
    @Mock
    List<TraegerArt> traegerArt;
    @Mock
    TraegerArt traegerAr;

    @InjectMocks
    TraegerArtService traegerArtService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddTraegerArt() {
        try {
            traegerArtService.addTraegerArt(new TraegerArt("art"));
        } catch (DuplicateTraegerArtException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdateTragerArt() {
        try {
            traegerArtService.updateTragerArt(new TraegerArt("art"));
        } catch (TraegerArtNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemoveTraegerArt() {
        try {
            traegerArtService.removeTraegerArt(new TraegerArt("art"));
        } catch (TraegerArtNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetByName() throws TraegerArtNotFoundException {

        when(traegerArtDAO.getByName(anyString())).thenReturn(traegerAr);

        TraegerArt result = traegerArtService.getByName("taName");

        Assertions.assertEquals(traegerAr, result);
    }

    @Test
    void testGetById() throws TraegerArtNotFoundException {

        when(traegerArtDAO.getById(anyInt())).thenReturn(traegerAr);
        TraegerArt result = traegerArtService.getById(0);
        Assertions.assertEquals(traegerAr, result);
    }

    @Test
    void testGetAll() {
        when(traegerArtDAO.getAll()).thenReturn(traegerArt);

        List<TraegerArt> result = traegerArtService.getAll();
        Assertions.assertEquals(traegerArt, result);
    }
}