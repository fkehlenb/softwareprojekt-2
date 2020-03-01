package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessSchritt;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittVorlageServiceTest {
    @Mock
    List<ProzessSchrittVorlage> vorlagen;
    @Mock
    ProzessSchrittVorlageDAO psvDAO;
    @Mock
    AuftragService auftragService;
    @Mock
    ProzessSchrittVorlage prozessKettenVorlage;
    @Mock
    Logger log;
    @Mock
    List<ProzessSchrittVorlage> prozessSchrittVorlages;
    @Mock
    List<Auftrag> auftrags;
    @Mock
    ProzessSchritt prozessSchritt;
    @InjectMocks
    ProzessSchrittVorlageService prozessSchrittVorlageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testGetProzessSchrittVorlagen() {
        when(psvDAO.getAll()).thenReturn(prozessSchrittVorlages);
        List<ProzessSchrittVorlage> result = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        Assertions.assertEquals(prozessSchrittVorlages,result);
    }

    @Test
    void testPersist() throws DuplicateProzessSchrittVorlageException {
        prozessSchrittVorlageService.persist(prozessKettenVorlage);
        verify(psvDAO).persist(any());
    }

    @Test
    void testByID() throws ProzessSchrittVorlageNotFoundException {

        when(psvDAO.getObjById(anyInt())).thenReturn(prozessKettenVorlage);

        ProzessSchrittVorlage result = prozessSchrittVorlageService.ByID(0);

        Assertions.assertEquals(prozessKettenVorlage, result);
    }

    @Test
    void testEdit() throws ProzessSchrittVorlageNotFoundException {
        prozessSchrittVorlageService.edit(prozessKettenVorlage);
        verify(psvDAO).update(prozessKettenVorlage);
    }


    @Test
    void testGetByID() throws ProzessSchrittVorlageNotFoundException {

        when(psvDAO.getObjById(anyInt())).thenReturn(prozessKettenVorlage);

        ProzessSchrittVorlage result = prozessSchrittVorlageService.getByID(0);

        Assertions.assertEquals(prozessKettenVorlage, result);
    }





    @Test
    void testSetPsvDAO() {
        prozessSchrittVorlageService.setPsvDAO(new ProzessSchrittVorlageDAO());
    }

    @Test
    void testSetAuftragService() {
        prozessSchrittVorlageService.setAuftragService(new AuftragService());
    }

    @Test
    void testEquals() {
        boolean result = prozessSchrittVorlageService.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = prozessSchrittVorlageService.canEqual("other");
        Assertions.assertEquals(false, result);
    }


    @Test
    void testToString() {
        String result = prozessSchrittVorlageService.toString();
        Assertions.assertEquals("ProzessSchrittVorlageService(vorlagen=vorlagen, psvDAO=psvDAO, auftragService=auftragService)", result);
    }
}