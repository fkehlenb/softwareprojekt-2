package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessSchrittZustandVorlageException;
import de.unibremen.sfb.Exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.Model.ProzessSchrittZustandVorlage;

/** This class manages the process step state template */
public class ProzessSchrittZustandVorlageDAO extends ObjectDAO<ProzessSchrittZustandVorlage> {

    /** Add new process step state template to the database
     * @param pszv - the process step state template to add to the database
     * @throws DuplicateProzessSchrittZustandVorlageException if the process step state template is already in the database */
    public void persist(ProzessSchrittZustandVorlage pszv) throws DuplicateProzessSchrittZustandVorlageException {}

    /** Update an existing process step state template in the database
     * @param pszv - the process step state template to update in the database
     * @throws ProzessSchrittVorlageNotFoundException if the process step state template cannot be found in the database */
    public void update(ProzessSchrittZustandVorlage pszv) throws ProzessSchrittVorlageNotFoundException{}

    /** Remove an existing process step state template from the database
     * @param pszv - the process step state template to remove from the database
     * @throws ProzessSchrittVorlageNotFoundException if the process step state template cannot be found in the database */
    public void remove(ProzessSchrittZustandVorlage pszv) throws ProzessSchrittVorlageNotFoundException{}

    /** @return the class of a process step state template */
    public Class<ProzessSchrittZustandVorlage> get(){
        return ProzessSchrittZustandVorlage.class;
    }
}
