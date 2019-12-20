package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessParameterException;
import de.unibremen.sfb.Exception.ProzessParameterNotFoundException;
import de.unibremen.sfb.Model.ProzessParameter;

/** This class handles the process parameters in the database */
public class ProzessParameterDAO extends ObjectDAO<ProzessParameter> {

    /** Add a process parameter to the database
     * @param pp - the process parameter to add to the database
     * @throws DuplicateProzessParameterException if the process parameter already exists in the database */
    public void persist(ProzessParameter pp) throws DuplicateProzessParameterException {}

    /** Update a process parameter in the database
     * @param pp - the process parameter to update in the database
     * @throws ProzessParameterNotFoundException if the process parameter couldn't be found in the database */
    public void update(ProzessParameter pp) throws ProzessParameterNotFoundException {}

    /** Remove a process parameter from the database
     * @param pp - the process parameter to remove from the database
     * @throws ProzessParameterNotFoundException if the process parameter couldn't be found in the database */
    public void remove(ProzessParameter pp) throws ProzessParameterNotFoundException{}

    /** @return the class of process parameters */
    public Class<ProzessParameter> get(){
        return ProzessParameter.class;
    }
}
