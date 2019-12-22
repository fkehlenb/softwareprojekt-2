package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.TraegerArt;
import de.unibremen.sfb.Model.User;

import java.util.Set;

public class AdminBean {

    /**
     * The user managed by this bean
     */
    public User admin;

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
}

