package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AuftragsPrioritaetTest {
    //Field KEINE of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    //Field ETWAS of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    //Field VIEL of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    //Field HOCH of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    //Field SEHR_HOCH of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums

    @Test
    void testValues() {
        AuftragsPrioritaet[] result = AuftragsPrioritaet.values();
        Assertions.assertArrayEquals(new AuftragsPrioritaet[]{AuftragsPrioritaet.KEINE}, result);
    }

    @Test
    void testValueOf() {
        AuftragsPrioritaet result = AuftragsPrioritaet.valueOf("name");
        Assertions.assertEquals(AuftragsPrioritaet.KEINE, result);
    }
}

