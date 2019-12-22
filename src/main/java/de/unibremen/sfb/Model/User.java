package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.Set;

/** This class is used to create user objects */
@Data
@NonNull
public class User {

    /** User's id  */
    public int id;

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
    public Set<Role> rollen;

    /** The experimenting stations a user is assigned to */
    public Set<ExperimentierStation> stationen;
}
