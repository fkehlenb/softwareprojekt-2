package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.persistence.TraegerDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TraegerServiceTest {
    @Mock
    TraegerDAO traegerDAO;
    @InjectMocks
    TraegerService traegerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() {
        try {
            traegerService.persist(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort")));
        } catch (DuplicateTraegerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try {
            traegerService.update(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort")));
        } catch (TraegerNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemove() {
        try {
            traegerService.remove(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort")));
        } catch (TraegerNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetTraegerById() {
        try {
            when(traegerDAO.getObjById(anyInt())).thenReturn(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort")));
        } catch (TraegerNotFoundException e) {
            e.printStackTrace();
        }

        Traeger result = null;
        try {
            result = traegerService.getTraegerById(0);
        } catch (TraegerNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort")), result);
    }

    @Test
    void testGetAll() {
        when(traegerDAO.getAll()).thenReturn(Arrays.<Traeger>asList(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort"))));

        List<Traeger> result = traegerService.getAll();
        Assertions.assertEquals(Arrays.<Traeger>asList(new Traeger(0, new TraegerArt("art"), new Standort(0, "ort"))), result);
    }
}