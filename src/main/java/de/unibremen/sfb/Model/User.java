package de.unibremen.sfb.Model;


import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/** This class is used to create user objects */

@Entity
@Table(name = "users")
public class User {

    /** User's id  */
    //@NonNull
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
    public byte[] password;

    /** Is the user verified? */
    //@NonNull
    public boolean wurdeVerifiziert;

    /** Creation date of the user object */
    //@NonNull
    public Date erstellungsDatum;

    /** The role's of the user */
    //@NonNull
    //public Set<Role> rollen;

    /** The experimenting stations a user is assigned to */
    //public Set<ExperimentierStation> stationen;

    /** The jobs a user has */

    //public Set<Auftrag> auftraege;

    /** The User's language preference */
    //@NonNull
    public String language;

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
    }
}
