package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Role;
import de.unibremen.sfb.Model.User;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * this class manages the interaction with models of users
 */
public class UserController {

    /**
     * the user managed by this controller
     */
    public User user;

    /**
     * sets the id of this user
     * @param id the new id
     */
    public void setID(int id) {}

    /**
     * returns the id of this user
     * @return the id
     */
    public int getID() { return 0; }

    /**
     * sets the roles of this user
     * @param role a set containing all roles this user has
     */
    public void addRole(Set<Enum<Role>> role) {}

    /**
     * returns the roles this user has
     * @return a set containing all roles of this user
     */
    public Set<Enum<Role>> getRole() { return null; }

    /**
     * removes a role from this user
     * @param role the role to be removed
     */
    public void removeRole(Enum<Role> role) {}

    /**
     * applies this user for a username //TODO was? evtl setusername
     * @param username the username
     */
    public void applyUsername(String username) {}

    /**
     * sets the email of this user
     * @param email the new email
     */
    public void setEmail(String email) {}

    /**
     * returns the email of this user
     * @return the email address
     */
    public String getEmail() { return null; }

    /**
     * sets the first name of this user
     * @param name the new first name
     */
    public void setName(String name) {}

    /**
     * returns the first name of this user
     * @return the name
     */
    public String getName() { return null; }

    /**
     * sets the surname of this user
     * @param name the new surname
     */
    public void setSurname(String name) {}

    /**
     * returns the surname of this user
     * @return the surname
     */
    public String getSurname() { return null; }

    /**
     * sets the password for this user
     * @param password the new password
     */
    public void setPassword(String password) {}

    /**
     * returns the encoded password of this user
     * @return the password
     */
    public Byte[] getPassword() { return null; }

    /**
     * sets the phone number for this user
     * @param pn the new phone number
     */
    public void setPhone(String pn) {}

    /**
     * returns the phone number of this user
     * @return the phone number
     */
    public String getPhone() { return null; }

    /**
     * ´sets whether or not the user is verified
     * @param v True: user is verified. False: user is not verified
     */
    public void setVerified(boolean v) {}

    /**
     * returns whether the user is verified
     * @return True: the user is verified. False: the user is not verified
     */
    public boolean getVerified() { return false; }

    /**
     * sets the creation time of this user
     * @param d the creation time
     */
    public void setCreationDate(LocalDateTime d) {}

    /**
     * returns the creation time for this user
     * @return the creation time
     */
    public LocalDateTime getCreationDate() { return null; }

    /**
     * returns the language everything is currently displayed in for this user
     * @return the language
     */
    public String getCurrentLanguage() { return null; }

    /**
     * sets the language everything is supposed to be displayed in for this user
     * @param l the new language
     */
    public void setDefaultLanguage(String l) {}

}
