package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.UserDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.IOException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction between the gui and the backend system in the case that the user is an admin
 */
@Transactional
@Named
@RequestScoped
@Slf4j
public class AdminBean implements Serializable {

    /**
     * The user managed by this bean
     */
    private User admin;
    @Inject
    private UserController userController;
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
    private Boolean  wurdeVerifiziert;
    @Getter
    @Setter
    private String erstellungsDatum;
    @Getter
    @Setter
    private String rolle;
    @Getter
    @Setter
    private String language;
    @Getter
    @Setter
    HashSet<Role> a = new HashSet<>();
    /**
     * Returns all users registered in this system
     * @return A set containing all users
     */
    public Set<User> getAllUser() { return null; }

    /**
     * Adds a new User to the System
     *  the new user
     */
    public void addUser() throws DuplicateUserException, UserNotFoundException {

        LocalDateTime date1=   LocalDateTime.now();
        List<Role> rol=new ArrayList<>();
        rol.add(Role.TECHNOLOGE);
        try{
            User b =userController.getUserByID(Integer.parseInt(id));
            b.setVorname(vorname);
            b.setNachname(nachname);
            b.setEmail(email);
            b.setTelefonnummer(telefonNummer);
            b.setUsername(userName);
            b.setPassword(password.getBytes());
            b.setWurdeVerifiziert(wurdeVerifiziert);
            b.setErstellungsDatum(date1);
            b.setRollen(rol);
            b.setLanguage(language);
            userController.update(b);
        }catch (Exception e){
            User b=new User();
            b.setId(Integer.parseInt(id));
            b.setVorname(vorname);
            b.setNachname(nachname);
            b.setEmail(email);
            b.setTelefonnummer(telefonNummer);
            b.setUsername(userName);
            b.setPassword(password.getBytes());
            b.setWurdeVerifiziert(wurdeVerifiziert);
            b.setErstellungsDatum(date1);
            b.setRollen(rol);
            b.setLanguage(language);
            userController.addUser(b);
        }


    }

    public void adminEditUser(String id) throws IOException {
        this.id= id;

    }

    /**
     * edits a user that already exists
     *  user the user to be edited
     */
    public List<User> findUsers() throws UserNotFoundException {
        try {
            return userController.getAll();
        }catch(Exception e){

        }
        return null;
    }


    /**
     * deletes a user from the system
     *  the user to be deleted
     */
    public void deleteUser(String idu) throws UserNotFoundException {
        int idUser = Integer.parseInt(idu);
        try {
            userController.removeUser(idUser);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


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
    public  void backup() throws SQLException {
        log.info("Trying to DB");
        String sqlFilePath = "./Backup" + LocalDateTime.now().toString();
//        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
//        log.info("Connected to " + conn.toString());
//        Statement stmt = conn.createStatement();
//        stmt.executeQuery(String.format("SCRIPT TO '%s'", sqlFilePath));
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
