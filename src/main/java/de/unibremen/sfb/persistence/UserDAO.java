package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the users in the database
 */
public class UserDAO extends ObjectDAO<User> {

    /**
     * Add a user object to the database
     *
     * @param u - the user object to add
     * @throws DuplicateUserException if the User already exists in the database
     */
    public void persist(User u) throws DuplicateUserException {
        if (u != null) {
            synchronized (User.class) {
                if (em.contains(u)) {
                    throw new DuplicateUserException();
                }
                em.persist(u);
            }
        }
    }

    /**
     * Update an existing user object in the database
     *
     * @param u - the user object to update in the database
     * @throws UserNotFoundException if the user couldn't be found in the database
     */
    public void update(User u) throws UserNotFoundException {
        if (u != null) {
            if (!em.contains(u)) {
                throw new UserNotFoundException();
            }
            em.merge(u);
        }
    }

    /**
     * Remove a user object from the database
     *
     * @param u - the user object to remove from the database
     * @throws UserNotFoundException if the user object couldn't be found
     */
    public void remove(User u) throws UserNotFoundException {
        if (u != null) {
            if (!em.contains(u)) {
                throw new UserNotFoundException();
            }
            em.remove(u);
        }
    }

    /**
     * @return the class of User
     */
    public Class<User> get() {
        return User.class;
    }

    /**
     * Get a user object using the user's id
     *
     * @param id - the user id whose user object to fetch from the database
     * @return the user object matching the user id
     * @throws UserNotFoundException if the user couldn't be found
     */
    public User getUserById(int id) throws UserNotFoundException {
        User u = em.find(get(), id);
        if (u == null) {
            throw new UserNotFoundException();
        }
        return u;
    }

    /**
     * Get a user object using the user's name
     *
     * @param n - the name of the user whose user object to fetch from the database
     * @return the user object whose name matches the given name
     * @throws UserNotFoundException if the user couldn't be found
     */
    public User getUserByName(String n) throws UserNotFoundException {
        try {
            return em.createNamedQuery("User.findByUsername",get()).setParameter("username",n).getSingleResult();
        }
        catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    /**
     * Get a user object using the user's email address
     *
     * @param m - the email of the user whose user object to fetch from the database
     * @return the user object whose email matches the given email
     * @throws UserNotFoundException if the user couldn't be found
     */
    public User getUserByMail(String m) throws UserNotFoundException {
        try {
            return em.createNamedQuery("User.findByEmail",get()).setParameter("email",m).getSingleResult();
        }
        catch (Exception e){
            throw new UserNotFoundException();
        }
    }

    /**
     * Get a list of all users in the database
     * @return all users in the database
     */
    public List<User> getAll() {
        try {
            return em.createNamedQuery("User.getAll", get()).getResultList();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }
}
