package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
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

    private String vorname;

    private String nachname;

    private String id;

    private String email;

    private String telefonNummer;

    private String userName;

    private String password;

    private Boolean  wurdeVerifiziert;

    private String language;

    private List<Role> rol = new ArrayList<>();

    private List<Auftrag> auftrags = new ArrayList<>();

    private boolean technologer;

    private boolean pkadminor;

    private boolean transporter;

    private boolean logistikerker;

    private boolean admintator;

    /**
     * Shiro password matcher for password encryption
     */
    private PasswordMatcher matcher = new PasswordMatcher();

    /**
     * Returns all users registered in this system
     * A set containing all users
     */
    public void addUser() throws DuplicateUserException {
        String  idOld="";

        try {
            idOld = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idx");
        }catch (Exception e){
           e.printStackTrace();
        }
        LocalDateTime date1=   LocalDateTime.now();
        if(technologer) {rol.add(Role.TECHNOLOGE);
        }
        if(pkadminor) {rol.add(Role.PKADMIN);
        }
        if(transporter) {rol.add(Role.TRANSPORT);
        }
        if(logistikerker) {rol.add(Role.LOGISTIKER);
        }
        if(admintator) {rol.add(Role.ADMIN);
        }
        try{
            User user = userService.getUserById(Integer.parseInt(idOld));
            user.setVorname(vorname);
            user.setNachname(nachname);
            user.setEmail(email);
            user.setTelefonnummer(telefonNummer);
            user.setUsername(userName);
            user.setPassword(matcher.getPasswordService().encryptPassword(password));
            user.setWurdeVerifiziert(wurdeVerifiziert);
            user.setErstellungsDatum(date1);
            user.setRollen(rol);
            user.setLanguage(language);
            user.setAuftraege(new ArrayList<>());
            userService.updateUser(user);
        }catch (Exception e){
            User user=new User(UUID.randomUUID().hashCode(),vorname,nachname,email,telefonNummer,
                    userName,matcher.getPasswordService().encryptPassword(password),wurdeVerifiziert,date1
                    ,rol,auftrags,language);
            userService.addUser(user);
        }
    }

    public void adminEditUser(String id) throws UserNotFoundException {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("idx", id);
        User user = userService.getUserById(Integer.parseInt(id));
        this.id=id;
        this.technologer =user.getRollen().contains(Role.TECHNOLOGE);
        this.pkadminor =user.getRollen().contains(Role.PKADMIN);
        this.transporter =user.getRollen().contains(Role.TRANSPORT);
        this.logistikerker =user.getRollen().contains(Role.LOGISTIKER);
        this.admintator =user.getRollen().contains(Role.ADMIN);
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
     * user the user to be edited
     */
    public List<User> findUsers() throws UserNotFoundException {
        try {
            return userService.getAll();
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
            userService.removeUser(userService.getUserById(idUser));
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
        //em.createNativeQuery(String.format("SCRIPT TO '%s'", sqlFilePath)).executeUpdate();

    }
    /**
     * the emtpy constructor
     */
    public AdminBean() {}

}
