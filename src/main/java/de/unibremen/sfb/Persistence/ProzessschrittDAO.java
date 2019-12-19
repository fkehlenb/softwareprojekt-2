package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessschrittException;
import de.unibremen.sfb.Exception.ProzessschrittNotFoundException;
import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.Prozessschritt;

/** This class handles the instantiated process chain steps in the database */
public class ProzessschrittDAO extends ObjectDAO<Prozessschritt> {

    /** Add a process chain step to the database
     * @param ps - the process chain step to be added to the database
     * @throws DuplicateProzessschrittException if the process chain step already exists in the database */
    public void persist(Prozessschritt ps) throws DuplicateProzessschrittException {}

    /** Update a process chain step in the database
     * @param ps - the process step to update in the database
     * @throws ProzessschrittNotFoundException if the process chain step object couldn't be found */
    public void update(Prozessschritt ps) throws ProzessschrittNotFoundException {}

    /** Remove a process chain step from the database
     * @param ps - the process step object to remove from the database
     * @throws ProzessschrittNotFoundException if the process chain step object couldn't be found */
    public void remove(Prozessschritt ps) throws ProzessschrittNotFoundException{}

    /** Fetch a process chain step object from the database
     * @return the requested process chain step object
     * @throws ProzessschrittNotFoundException if the process chain step object couldn't be found */
    public Prozessschritt get() throws ProzessschrittNotFoundException{
        return null;
    }

    /** Fetch a process chain step matching a specific id from the database
     * @param id - the id whose process chain step to fetch from the database
     * @return the process chain step matching the given id
     * @throws ProzessschrittNotFoundException if the process chain step couldn't be found */
    public Prozessschritt getObjById(int id) throws ProzessschrittNotFoundException{
        return null;
    }

    /** Get the station where a given process chain step is being carried out
     * @return the experimenting station where a process chain step is being carried out */
    public ExperimentierStation getES(){
        return null;
    }

    /** Get the job which a given process chain step belongs to
     * @return the job which a given process chain step belongs to */
    public Auftrag getAuftrag(){
        return null;
    }
}
