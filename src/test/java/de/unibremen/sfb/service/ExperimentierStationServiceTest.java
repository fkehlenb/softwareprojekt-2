package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ExperimentierStation;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class ExperimentierStationServiceTest {
    @InjectMocks
    ExperimentierStationService experimentierStationService;
    @Mock
    ExperimentierStation experimentierStation;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}