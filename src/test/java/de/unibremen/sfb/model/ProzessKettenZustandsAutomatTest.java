package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProzessKettenZustandsAutomatTest {
    //Field INSTANZIIERT of type ProzessKettenZustandsAutomat - was not mocked since Mockito doesn't mock enums
    //Field FREIGEGEBEN of type ProzessKettenZustandsAutomat - was not mocked since Mockito doesn't mock enums
    //Field GESTARTET of type ProzessKettenZustandsAutomat - was not mocked since Mockito doesn't mock enums
    //Field ABGEBROCHEN of type ProzessKettenZustandsAutomat - was not mocked since Mockito doesn't mock enums
    //Field DURCHGEFUEHRT of type ProzessKettenZustandsAutomat - was not mocked since Mockito doesn't mock enums
    //Field ABGELEHNT of type ProzessKettenZustandsAutomat - was not mocked since Mockito doesn't mock enums

    @Test
    void testValues() {
        ProzessKettenZustandsAutomat[] result = ProzessKettenZustandsAutomat.values();
        Assertions.assertArrayEquals(new ProzessKettenZustandsAutomat[]{ProzessKettenZustandsAutomat.INSTANZIIERT}, result);
    }

    @Test
    void testValueOf() {
        ProzessKettenZustandsAutomat result = ProzessKettenZustandsAutomat.valueOf("name");
        Assertions.assertEquals(ProzessKettenZustandsAutomat.INSTANZIIERT, result);
    }
}

