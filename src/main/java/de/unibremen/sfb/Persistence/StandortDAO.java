package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateStandortException;
import de.unibremen.sfb.Exception.StandortNotFoundException;
import de.unibremen.sfb.Model.Standort;

/** This class handles the location objects in the database*/
public class StandortDAO extends ObjectDAO<Standort> {

    /** Add a location object to the database
     * @param s - the location object to add to the database
     * @throws DuplicateStandortException if the location already exists in the database */
    public void persist(Standort s) throws DuplicateStandortException {}

    /** Update a location object in the database
     * @param s - the location to update in the database
     * @throws StandortNotFoundException if the location couldn't be found in the database */
    public void update(Standort s) throws StandortNotFoundException{}

    /** Remove a location object from the database
     * @param s - the location object to remove from the database
     * @throws StandortNotFoundException if the location object couldn't be found in the database */
    public void remove(Standort s) throws StandortNotFoundException{}

    /** @return the class of location */
    public Class<Standort> get(){
        return Standort.class;
    }

    /** Get a location object from the database using its unique id
     * @return the location object with an id matching the entered one
     * @param id - Die ID des Standortes
     * @throws StandortNotFoundException if the location object couldn't be found in the database */
    public Standort getObjById(int id) throws StandortNotFoundException{
        return null;
    }
}
