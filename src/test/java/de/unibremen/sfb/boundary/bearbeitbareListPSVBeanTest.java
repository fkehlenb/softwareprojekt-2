package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class bearbeitbareListPSVBeanTest {
    @Mock
    ProzessSchrittVorlageService bearbeitbareListPSVBeanService;
    @Mock
    List<ProzessSchrittVorlage> bearbeitbareList;
    @Mock
    Logger log;
    @InjectMocks
    de.unibremen.sfb.boundary.bearbeitbareListPSVBean bearbeitbareListPSVBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(bearbeitbareListPSVBeanService.akzeptiertePSV()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0)));
        bearbeitbareListPSVBeanService.init();
    }



    @Test
    void testEquals() {
        boolean result = bearbeitbareListPSVBeanService.equals("o");
        Assertions.assertEquals(false, result);
    }



    @Test
    void testToString() {
        String result = bearbeitbareListPSVBeanService.toString();
        Assertions.assertEquals("bearbeitbareListPSVBeanService", result);
    }
}

