package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.AuftragsLog;
import de.unibremen.sfb.persistence.AuftragsLogDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class AuftragsLogsServiceTest {
    @InjectMocks
    AuftragsLogsService auftragsLogsService;
    @Mock
    AuftragsLog auftragsLog;
    @Mock
    AuftragsLogDAO auftragsLogDAO;
    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}