package de.unibremen.sfb.controller;

import java.io.Serializable;

/**
 * this class manages the interaction between the gui and the backend system for situations in which a new user is registered
 */
public class RegisterBean implements Serializable {

    /**
     * the username of the new user
     */
    public String username;
    /**
     * the first name of the new user
     */
    public String name;
    /**
     * the surname of the new user
     */
    public String surname;
    /**
     * the email address of the new user
     */
    public String email;
    /**
     * the password of the new user
     */
    public String password;

    /**
     * registers the new user in the data base
     */
    public void register() {}

    /**
     * sets the language of the new user
     * @param l the language
     */
    public void setLanguage(String l) {}

    /**
     * returns the language everything is currently displayed in
     * @return the language
     */
    public String getCurrentLanguage() { return null; }

    /**
     * the empty constructor
     */
    public RegisterBean() {}

    /**
     * returns the username
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the username
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * returns the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the surname
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * sets the surname
     * @param surname the new surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * returns the email address
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the email address
     * @param email the new email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * returns the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets the password
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
