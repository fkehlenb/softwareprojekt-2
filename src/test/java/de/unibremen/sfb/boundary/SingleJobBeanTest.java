package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ProbenService;
import de.unibremen.sfb.service.ProzessSchrittService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
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

class SingleJobBeanTest {
    @Mock
    ProzessSchritt ps;
    @Mock
    List<QualitativeEigenschaft> verEigenschaften;
    @Mock
    List<QualitativeEigenschaft> ausEigenschaften;
    @Mock
    ProbenService probeService;
    @Mock
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Mock
    ProzessSchrittService prozessSchrittService;
    @Mock
    ProzessSchrittService psService;
    @Mock
    AuftragService auftragService;
    @Mock
    Logger log;
    @InjectMocks
    SingleJobBean singleJobBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(qualitativeEigenschaftService.getEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));

        singleJobBean.init();
    }

    @Test
    void testSinglejob() throws ProzessSchrittNotFoundException {
        when(qualitativeEigenschaftService.getEigenschaften()).thenReturn(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
        when(psService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));

        String result = singleJobBean.singlejob(0);
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testGetLetzterZustand() {
        when(ps.getProzessSchrittZustandsAutomat()).thenReturn(new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")));

        String result = singleJobBean.getLetzterZustand(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }

    @Test
    void testAddComment() {
        when(auftragService.getAuftrag(any())).thenReturn(new Auftrag());

        singleJobBean.addComment();
    }

    @Test
    void testGetProben() throws ProzessSchrittNotFoundException {
        when(ps.getId()).thenReturn(0);
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
        when(psService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
        when(auftragService.getAuftrag(any())).thenReturn(new Auftrag());

        List<Probe> result = singleJobBean.getProben();
        Assertions.assertEquals(Arrays.<Probe>asList(null), result);
    }

    @Test
    void testZuweisen() throws ProzessSchrittNotFoundException {
        when(ps.getId()).thenReturn(0);
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
        when(psService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
        when(auftragService.getAuftrag(any())).thenReturn(new Auftrag());

        singleJobBean.zuweisen();
    }

    @Test
    void testGetParameter() {
        when(ps.getProzessSchrittParameters()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));

        List<ProzessSchrittParameter> result = singleJobBean.getParameter();
        Assertions.assertEquals(Arrays.<ProzessSchrittParameter>asList(null), result);
    }

    @Test
    void testDownload() {
        when(ps.getProzessSchrittParameters()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));

        singleJobBean.download();
    }

    @Test
    void testErrorMessage() {
        singleJobBean.errorMessage("e");
    }

    @Test
    void testMessage() {
        singleJobBean.message("e");
    }

    @Test
    void testDownload2() {
        singleJobBean.download(Arrays.<ProzessSchrittParameter>asList(null));
    }

    @Test
    void testFindStation() {
        when(prozessSchrittService.findStation(any())).thenReturn(new ExperimentierStation());
        when(psService.findStation(any())).thenReturn(new ExperimentierStation());

        ExperimentierStation result = singleJobBean.findStation();
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    @Test
    void testSetJobZustand() throws ProzessSchrittZustandsAutomatNotFoundException, DuplicateProzessSchrittLogException {
        when(ps.getProzessSchrittZustandsAutomat()).thenReturn(new ProzessSchrittZustandsAutomat(0, "current", Arrays.<String>asList("String")));

        singleJobBean.setJobZustand();
    }

    @Test
    void testSetPs() {
        singleJobBean.setPs(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0));
    }

    @Test
    void testSetVerEigenschaften() {
        singleJobBean.setVerEigenschaften(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetAusEigenschaften() {
        singleJobBean.setAusEigenschaften(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")));
    }

    @Test
    void testSetKommentarForAll() {
        singleJobBean.setKommentarForAll("kommentarForAll");
    }

    @Test
    void testSetProbeService() {
        singleJobBean.setProbeService(new ProbenService());
    }

    @Test
    void testSetQualitativeEigenschaftService() {
        singleJobBean.setQualitativeEigenschaftService(new QualitativeEigenschaftService());
    }

    @Test
    void testSetProzessSchrittService() {
        singleJobBean.setProzessSchrittService(new ProzessSchrittService());
    }

    @Test
    void testSetPsService() {
        singleJobBean.setPsService(new ProzessSchrittService());
    }

    @Test
    void testSetAuftragService() {
        singleJobBean.setAuftragService(new AuftragService());
    }
}

