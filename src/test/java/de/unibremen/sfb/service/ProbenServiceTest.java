package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.persistence.ProbeDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class ProbenServiceTest {
    @InjectMocks
    ProbenService probenService;
    @Mock
    Probe probe;
    @Mock
    ProbeDAO probeDAO;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}