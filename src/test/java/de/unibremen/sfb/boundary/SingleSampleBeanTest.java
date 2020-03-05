package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Kommentar;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.ProbenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SingleSampleBeanTest {
    @Mock
    Probe p;
    @Mock
    ProbenService probenService;
    @Mock
    TechnologeView technologeView;
    @Mock
    Logger log;
    @InjectMocks
    SingleSampleBean singleSampleBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSingleprobe() {
        String result = singleSampleBean.singleprobe(new Probe("probenID", 0, null, new Standort(0, "ort")));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testKommentarEdit() {
        when(p.getProbenID()).thenReturn("getProbenIDResponse");
        when(p.getKommentar()).thenReturn(Arrays.<Kommentar>asList(new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 38, 41), "text")));

        singleSampleBean.KommentarEdit(null);
    }

    @Test
    void testEditProbenComment() {
        when(p.getProbenID()).thenReturn("getProbenIDResponse");

        singleSampleBean.editProbenComment(new Probe("probenID", 0, null, new Standort(0, "ort")), new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 38, 41), "text"), "c");
    }

    @Test
    void testDeleteProbenComment() {
        when(p.getProbenID()).thenReturn("getProbenIDResponse");

        singleSampleBean.deleteProbenComment(new Kommentar(LocalDateTime.of(2020, Month.MARCH, 5, 16, 38, 41), "text"));
    }

    @Test
    void testAddProbenComment() {
        when(p.getProbenID()).thenReturn("getProbenIDResponse");

        singleSampleBean.addProbenComment();
    }

    @Test
    void testErrorMessage() {
        singleSampleBean.errorMessage("e");
    }

    @Test
    void testGetEigenschaften() {
        when(p.getEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));

        List<QualitativeEigenschaft> result = singleSampleBean.getEigenschaften();
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testSetP() {
        singleSampleBean.setP(new Probe("probenID", 0, null, new Standort(0, "ort")));
    }

    @Test
    void testSetProbenService() {
        singleSampleBean.setProbenService(new ProbenService());
    }

    @Test
    void testSetTechnologeView() {
        singleSampleBean.setTechnologeView(new TechnologeView());
    }

    @Test
    void testSetSingleKommentar() {
        singleSampleBean.setSingleKommentar("singleKommentar");
    }
}

