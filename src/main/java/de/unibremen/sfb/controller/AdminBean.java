package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.UserDAO;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * this class manages the interaction between the gui and the backend system in the case that the user is an admin
 */
@Named
@RequestScoped
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
     *   the new user
     */
    public void login() {
        User user = new User();
        try {
        user.id = Integer.parseInt(id);
        user.vorname = vorname;
        user.nachname = nachname;
        user.email = email;
        user.telefonnummer = telefonNummer;
        user.username = userName;
        var pw = "12345678";
        user.password = pw.getBytes();
        user.wurdeVerifiziert = Boolean.parseBoolean(wurdeVerifiziert);
        user.erstellungsDatum = new Date();
        a.add(Role.TECHNOLOGE);
        //user.rollen = a;
        user.language = language;

            userDAO.persist(user);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * edits a user that already exists
     *  user the user to be edited
     */
    public String findUser() throws UserNotFoundException {
        try {
            return userDAO.getUserById(11).username;
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
    public void backup() {}

    /**
     * the emtpy constructor
     */
    public AdminBean() {}

    /**
     * returns the administrator managed by this bean
     * @return the usern
     */
    public User getAdmin() { return admin; }

    /**
     * sets the administrator managed by this bean
     * @param admin the user
     */
    public void setUser(User admin) { this.admin = admin; }

    public void setAdmin(User admin) {
        this.admin = admin;
    }



    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWurdeVerifiziert() {
        return wurdeVerifiziert;
    }

    public void setWurdeVerifiziert(String wurdeVerifiziert) {
        this.wurdeVerifiziert = wurdeVerifiziert;
    }

    public String getErstellungsDatum() {
        return erstellungsDatum;
    }

    public void setErstellungsDatum(String erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

