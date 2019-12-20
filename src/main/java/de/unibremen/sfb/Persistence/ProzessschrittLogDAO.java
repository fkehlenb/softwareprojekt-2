package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessschrittLogException;
import de.unibremen.sfb.Exception.ProzessschrittLogNotFoundException;
import de.unibremen.sfb.Model.ProzessschrittLog;

/** This class handles the process step log objects in the database */
public class ProzessschrittLogDAO extends ObjectDAO<ProzessschrittLog> {

    /** Add a new process step log object to the database
     * @param pl - the process step log object to add to the database
     * @throws DuplicateProzessschrittLogException if the process step log object already exists in the database */
    public void persist(ProzessschrittLog pl) throws DuplicateProzessschrittLogException{}

    /** Update an existing process step log object in the database
     * @param pl - the process step log object to update in the database
     * @throws ProzessschrittLogNotFoundException if the process step log couldn't be found in the database */
    public void update(ProzessschrittLog pl) throws ProzessschrittLogNotFoundException{}

    /** Remove an existing process step log object from the database
     * @param pl - the process step log object to remove from the database
     * @throws ProzessschrittLogNotFoundException if the process step log couldn't be found in the database */
    public void remove(ProzessschrittLog pl) throws ProzessschrittLogNotFoundException{}

    /** @return the class of process step logs */
    public Class<ProzessschrittLog> get(){
        return ProzessschrittLog.class;
    }
}
