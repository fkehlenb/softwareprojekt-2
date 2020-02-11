package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.UserDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.PasswordMatcher;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.*;

/**
 * this class manages the interaction between the gui and the backend system in the case that the user is an admin
 */
@Transactional
@Named
@ViewScoped
@Slf4j
@Getter
@Setter
public class AdminBean implements Serializable {

    /**
     * UserDAO
     */
    @Inject
    private UserDAO userDAO;

    /**
     * The user's first name
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
     * The user's email
     */
    private String email;

    /**
     * The user's phone number
     */
    private String telefonNummer;

    /**
     * The user's username
     */
    private String username;

    /**
     * The user's password
     */
    private String password;

    /**
     * Whether or not the user was verified
     */
    private Boolean wurdeVerifiziert;

    /**
     * The user's language
     */
    private String language;

    /**
     * The roles the user will have in the system
     */
    List<Role> roles = new ArrayList<>();

    /**
     * If the user is a technologe
     */
    private boolean technologe;
    //private boolean TECHNOLOGER;

    /**
     * If the user is a pkadmin
     */
    private boolean pkadmin;
    //private boolean PKADMINOR;

    /**
     * If the user is a transporter
     */
    //TODO variables are small
    private boolean TRANSPORTER;

    /**
     * If the user is a logistiker
     */
    //TODO variables are small
    private boolean LOGISTIKERKER;

    /**
     * If the user is an administrator
     */
    //TODO variables are small
    private boolean ADMINTATOR;

    /**
     * Shiro password matcher for password encryption
     */
    private PasswordMatcher matcher = new PasswordMatcher();

    /**
     * Returns all users registered in this system
     * A set containing all users
     */
    public void addUser() throws DuplicateUserException {
        // TODO NO SESSION MAP
        String idOld = "";
        try {
            idOld = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idx");
        } catch (Exception e) {
            e.printStackTrace();
        }

        LocalDateTime date1 = LocalDateTime.now();

        if (technologe) {
            roles.add(Role.TECHNOLOGE);
        }
        if (pkadmin) {
            roles.add(Role.PKADMIN);
        }
        if (TRANSPORTER) {
            roles.add(Role.TRANSPORT);
        }
        if (LOGISTIKERKER) {
            roles.add(Role.LOGISTIKER);
        }
        if (ADMINTATOR) {
            roles.add(Role.ADMIN);
        }
        //TODO DUPLICATE CODE: Dont use no args constr
        //TODO use usercontroller instead of dao
        try {
            User b = userDAO.getUserById(Integer.parseInt(idOld));
            b.setVorname(vorname);
            b.setNachname(nachname);
            b.setEmail(email);
            b.setTelefonnummer(telefonNummer);
            b.setUsername(username);
            b.setPassword(matcher.getPasswordService().encryptPassword(password));
            b.setWurdeVerifiziert(wurdeVerifiziert);
            b.setErstellungsDatum(date1);
            b.setRollen(roles);
            b.setLanguage(language);
            b.setAuftraege(new ArrayList<>());
            userDAO.update(b);
        } catch (Exception e) {
            User b = new User();
            b.setId(UUID.randomUUID().hashCode());
            b.setVorname(vorname);
            b.setNachname(nachname);
            b.setEmail(email);
            b.setTelefonnummer(telefonNummer);
            b.setUsername(username);
            b.setPassword(matcher.getPasswordService().encryptPassword(password));
            b.setWurdeVerifiziert(wurdeVerifiziert);
            b.setErstellungsDatum(date1);
            b.setRollen(roles);
            b.setLanguage(language);
            userDAO.persist(b);
        }


    }

    public void adminEditUser(String id) throws UserNotFoundException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idx", id);
        User user = userDAO.getUserById(Integer.parseInt(id));
        this.technologe = user.getRollen().contains(Role.TECHNOLOGE);
        this.pkadmin = user.getRollen().contains(Role.PKADMIN);
        this.TRANSPORTER = user.getRollen().contains(Role.TRANSPORT);
        this.LOGISTIKERKER = user.getRollen().contains(Role.LOGISTIKER);
        this.ADMINTATOR = user.getRollen().contains(Role.ADMIN);
        this.vorname = user.getVorname();
        this.nachname = user.getNachname();
        this.email = user.getEmail();
        this.telefonNummer = user.getTelefonnummer();
        this.username = user.getUsername();
        this.password = new String(user.getPassword());
        this.wurdeVerifiziert = user.isWurdeVerifiziert();
        this.language = user.getLanguage();
    }


    /**
     * edits a user that already exists
     * user the user to be edited
     */
    public List<User> findUsers() throws UserNotFoundException {
        try {
            // TODO userservice not dao
            return userDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Deletes a user using his id
     *
     * @param idu - the user's id
     * @throws UserNotFoundException if the user couldn't be found in the database
     */
    public void deleteUser(String idu) throws UserNotFoundException {
        int idUser = Integer.parseInt(idu);
        try {
            //TODO user service
            userDAO.remove(userDAO.getUserById(idUser));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * adds a carrier type
     *
     * @param ta the new carrier type
     */
    public void addTraegerArt(TraegerArt ta) {
    }

    /**
     * edits a carrier type
     *
     * @param ta the carrier type to be edited
     */
    public void editTraegerArt(TraegerArt ta) {
    }

    /**
     * deletes a carrier type
     *
     * @param ta the carrier type to be deleted
     */
    public void deleteTraegerArt(TraegerArt ta) {
    }

    /**
     * adds a new experimentation station
     *
     * @param es the new station
     */
    public void addStation(ExperimentierStation es) {
    }

    /**
     * edits a experimentation station that already exists
     *
     * @param es the station to be edited
     */
    public void editStation(ExperimentierStation es) {
    }

    /**
     * deletes a station
     *
     * @param es the station to be edited
     */
    public void deleteStation(ExperimentierStation es) {
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

    /**
     * returns all experimentation stations existing
     *
     * @return a set containing all stations
     */
    public Set<ExperimentierStation> getES() {
        return null;
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
        Connection conn = DriverManager.getConnection("jdbc:h2:./swp2", "swp", "swp");
        log.info("Connected to " + conn.toString());
        Statement stmt = conn.createStatement();
        log.info("Executing: SCRIPT TO " + sqlFilePath);
        stmt.executeQuery(String.format("SCRIPT TO '%s'", sqlFilePath));
    }

    /**
     * the emtpy constructor
     */
    public AdminBean() {
    }

}
