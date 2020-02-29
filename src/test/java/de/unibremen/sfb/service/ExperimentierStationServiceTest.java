package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ExperimentierStationServiceTest {
    @InjectMocks
    ExperimentierStationService experimentierStationService;
    @Mock
    ExperimentierStation experimentierStation;
    @Mock
    ExperimentierStationDAO experimentierStationDAO;
    @Mock
    private List<ExperimentierStation> esSet;
    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() throws ExperimentierStationNotFoundException {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
        when(experimentierStation.getEsID()).thenReturn(3);
        when(experimentierStationDAO.getAll()).thenReturn(new ArrayList<ExperimentierStation>());
        when(experimentierStationDAO.getObjById(3)).thenReturn(experimentierStation);
    }

    @Test
    public void testPErsistenceExperimentierStationService() throws ExperimentierStationNotFoundException, DuplicateExperimentierStationException {
        experimentierStationService.addES(experimentierStation);
        verify(experimentierStationDAO, VerificationModeFactory.times(1)).persist(experimentierStation);
        Assert.assertEquals(experimentierStation, experimentierStationService.getById(experimentierStation.getEsID()));
    }
    @Test
    public void testUpdateExperimentierStationService() throws ExperimentierStationNotFoundException, DuplicateExperimentierStationException {
        experimentierStationService.updateES(experimentierStation);
        verify(experimentierStationDAO, VerificationModeFactory.times(1)).update(experimentierStation);
        Assert.assertEquals(experimentierStation, experimentierStationService.getById(experimentierStation.getEsID()));
    }

    @Test
    public void testgetexperimentierStationeService()  {
        Assert.assertEquals(experimentierStationService.getAll(), new ArrayList<ExperimentierStation>());
        verify(experimentierStationDAO, VerificationModeFactory.times(1)).getAll();

    }
}