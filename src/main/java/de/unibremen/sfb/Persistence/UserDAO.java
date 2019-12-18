package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateUserException;
import de.unibremen.sfb.Exception.UserNotFoundException;
import de.unibremen.sfb.Model.User;

/** This class handles the users in the database */
public class UserDAO extends ObjectDAO<User> {

    /** Add a user object to the database
     * @param u - the user object to add
     * @throws DuplicateUserException if the User already exists in the database */
    public void persist(User u) throws DuplicateUserException {}

    /** Update an existing user object in the database
     * @param u - the user object to update in the database
     * @throws UserNotFoundException if the user couldn't be found in the database */
    public void update(User u) throws UserNotFoundException{}

    /** Remove a user object from the database
     * @param u - the user object to remove from the database
     * @throws UserNotFoundException if the user object couldn't be found*/
    public void remove(User u) throws UserNotFoundException {}

    /** Get a user object from the database
     * @return the requested User object
     * @throws UserNotFoundException if the user object couldn't be found in the database*/
    private User get() throws UserNotFoundException{
        return null;
    }

    /** Get a user object using the user's id
     * @param id - the user id whose user object to fetch from the database
     * @return the user object matching the user id
     * @throws UserNotFoundException if the user couldn't be found */
    public User getUserById(int id) throws UserNotFoundException{
        return null;
    }

    /** Get a user object using the user's name
     * @param n - the name of the user whose user object to fetch from the database
     * @return the user object whose name matches the given name
     * @throws UserNotFoundException if the user couldn't be found */
    public User getUserByName(String n) throws UserNotFoundException{
        return null;
    }

    /** Get a user object using the user's email address
     * @param m - the email of the user whose user object to fetch from the database
     * @return the user object whose email matches the given email
     * @throws UserNotFoundException if the user couldn't be found */
    public User getUserByMail(String m) throws UserNotFoundException{
        return null;
    }
}
