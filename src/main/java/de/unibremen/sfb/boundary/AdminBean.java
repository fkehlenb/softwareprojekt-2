package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.TraegerArtService;
import de.unibremen.sfb.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordMatcher;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    private List<User> experimentierStationBenutzer;

    /**
     * Shiro password matcher for password encryption
     */
    private PasswordMatcher matcher = new PasswordMatcher();

    /**
     * Returns all users registered in this system
     * A set containing all users
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
            user.setAuftraege(new ArrayList<>());
            userService.updateUser(user);
            String id = "";
            resetVariables();
            log.info("Added new User, Username: " + userName);
            userService.sendMail(user,"Account Created!","Thank you for creating your account at SFB - Farbige Zustände!\nIf you did not create" +
                    "this account, please contact the system administrator!");
        } catch (Exception e) {
            User user = new User(UUID.randomUUID().hashCode(), vorname, nachname, email, telefonNummer,
                    userName, matcher.getPasswordService().encryptPassword(password), wurdeVerifiziert, date1
                    , rollen, auftrags, language);
            userService.addUser(user);
            resetVariables();
            log.info("User updated, Username: " + userName);
        }
    }

    /**
     * Edit a user
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
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Couldn't edit user, Username: " + userName);
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
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't delete user, ID: " + idu);
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
     * adds a new experimentation station
     */
    public void addStation() {
        try {
            experimentierStationService.getStationByName(experimentierStationName);
            FacesContext.getCurrentInstance().addMessage("Eine Experimentierstation mir diesem Namen gibt es schon", null);
            log.error("Error adding experimenting station, station already exists! Name: " + experimentierStationName);
        } catch (Exception e) {
            e.printStackTrace();
            ExperimentierStation es = new ExperimentierStation(UUID.randomUUID().hashCode(), experimentierStationStandort, experimentierStationName, ExperimentierStationZustand.VERFUEGBAR, experimentierStationBenutzer);
            try {
                experimentierStationService.addES(es);
                log.info("Added experimenting station! Name: " + experimentierStationName);
            } catch (Exception f) {
                f.printStackTrace();
                log.error("Couldn't add experimenting station! Name: " + experimentierStationName);
            }
        }
    }

    /**
     * edits a experimentation station that already exists
     */
    public void editStation(int esID) {
        try{
            ExperimentierStation es = experimentierStationService.getById(esID);
            es.setBenutzer(experimentierStationBenutzer);
            es.setName(experimentierStationName);
            es.setStandort(experimentierStationStandort);
            experimentierStationService.updateES(es);
            log.info("Updated experimenting station! ID: " + esID);
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Failed to update experimenting station! ID: " + esID);
        }
    }

    /**
     * deletes a station
     *
     */
    public void deleteStation(int esID) {
        try{
            ExperimentierStation es = experimentierStationService.getById(esID);
            System.out.println("PASSED STEP 1");
            experimentierStationService.loescheES(es);
            log.info("Deleted experimenting station! ID: " + esID);
        }
        catch (Exception e){
            e.printStackTrace();
            log.info("Failed to remove experimenting station! ID: " + esID);
        }
    }

    /**
     * assigns a user to a station
     *
     * @param us the user
     * @param es the station
     */
    public void userToStation(User us, ExperimentierStation es) {
    }

    /**
     * generates a regestration mail that is supposed to be sent out to new users
     */
    public void generateRegestrationMail() {
    }

    /** Action listener for table updates */
    public void onClickExperimentierStationEdit(){

    }

    /**
     * returns all experimentation stations existing
     *
     * @return a set containing all stations
     */
    public List<ExperimentierStation> getES() {
        return experimentierStationService.getAll();
    }

    /**
     * edits the time of a job
     *
     * @param a the job
     */
    public void editAuftragTime(Auftrag a) {
    }

    /**
     * backs the system up
     */
    public void backup() throws SQLException {
        log.info("Trying to connect with DB");
        String sqlFilePath = "./Backup" + LocalDateTime.now().toString();
        //em.createNativeQuery(String.format("SCRIPT TO '%s'", sqlFilePath)).executeUpdate();

    }

    /**
     * the emtpy constructor
     */
    public AdminBean() {
    }

}
