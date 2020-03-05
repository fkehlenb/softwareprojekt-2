package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProzessSchrittArtTest {
    //Field UMFORMEND of type ProzessSchrittArt - was not mocked since Mockito doesn't mock enums
    //Field FAEBEND of type ProzessSchrittArt - was not mocked since Mockito doesn't mock enums
    //Field ERMITTELND of type ProzessSchrittArt - was not mocked since Mockito doesn't mock enums

    @Test
    void testValues() {
        ProzessSchrittArt[] result = ProzessSchrittArt.values();
        Assertions.assertArrayEquals(new ProzessSchrittArt[]{ProzessSchrittArt.UMFORMEND}, result);
    }

    @Test
    void testValueOf() {
        ProzessSchrittArt result = ProzessSchrittArt.valueOf("name");
        Assertions.assertEquals(ProzessSchrittArt.UMFORMEND, result);
    }
}

