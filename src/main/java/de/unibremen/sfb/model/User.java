package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * This class is used to create user objects
 */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findById", query = "SELECT u from User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u from User u WHERE u.username = :username"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u from User u WHERE u.email = :email")
})

@Table(name = "users")
public class User {

    /**
     * User's id
     */
    @Id
    @NonNull
    public int id;

    /**
     * The user's name
     */
    @NonNull
    public String vorname;

    /**
     * The user's surname
     */
    @NonNull
    public String nachname;

    /**
     * User's email address
     */
    @NonNull
    public String email;

    /**
     * The user's phone number
     */
    @NonNull
    public String telefonnummer;

    /**
     * User's username
     */
    @NonNull
    public String username;

    /**
     * User's hashed password
     */
    @NonNull
    public byte[] password;

    /**
     * Is the user verified?
     */
    @NonNull
    public boolean wurdeVerifiziert;

    /**
     * Creation date of the user object
     */
    @NonNull
    public Date erstellungsDatum;

    /**
     * The role's of the user
     */
    @NonNull
    @OneToMany
    public Set<Role> rollen;

    /**
     * The experimenting stations a user is assigned to
     */
    @OneToMany
    public Set<ExperimentierStation> stationen;

    /**
     * The jobs a user has
     */
    @NonNull
    @OneToMany
    public Set<Auftrag> auftraege;

    /**
     * The User's language preference
     */
    @NonNull
    public String language;

    /**
     * Empty Constructor
     */
    public User() {
        /* Required by jpa */
    }
}
