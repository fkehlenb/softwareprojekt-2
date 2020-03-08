package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatVorlageDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittZustandsAutomatVorlageServiceTest {
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> psvVorlagen;
    @Mock
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;
    @InjectMocks
    ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;
    @Mock
    ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage;
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> prozessSchrittZustandsAutomatVorlages;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDelete() {
        try {
            prozessSchrittZustandsAutomatVorlageService.delete(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEdit() {
        try {
            prozessSchrittZustandsAutomatVorlageService.edit(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testInit() {
        when(prozessSchrittZustandsAutomatVorlageDAO.getAll()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));

        prozessSchrittZustandsAutomatVorlageService.init();
    }

    @Test
    void testGetProzessSchrittZustandsAutomatVorlagen() {
        when(prozessSchrittZustandsAutomatVorlageDAO.getAll()).thenReturn(prozessSchrittZustandsAutomatVorlages);
        List<ProzessSchrittZustandsAutomatVorlage> result = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        Assertions.assertEquals(prozessSchrittZustandsAutomatVorlages, result);
    }

    @Test
    void testAddVorlage() {
        try {
            prozessSchrittZustandsAutomatVorlageService.addVorlage(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
        } catch (DuplicateProzessSchrittZustandsAutomatVorlageException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetByID() throws ProzessSchrittZustandsAutomatVorlageNotFoundException {

        when(prozessSchrittZustandsAutomatVorlageDAO.getById(anyInt())).thenReturn(prozessSchrittZustandsAutomatVorlage);

        ProzessSchrittZustandsAutomatVorlage result = prozessSchrittZustandsAutomatVorlageService.getByID(0);

        Assertions.assertEquals(prozessSchrittZustandsAutomatVorlage, result);
    }
}