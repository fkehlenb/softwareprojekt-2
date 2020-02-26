package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.primefaces.model.file.UploadedFile;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

/**
 * this class manages the interaction between the gui and the backend system in the case that the user is an admin
 */
@Transactional
@Named
@RequestScoped
@Slf4j
@Setter
@Getter
public class AdminBean implements Serializable {

    /**
     * UserService
     */
    @Inject
    private UserService userService;

    /**
     * TragerArt Service
     */
    @Inject
    private TraegerArtService traegerArtService;

    /**
     * Experimenting Station Service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * Standort service
     */
    @Inject
    private StandortService standortService;

    /**
     * Database backup service
     */
    @Inject
    private BackupService backupService;

    /** Job Service */
    @Inject
    private AuftragService auftragService;

    /**
     * The user's name
     */
    private String vorname;

    /**
     * The user's surname
     */
    private String nachname;

    /**
     * The user's id
     */
    private String id;

    /**
     * The user's email address
     */
    private String email;

    /**
     * The user's phone number
     */
    private String telefonNummer;

    /**
     * The user's username
     */
    private String userName;

    /**
     * The user's password
     */
    private String password;

    /**
     * Whether the user is verified or not
     */
    private Boolean wurdeVerifiziert;

    /**
     * The user's language
     */
    private String language;

    /**
     * The roles the user has in the system
     */
    private List<Role> rollen = new ArrayList<>();

    /**
     * The jobs the user has in the system
     */
    private List<Auftrag> auftrags = new ArrayList<>();

    /**
     * If the user is a technologe
     */
    private boolean technologe;

    /**
     * If the user is a process chain admin
     */
    private boolean pkAdministrator;

    /**
     * If the user is a transporter
     */
    private boolean transporter;

    /**
     * If the user is a logistiker
     */
    private boolean logistiker;

    /**
     * If the user is an administrator
     */
    private boolean administrator;

    /**
     * Experimenting station name
     */
    private String experimentierStationName;

    /**
     * Experimenting station location
     */
    private Standort experimentierStationStandort;

    /**
     * Users assigned to the experimenting station
     */
    private User[] experimentierStationBenutzer;

    /**
     * All users
     */
    private List<User> allUsers;

    /**
     * All locations
     */
    private List<Standort> allLocations;

    /**
     * All experimenting stations
     */
    private List<ExperimentierStation> experimentierStations;

    /** Database import file */
    private UploadedFile importFile;

    /** Old jobs */
    private List<Auftrag> auftrage = new ArrayList<>();

    /** Strings to be converted to datetime */
    private String erstellt;
    private String gestartet;
    private String beendet;
    private String archiviert;

    /**
     * Init called on bean creation
     */
    @PostConstruct
    private void init() {
        allLocations = standortService.getStandorte();
        allUsers = userService.getAll();
        experimentierStations = experimentierStationService.getAll();
        for (Auftrag a : auftragService.getAuftrage()){
            if (a.getProzessKettenZustandsAutomat()==ProzessKettenZustandsAutomat.DURCHGEFUEHRT){
                auftrage.add(a);
            }
        }
    }

    /**
     * Shiro password matcher for password encryption
     */
    private PasswordMatcher matcher = new PasswordMatcher();

    /**
     * Add a new user
     */
    public void addUser() throws DuplicateUserException {
        LocalDateTime date1 = LocalDateTime.now();
        builtRollenList();
        try {
            String idOld = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idx");
            User user = userService.getUserById(Integer.parseInt(idOld));
            user.setVorname(vorname);
            user.setNachname(nachname);
            user.setEmail(email);
            user.setTelefonnummer(telefonNummer);
            user.setUsername(userName);
            user.setPassword(matcher.getPasswordService().encryptPassword(password));
            user.setWurdeVerifiziert(wurdeVerifiziert);
            user.setErstellungsDatum(date1);
            user.setRollen(rollen);
            user.setLanguage(language);
            userService.updateUser(user);
            String id = "";
            resetVariables();
            log.info("User updated, ID: " + idOld);
            facesNotification("User updated! ID: " + idOld);
        } catch (Exception e) {
            User user = new User(UUID.randomUUID().hashCode(), vorname, nachname, email, telefonNummer,
                    userName, matcher.getPasswordService().encryptPassword(password), wurdeVerifiziert, date1
                    , rollen, language);
            userService.addUser(user);
            log.info("Added new User, Username: " + userName);
            facesNotification("Created new user! Username: " + userName);
            userService.sendMail(user, "Account Created!", "Thank you for creating your account at SFB - Farbige Zustaende!\nIf you did not create" +
                    "this account, please contact the system administrator!");
            resetVariables();
        }
    }

