package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.AuftragsLogNotFoundException;
import de.unibremen.sfb.Exception.DuplicateAuftragsLogException;
import de.unibremen.sfb.Model.AuftragsLog;

/** This class handles the job logs in the database*/
public class AuftragsLogDAO extends ObjectDAO<AuftragsLog> {

    /** Add a job log to the database
     * @param a - the job log to add to the database
     * @throws DuplicateAuftragsLogException if the job log already exists in the database */
    public void persist(AuftragsLog a) throws DuplicateAuftragsLogException{
        if (a!=null) {
            synchronized (AuftragsLog.class) {
                if (em.contains(a)) {
                    throw new DuplicateAuftragsLogException();
                }
                em.persist(a);
            }
        }
    }

    /** Update a job log in the database
     * @param a - the job log to update in the database
     * @throws AuftragsLogNotFoundException  - is thrown when the job log cannot be found in the database */
    public void update(AuftragsLog a) throws AuftragsLogNotFoundException {
        if (a!=null){
            if (!em.contains(a)){
                throw new AuftragsLogNotFoundException();
            }
            em.merge(a);
        }
    }

    /** Remove a job log from the database
     * @param a - the job log to remove from the database
     * @throws AuftragsLogNotFoundException if the job log cannot be found in the database */
    public void remove(AuftragsLog a) throws AuftragsLogNotFoundException{
        if (a!=null){
            if (!em.contains(a)){
                throw new AuftragsLogNotFoundException();
            }
            em.remove(a);
        }
    }

    /** @return class of job logs */
    public Class<AuftragsLog> get(){
        return AuftragsLog.class;
    }
}
