package de.unibremen.sfb.Persistence;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/** This class is the template database handler for its children
 * It handles the connection to the database using the EntityManager em*/
public abstract class ObjectDAO<T> {

    @Inject
    protected EntityManager em;

    /** Add an object to the database
     * @param t - the object to add to the database
     * @throws Exception if the object couldn't be added to the database */
    public abstract void persist(T t) throws Exception;

    /** Update an object in the database
     * @param t - the object to update in the database
     * @throws Exception if the object couldn't be updated in the database */
    public abstract void update(T t) throws Exception;

    /** Remove an object from the database
     * @param t - the object to remove from the database
     * @throws Exception if the requested object couldn't be removed from the database */
    public abstract void remove(T t) throws Exception;
}