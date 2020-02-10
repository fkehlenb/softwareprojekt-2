package de.unibremen.sfb.controller;


import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.UserDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
public class AdminBean implements Serializable {

    /**
     * The user managed by this bean
     */

    @Inject
    private UserDAO userDAO;

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
    private String language;
    @Getter
    @Setter
    List<Role> rol = new ArrayList<>();
    @Getter
    @Setter
    private boolean TECHNOLOGER;
    @Getter
    @Setter
    private boolean PKADMINOR;
    @Getter
    @Setter
    private boolean TRANSPORTER;
    @Getter
    @Setter
    private boolean LOGISTIKERKER;
    @Getter
    @Setter
    private boolean ADMINTATOR;

    /**
     * Returns all users registered in this system
     * A set containing all users
     */
    public void addUser() throws DuplicateUserException {
        String idOld="";
        try {
             idOld = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idx");
        }catch (Exception e){
           e.printStackTrace();
        }

        LocalDateTime date1=   LocalDateTime.now();

        if(TECHNOLOGER) {rol.add(Role.TECHNOLOGE);

        }
        if(PKADMINOR) {rol.add(Role.PKADMIN);
        }
        if(TRANSPORTER) {rol.add(Role.TRANSPORT);
        }
        if(LOGISTIKERKER) {rol.add(Role.LOGISTIKER);
        }
        if(ADMINTATOR) {rol.add(Role.ADMIN);
        }

        try{
            User b =userDAO.getUserById(Integer.parseInt(idOld));
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
            b.setAuftraege(new ArrayList<>());
            userDAO.update(b);
        }catch (Exception e){
            User b=new User();
            b.setId(UUID.randomUUID().hashCode());
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
            userDAO.persist(b);
        }


    }

    public void adminEditUser(String id) throws UserNotFoundException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idx",id);

        User user = userDAO.getUserById(Integer.parseInt(id));
        this.TECHNOLOGER=user.getRollen().contains(Role.TECHNOLOGE);
        this.PKADMINOR=user.getRollen().contains(Role.PKADMIN);
        this.TRANSPORTER=user.getRollen().contains(Role.TRANSPORT);
        this.LOGISTIKERKER=user.getRollen().contains(Role.LOGISTIKER);
        this.ADMINTATOR=user.getRollen().contains(Role.ADMIN);
        this.vorname=user.getVorname();
        this.nachname=user.getNachname();
        this.email=user.getEmail();
        this.telefonNummer=user.getTelefonnummer();
        this.userName=user.getUsername();
        this.password=new String(user.getPassword());
        this.wurdeVerifiziert = user.isWurdeVerifiziert();
        this.language = user.getLanguage();
    }



    /**
     * edits a user that already exists
     *  user the user to be edited
     */
    public List<User> findUsers() throws UserNotFoundException {
        try {
            return userDAO.getAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     * deletes a user from the system
     *  the user to be deleted
     */
    public void deleteUser(String idu)  {
        int idUser = Integer.parseInt(idu);
        try {

            userDAO.remove(userDAO.getUserById(idUser));
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
    public AdminBean() {}

}
