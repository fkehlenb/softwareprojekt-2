package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessKettenVorlage;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class ProzessKettenVorlageServiceTest {
    @InjectMocks
    ProzessKettenVorlageService prozessKettenVorlageService;

    @Mock
    ProzessKettenVorlage prozessKettenVorlage;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}