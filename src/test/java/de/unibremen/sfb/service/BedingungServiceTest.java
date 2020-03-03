//package de.unibremen.sfb.service;
//
//import de.unibremen.sfb.exception.BedingungNotFoundException;
//import de.unibremen.sfb.exception.DuplicateBedingungException;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//
//class BedingungServiceTest {
//    @Mock
//    List<Bedingung> bs;
//    @Mock
//    BedingungDAO bedingungDAO;
//    @Mock
//    Logger log;
//    @Mock
//    Bedingung bedingung;
//    @InjectMocks
//    BedingungService bedingungService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testInit() {
//        bedingungService.init();
//    }
//
//    @Test
//    void testAddES() throws DuplicateBedingungException {
//
//
//        bedingungService.addES(bedingung);
//        verify(bedingungDAO).persist(bedingung);
//
//    }
//
//    @Test
//    void testLoescheES() throws BedingungNotFoundException {
//        bedingungService.loescheES(bedingung);
//        verify(bedingungDAO).remove(bedingung);
//    }
//
//    @Test
//    void testFindByID() {
//        when(bedingungDAO.findById(anyInt())).thenReturn(bedingung);
//        Bedingung result = bedingungService.findByID(0);
//        Assertions.assertEquals(bedingung, result);
//        verify(bedingungDAO).findById(0);
//    }
//
//    @Test
//    void testGetAll() {
//        when(bedingungDAO.getAll()).thenReturn(bs);
//        List<Bedingung> result = bedingungService.getAll();
//        Assertions.assertEquals(bs, result);
//    }
//
//    @Test
//    void testUpdateES() throws BedingungNotFoundException {
//        bedingungService.updateES(bedingung);
//        verify(bedingungDAO).update(bedingung);
//    }
//
//    @Test
//    void testAddBedingung() {
//        bedingungService.addBedingung(bedingung);
//        verify(bedingungDAO).persist(bedingung);
//    }
//
//    @Test
//    void testRemove() throws BedingungNotFoundException {
//        bedingungService.remove(bedingung);
//        verify(bedingungDAO).remove(bedingung);
//    }
//
//    @Test
//    void testEdit() throws BedingungNotFoundException {
//        bedingungService.edit(bedingung);
//        verify(bedingungDAO).update(bedingung);
//    }
//
//
//    @Test
//    void testSetBedingungDAO() {
//        bedingungService.setBedingungDAO(new BedingungDAO());
//    }
//}