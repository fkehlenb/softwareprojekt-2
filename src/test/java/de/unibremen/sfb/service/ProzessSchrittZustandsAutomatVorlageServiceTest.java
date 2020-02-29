package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatVorlageDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class ProzessSchrittZustandsAutomatVorlageServiceTest {
    @InjectMocks
    ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    @Mock
    ProzessSchrittZustandsAutomatVorlage  prozessSchrittZustandsAutomatVorlage;
    @Mock
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}