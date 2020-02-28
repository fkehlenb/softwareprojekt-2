package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class ProzessSchrittZustandsAutomatServiceTest {
    @InjectMocks
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    @Mock
    ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}