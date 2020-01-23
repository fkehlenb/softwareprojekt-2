package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ProzessSchritt;

/** This class handles the instantiated process chain steps in the database */
public class ProzessSchrittDAO extends ObjectDAO<ProzessSchritt> {

    /** Add a process chain step to the database
     * @param ps - the process chain step to be added to the database
     * @throws DuplicateProzessSchrittException if the process chain step already exists in the database */
    public void persist(ProzessSchritt ps) throws DuplicateProzessSchrittException {
        if (ps!=null){
            synchronized (ProzessSchritt.class){
                if (em.contains(ps)){
                    throw new DuplicateProzessSchrittException();
                }
                em.persist(ps);
            }
        }
    }

    /** Update a process chain step in the database
     * @param ps - the process step to update in the database
     * @throws ProzessSchrittNotFoundException if the process chain step object couldn't be found */
    public void update(ProzessSchritt ps) throws ProzessSchrittNotFoundException {
        if (ps!=null){
            if (!em.contains(ps)){
                throw new ProzessSchrittNotFoundException();
            }
            em.merge(ps);
        }
    }

    /** Remove a process chain step from the database
     * @param ps - the process step object to remove from the database
     * @throws ProzessSchrittNotFoundException if the process chain step object couldn't be found */
    public void remove(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        if (ps!=null){
            if (!em.contains(ps)){
                throw new ProzessSchrittNotFoundException();
            }
            em.remove(ps);
        }
    }

    /** Get the class of process steps
     * @return the class of Process Steps */
    public Class<ProzessSchritt> get(){
        return ProzessSchritt.class;
    }

    /** Fetch a process chain step matching a specific id from the database
     * @param id - the id whose process chain step to fetch from the database
     * @return the process chain step matching the given id
     * @throws ProzessSchrittNotFoundException if the process chain step couldn't be found */
    public ProzessSchritt getObjById(int id) throws ProzessSchrittNotFoundException{
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
