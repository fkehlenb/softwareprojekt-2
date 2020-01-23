package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.User;

import java.io.Serializable;

/**
 * this class manages the interaction between the gui and the backend system for situations in which the user is logged in
 */
public class LoggedInBean implements Serializable {

    /**
     * the user
     */
    public User user;

    /**
     * sets the view for this user
     */
    public void setView() {}

    /**
     * logs the user out
     */
    public void logout() {}

    /**
     * the empty constructor
     */
    public LoggedInBean() {}

    /**
     * returns the user managed by this bean
     * @return the user
     */
    public User getUser() { return user; }

    /**
     * sets the user managed by this bean
     * @param user the user
     */
    public void setUser(User user) { this.user = user; }
}
