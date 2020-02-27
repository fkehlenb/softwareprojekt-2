package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittParameterException;
import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;

import java.util.ArrayList;
import java.util.List;

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
            pp.setValidData(false);
            update(pp);
        }
    }

    /** @return the class of process parameters */
    public Class<ProzessSchrittParameter> get(){
        return ProzessSchrittParameter.class;
    }

    /**
     * Fetches all PSP
     * @return all process parameters empty ArrayList if there are none */
    public List<ProzessSchrittParameter> getAll(){
        try {
            return em.createQuery("SELECT psp FROM ProzessSchrittParameter psp", get()).getResultList();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }

    /** Return the process parameter with the given id
     * @param id - the process parameter id to look for
     * @return the process parameter searched for
     * @throws ProzessSchrittParameterNotFoundException if a matching process parameter couldn't be found */
    public ProzessSchrittParameter getPSPByID(int id) throws ProzessSchrittParameterNotFoundException{
        try {
            ProzessSchrittParameter pp = em.find(get(),id);
            if (!pp.isValidData()){
                throw new Exception();
            }
            return pp;
        }
        catch (Exception e){
            throw new ProzessSchrittParameterNotFoundException();
        }
    }
}
