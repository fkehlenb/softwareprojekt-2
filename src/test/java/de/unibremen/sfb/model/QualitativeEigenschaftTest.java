package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QualitativeEigenschaftTest {
    QualitativeEigenschaft qualitativeEigenschaft = new QualitativeEigenschaft(0, "name");

    @Test
    void testSetValidData() {
        qualitativeEigenschaft.setValidData(true);
    }

    @Test
    void testSetId() {
        qualitativeEigenschaft.setId(0);
    }

    @Test
    void testSetName() {
        qualitativeEigenschaft.setName("name");
    }

    @Test
    void testEquals() {
        boolean result = qualitativeEigenschaft.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = qualitativeEigenschaft.canEqual("other");
        Assertions.assertEquals(false, result);
    }



    @Test
    void testToString() {
        String result = qualitativeEigenschaft.toString();
        Assertions.assertEquals("QualitativeEigenschaft name", result);
    }
}

