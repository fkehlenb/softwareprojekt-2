package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProbenZustandTest {
    //Field KAPUTT  was not mocked, Mockito doesn't mock enums
    //Field VERLOREN was not mocked since Mockito doesn't mock enums
    //Field VORHANDEN was not mocked since Mockito doesn't mock enums
    //Field ARCHIVIERT was not mocked since Mockito doesn't mock enums

    @Test
    void testValues() {
        ProbenZustand[] result = ProbenZustand.values();
        Assertions.assertArrayEquals(new ProbenZustand[]{ProbenZustand.KAPUTT}, result);
    }

    @Test
    void testValueOf() {
        ProbenZustand result = ProbenZustand.valueOf("name");
        Assertions.assertEquals(ProbenZustand.KAPUTT, result);
    }
}

