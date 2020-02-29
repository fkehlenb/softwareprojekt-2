package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TraegerArtTest {
    TraegerArt traegerArt = new TraegerArt("art");

    @Test
    void testToString() {
        String result = traegerArt.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testSetValidData() {
        traegerArt.setValidData(true);
    }

    @Test
    void testSetArt() {
        traegerArt.setArt("art");
    }
}