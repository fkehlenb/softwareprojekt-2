package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessKettenVorlageServiceTest {
    @Mock
    ArrayList<ProzessKettenVorlage> pkVorlagen;
    @Mock
    ProzessKettenVorlage prozessKettenVorlage;
    @Mock
    ProzessKettenVorlageDAO prozessKettenVorlageDAO;
    @Mock
    List<ProzessSchrittVorlage> psVorlagen;
    @Mock
    ProzessKettenVorlageDAO pkvDAO;
    @Mock
    ProzessSchrittVorlageService prozessSchrittVorlageService;
    @Mock
    Logger log;

    @InjectMocks
    ProzessKettenVorlageService prozessKettenVorlageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(prozessSchrittVorlageService.getVorlagen()).thenReturn(psVorlagen);
        prozessKettenVorlageService.init();
    }

    @Test
    void testGetPKV() throws ProzessKettenVorlageNotFoundException {
        when(pkvDAO.getObjById(anyInt())).thenReturn(prozessKettenVorlage);
        ProzessKettenVorlage result = prozessKettenVorlageService.getPKV(0);
        Assertions.assertEquals(prozessKettenVorlage, result);
    }

    @Test
    void testGetProzessKettenVorlagen() {
        when(pkvDAO.getAll()).thenReturn(pkVorlagen);
        List<ProzessKettenVorlage> result = prozessKettenVorlageService.getProzessKettenVorlagen();
        Assertions.assertEquals(pkVorlagen, result);
    }
    @Test
    void testPersist() throws DuplicateProzessKettenVorlageException {

        prozessKettenVorlageService.persist(prozessKettenVorlage);
        verify(pkvDAO).persist(prozessKettenVorlage);
    }

    @Test
    void testByID() throws ProzessKettenVorlageNotFoundException {

        when(pkvDAO.getObjById(anyInt())).thenReturn(prozessKettenVorlage);

        ProzessKettenVorlage result = prozessKettenVorlageService.ByID(0);

        Assertions.assertEquals(prozessKettenVorlage, result);
    }

    //@Test
    void testEdit() throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageService.edit(prozessKettenVorlage);
        verify(prozessKettenVorlageDAO).update(prozessKettenVorlage);
    }


    @Test
    void testGetPKVs() {
        when(pkvDAO.getAll()).thenReturn(pkVorlagen);
        List<ProzessKettenVorlage> result = prozessKettenVorlageService.getPKVs();
        verify(pkvDAO).getAll();
    }
}