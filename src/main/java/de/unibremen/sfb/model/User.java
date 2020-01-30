package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User {

    /**
     * User's id
     */
    @Id
    @NonNull
    private int id;

    /**
     * The user's name
     */
    @NonNull
    private String vorname;

    /**
     * The user's surname
     */
    @NonNull
    private String nachname;

    /**
     * User's email address
     */
    @NonNull
    private String email;

    /**
     * The user's phone number
     */
    @NonNull
    private String telefonnummer;

    /**
     * User's username
     */
    @NonNull
    private String username;

    /**
     * User's hashed password
     */
    @NonNull
    private byte[] password;

    /**
     * Is the user verified?
     */
    @NonNull
    private boolean wurdeVerifiziert;

    /**
     * Creation date of the user object
     */
    @NonNull
    private Date erstellungsDatum;

    /**
     * The role's of the user
     */
    @NonNull
    @ElementCollection
    private Set<Role> rollen;

    /**
     * The experimenting stations a user is assigned to
     */
    @ManyToMany
    private List<ExperimentierStation> stationen;

    /**
     * The jobs a user has
     */
    @NonNull
    @ManyToMany
    private List<Auftrag> auftraege;

    /**
     * The User's language preference
     */
    @NonNull
    private String language;
}
