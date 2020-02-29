package de.unibremen.sfb.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class ProzessSchrittLogTest {
    //Field gestartet of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field geendet of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    ProzessSchrittLog prozessSchrittLog = new ProzessSchrittLog(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 28), "zustandsAutomat");

    @Test
    void testSetValidData() {
        prozessSchrittLog.setValidData(true);
    }

    @Test
    void testSetId() {
        prozessSchrittLog.setId(0);
    }

    @Test
    void testSetGestartet() {
        prozessSchrittLog.setGestartet(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 28));
    }

    @Test
    void testSetGeendet() {
        prozessSchrittLog.setGeendet(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 28));
    }

    @Test
    void testSetZustandsAutomat() {
        prozessSchrittLog.setZustandsAutomat("zustandsAutomat");
    }
}