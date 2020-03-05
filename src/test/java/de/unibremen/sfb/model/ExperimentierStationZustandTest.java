package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExperimentierStationZustandTest {
    //Field VERFUEGBAR of type ExperimentierStationZustand - was not mocked since Mockito doesn't mock enums
    //Field BESETZT of type ExperimentierStationZustand - was not mocked since Mockito doesn't mock enums
    //Field KAPUTT of type ExperimentierStationZustand - was not mocked since Mockito doesn't mock enums

    @Test
    void testValues() {
        ExperimentierStationZustand[] result = ExperimentierStationZustand.values();
        Assertions.assertArrayEquals(new ExperimentierStationZustand[]{ExperimentierStationZustand.VERFUEGBAR}, result);
    }

    @Test
    void testValueOf() {
        ExperimentierStationZustand result = ExperimentierStationZustand.valueOf("name");
        Assertions.assertEquals(ExperimentierStationZustand.VERFUEGBAR, result);
    }
}

