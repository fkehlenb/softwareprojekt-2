package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragsLogNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragsLogException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragsLogDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AuftragsLogsServiceTest {
    @Mock
    List<AuftragsLog> auftragsLogs;
    @Mock
    List<ProzessKettenVorlage> pkv;
    @Mock
    AuftragsLogDAO auftragsLogDAO;
    @Mock
    Logger log;
    @InjectMocks
    AuftragsLogsService auftragsLogsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUpdate() {
        try {
            auftragsLogsService.update(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 28)));
        } catch (AuftragsLogNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAdd() {
        try {
            auftragsLogsService.add(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 28)));
        } catch (DuplicateAuftragsLogException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testEdit() {
        auftragsLogsService.edit(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 28)));
    }


}