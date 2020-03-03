package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.*;

class ProzessSchrittZustandsAutomatServiceTest {
    @Mock
    ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;
    @InjectMocks
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

//    @Test
//    void testDelete() {
//        try {
//            prozessSchrittZustandsAutomatService.delete(Arrays.<ProzessSchrittZustandsAutomat>asList(new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))));
//        } catch (ProzessSchrittZustandsAutomatNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testEdit() {
//        try {
//            prozessSchrittZustandsAutomatService.edit(new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
//        } catch (ProzessSchrittZustandsAutomatNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testAdd() {
//        try {
//            prozessSchrittZustandsAutomatService.add(new ProzessSchrittZustandsAutomat(0, "current", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
//        } catch (DuplicateProzessSchrittZustandsAutomatException e) {
//            e.printStackTrace();
//        }
//    }
}