package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittLog;
import de.unibremen.sfb.persistence.ProzessSchrittLogDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class ProzessSchrittLogServiceTest {
    @Mock
    ProzessSchrittLogDAO pslDAO;

    @InjectMocks
    ProzessSchrittLogService prozessSchrittLogService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCloseLog() {
        try {
            prozessSchrittLogService.closeLog(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 20), "zustandsAutomat"), LocalDateTime.now());
        } catch (ProzessSchrittLogNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testNewLog() {
        ProzessSchrittLog result = null;
        try {
            result = prozessSchrittLogService.newLog("z", LocalDateTime.now());
            verify(pslDAO).persist(result);
        } catch (DuplicateProzessSchrittLogException e) {
            e.printStackTrace();
        }

    }

    @Test
    void testAdd() {
        try {
            prozessSchrittLogService.add(new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 29, 20), "zustandsAutomat"));
            verify(pslDAO).persist(any());
        } catch (DuplicateProzessSchrittLogException e) {
            e.printStackTrace();
        }
    }
}