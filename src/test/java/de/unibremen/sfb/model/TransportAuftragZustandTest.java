package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TransportAuftragZustandTest {
    //Field ERSTELLT of type TransportAuftragZustand - was not mocked since Mockito doesn't mock enums
    //Field ABGEHOLT of type TransportAuftragZustand - was not mocked since Mockito doesn't mock enums
    //Field ABGELIEFERT of type TransportAuftragZustand - was not mocked since Mockito doesn't mock enums

    @Test
    void testValues() {
        TransportAuftragZustand[] result = TransportAuftragZustand.values();
        Assertions.assertArrayEquals(new TransportAuftragZustand[]{TransportAuftragZustand.ERSTELLT}, result);
    }

    @Test
    void testValueOf() {
        TransportAuftragZustand result = TransportAuftragZustand.valueOf("name");
        Assertions.assertEquals(TransportAuftragZustand.ERSTELLT, result);
    }
}

