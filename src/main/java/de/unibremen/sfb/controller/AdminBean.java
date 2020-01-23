package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.model.User;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
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
    @Getter
    @Setter
    private User admin;
    @Getter
    @Setter
    private String vorname;
    @Getter
    @Setter
    private String nachname;
    @Getter
    @Setter
    private String id;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String telefonNummer;
    @Getter
    @Setter
    private String userName;
    @Getter
    @Setter
    private String password;
    @Getter
    @Setter
    private String  wurdeVerifiziert;
    @Getter
    @Setter
    private String erstellungsDatum;
    @Getter
    @Setter
    private String rolle;
    @Getter
    @Setter
    private String language;

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
     * @param user the user to be edited
     */
    public void editUser(User user) {}

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
     * @return the user
     */
    public User getAdmin() { return admin; }

    /**
     * sets the administrator managed by this bean
     * @param admin the user
     */
    public void setUser(User admin) { this.admin = admin; }
}

