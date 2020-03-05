package de.unibremen.sfb.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class KommentarTest {
    //Field dateTime of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    Kommentar kommentar = new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 47, 1), "text");

    @Test
    void testSetValidData() {
        kommentar.setValidData(true);
    }

    @Test
    void testSetId() {
        kommentar.setId(0);
    }

    @Test
    void testSetDateTime() {
        kommentar.setDateTime(LocalDateTime.of(2020, Month.MARCH, 5, 16, 47, 1));
    }

    @Test
    void testSetText() {
        kommentar.setText("text");
    }
}

