package de.unibremen.sfb.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class AuftragsLogTest {
    //Field erstellt of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field start of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field beendet of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field archiviert of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    AuftragsLog auftragsLog = new AuftragsLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 4));

    @Test
    void testSetValidData() {
        auftragsLog.setValidData(true);
    }

    @Test
    void testSetId() {
        auftragsLog.setId(0);
    }

    @Test
    void testSetErstellt() {
        auftragsLog.setErstellt(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 4));
    }

    @Test
    void testSetStart() {
        auftragsLog.setStart(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 4));
    }

    @Test
    void testSetBeendet() {
        auftragsLog.setBeendet(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 4));
    }

    @Test
    void testSetArchiviert() {
        auftragsLog.setArchiviert(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 49, 4));
    }
}