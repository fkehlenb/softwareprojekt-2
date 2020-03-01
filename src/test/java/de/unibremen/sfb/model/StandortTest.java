package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StandortTest {
    Standort standort = new Standort(0, "ort");

    @Test
    void testToString() {
        String result = standort.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetValidData() {
        standort.setValidData(true);
    }

    @Test
    void testSetId() {
        standort.setId(0);
    }

    @Test
    void testSetOrt() {
        standort.setOrt("ort");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme