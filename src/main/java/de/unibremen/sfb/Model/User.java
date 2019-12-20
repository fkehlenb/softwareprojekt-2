package de.unibremen.sfb.Model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
/* This class is used to create user objects */
public class User {

    /** User's id  */
    public final int id;

    /** The user's name */
    public String vorname;

    /** The user's surname */
    public String nachname;

    /** User's email address */
    public String email;

    /** The user's phone number */
    public String telefonnummer;

    /** User's username */
    public String username;

    /** User's hashed password */
    public Byte[] password;

    /** Is the user verified? */
    public boolean wurdeVerifiziert;

    /** Creation date of the user object */
    public Date erstellungsDatum;

    /** The role's of the user */
    public List<Role> rollen;

    /** The experimenting stations a user is assigned to */
    public List<ExperimentierStation> stationen;

    /** Constructor for the user objects
     * @param id - the user's unique identification number */
    public User(int id){
        this.id=id;
    }
}
