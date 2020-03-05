package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatZustaende;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatZustaendeDAO;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import de.unibremen.sfb.service.ProzessSchrittZustandsAutomatVorlageService;
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

class PSZAVViewTest {
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> selpszav;
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> verpszav;
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> filteredpszav;
    @Mock
    List<String> sourceZ;
    @Mock
    List<String> targetZ;
    @Mock
    DualListModel<String> dualZ;
    @Mock
    ProzessSchrittZustandsAutomatZustaendeDAO prozessSchrittZustandsAutomatZustaendeDAO;
    @Mock
    ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;
    @Mock
    ProzessSchrittVorlageService prozessSchrittVorlageService;
    @Mock
    Logger log;
    @InjectMocks
    PSZAVView pSZAVView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(prozessSchrittZustandsAutomatZustaendeDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));

        pSZAVView.init();
    }

    @Test
    void testRefresh() {
        when(prozessSchrittZustandsAutomatZustaendeDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));

        pSZAVView.refresh();
    }

    @Test
    void testAddToAutomaton() {
        when(prozessSchrittZustandsAutomatZustaendeDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));

        pSZAVView.addToAutomaton();
    }

    @Test
    void testErstellePSZAV() {
        when(prozessSchrittZustandsAutomatZustaendeDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));

        pSZAVView.erstellePSZAV();
    }

    @Test
    void testDeletePSZAV() throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        when(prozessSchrittZustandsAutomatZustaendeDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        when(prozessSchrittZustandsAutomatVorlageService.getByID(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));

        pSZAVView.deletePSZAV(0);
    }

    @Test
    void testOnRowEdit() throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        when(prozessSchrittZustandsAutomatZustaendeDAO.getById(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatZustaende(0, Arrays.<String>asList("String")));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        when(prozessSchrittZustandsAutomatVorlageService.getByID(anyInt())).thenReturn(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));

        pSZAVView.onRowEdit(0);
    }

    @Test
    void testOnRowCancel() {
        pSZAVView.onRowCancel();
    }

    @Test
    void testSetSelpszav() {
        pSZAVView.setSelpszav(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

    @Test
    void testSetVerpszav() {
        pSZAVView.setVerpszav(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

    @Test
    void testSetFilteredpszav() {
        pSZAVView.setFilteredpszav(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

    @Test
    void testSetSourceZ() {
        pSZAVView.setSourceZ(Arrays.<String>asList("String"));
    }

    @Test
    void testSetTargetZ() {
        pSZAVView.setTargetZ(Arrays.<String>asList("String"));
    }

    @Test
    void testSetDualZ() {
        pSZAVView.setDualZ(null);
    }

    @Test
    void testSetToaddd() {
        pSZAVView.setToaddd("toaddd");
    }

    @Test
    void testSetName() {
        pSZAVView.setName("name");
    }

    @Test
    void testSetProzessSchrittZustandsAutomatZustaendeDAO() {
        pSZAVView.setProzessSchrittZustandsAutomatZustaendeDAO(new ProzessSchrittZustandsAutomatZustaendeDAO());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatVorlageService() {
        pSZAVView.setProzessSchrittZustandsAutomatVorlageService(new ProzessSchrittZustandsAutomatVorlageService());
    }

    @Test
    void testSetProzessSchrittVorlageService() {
        pSZAVView.setProzessSchrittVorlageService(new ProzessSchrittVorlageService());
    }

    @Test
    void testSetZustandsname() {
        pSZAVView.setZustandsname("zustandsname");
    }

    @Test
    void testSetNewOrder() {
        pSZAVView.setNewOrder(new String[]{"newOrder"});
    }
}

