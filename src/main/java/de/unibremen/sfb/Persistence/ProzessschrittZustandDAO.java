package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessschrittZustandException;
import de.unibremen.sfb.Exception.ProzessschrittZustandNotFoundException;
import de.unibremen.sfb.Model.ProzessschrittZustand;

/** This class handles the process step state objects stored in the database */
public class ProzessschrittZustandDAO extends ObjectDAO<ProzessschrittZustand> {

    /** Add a new process step state object to the database
     * @param psz - the process step state object to add to the database
     * @throws DuplicateProzessschrittZustandException if the process step state object already exists in the database */
    public void persist(ProzessschrittZustand psz) throws DuplicateProzessschrittZustandException{}

    /** Update an existing process step state object in the database
     * @param psz - the process step state object to be updated in the database
     * @throws ProzessschrittZustandNotFoundException if the process step state cannot be found in the database */
    public void update(ProzessschrittZustand psz) throws ProzessschrittZustandNotFoundException{}

    /** Remove an existing process step state object from the database
     * @param psz - the process step state object to be removed from the database
     * @throws ProzessschrittZustandNotFoundException if the process step state cannot be found in the database */
    public void remove(ProzessschrittZustand psz) throws ProzessschrittZustandNotFoundException{}

    /** @return the class of process step state objects */
    public Class<ProzessschrittZustand> get(){
        return ProzessschrittZustand.class;
    }
}
