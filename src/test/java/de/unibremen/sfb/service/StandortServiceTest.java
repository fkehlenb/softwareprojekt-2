package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class StandortServiceTest {
    @Mock
    StandortDAO standortDAO;
    @Mock
    List<Standort> standorte;
    @Mock
    Standort standort;
    @InjectMocks
    StandortService standortService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() {
        standortService.persist(new Standort(0, "ort"));
    }

    @Test
    void testInit() {
        when(standortDAO.getAll()).thenReturn(Arrays.<Standort>asList(new Standort(0, "ort")));

        standortService.init();
    }

    @Test
    void testGetStandorte() {
        when(standortDAO.getAll()).thenReturn(standorte);
        List<Standort> result = standortService.getStandorte();
        Assertions.assertEquals(standorte, result);
    }

    @Test
    void testRemove() {
        try {
            standortService.remove(new Standort(0, "ort"));
        } catch (StandortNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindByLocation() throws StandortNotFoundException {

        when(standortDAO.getByOrt(anyString())).thenReturn(standort);

        Standort result = standortService.findByLocation("standort");

        Assertions.assertEquals(standort, result);
    }

    @Test
    void testFindById() throws StandortNotFoundException {

        when(standortDAO.getObjById(anyInt())).thenReturn(standort);

        Standort result =standortService.findById(0);

        Assertions.assertEquals(standort, result);
    }

    @Test
    void testAdd() {
        when(standortDAO.getAll()).thenReturn(Arrays.<Standort>asList(new Standort(0, "ort")));

        try {
            standortService.add(new Standort(0, "ort"));
        } catch (DuplicateStandortException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testUpdate() {
        when(standortDAO.getAll()).thenReturn(Arrays.<Standort>asList(new Standort(0, "ort")));

        try {
            standortService.update(new Standort(0, "ort"));
        } catch (StandortNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddStandort() {
        standortService.addStandort(new Standort(0, "ort"));
    }

    @Test
    void testSetStandortDAO() {
        standortService.setStandortDAO(new StandortDAO());
    }

    @Test
    void testSetStandorte() {
        standortService.setStandorte(Arrays.<Standort>asList(new Standort(0, "ort")));
    }
}