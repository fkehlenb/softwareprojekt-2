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
        try {
            auftragsLogsService.edit(new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 28, 28)));
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testDelete() {
        auftragsLogsService.delete(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, "dauer", "name", "psArt", Arrays.<ExperimentierStation>asList(new ExperimentierStation()), Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"))))));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme