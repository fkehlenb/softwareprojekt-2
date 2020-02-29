package de.unibremen.sfb.service;


import de.unibremen.sfb.exception.ArchivNotFoundException;
import de.unibremen.sfb.model.Archiv;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.ArchivDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArchivServiceTest {
    @InjectMocks
    ArchivService archivService;
    @Mock
    Archiv archiv;
    @Mock
    ArchivDAO archivDAO;
    @BeforeMethod(alwaysRun = true)
    public void injectInitializierung() throws ArchivNotFoundException {
       // MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
       // when(archivDAO.getObjById(1)).thenReturn(archiv);
    }
    @Test
    public void testgetQuantitativeEingeschaft() {

    }
}