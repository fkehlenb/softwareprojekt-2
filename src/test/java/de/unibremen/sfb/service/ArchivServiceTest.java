package de.unibremen.sfb.service;


import de.unibremen.sfb.model.Archiv;
import de.unibremen.sfb.persistence.ArchivDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class ArchivServiceTest {
    @InjectMocks
    ArchivService archivService;
    @Mock
    Archiv archiv;
    @Mock
    ArchivDAO archivDAO;
    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}