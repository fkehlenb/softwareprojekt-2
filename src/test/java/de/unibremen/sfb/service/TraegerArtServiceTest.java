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

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TraegerArtServiceTest {
    @Mock
    List<TraegerArt> verTraeger;
    @Mock
    TraegerArtDAO traegerArtDAO;
    @Mock
    List<TraegerArt> traegerArt;
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
    void testGetByName() {
        try {
            when(traegerArtDAO.getByName(anyString())).thenReturn(new TraegerArt("art"));
        } catch (TraegerArtNotFoundException e) {
            e.printStackTrace();
        }

        TraegerArt result = null;
        try {
            result = traegerArtService.getByName("taName");
        } catch (TraegerArtNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new TraegerArt("art"), result);
    }

    @Test
    void testGetById() {
        try {
            when(traegerArtDAO.getById(anyInt())).thenReturn(new TraegerArt("art"));
        } catch (TraegerArtNotFoundException e) {
            e.printStackTrace();
        }

        TraegerArt result = null;
        try {
            result = traegerArtService.getById(0);
        } catch (TraegerArtNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new TraegerArt("art"), result);
    }

    @Test
    void testGetAll() {
        when(traegerArtDAO.getAll()).thenReturn(Arrays.<TraegerArt>asList(new TraegerArt("art")));

        List<TraegerArt> result = traegerArtService.getAll();
        Assertions.assertEquals(Arrays.<TraegerArt>asList(new TraegerArt("art")), result);
    }
}