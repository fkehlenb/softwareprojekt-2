package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.TraegerDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class TraegerServiceTest {
    @Mock
    TraegerDAO traegerDAO;
    @Mock
    Traeger traeger;
    @Mock
    List<Traeger> traegers;
    @InjectMocks
    TraegerService traegerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
//FIXME
 /*   @Test
    void testPersist() {
        try {
            traegerService.persist(new Traeger(0, new TraegerArt("art"), List.of(new Probe(UUID.randomUUID().toString(),
                    99, ProbenZustand.VORHANDEN, new Standort(UUID.randomUUID().hashCode(), "Langweilig")))));
        } catch (DuplicateTraegerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        try {
            traegerService.update(new Traeger(0, new TraegerArt("art"), List.of(new Probe(UUID.randomUUID().toString(),
                    99, ProbenZustand.VORHANDEN, new Standort(UUID.randomUUID().hashCode(), "Langweilig")))));
        } catch (TraegerNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemove() {
        try {
            traegerService.remove(new Traeger(0, new TraegerArt("art"), List.of(new Probe(UUID.randomUUID().toString(),
                    99, ProbenZustand.VORHANDEN, new Standort(UUID.randomUUID().hashCode(), "Langweilig")))));
        } catch (TraegerNotFoundException e) {
            e.printStackTrace();
        }
    }*/

    @Test
    void testGetTraegerById() throws TraegerNotFoundException {

        when(traegerDAO.getObjById(anyInt())).thenReturn(traeger);

        Traeger result = traegerService.getTraegerById(0);

        Assertions.assertEquals(traeger, result);
    }

    @Test
    void testGetAll() {
        when(traegerDAO.getAll()).thenReturn(traegers);
        List<Traeger> result = traegerService.getAll();
        Assertions.assertEquals(traegers, result);
        verify(traegerDAO).getAll();
    }
}