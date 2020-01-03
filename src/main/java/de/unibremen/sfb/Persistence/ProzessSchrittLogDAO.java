package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.Exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.Model.ProzessSchrittLog;

/** This class handles the process step log objects in the database */
public class ProzessSchrittLogDAO extends ObjectDAO<ProzessSchrittLog> {

    /** Add a new process step log object to the database
     * @param pl - the process step log object to add to the database
     * @throws DuplicateProzessSchrittLogException if the process step log object already exists in the database */
    public void persist(ProzessSchrittLog pl) throws DuplicateProzessSchrittLogException{
        if (pl!=null) {
            synchronized (ProzessSchrittLog.class) {
                if (em.contains(pl)) {
                    throw new DuplicateProzessSchrittLogException();
                }
                em.persist(pl);
            }
        }
    }

    /** Update an existing process step log object in the database
     * @param pl - the process step log object to update in the database
     * @throws ProzessSchrittLogNotFoundException if the process step log couldn't be found in the database */
    public void update(ProzessSchrittLog pl) throws ProzessSchrittLogNotFoundException{
        if (pl!=null){
            if (!em.contains(pl)){
                throw new ProzessSchrittLogNotFoundException();
            }
            em.merge(pl);
        }
    }

    /** Remove an existing process step log object from the database
     * @param pl - the process step log object to remove from the database
     * @throws ProzessSchrittLogNotFoundException if the process step log couldn't be found in the database */
    public void remove(ProzessSchrittLog pl) throws ProzessSchrittLogNotFoundException{
        if (pl!=null){
            if (!em.contains(pl)){
                throw new ProzessSchrittLogNotFoundException();
            }
            em.remove(pl);
        }
    }

    /** Get an existing process step log object from the database
     * @return the requested process step log object from the database
     * @throws ProzessSchrittLogNotFoundException if the process step log couldn't be found in the database */
    public Class<ProzessSchrittLog> get() throws ProzessSchrittLogNotFoundException{
        return ProzessSchrittLog.class;
    }
}
