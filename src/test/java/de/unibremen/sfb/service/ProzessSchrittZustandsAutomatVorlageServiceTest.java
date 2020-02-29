package de.unibremen.sfb.service;

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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testDelete() {
        try {
            prozessSchrittZustandsAutomatVorlageService.delete(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEdit() {
        try {
            prozessSchrittZustandsAutomatVorlageService.edit(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
        } catch (ProzessSchrittVorlageNotFoundException e) {
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
        when(prozessSchrittZustandsAutomatVorlageDAO.getAll()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));

        List<ProzessSchrittZustandsAutomatVorlage> result = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        Assertions.assertEquals(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")), result);
    }

    @Test
    void testAddVorlage() {
        prozessSchrittZustandsAutomatVorlageService.addVorlage(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testGetByID() {
        try {
            when(prozessSchrittZustandsAutomatVorlageDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
        } catch (ProzessSchrittZustandsAutomatVorlageNotFoundException e) {
            e.printStackTrace();
        }

        ProzessSchrittZustandsAutomatVorlage result = null;
        try {
            result = prozessSchrittZustandsAutomatVorlageService.getByID(0);
        } catch (ProzessSchrittZustandsAutomatVorlageNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme