package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.TransportAuftragNotFoundException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ProzessKettenVorlageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class TransporterBeanTest {
    @Mock
    List<ProzessSchritt> prozessSchrittList;
    @Mock
    List<ProzessSchritt> prozessSchrittList2;
    @Mock
    List<ProzessSchritt> prozessSchrittList3;
    @Mock
    List<TransportAuftrag> transportAuftragSelected;
    @Mock
    AuftragService auftragService;
    @Mock
    ProzessKettenVorlageService prozessKettenVorlageService;
    @Mock
    TransportAuftrag transportAuftrag;
    @Mock
    Logger log;
    @InjectMocks
    TransporterBean transporterBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Mock
    List<ProzessSchritt> prozessSchritts;
    @Mock
    List<ProzessSchritt> prozessSchritts1;
    @Mock
    List<ProzessSchritt> prozessSchritts13;
    @Test
    void testInit() throws UserNotFoundException {
        when(auftragService.getTransportSchritt()).thenReturn(prozessSchritts);
        when(auftragService.getTransportSchritt2()).thenReturn(prozessSchritts1);
        when(auftragService.getTransportSchritt3()).thenReturn(prozessSchritts13);
        transporterBean.init();
    }

    @Test
    void testGetAuftragList() {
        when(auftragService.getTransportSchritt()).thenReturn(prozessSchritts);
        List<ProzessSchritt> result = transporterBean.getAuftragList();
        Assertions.assertEquals(prozessSchritts, result); }

    @Test
    void testChangeTransportZustandAbgeholt() throws UserNotFoundException, TransportAuftragNotFoundException {
        when(auftragService.getTransportSchritt()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportSchritt2()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportSchritt3()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportAuftragByID(anyInt())).thenReturn(new TransportAuftrag(LocalDateTime.of(2020, Month.MARCH, 5, 16, 40, 3), null, new Standort(0, "ort"), new Standort(0, "ort")));

        transporterBean.changeTransportZustandAbgeholt(0);
    }

    @Test
    void testChangeTransportZustandAbgeliefert() throws UserNotFoundException, TransportAuftragNotFoundException {
        when(auftragService.getTransportSchritt()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportSchritt2()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportSchritt3()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportAuftragByID(anyInt())).thenReturn(new TransportAuftrag(LocalDateTime.of(2020, Month.MARCH, 5, 16, 40, 3), null, new Standort(0, "ort"), new Standort(0, "ort")));

        transporterBean.changeTransportZustandAbgeliefert(0);
    }

    @Test
    void testUpdateTabellen() throws UserNotFoundException {
        when(auftragService.getTransportSchritt()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportSchritt2()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));
        when(auftragService.getTransportSchritt3()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0)));

        transporterBean.updateTabellen();
    }



    @Test
    void testSetProzessSchrittList2() {
        transporterBean.setProzessSchrittList2(prozessSchrittList2);
    }

    @Test
    void testSetProzessSchrittList3() {
        transporterBean.setProzessSchrittList3(prozessSchrittList3);  }
    @Mock
    List<TransportAuftrag> transportAuftrags;
    @Test
    void testSetTransportAuftragSelected() {
        transporterBean.setTransportAuftragSelected( transportAuftrags);
    }

    @Test
    void testSetAuftragService() {
        transporterBean.setAuftragService(new AuftragService());
    }

    @Test
    void testSetProzessKettenVorlageService() {
        transporterBean.setProzessKettenVorlageService(new ProzessKettenVorlageService());
    }
}

