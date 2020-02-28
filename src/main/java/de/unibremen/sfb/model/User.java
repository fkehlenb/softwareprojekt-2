package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * This class is used to create user objects
 */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findById", query = "SELECT u from User u WHERE u.id = :id"),
        @NamedQuery(name = "User.findByUsername", query = "SELECT u from User u WHERE u.username = :username AND u.isValidData = true"),
        @NamedQuery(name = "User.findByEmail", query = "SELECT u from User u WHERE u.email = :email AND u.isValidData = true"),
        @NamedQuery(name = "User.getAll", query = "SELECT u FROM User u WHERE u.isValidData = true")
})
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class User implements Serializable {

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

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
    private String password;

    /**
     * Is the user verified?
     */
    @NonNull
    private boolean wurdeVerifiziert;

    /**
     * Creation date of the user object
     */
    @NonNull
    private LocalDateTime erstellungsDatum;

    /**
     * The User's language preference
     */
    @NonNull
    private String language;

    @Override
    public String toString() {
        return username;
    }
}
