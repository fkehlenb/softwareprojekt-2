package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.primefaces.model.DualListModel;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AuftragViewTest {
    @Mock
    AuftragService auftragService;
    @Mock
    ProzessSchrittService prozessSchrittService;
    @Mock
    ProzessKettenVorlageService prozessKettenVorlageService;
    @Mock
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;
    @Mock
    ProzessSchrittLogService prozessSchrittLogService;
    @Mock
    AuftragsLogsService auftragsLogsService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    List<Auftrag> availableJobs;
    @Mock
    List<ProzessSchritt> availableProzessSchritte;
    @Mock
    List<ProzessSchritt> selectedProzessSchritte;
    @Mock
    DualListModel<ProzessSchritt> dualListModel;
    @Mock
    List<ProzessKettenVorlage> availableProzessKettenVorlagen;
    @Mock
    ProzessKettenVorlage selectedProzesskettenVorlage;
    @Mock
    List<AuftragsPrioritaet> availablePriorities;
    //Field selectedPriority of type AuftragsPrioritaet - was not mocked since Mockito doesn't mock enums
    @Mock
    Auftrag selectedAuftraege;
    @Mock
    List<Auftrag> filteredAuftrag;
    @Mock
    List<Auftrag> auftrage;
    //Field prios of type AuftragsPrioritaet[] - was not mocked since Mockito doesn't mock arrays
    //Field prozessKettenZustandsAutomatList of type ProzessKettenZustandsAutomat[] - was not mocked since Mockito doesn't mock arrays
    @Mock
    Logger log;
    @InjectMocks
    AuftragView auftragView;
    @Mock
    List <Auftrag> auftrags;
    @Mock
    List <ProzessSchritt> prozessSchritts;
    @Mock
    ProzessSchritt prozessSchritt;
    @Mock
    List <ProzessKettenVorlage> ProzessKettenVorlagen;
    @Mock
    Auftrag auftrag;
    @Mock
    List<ProzessKettenZustandsAutomat> ProzessKettenZustandsAutomate;
    @Mock
    ExperimentierStation experimentierStation;
    @Mock
    List<ProzessKettenVorlage> prozessKettenVorlage;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Mock
    List<ProzessSchrittVorlage> prozessKettenVorlagen;
    @Test
    void testCreateJob() {
        when(auftragService.getAll()).thenReturn(auftrags);
        when(prozessSchrittService.getAllAvailable()).thenReturn(prozessSchritts);
        when(prozessKettenVorlageService.getAll()).thenReturn(prozessKettenVorlage);
        when(selectedProzesskettenVorlage.getProzessSchrittVorlagen()).thenReturn(prozessKettenVorlagen);
        auftragView.createJob();
    }


    //@Test Abhangig von Enum mockito nicht arbeit mit enums
    void testEdit() throws AuftragNotFoundException {
        when(auftragService.getAll()).thenReturn(auftrags);
        when(auftragService.getObjById(anyInt())).thenReturn(auftrag);
        when(prozessSchrittService.getAllAvailable()).thenReturn(prozessSchritts);
        when(prozessKettenVorlageService.getAll()).thenReturn(ProzessKettenVorlagen);
        when(selectedAuftraege.getProzessSchritte()).thenReturn(prozessSchritts);
        when(selectedAuftraege.getProzessKettenZustandsAutomat()).thenReturn(null);

        auftragView.edit(0);
    }

    @Test
    void testRemove() throws AuftragNotFoundException {
        when(auftragService.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));
        when(auftragService.getObjById(anyInt())).thenReturn(new Auftrag());
        when(prozessSchrittService.getAllAvailable()).thenReturn(Arrays.<ProzessSchritt>asList(null));
        when(prozessKettenVorlageService.getAll()).thenReturn(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(null))));
        when(selectedAuftraege.getProzessSchritte()).thenReturn(Arrays.<ProzessSchritt>asList(null));

        auftragView.remove(0);
    }

    @Test
    void testStopJob() throws AuftragNotFoundException {
        when(auftragService.getAll()).thenReturn(auftrags);
        when(auftragService.getObjById(anyInt())).thenReturn(auftrag);
        when(prozessSchrittService.getAllAvailable()).thenReturn(prozessSchritts);
        when(prozessKettenVorlageService.getAll()).thenReturn(prozessKettenVorlage);
        when(selectedAuftraege.getProzessSchritte()).thenReturn(prozessSchritts);

        auftragView.stopJob(0);
    }

    //@Test Enums Abhangings
    void testSetStarted() throws AuftragNotFoundException {
        when(auftragService.getAll()).thenReturn(auftrags);
        when(auftragService.getObjById(anyInt())).thenReturn(new Auftrag());
        when(prozessSchrittService.getAllAvailable()).thenReturn(prozessSchritts);
        when(prozessKettenVorlageService.getAll()).thenReturn(prozessKettenVorlage);
        when(selectedAuftraege.getProzessKettenZustandsAutomat()).thenReturn(null);

        auftragView.setStarted(0);
    }

    @Test
    void testGetES() throws ProzessSchrittNotFoundException {
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(prozessSchritt);
        when(experimentierStationService.getESfromPS(any())).thenReturn(experimentierStation);
        ExperimentierStation result = auftragView.getES(0);
        Assertions.assertEquals(experimentierStation, result);
    }

    @Test
    void testSetES() throws ProzessSchrittNotFoundException, ExperimentierStationNotFoundException {
        when(prozessSchrittService.getObjById(anyInt())).thenReturn(prozessSchritt);
        when(experimentierStationService.getById(anyInt())).thenReturn(experimentierStation);

        auftragView.setES(0, 0);
    }


    @Test
    void testSetAuftragService() {
        auftragView.setAuftragService(new AuftragService());
    }

    @Test
    void testSetProzessSchrittService() {
        auftragView.setProzessSchrittService(new ProzessSchrittService());
    }

    @Test
    void testSetProzessKettenVorlageService() {
        auftragView.setProzessKettenVorlageService(new ProzessKettenVorlageService());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatService() {
        auftragView.setProzessSchrittZustandsAutomatService(new ProzessSchrittZustandsAutomatService());
    }

    @Test
    void testSetProzessSchrittLogService() {
        auftragView.setProzessSchrittLogService(new ProzessSchrittLogService());
    }

    @Test
    void testSetAuftragsLogsService() {
        auftragView.setAuftragsLogsService(new AuftragsLogsService());
    }

    @Test
    void testSetExperimentierStationService() {
        auftragView.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetAvailableJobs() {
        auftragView.setAvailableJobs(Arrays.<Auftrag>asList(new Auftrag()));
    }



    @Test
    void testSetDualListModel() {
        auftragView.setDualListModel(null);
    }



    @Test
    void testSetAvailablePriorities() {
        auftragView.setAvailablePriorities(Arrays.<AuftragsPrioritaet>asList(AuftragsPrioritaet.KEINE));
    }

    @Test
    void testSetSelectedPriority() {
        auftragView.setSelectedPriority(AuftragsPrioritaet.KEINE);
    }

    @Test
    void testSetSelectedName() {
        auftragView.setSelectedName("selectedName");
    }

    @Test
    void testSetSelectedAuftraege() {
        auftragView.setSelectedAuftraege(new Auftrag());
    }

    @Test
    void testSetFilteredAuftrag() {
        auftragView.setFilteredAuftrag(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetAuftrage() {
        auftragView.setAuftrage(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetPrios() {
        auftragView.setPrios(new AuftragsPrioritaet[]{AuftragsPrioritaet.KEINE});
    }

    @Test
    void testSetProzessKettenZustandsAutomatList() {
        auftragView.setProzessKettenZustandsAutomatList(new ProzessKettenZustandsAutomat[]{ProzessKettenZustandsAutomat.INSTANZIIERT});
    }
}

