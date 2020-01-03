package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessSchrittParameterException;
import de.unibremen.sfb.Exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.Model.ProzessSchritt;
import de.unibremen.sfb.Model.ProzessSchrittParameter;

/** This class handles the process parameters in the database */
public class ProzessSchrittParameterDAO extends ObjectDAO<ProzessSchrittParameter> {

    /** Add a process parameter to the database
     * @param pp - the process parameter to add to the database
     * @throws DuplicateProzessSchrittParameterException if the process parameter already exists in the database */
    public void persist(ProzessSchrittParameter pp) throws DuplicateProzessSchrittParameterException {
        if (pp!=null){
            synchronized (ProzessSchrittParameter.class){
                if (em.contains(pp)){
                    throw new DuplicateProzessSchrittParameterException();
                }
                em.persist(pp);
            }
        }
    }

    /** Update a process parameter in the database
     * @param pp - the process parameter to update in the database
     * @throws ProzessSchrittParameterNotFoundException if the process parameter couldn't be found in the database */
    public void update(ProzessSchrittParameter pp) throws ProzessSchrittParameterNotFoundException {
        if (pp!=null){
            if (!em.contains(pp)){
                throw new ProzessSchrittParameterNotFoundException();
            }
            em.merge(pp);
        }
    }

    /** Remove a process parameter from the database
     * @param pp - the process parameter to remove from the database
     * @throws ProzessSchrittParameterNotFoundException if the process parameter couldn't be found in the database */
    public void remove(ProzessSchrittParameter pp) throws ProzessSchrittParameterNotFoundException{
        if (pp!=null){
            if (!em.contains(pp)){
                throw new ProzessSchrittParameterNotFoundException();
            }
            em.remove(pp);
        }
    }

    /** @return the class of process parameters */
    public Class<ProzessSchrittParameter> get(){
        return ProzessSchrittParameter.class;
    }
}
