package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;

/** This class handles the process step state objects stored in the database */
public class ProzessSchrittZustandsAutomatDAO extends ObjectDAO<ProzessSchrittZustandsAutomat> {

    /** Add a new process step state object to the database
     * @param psz - the process step state object to add to the database
     * @throws DuplicateProzessSchrittZustandsAutomatException if the process step state object already exists in the database */
    public void persist(ProzessSchrittZustandsAutomat psz) throws DuplicateProzessSchrittZustandsAutomatException{
        if (psz!=null){
            synchronized (ProzessSchrittZustandsAutomat.class){
                if (em.contains(psz)){
                    throw new DuplicateProzessSchrittZustandsAutomatException();
                }
                em.persist(psz);
            }
        }
    }

    /** Update an existing process step state object in the database
     * @param psz - the process step state object to be updated in the database
     * @throws ProzessSchrittZustandsAutomatNotFoundException if the process step state cannot be found in the database */
    public void update(ProzessSchrittZustandsAutomat psz) throws ProzessSchrittZustandsAutomatNotFoundException{
        if (psz!=null){
            if (!em.contains(psz)){
                throw new ProzessSchrittZustandsAutomatNotFoundException();
            }
            em.merge(psz);
        }
    }

    /** Remove an existing process step state object from the database
     * @param psz - the process step state object to be removed from the database
     * @throws ProzessSchrittZustandsAutomatNotFoundException if the process step state cannot be found in the database */
    public void remove(ProzessSchrittZustandsAutomat psz) throws ProzessSchrittZustandsAutomatNotFoundException{
        if (psz!=null){
            if (!em.contains(psz)){
                throw new ProzessSchrittZustandsAutomatNotFoundException();
            }
            psz.setValidData(false);
            update(psz);
        }
    }

    /** @return the class of process step state objects */
    public Class<ProzessSchrittZustandsAutomat> get(){
        return ProzessSchrittZustandsAutomat.class;
    }
}
