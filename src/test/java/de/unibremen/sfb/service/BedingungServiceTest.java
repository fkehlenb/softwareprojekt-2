package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.persistence.BedingungDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
        when(bedingung.getId()).thenReturn(3);
        when(bedingungDAO.getAll()).thenReturn(new ArrayList<Bedingung>());
        when(bedingungDAO.findById(3)).thenReturn(bedingung);
    }

    @Test
    public void testPErsistenceBedingungService()  {
        bedingungService.addBedingung(bedingung);
        verify(bedingungDAO, VerificationModeFactory.times(1)).persist(bedingung);
        Assert.assertEquals(bedingung, bedingungService.findByID(bedingung.getId()));
    }

    @Test
    public void testgetBedingungeService()  {
        Assert.assertEquals(bedingungService.getAll(), new ArrayList<Bedingung>());
        verify(bedingungDAO, VerificationModeFactory.times(1)).getAll();

    }
}