package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.AuftragDAO;
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
import java.util.List;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class AuftragServiceTest {
    @Mock
    Auftrag auftrag;

    @Mock
    AuftragDAO auftragDAO;

    @InjectMocks
    AuftragService auftragService;
    @Mock
    QuantitativeEigenschaft quantitativeEigenschaft;

    @Mock
    List<Auftrag> auftrage;
    @InjectMocks
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    @BeforeMethod(alwaysRun = true)
    public void injectInitializer() throws AuftragNotFoundException {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
        when(auftrag.getPkID()).thenReturn(3);
        when(auftragDAO.getAll()).thenReturn(new ArrayList<Auftrag>());
        when(auftragDAO.getObjById(3)).thenReturn(auftrag);
    }

    @Test
    @org.junit.Test
    public void testPErsistenceAuftragService() throws DuplicateAuftragException {
        auftragService.add(auftrag);
        verify(auftragDAO, VerificationModeFactory.times(1)).persist(auftrag);
    }

    @Test
    public void testgetAuftrageService()  {
        Assert.assertEquals(auftragService.getAuftrage(), new ArrayList<Auftrag>());
    }
}