package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ProzessSchrittViewTest {
    @Mock
    ProzessSchrittVorlageService prozessSchrittVorlageService;
    @Mock
    ProzessSchrittService prozessSchrittService;
    @Mock
    ProzessSchrittLogService prozessSchrittLogService;
    @Mock
    ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;
    @Mock
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    List<ProzessSchrittVorlage> prozessSchrittVorlagen;
    @Mock
    ProzessSchrittVorlage selectedProzessSchrittVorlage;
    @Mock
    List<ProzessSchritt> prozessSchritte;
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> prozessSchrittZustandsAutomatVorlagen;
    @Mock
    ProzessSchrittZustandsAutomatVorlage selectedProzessSchrittZustandsAutomatVorlage;
    @Mock
    List<ExperimentierStation> experimentierStationen;
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    List<ProzessSchrittParameter> availableProzessSchrittParameter;
    @Mock
    List<ProzessSchrittParameter> selectedProzessSchrittParameter;
    @Mock
    ExperimentierStation experimentierStation;
    @Mock
    Logger log;
    @InjectMocks
    ProzessSchrittView prozessSchrittView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetES() throws ProzessSchrittNotFoundException {
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(experimentierStationService.getESfromPS(any())).thenReturn(new ExperimentierStation());

        ExperimentierStation result = prozessSchrittView.getES(0);
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    @Test
    void testSetES() throws ProzessSchrittNotFoundException, ExperimentierStationNotFoundException {
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(experimentierStationService.getById(anyInt())).thenReturn(new ExperimentierStation());

        prozessSchrittView.setES(0, 0);
    }

    @Test
    void testCreatePS() throws ProzessSchrittVorlageNotFoundException, ProzessSchrittNotFoundException, ExperimentierStationNotFoundException {
        when(prozessSchrittVorlageService.getProzessSchrittVorlagen()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0,new ArrayList<>(),new ArrayList<>())));
        when(prozessSchrittVorlageService.getByID(anyInt())).thenReturn(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), new ExperimentierStation(), "dauer", "name", null, true, 0,new ArrayList<>(),new ArrayList<>()));
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(prozessSchrittService.getAll()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        when(experimentierStationService.getById(anyInt())).thenReturn(new ExperimentierStation());
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
        when(selectedProzessSchrittVorlage.getPsVID()).thenReturn(0);
        when(selectedProzessSchrittVorlage.getProzessSchrittParameters()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));
        when(selectedProzessSchrittVorlage.getExperimentierStation()).thenReturn(new ExperimentierStation());
        when(selectedProzessSchrittVorlage.getDauer()).thenReturn("getDauerResponse");
        when(selectedProzessSchrittVorlage.getZustandsAutomatVorlage()).thenReturn(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
        when(selectedProzessSchrittZustandsAutomatVorlage.getZustaende()).thenReturn(Arrays.<String>asList("String"));
        when(selectedProzessSchrittZustandsAutomatVorlage.getName()).thenReturn("getNameResponse");
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));
        when(experimentierStation.getEsID()).thenReturn(0);

        prozessSchrittView.createPS();
    }

    @Test
    void testOnRowEdit() throws ProzessSchrittNotFoundException, ExperimentierStationNotFoundException {
        when(prozessSchrittVorlageService.getProzessSchrittVorlagen()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0,new ArrayList<>(),new ArrayList<>())));
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(prozessSchrittService.getAll()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        when(experimentierStationService.getById(anyInt())).thenReturn(new ExperimentierStation());
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
        when(selectedProzessSchrittZustandsAutomatVorlage.getZustaende()).thenReturn(Arrays.<String>asList("String"));
        when(selectedProzessSchrittZustandsAutomatVorlage.getName()).thenReturn("getNameResponse");
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));
        when(experimentierStation.getEsID()).thenReturn(0);

        prozessSchrittView.onRowEdit(0);
    }

    @Test
    void testOnRowEditCancelled() {
        prozessSchrittView.onRowEditCancelled();
    }

    @Test
    void testRemovePS() throws ProzessSchrittNotFoundException {
        when(prozessSchrittVorlageService.getProzessSchrittVorlagen()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0,new ArrayList<>(),new ArrayList<>())));
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
        when(prozessSchrittService.getAll()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));

        prozessSchrittView.removePS(0);
    }

    @Test
    void testSetProzessSchrittVorlageService() {
        prozessSchrittView.setProzessSchrittVorlageService(new ProzessSchrittVorlageService());
    }

    @Test
    void testSetProzessSchrittService() {
        prozessSchrittView.setProzessSchrittService(new ProzessSchrittService());
    }

    @Test
    void testSetProzessSchrittLogService() {
        prozessSchrittView.setProzessSchrittLogService(new ProzessSchrittLogService());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatVorlageService() {
        prozessSchrittView.setProzessSchrittZustandsAutomatVorlageService(new ProzessSchrittZustandsAutomatVorlageService());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatService() {
        prozessSchrittView.setProzessSchrittZustandsAutomatService(new ProzessSchrittZustandsAutomatService());
    }

    @Test
    void testSetExperimentierStationService() {
        prozessSchrittView.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetProzessSchrittVorlagen() {
        prozessSchrittView.setProzessSchrittVorlagen(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), null, "dauer", "name", null, true, 0,new ArrayList<>(),new ArrayList<>())));
    }

    @Test
    void testSetSelectedProzessSchrittVorlage() {
        prozessSchrittView.setSelectedProzessSchrittVorlage(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(null), new ExperimentierStation(), "dauer", "name", null, true, 0,new ArrayList<>(),new ArrayList<>()));
    }

    @Test
    void testSetProzessSchritte() {
        prozessSchrittView.setProzessSchritte(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
    }

    @Test
    void testSetProzessSchrittAttribute() {
        prozessSchrittView.setProzessSchrittAttribute("prozessSchrittAttribute");
    }

    @Test
    void testSetProzessSchrittName() {
        prozessSchrittView.setProzessSchrittName("prozessSchrittName");
    }

    @Test
    void testSetProzessSchrittZustandsAutomatVorlagen() {
        prozessSchrittView.setProzessSchrittZustandsAutomatVorlagen(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

    @Test
    void testSetSelectedProzessSchrittZustandsAutomatVorlage() {
        prozessSchrittView.setSelectedProzessSchrittZustandsAutomatVorlage(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testSetExperimentierStationen() {
        prozessSchrittView.setExperimentierStationen(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
    }

    @Test
    void testSetProzessSchrittParameterService() {
        prozessSchrittView.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetAvailableProzessSchrittParameter() {
        prozessSchrittView.setAvailableProzessSchrittParameter(Arrays.<ProzessSchrittParameter>asList(null));
    }

    @Test
    void testSetSelectedProzessSchrittParameter() {
        prozessSchrittView.setSelectedProzessSchrittParameter(Arrays.<ProzessSchrittParameter>asList(null));
    }

    @Test
    void testSetPsDuration() {
        prozessSchrittView.setPsDuration("psDuration");
    }

    @Test
    void testSetExperimentierStation() {
        prozessSchrittView.setExperimentierStation(new ExperimentierStation());
    }

    @Test
    void testSetUrformend() {
        prozessSchrittView.setUrformend(true);
    }

    @Test
    void testSetAmountCreated() {
        prozessSchrittView.setAmountCreated(0);
    }
}

