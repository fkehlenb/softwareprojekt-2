package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.persistence.BedingungDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class BedingungServiceTest {
    @InjectMocks
    BedingungService bedingungService;
    @Mock
    Bedingung bedingung;
    @Mock
    BedingungDAO bedingungDAO;
    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}