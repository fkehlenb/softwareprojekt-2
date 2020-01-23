package de.unibremen.sfb.Model;


import lombok.NonNull;

<<<<<<< HEAD:src/main/java/de/unibremen/sfb/model/User.java
import javax.persistence.*;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
>>>>>>> adminBean persistence workin:src/main/java/de/unibremen/sfb/Model/User.java
import java.util.Date;
import java.util.Set;

/** This class is used to create user objects */
<<<<<<< HEAD:src/main/java/de/unibremen/sfb/model/User.java
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findById", query = "SELECT u from User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u from User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u from User u WHERE u.email = :email")
})
public class User {

    /** User's id  */
    @NonNull
=======

@Entity
@Table(name = "users")
public class User {

    /** User's id  */
    //@NonNull
>>>>>>> adminBean persistence workin:src/main/java/de/unibremen/sfb/Model/User.java
    @Id
    public int id;

    /** The user's name */
    //@NonNull
    public String vorname;

    /** The user's surname */
    //@NonNull
    public String nachname;

    /** User's email address */
    //@NonNull
    public String email;

    /** The user's phone number */
    //@NonNull
    public String telefonnummer;

    /** User's username */
    //@NonNull
    public String username;

    /** User's hashed password */
<<<<<<< HEAD:src/main/java/de/unibremen/sfb/model/User.java
    @NonNull
=======
>>>>>>> adminBean persistence workin:src/main/java/de/unibremen/sfb/Model/User.java
    public byte[] password;

    /** Is the user verified? */
    //@NonNull
    public boolean wurdeVerifiziert;

    /** Creation date of the user object */
    //@NonNull
    public Date erstellungsDatum;

    /** The role's of the user */
<<<<<<< HEAD:src/main/java/de/unibremen/sfb/model/User.java
    @NonNull
    @OneToMany
    public Set<Role> rollen;

    /** The experimenting stations a user is assigned to */
    @OneToMany
    public Set<ExperimentierStation> stationen;

    /** The jobs a user has */
    @NonNull
    @OneToMany
    public Set<Auftrag> auftraege;
=======
    //@NonNull
    //public Set<Role> rollen;

    /** The experimenting stations a user is assigned to */
    //public Set<ExperimentierStation> stationen;

    /** The jobs a user has */

    //public Set<Auftrag> auftraege;
>>>>>>> adminBean persistence workin:src/main/java/de/unibremen/sfb/Model/User.java

    /** The User's language preference */
    //@NonNull
    public String language;

<<<<<<< HEAD:src/main/java/de/unibremen/sfb/model/User.java
    /** Empty Constructor */
    public User(){
        /* Required by jpa */
=======
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public boolean isWurdeVerifiziert() {
        return wurdeVerifiziert;
    }

    public void setWurdeVerifiziert(boolean wurdeVerifiziert) {
        this.wurdeVerifiziert = wurdeVerifiziert;
    }

    public Date getErstellungsDatum() {
        return erstellungsDatum;
    }

    public void setErstellungsDatum(Date erstellungsDatum) {
        this.erstellungsDatum = erstellungsDatum;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
>>>>>>> adminBean persistence workin:src/main/java/de/unibremen/sfb/Model/User.java
    }
}
