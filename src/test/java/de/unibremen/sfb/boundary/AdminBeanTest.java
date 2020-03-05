package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.primefaces.model.file.UploadedFile;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class AdminBeanTest {
    @Mock
    UserService userService;
    @Mock
    TraegerArtService traegerArtService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    StandortService standortService;
    @Mock
    BackupService backupService;
    @Mock
    AuftragService auftragService;
    @Mock
    RoleService roleService;
    @Mock
    List<String> rollen;
    @Mock
    List<Auftrag> auftrags;
    @Mock
    Standort experimentierStationStandort;
    //Field experimentierStationBenutzer of type User[] - was not mocked since Mockito doesn't mock arrays
    @Mock
    List<User> allUsers;
    @Mock
    List<Standort> allLocations;
    @Mock
    List<ExperimentierStation> experimentierStations;
    @Mock
    UploadedFile importFile;
    @Mock
    List<Auftrag> auftrage;
    @Mock
    List<ProzessSchrittParameter> bedingungen;
    @Mock
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Mock
    List<ProzessSchrittParameter> availableBedingungen;
    @Mock
    PasswordMatcher matcher;
    @Mock
    Logger log;
    @InjectMocks
    AdminBean adminBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddUser() throws UserNotFoundException, DuplicateUserException, RoleNotFoundException, DuplicateRoleException {
        when(userService.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language"));
        adminBean.addUser();
    }

    @Test
    void testAdminEditUser() throws UserNotFoundException {
        when(userService.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language"));
        when(roleService.getRolesByUser(anyString())).thenReturn(Arrays.<Role>asList(new Role(0, "name")));

        adminBean.adminEditUser("id");
    }

    @Test
    void testResetVariables() {
        adminBean.resetVariables();
    }

    @Test
    void testBuiltRollenList() {
        adminBean.builtRollenList();
    }

    @Test
    void testFindUsers() {
        when(userService.getAll()).thenReturn(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language")));

        List<User> result = adminBean.findUsers();
        Assertions.assertEquals(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language")), result);
    }

    @Test
    void testDeleteUser() throws UserNotFoundException {
        when(userService.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language"));

        adminBean.deleteUser("idu");
    }

    @Test
    void testAddTraegerArt() throws TraegerArtNotFoundException {
        when(traegerArtService.getByName(anyString())).thenReturn(new TraegerArt("art"));

        adminBean.addTraegerArt("newTa");
    }

    @Test
    void testEditTraegerArt() throws TraegerArtNotFoundException {
        when(traegerArtService.getByName(anyString())).thenReturn(new TraegerArt("art"));
        when(traegerArtService.getById(anyInt())).thenReturn(new TraegerArt("art"));

        adminBean.editTraegerArt(0, "newTa");
    }

    @Test
    void testDeleteTraegerArt() throws TraegerArtNotFoundException {
        when(traegerArtService.getById(anyInt())).thenReturn(new TraegerArt("art"));

        adminBean.deleteTraegerArt(0);
    }

    @Test
    void testOnRowEditES() throws ExperimentierStationNotFoundException {
        when(experimentierStationService.getById(anyInt())).thenReturn(new ExperimentierStation());
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        adminBean.onRowEditES(0);
    }

    @Test
    void testOnRowEditCancelES() {
        adminBean.onRowEditCancelES();
    }

    @Test
    void testAddStation() throws ExperimentierStationNotFoundException {
        when(experimentierStationService.getStationByName(anyString())).thenReturn(new ExperimentierStation());
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        adminBean.addStation();
    }

    @Test
    void testDeleteStation() throws ExperimentierStationNotFoundException {
        when(experimentierStationService.getById(anyInt())).thenReturn(new ExperimentierStation());
        when(experimentierStationService.getAll()).thenReturn(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));

        adminBean.deleteStation(0);
    }

    @Test
    void testOnRowEditGlobaleEinstellungen() throws AuftragNotFoundException {
        when(auftragService.getObjById(anyInt())).thenReturn(new Auftrag());

        adminBean.onRowEditGlobaleEinstellungen(0);
    }

    @Test
    void testOnRowEditCancelGlobaleEinstellungen() {
        adminBean.onRowEditCancelGlobaleEinstellungen();
    }

    @Test
    void testDatabaseImport() {
        adminBean.databaseImport();
    }

    @Test
    void testBackup() {
        adminBean.backup();
    }

    @Test
    void testSetUserService() {
        adminBean.setUserService(new UserService());
    }

    @Test
    void testSetTraegerArtService() {
        adminBean.setTraegerArtService(new TraegerArtService());
    }

    @Test
    void testSetExperimentierStationService() {
        adminBean.setExperimentierStationService(new ExperimentierStationService());
    }

    @Test
    void testSetStandortService() {
        adminBean.setStandortService(new StandortService());
    }

    @Test
    void testSetBackupService() {
        adminBean.setBackupService(new BackupService());
    }

    @Test
    void testSetAuftragService() {
        adminBean.setAuftragService(new AuftragService());
    }

    @Test
    void testSetRoleService() {
        adminBean.setRoleService(new RoleService());
    }

    @Test
    void testSetVorname() {
        adminBean.setVorname("vorname");
    }

    @Test
    void testSetNachname() {
        adminBean.setNachname("nachname");
    }

    @Test
    void testSetId() {
        adminBean.setId("id");
    }

    @Test
    void testSetEmail() {
        adminBean.setEmail("email");
    }

    @Test
    void testSetTelefonNummer() {
        adminBean.setTelefonNummer("telefonNummer");
    }

    @Test
    void testSetUserName() {
        adminBean.setUserName("userName");
    }

    @Test
    void testSetPassword() {
        adminBean.setPassword("password");
    }

    @Test
    void testSetWurdeVerifiziert() {
        adminBean.setWurdeVerifiziert(Boolean.TRUE);
    }

    @Test
    void testSetLanguage() {
        adminBean.setLanguage("language");
    }

    @Test
    void testSetRollen() {
        adminBean.setRollen(Arrays.<String>asList("String"));
    }

    @Test
    void testSetAuftrags() {
        adminBean.setAuftrags(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetTechnologe() {
        adminBean.setTechnologe(true);
    }

    @Test
    void testSetPkAdministrator() {
        adminBean.setPkAdministrator(true);
    }

    @Test
    void testSetTransporter() {
        adminBean.setTransporter(true);
    }

    @Test
    void testSetLogistiker() {
        adminBean.setLogistiker(true);
    }

    @Test
    void testSetAdministrator() {
        adminBean.setAdministrator(true);
    }

    @Test
    void testSetExperimentierStationName() {
        adminBean.setExperimentierStationName("experimentierStationName");
    }

    @Test
    void testSetExperimentierStationStandort() {
        adminBean.setExperimentierStationStandort(new Standort(0, "ort"));
    }

    @Test
    void testSetExperimentierStationBenutzer() {
        adminBean.setExperimentierStationBenutzer(new User[]{new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language")});
    }

    @Test
    void testSetAllUsers() {
        adminBean.setAllUsers(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 19, 14), "language")));
    }

    @Test
    void testSetAllLocations() {
        adminBean.setAllLocations(Arrays.<Standort>asList(new Standort(0, "ort")));
    }

    @Test
    void testSetExperimentierStations() {
        adminBean.setExperimentierStations(Arrays.<ExperimentierStation>asList(new ExperimentierStation()));
    }

    @Test
    void testSetImportFile() {
        adminBean.setImportFile(null);
    }

    @Test
    void testSetAuftrage() {
        adminBean.setAuftrage(Arrays.<Auftrag>asList(new Auftrag()));
    }

    @Test
    void testSetBedingungen() {
        adminBean.setBedingungen(Arrays.<ProzessSchrittParameter>asList(null));
    }

    @Test
    void testSetProzessSchrittParameterService() {
        adminBean.setProzessSchrittParameterService(new ProzessSchrittParameterService());
    }

    @Test
    void testSetAvailableBedingungen() {
        adminBean.setAvailableBedingungen(Arrays.<ProzessSchrittParameter>asList(null));
    }

    @Test
    void testSetErstellt() {
        adminBean.setErstellt("erstellt");
    }

    @Test
    void testSetGestartet() {
        adminBean.setGestartet("gestartet");
    }

    @Test
    void testSetBeendet() {
        adminBean.setBeendet("beendet");
    }

    @Test
    void testSetArchiviert() {
        adminBean.setArchiviert("archiviert");
    }

    @Test
    void testSetMatcher() {
        adminBean.setMatcher(null);
    }
}

