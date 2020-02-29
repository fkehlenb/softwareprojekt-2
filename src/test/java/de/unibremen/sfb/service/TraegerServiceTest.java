package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.persistence.TraegerDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class TraegerServiceTest {
    @InjectMocks
    TraegerService traegerService;
    @Mock
    Traeger traeger;
    @Mock
    TraegerDAO traegerDAO;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}