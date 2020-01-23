package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.Set;

/** This class is used to create user objects */
@Data
public class User {

    public User() {}

    /** User's id  */
    @NonNull
    public int id;

    /** The user's name */
    @NonNull
    public String vorname;

    /** The user's surname */
    @NonNull
    public String nachname;

    /** User's email address */
    @NonNull
    public String email;

    /** The user's phone number */
    @NonNull
    public String telefonnummer;

    /** User's username */
    @NonNull
    public String username;

    /** User's hashed password */
    public byte[] password;

    /** Is the user verified? */
    @NonNull
    public boolean wurdeVerifiziert;

    /** Creation date of the user object */
    @NonNull
    public Date erstellungsDatum;

    /** The role's of the user */
    @NonNull
    public Set<Role> rollen;

    /** The experimenting stations a user is assigned to */
    public Set<ExperimentierStation> stationen;

    /** The jobs a user has */
    public Set<Auftrag> auftraege;

    /** The User's language preference */
    @NonNull
    public String language;
}
