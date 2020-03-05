package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class pkAdminJSONTest {
    @Mock
    AuftragService auftragService;
    @Mock
    ProzessKettenVorlageService prozessKettenVorlageService;
    @Mock
    ProzessSchrittService prozessSchrittService;
    @Mock
    ProzessSchrittVorlageService prozessSchrittVorlageService;
    @Mock
    ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;
    @Mock
    ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    UserService userService;
    @Mock
    List<ProzessSchritt> availableProzessSchritte;
    @Mock
    ProzessSchritt selectedProzessSchritt;
    @Mock
    List<Auftrag> availableAuftraege;
    @Mock
    Auftrag selectedAuftrag;
    @Mock
    JsonbConfig config;
    @Mock
    Jsonb jsonb;
    @Mock
    Logger log;
    @InjectMocks
    de.unibremen.sfb.boundary.pkAdminJSON pkAdminJSON;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testExportAuftrags() {
        when(auftragService.getAll()).thenReturn(Arrays.<Auftrag>asList(new Auftrag()));

        pkAdminJSON.exportAuftrags();
    }

    @Test
    void testExportPKV() {
        when(prozessKettenVorlageService.getAll()).thenReturn(Arrays.<ProzessKettenVorlage>asList(new ProzessKettenVorlage(0, "name", Arrays.<ProzessSchrittVorlage>asList(null))));

        pkAdminJSON.exportPKV();
    }

    @Test
    void testExportPS() {
        when(prozessSchrittService.getAll()).thenReturn(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));

        pkAdminJSON.exportPS();
    }

    @Test
    void testExportPSV() {
        when(prozessSchrittVorlageService.getProzessSchrittVorlagen()).thenReturn(Arrays.<ProzessSchrittVorlage>asList(null));

        pkAdminJSON.exportPSV();
    }

    @Test
    void testExportPSP() {
        when(prozessSchrittParameterService.getAll()).thenReturn(Arrays.<ProzessSchrittParameter>asList(null));

        pkAdminJSON.exportPSP();
    }

    @Test
    void testExportES() {
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        pkAdminJSON.exportES();
    }

    @Test
    void testExportUsers() {
        when(userService.getAll()).thenReturn(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 34, 53), "language")));

        pkAdminJSON.exportUsers();
    }

    @Test
    void testExportProcessStepLog() {
        when(selectedProzessSchritt.getProzessSchrittLog()).thenReturn(Arrays.<ProzessSchrittLog>asList(null));

        pkAdminJSON.exportProcessStepLog();
    }

    @Test
    void testExportAuftragsLog() {
        when(selectedAuftrag.getLog()).thenReturn(new AuftragsLog(LocalDateTime.of(2020, Month.MARCH, 5, 16, 34, 53)));

        pkAdminJSON.exportAuftragsLog();
    }

    @Test
    void testSetAuftragService() {
        pkAdminJSON.setAuftragService(new AuftragService());
    }

    @Test
    void testSetProzessKettenVorlageService() {
        pkAdminJSON.setProzessKettenVorlageService(new ProzessKettenVorlageService());
    }

    @Test
    void testSetProzessSchrittService() {
        pkAdminJSON.setProzessSchrittService(new ProzessSchrittService());
    }

    @Test
    void testSetProzessSchrittVorlageService() {
        pkAdminJSON.setProzessSchrittVorlageService(new ProzessSchrittVorlageService());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatService() {
        pkAdminJSON.setProzessSchrittZustandsAutomatService(new ProzessSchrittZustandsAutomatService());
    }

    @Test
    void testSetProzessSchrittZustandsAutomatVorlageService() {
        pkAdminJSON.setProzessSchrittZustandsAutomatVorlageService(new ProzessSchrittZustandsAutomatVorlageService());
    }

    @Test
    void testSetProzessSchrittParameterService() {
        pkAdminJSON.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetExperimentierStationService() {
        pkAdminJSON.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetUserService() {
        pkAdminJSON.setUserService(new UserService());
    }

    @Test
    void testSetAvailableProzessSchritte() {
        pkAdminJSON.setAvailableProzessSchritte(Arrays.<ProzessSchritt>asList(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>())));
    }

    @Test
    void testSetSelectedProzessSchritt() {
        pkAdminJSON.setSelectedProzessSchritt(new ProzessSchritt(0, null, "duration", Arrays.<ProzessSchrittParameter>asList(null), "attribute", Arrays.<ProzessSchrittLog>asList(null), "name", true, 0,new ArrayList<>(),new ArrayList<>()));
    }

    @Test
    void testSetAvailableAuftraege() {
        pkAdminJSON.setAvailableAuftraege(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetSelectedAuftrag() {
        pkAdminJSON.setSelectedAuftrag(new Auftrag());
    }

    @Test
    void testSetConfig() {
        pkAdminJSON.setConfig(null);
    }

    @Test
    void testSetJsonb() {
        pkAdminJSON.setJsonb(null);
    }
}

