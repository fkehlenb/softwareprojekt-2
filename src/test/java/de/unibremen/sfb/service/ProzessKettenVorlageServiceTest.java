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
    void testGetPKV() {
        try {
            when(pkvDAO.getObjById(anyInt())).thenReturn(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null))));
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }

        ProzessKettenVorlage result = prozessKettenVorlageService.getPKV(0);
        Assertions.assertEquals(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null))), result);
    }

    @Test
    void testGetProzessKettenVorlagen() {
        when(pkvDAO.getAll()).thenReturn(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)))));

        List<ProzessKettenVorlage> result = prozessKettenVorlageService.getProzessKettenVorlagen();
        Assertions.assertEquals(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)))), result);
    }

    @Test
    void testPersist() {
        try {
            prozessKettenVorlageService.persist(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null))));
        } catch (DuplicateProzessKettenVorlageException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testByID() {
        try {
            when(pkvDAO.getObjById(anyInt())).thenReturn(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null))));
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }

        ProzessKettenVorlage result = null;
        try {
            result = prozessKettenVorlageService.ByID(0);
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null))), result);
    }

    @Test
    void testEdit() {
        try {
            prozessKettenVorlageService.edit(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null))));
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDelete() {
        prozessKettenVorlageService.delete(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)))));
    }

    @Test
    void testGetPKVs() {
        when(pkvDAO.getAll()).thenReturn(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)))));

        List<ProzessKettenVorlage> result = prozessKettenVorlageService.getPKVs();
        Assertions.assertEquals(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(null), Arrays.<Bedingung>asList(null), null)))), result);
    }
}