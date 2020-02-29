package de.unibremen.sfb.service;

import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.persistence.TraegerArtDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

@RunWith(MockitoJUnitRunner.class)
class TraegerArtServiceTest {
    @InjectMocks
    TraegerArtService traegerArtService;

    @Mock
    TraegerArt traegerArt;
    @Mock
    TraegerArtDAO traegerArtDAO;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
    }
}