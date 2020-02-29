package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class StandortServiceTest {
    @InjectMocks
    StandortService standortService;

    @Mock
    Standort standort;

    @Mock
    StandortDAO standortDAO;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}