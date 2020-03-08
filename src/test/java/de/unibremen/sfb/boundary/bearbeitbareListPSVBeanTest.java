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
    @Mock
    List<ProzessSchrittVorlage> aPSV;
    @InjectMocks
    de.unibremen.sfb.boundary.bearbeitbareListPSVBean bearbeitbareListPSVBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(bearbeitbareListPSVBeanService.akzeptiertePSV()).thenReturn(aPSV);
        bearbeitbareListPSVBeanService.init();
    }


    @Test
    void testEquals() {
        boolean result = bearbeitbareListPSVBeanService.equals(bearbeitbareListPSVBeanService);
        Assertions.assertEquals(true, result);
    }
}
