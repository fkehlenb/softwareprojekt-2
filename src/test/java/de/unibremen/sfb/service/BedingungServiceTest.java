package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.BedingungNotFoundException;
import de.unibremen.sfb.exception.DuplicateBedingungException;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.persistence.BedingungDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class BedingungServiceTest {
    @Mock
    List<Bedingung> bs;
    @Mock
    BedingungDAO bedingungDAO;
    @Mock
    Logger log;
    @InjectMocks
    BedingungService bedingungService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(bedingungDAO.getAll()).thenReturn(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0)));

        bedingungService.init();
    }

    @Test
    void testAddES() {
        try {
            bedingungService.addES(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
        } catch (DuplicateBedingungException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testLoescheES() {
        when(bedingungDAO.getAll()).thenReturn(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0)));

        try {
            bedingungService.loescheES(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
        } catch (BedingungNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testFindByID() {
        when(bedingungDAO.findById(anyInt())).thenReturn(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));

        Bedingung result = bedingungService.findByID(0);
        Assertions.assertEquals(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0), result);
    }

    @Test
    void testGetAll() {
        when(bedingungDAO.getAll()).thenReturn(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0)));

        List<Bedingung> result = bedingungService.getAll();
        Assertions.assertEquals(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0)), result);
    }

    @Test
    void testUpdateES() {
        try {
            bedingungService.updateES(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
        } catch (BedingungNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddBedingung() {
        bedingungService.addBedingung(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
    }

    @Test
    void testRemove() {
        bedingungService.remove(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
    }

    @Test
    void testEdit() {
        bedingungService.edit(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0));
    }

    @Test
    void testSetBs() {
        bedingungService.setBs(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(null), 0)));
    }

    @Test
    void testSetBedingungDAO() {
        bedingungService.setBedingungDAO(new BedingungDAO());
    }
}