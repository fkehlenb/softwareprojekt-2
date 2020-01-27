package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.UserDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * this class manages the interaction between the gui and the backend system in the case that the user is an admin
 */

@Slf4j
public class AdminBean implements Serializable {

    /**
     * The user managed by this bean
     */


    private User admin;

    @Inject
    private UserDAO userDAO;


    private String vorname;

    private String nachname;

    private String id;

    private String email;

    private String telefonNummer;

    private String userName;

    private String password;

    private String  wurdeVerifiziert;

    private String erstellungsDatum;

    private String rolle;

    private String language;

    HashSet<Role> a = new HashSet<>();
    /**
     * Returns all users registered in this system
     * @return A set containing all users
     */
    public Set<User> getAllUser() { return null; }

    /**
     * Adds a new User to the System
     * @param user the new user
     */
    public void addUser(User user) {}

    /**
     * edits a user that already exists
     *  user the user to be edited
     */
    public String findUser() throws UserNotFoundException {
        try {
            return userDAO.getUserById(11).getUsername();
        }catch(Exception e){

        }
        return "Not Fonud";
    }

    /**
     * deletes a user from the system
     * @param user the user to be deleted
     */
    public void deleteUser(User user) {}

    /**
     * adds a carrier type
     * @param ta the new carrier type
     */
    public void addTraegerArt(TraegerArt ta) {}

    /**
     * edits a carrier type
     * @param ta the carrier type to be edited
     */
    public void editTraegerArt(TraegerArt ta) {}

    /**
     * deletes a carrier type
     * @param ta the carrier type to be deleted
     */
    public void deleteTraegerArt(TraegerArt ta) {}

    /**
     * adds a new experimentation station
     * @param es the new station
     */
    public void addStation(ExperimentierStation es) {}

    /**
     * edits a experimentation station that already exists
     * @param es the station to be edited
     */
    public void editStation(ExperimentierStation es) {}

    /**
     * deletes a station
     * @param es the station to be edited
     */
    public void deleteStation(ExperimentierStation es) {}

    /**
     * assigns a user to a station
     * @param us the user
     * @param es the station
     */
    public void userToStation(User us, ExperimentierStation es) {}

    /**
     * generates a regestration mail that is supposed to be sent out to new users
     */
    public void generateRegestrationMail()  {}

    /**
     * returns all experimentation stations existing
     * @return a set containing all stations
     */
    public Set<ExperimentierStation> getES()  { return null; }

    /**
     * edits the time of a job
     * @param a the job
     */
    public void editAuftragTime(Auftrag a) {}

    /**
     * backs the system up
     */
    public void backup() throws SQLException {
        log.info("Trying to DB");
        String sqlFilePath = "./Backup" + LocalDateTime.now().toString();
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        log.info("Connected to " + conn.toString());
        Statement stmt = conn.createStatement();
        stmt.executeQuery(String.format("SCRIPT TO '%s'", sqlFilePath));
    }

    /**
     * the emtpy constructor
     */
    public AdminBean() {}

    /**
     * returns the administrator managed by this bean
     * @return the user
     */
    public User getAdmin() { return admin; }

    /**
     * sets the administrator managed by this bean
     * @param admin the user
     */
    public void setUser(User admin) { this.admin = admin; }
}