    /**
     * Edit a user
     *
     * @param id - the id of the user to be edited
     */
    public void adminEditUser(String id) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idx", id);
            User user = userService.getUserById(Integer.parseInt(id));
            this.id = id;
            this.technologe = user.getRollen().contains(Role.TECHNOLOGE);
            this.pkAdministrator = user.getRollen().contains(Role.PKADMIN);
            this.transporter = user.getRollen().contains(Role.TRANSPORT);
            this.logistiker = user.getRollen().contains(Role.LOGISTIKER);
            this.administrator = user.getRollen().contains(Role.ADMIN);
            this.vorname = user.getVorname();
            this.nachname = user.getNachname();
            this.email = user.getEmail();
            this.telefonNummer = user.getTelefonnummer();
            this.userName = user.getUsername();
            this.password = new String(user.getPassword());
            this.wurdeVerifiziert = user.isWurdeVerifiziert();
            this.language = user.getLanguage();
            log.info("Updated User! ID: " + id);
            facesNotification("Updated user! ID: " + id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Couldn't edit user, Username: " + userName);
            facesError("Couldn't update user! ID: " + id);
        }
    }

    /**
     * resetVariables in the Frontend when a new user is added
     */
    public void resetVariables() {
        this.technologe = false;
        this.pkAdministrator = false;
        this.transporter = false;
        this.logistiker = false;
        this.administrator = false;
        this.id = null;
        this.vorname = null;
        this.nachname = null;
        this.email = null;
        this.telefonNummer = null;
        this.userName = null;
        this.password = null;
        this.wurdeVerifiziert = false;
        this.rollen = null;
        this.auftrags = null;
        this.language = null;
        //Control Variable for the Process -> Edit -> Add User
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idx", id);
    }

    /**
     * Help method for the List Roles to built
     */
    public void builtRollenList() {
        if (technologe) {
            rollen.add(Role.TECHNOLOGE);

        }
        if (pkAdministrator) {
            rollen.add(Role.PKADMIN);
        }
        if (transporter) {
            rollen.add(Role.TRANSPORT);
        }
        if (logistiker) {
            rollen.add(Role.LOGISTIKER);
        }
        if (administrator) {
            rollen.add(Role.ADMIN);
        }
    }

    /**
     * edits a user that already exists
     * user the user to be edited
     */
    public List<User> findUsers() {
        try {
            return userService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't fetch users from the database!");
        }
        return new ArrayList<>();
    }

    /**
     * Deletes a user using his id
     *
     * @param idu - the user's id
     */
    public void deleteUser(String idu) {
        int idUser = Integer.parseInt(idu);
        try {
            userService.removeUser(userService.getUserById(idUser));
            facesNotification("Deleted user! ID: " + idu);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't delete user, ID: " + idu);
            facesError("Couldn't delete user! ID: " + idu);
        }
    }

    /**
     * adds a carrier type
     *
     * @param newTa the new carrier type String
     */
    public void addTraegerArt(String newTa) {
        try {
            traegerArtService.getByName(newTa);
        } catch (Exception e) {
            try {
                TraegerArt ta = new TraegerArt(newTa);
                traegerArtService.addTraegerArt(ta);
                log.info("Added new container type");
            } catch (Exception f) {
                f.printStackTrace();
                log.error("Couldn't add new container type! Type: " + newTa);
            }
        }
    }

    /**
     * edits a carrier type
     *
     * @param id    the id of the container type
     * @param newTa - the new type of the container
     */
    public void editTraegerArt(int id, String newTa) {
        try {
            traegerArtService.getByName(newTa);
        } catch (Exception e) {
            try {
                TraegerArt ta = traegerArtService.getById(id);
                ta.setArt(newTa);
                traegerArtService.updateTragerArt(ta);
                log.info("Updated container type! ID: " + Integer.toString(id));
            } catch (Exception f) {
                f.printStackTrace();
                log.error("Couldn't edit container type! ID: " + Integer.toString(id));
            }
        }
    }

    /**
     * deletes a carrier type
     *
     * @param id - the id of the container type to remove
     */
    public void deleteTraegerArt(int id) {
        try {
            traegerArtService.removeTraegerArt(traegerArtService.getById(id));
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't delete container type! ID: " + Integer.toString(id));
        }
    }


    /**
     * Edit row for experimenting stations
     *
     * @param experimentierStationId - the id of the experimenting station currently being edited
     */
    public void onRowEditES(int experimentierStationId) {
        try {
            ExperimentierStation es = experimentierStationService.getById(experimentierStationId);
            List<User> newEsBenutzerList = new ArrayList<>();
            Collections.addAll(newEsBenutzerList, experimentierStationBenutzer);
            es.setBenutzer(newEsBenutzerList);
            es.setStandort(experimentierStationStandort);
            es.setName(experimentierStationName);
            experimentierStationService.updateES(es);
            log.info("Updated experimenting station! ID: " + experimentierStationId);
            facesNotification("Updated experimentierstation! ID: " + experimentierStationId);
            experimentierStations = experimentierStationService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't update experimenting station! ID: " + experimentierStationId);
            facesError("Couldn't update experimentierstatioN! ID: " + experimentierStationId);
        }
    }

    /**
     * Edit row for experimenting stations canceled
     * Reset variables on cancel
     */
    public void onRowEditCancelES() {
        experimentierStationName = "";
        experimentierStationStandort = null;
    }

    /**
     * adds a new experimentation station
     */
    public void addStation() {
        try {
            experimentierStationService.getStationByName(experimentierStationName);
            FacesContext.getCurrentInstance().addMessage("Eine Experimentierstation mir diesem Namen gibt es schon", null);
            log.error("Error adding experimenting station, station already exists! Name: " + experimentierStationName);
            facesError("Error adding experimenting station, station already exists! Name: " + experimentierStationName);
        } catch (Exception e) {
            e.printStackTrace();
            List<User> experimentierStationUsers = new ArrayList<>();
            Collections.addAll(experimentierStationUsers, experimentierStationBenutzer);
            ExperimentierStation es = new ExperimentierStation(UUID.randomUUID().hashCode(), experimentierStationStandort, experimentierStationName, ExperimentierStationZustand.VERFUEGBAR, experimentierStationUsers);
            try {
                experimentierStationService.addES(es);
                log.info("Added experimenting station! Name: " + experimentierStationName);
                facesNotification("Added experimenting station! Name: " + experimentierStationName);
                experimentierStations = experimentierStationService.getAll();
            } catch (Exception f) {
                f.printStackTrace();
                log.error("Couldn't add experimenting station! Name: " + experimentierStationName);
                facesError("Couldn't add experimenting station! Name: " + experimentierStationName);
            }
        }
    }

    /**
     * deletes a station
     */
    public void deleteStation(int esID) {
        ExperimentierStation es = null;
        try {
            es = experimentierStationService.getById(esID);
            try {
                experimentierStationService.loescheES(es);
                log.info("Deleted experimenting station! ID: " + esID);
                facesNotification("Deleted experimenting station! ID: " + esID);
                experimentierStations = experimentierStationService.getAll();
            }
            catch (Exception e){
                e.printStackTrace();
                log.info("Couldn't delete exoerimenting station! ID: " + esID);
                facesError("Couldn't delete exoerimenting station! ID: " + esID);
            }
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Convert a char[] to a localdatetime object
     * @param s - the char[] to convert
     * @return a localdatetime object matching the string */
    private LocalDateTime getDateTimeFromCharArray(char[] s){
        return LocalDateTime
                .of(Integer.parseInt(String.valueOf(s[0]+s[1]+s[2]+s[3]))
                        ,Integer.parseInt(String.valueOf(s[5]+s[6]))
                        ,Integer.parseInt(String.valueOf(s[8]+s[9]))
                        ,Integer.parseInt(String.valueOf(s[11]+s[12]))
                        ,Integer.parseInt(String.valueOf(s[14]+s[15])));
    }

    /** Globale einstellungen action listener
     * @param id - the id of the job to update */
    public void onRowEditGlobaleEinstellungen(int id){
        try {
            char[] aufErstellt = erstellt.toCharArray();
            char[] aufGestartet = gestartet.toCharArray();
            char[] aufBeendet = beendet.toCharArray();
            char[] aufArchiviert = archiviert.toCharArray();
            LocalDateTime hierErstellt = getDateTimeFromCharArray(aufErstellt);
            LocalDateTime hierGestartet = getDateTimeFromCharArray(aufGestartet);
            LocalDateTime hierBeendet = getDateTimeFromCharArray(aufBeendet);
            LocalDateTime hierArchiviert = getDateTimeFromCharArray(aufArchiviert);
            Auftrag a = auftragService.getAuftrag(id);
            AuftragsLog alog = a.getLog();
            alog.setErstellt(hierErstellt);
            alog.setStart(hierGestartet);
            alog.setBeendet(hierBeendet);
            alog.setArchiviert(hierArchiviert);
            a.setLog(alog);
            // Automatically updates log thanks to cascadeType
            auftragService.update(a);
            facesNotification("Updated job! ID: " + id);
            log.info("Updated job! ID: " + id);
        }
        catch (Exception e){
            e.printStackTrace();
            facesError("Couldn't update job! ID: " + id);
            log.error("Couldn't update job! ID: " + id);
        }
    }

    /** Globale einstellungen cancel action listener */
    public void onRowEditCancelGlobaleEinstellungen(){
        facesNotification("Canceled!");
    }

    /** Database import */
    public void databaseImport(){
        try{
            backupService.upload(importFile);
            facesNotification("Imported successfully!");
        }
        catch (Exception e){
            e.printStackTrace();
            facesError("Couldn't impoort data!");
        }
    }

    /**
     * backs the system up
     */
    public void backup() {
        try {
            backupService.backup();
            facesNotification("Backed up database!");
        } catch (Exception e) {
            e.printStackTrace();
            facesError("Couldn't backup database!");
        }
    }

    /**
     * the empty constructor
     */
    public AdminBean() {
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
