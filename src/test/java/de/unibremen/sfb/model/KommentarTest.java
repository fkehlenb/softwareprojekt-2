package de.unibremen.sfb.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class KommentarTest {
    //Field dateTime of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    Kommentar kommentar = new Kommentar(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 6), "text");

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
        kommentar.setDateTime(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 50, 6));
    }

    @Test
    void testSetText() {
        kommentar.setText("text");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme