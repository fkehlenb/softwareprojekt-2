package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessSchrittZustandException;
import de.unibremen.sfb.Exception.ProzessSchrittZustandNotFoundException;
import de.unibremen.sfb.Model.ProzessSchrittZustand;

/** This class handles the process step state objects stored in the database */
public class ProzessSchrittZustandDAO extends ObjectDAO<ProzessSchrittZustand> {

    /** Add a new process step state object to the database
     * @param psz - the process step state object to add to the database
     * @throws DuplicateProzessSchrittZustandException if the process step state object already exists in the database */
    public void persist(ProzessSchrittZustand psz) throws DuplicateProzessSchrittZustandException{}

    /** Update an existing process step state object in the database
     * @param psz - the process step state object to be updated in the database
     * @throws ProzessSchrittZustandNotFoundException if the process step state cannot be found in the database */
    public void update(ProzessSchrittZustand psz) throws ProzessSchrittZustandNotFoundException{}

    /** Remove an existing process step state object from the database
     * @param psz - the process step state object to be removed from the database
     * @throws ProzessSchrittZustandNotFoundException if the process step state cannot be found in the database */
    public void remove(ProzessSchrittZustand psz) throws ProzessSchrittZustandNotFoundException{}

    /** @return the class of process step state objects */
    public Class<ProzessSchrittZustand> get(){
        return ProzessSchrittZustand.class;
    }
}
