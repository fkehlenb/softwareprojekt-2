package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import de.unibremen.sfb.service.ProzessSchrittZustandsAutomatVorlageService;
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

class PSVViewTest {
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    List<ProzessSchrittParameter> availableProzessSchrittParameterList;
    @Mock
    List<ProzessSchrittParameter> selectedProzessSchrittParameterList;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    List<ExperimentierStation> availableExperimentierStationList;
    @Mock
    ExperimentierStation selectedExperimentierStation;
    @Mock
    ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;
    @Mock
    List<ProzessSchrittZustandsAutomatVorlage> availableProzessSchrittZustandsAutomatVorlageList;
    @Mock
    ProzessSchrittZustandsAutomatVorlage selectedProzessSchrittZustandsAutomatVorlage;
    @Mock
    ProzessSchrittVorlageService prozessSchrittVorlageService;
    @Mock
    List<ProzessSchrittVorlage> availableProzessSchrittVorlageList;
    @Mock
    Logger log;
    @InjectMocks
    PSVView pSVView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreatePSV() {
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
        when(prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen()).thenReturn(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
        when(prozessSchrittVorlageService.getVorlagen()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0,new ArrayList<>(),new ArrayList<>())));

        pSVView.createPSV();
    }

    @Test
    void testOnRowEdit() throws ProzessSchrittVorlageNotFoundException {
        when(prozessSchrittVorlageService.getByID(anyInt())).thenReturn(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0,new ArrayList<>(),new ArrayList<>()));

        pSVView.onRowEdit(0);
    }

    @Test
    void testRemovePSV() throws ProzessSchrittVorlageNotFoundException {
        when(prozessSchrittVorlageService.getByID(anyInt())).thenReturn(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0,new ArrayList<>(),new ArrayList<>()));

        pSVView.removePSV(0);
    }

    @Test
    void testOnRowEditCancelled() {
        pSVView.onRowEditCancelled();
    }

    @Test
    void testSetProzessSchrittParameterService() {
        pSVView.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetAvailableProzessSchrittParameterList() {
        pSVView.setAvailableProzessSchrittParameterList(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
    }

    @Test
    void testSetSelectedProzessSchrittParameterList() {
        pSVView.setSelectedProzessSchrittParameterList(Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))));
    }

    @Test
    void testSetSelectedName() {
        pSVView.setSelectedName("selectedName");
    }

    @Test
    void testSetSelectedDuration() {
        pSVView.setSelectedDuration("selectedDuration");
    }

    @Test
    void testSetExperimentierStationService() {
        pSVView.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetAvailableExperimentierStationList() {
        pSVView.setAvailableExperimentierStationList(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
    }

    @Test
    void testSetSelectedExperimentierStation() {
        pSVView.setSelectedExperimentierStation(new ExperimentierStation());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatVorlageService() {
        pSVView.setProzessSchrittZustandsAutomatVorlageService(new ProzessSchrittZustandsAutomatVorlageService());
    }

    @Test
    void testSetAvailableProzessSchrittZustandsAutomatVorlageList() {
        pSVView.setAvailableProzessSchrittZustandsAutomatVorlageList(Arrays.<ProzessSchrittZustandsAutomatVorlage>asList(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name")));
    }

    @Test
    void testSetSelectedProzessSchrittZustandsAutomatVorlage() {
        pSVView.setSelectedProzessSchrittZustandsAutomatVorlage(new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"));
    }

    @Test
    void testSetProzessSchrittVorlageService() {
        pSVView.setProzessSchrittVorlageService(new ProzessSchrittVorlageService());
    }

    @Test
    void testSetAvailableProzessSchrittVorlageList() {
        pSVView.setAvailableProzessSchrittVorlageList(Arrays.<ProzessSchrittVorlage>asList(new ProzessSchrittVorlage(0, Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(null))), new ExperimentierStation(), "dauer", "name", new ProzessSchrittZustandsAutomatVorlage(0, Arrays.<String>asList("String"), "name"), true, 0,new ArrayList<>(),new ArrayList<>())));
    }

    @Test
    void testSetUrformend() {
        pSVView.setUrformend(true);
    }

    @Test
    void testSetAmountCreated() {
        pSVView.setAmountCreated(0);
    }
}

