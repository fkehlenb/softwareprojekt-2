package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.UserDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Getter
@Transactional
public class UserService implements Serializable {

    /**
     * UserDAO for user management in database
     */
    @Inject
    private UserDAO userDAO;

    /** Mailing service */
    @Inject
    private MailingService mailingService;

    /**
     * List of all users in the system
     */
    private List<User> users;

    /**
     * Initializer
     */
    @PostConstruct
    public void init() {
        users = userDAO.getAll();
    }

    /**
     * Add a new user to the system
     *
     * @param u - the user to add
     * @throws DuplicateUserException if the user already exists in the database
     */
    public void addUser(User u) throws DuplicateUserException {
        userDAO.persist(u);
    }

    /**
     * Get a user using his id
     *
     * @param id - the user's id
     * @return the user object with a matching id
     * @throws UserNotFoundException if the id has no registered user in the database
     */
    public User getUserById(int id) throws UserNotFoundException {
        return userDAO.getUserById(id);
    }

    /**
     * Get a user using his username
     *
     * @param username - the user's username
     * @return the user object which's username matches the entered one
     * @throws UserNotFoundException if the username has no registered user in the database
     */
    public User getUserByUsername(String username) throws UserNotFoundException {
        return userDAO.getUserByName(username);
    }

    /**
     * Get a user using his email address
     *
     * @param email - the user's email address
     * @return the user object which's email matches the entered one
     * @throws UserNotFoundException if the email has no registered user in the database
     */
    public User getUserByEmail(String email) throws UserNotFoundException {
        return userDAO.getUserByMail(email);
    }

    /**
     * Get all users from the database
     *
     * @return a list of all users in the database
     */
    public List<User> getAll() {
        return userDAO.getAll();
    }

    /** Check if a user exists in the database
     * @param u - the user to check
     * @return user exists in database? */
    public boolean containsUser(User u) throws UserNotFoundException{
        try {
            userDAO.getUserById(u.getId());
            return true;
        }
        // On fail the dao will throw an exception
        catch (Exception e){
            return false;
        }
    }

    /** Check if a username has a user registered in the database
     * @param username - the username to check
     * @return user exists in database? */
    public boolean containsUserWithUsername(String username){
        try {
            getUserByUsername(username);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /** Check if an email address has a user registered in the database
     * @param email - the email address to check
     * @return the user exists in the database? */
    public boolean containsUserWithEmail(String email){
        try{
            getUserByEmail(email);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    /** Update a user in the database
     * @param u - the user to update in the database
     * @throws UserNotFoundException if the user couldn't be found in the database */
    public void updateUser(User u) throws UserNotFoundException{
        userDAO.update(u);
    }

    /** Remove a user from the database
     * @param u - the user to remove from the database
     * @throws UserNotFoundException if the user couldn't be found in the database */
    public void removeUser(User u) throws UserNotFoundException{
        userDAO.remove(u);
    }

    /** Remove a user from the database using his id
     * @param id - the user's id
     * @throws UserNotFoundException if the id has no registered user in the database */
    public void removeUserById(int id) throws UserNotFoundException{
        userDAO.remove(getUserById(id));
    }

    /** Remove a user from the database using his username
     * @param username - the user's username
     * @throws UserNotFoundException if the username has no registered user in the database */
    public void removeUserByUsername(String username) throws UserNotFoundException{
        userDAO.remove(getUserByUsername(username));
    }

    /** Remove a user from the database using his email address
     * @param email - the user's email address
     * @throws UserNotFoundException if the email address has no registered user in the database */
    public void removeUserByMail(String email) throws UserNotFoundException{
        userDAO.remove(getUserByEmail(email));
    }

    /** Get the experimenting stations that are assigned to a user
     * @param u - the user whose experimenting stations to return
     * @return the user's experimenting stations */
    public List<ExperimentierStation> getEsByUser(User u){
        return u.getStationen();
    }

    /** Send an email address to a user
     * @param u - the user to send the email to
     * @param subject - the email subject
     * @param message - the email content message */
    public void sendMail(User u,String subject,String message){
        mailingService.sendmail(u.getEmail(),message,subject);
    }
}
