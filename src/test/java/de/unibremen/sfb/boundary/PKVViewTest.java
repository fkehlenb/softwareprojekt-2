package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.service.ProzessKettenVorlageService;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PKVViewTest {
    @Mock
    List<ProzessKettenVorlage> selPKV;
    @Mock
    List<ProzessKettenVorlage> verPKV;
    @Mock
    List<ProzessSchrittVorlage> sourcePSV;
    @Mock
    List<ProzessSchrittVorlage> targetPSV;
    @Mock
    DualListModel<ProzessSchrittVorlage> psvs;
    @Mock
    ProzessKettenVorlageService prozessKettenVorlageService;
    @Mock
    ProzessSchrittVorlageService prozessSchrittVorlageService;
    @Mock
    Logger log;
    @Mock
    List<ProzessKettenVorlage> listPKV;
    @Mock
    List<ProzessSchrittVorlage> psvList;
    @Mock
    ProzessKettenVorlage pkv;
    @InjectMocks
    PKVView pKVView;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(prozessKettenVorlageService.getAll()).thenReturn(listPKV);
        when(prozessSchrittVorlageService.getProzessSchrittVorlagen()).thenReturn(psvList);
        pKVView.init();
    }

    @Test
    void testErstellePSK() {
        pKVView.erstellePSK();
    }

    @Test
    void testDeletePKV() throws ProzessKettenVorlageNotFoundException {
        when(prozessKettenVorlageService.getObjById(anyInt())).thenReturn(pkv);
        pKVView.deletePKV(0);
    }

    @Test
    void testOnRowEdit() throws ProzessKettenVorlageNotFoundException {
        when(prozessKettenVorlageService.getObjById(anyInt())).thenReturn(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0))));

        pKVView.onRowEdit(0);
    }


    @Test
    void testSetSelPKV() {
        pKVView.setSelPKV(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0)))));
    }

    @Test
    void testSetVerPKV() {
        pKVView.setVerPKV(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0)))));
    }

    @Test
    void testSetSourcePSV() {
        pKVView.setSourcePSV(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0)));
    }

    @Test
    void testSetTargetPSV() {
        pKVView.setTargetPSV(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0)));
    }

    @Test
    void testSetPsvs() {
        pKVView.setPsvs(null);
    }

    @Test
    void testSetProzessKettenVorlageService() {
        pKVView.setProzessKettenVorlageService(new ProzessKettenVorlageService());
    }

    @Test
    void testSetProzessSchrittVorlageService() {
        pKVView.setProzessSchrittVorlageService(new ProzessSchrittVorlageService());
    }

    @Test
    void testSetSelectedName() {
        pKVView.setSelectedName("selectedName");
    }
}

