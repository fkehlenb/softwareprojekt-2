package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.DuplicateAuftragsLogException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.AuftragsLog;
import de.unibremen.sfb.persistence.AuftragsLogDAO;
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
public class AuftragsLogsServiceTest {
    @InjectMocks
    AuftragsLogsService auftragsLogsService;
    @Mock
    AuftragsLog auftragsLog;
    @Mock
    AuftragsLogDAO auftragsLogDAO;

    @BeforeMethod(enabled = true)
    public void injectInitializierung() {
        MockitoAnnotations.initMocks(this); //Notweding und Injection zu inizielizieren bitte nicht entfernen
        when(auftragsLog.getId()).thenReturn(5);
    }

    @Test
    public void testPErsistenceauftragsLogService() throws DuplicateAuftragsLogException {
        auftragsLogsService.add(auftragsLog);
        verify(auftragsLogDAO, VerificationModeFactory.times(1)).persist(auftragsLog);
    }

}