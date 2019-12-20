package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessschrittZustandVorlageException;
import de.unibremen.sfb.Exception.ProzessschrittVorlageNotFoundException;
import de.unibremen.sfb.Model.ProzessschrittZustandVorlage;

/** This class manages the process step state template */
public class ProzessschrittZustandVorlageDAO extends ObjectDAO<ProzessschrittZustandVorlage> {

    /** Add new process step state template to the database
     * @param pszv - the process step state template to add to the database
     * @throws DuplicateProzessschrittZustandVorlageException if the process step state template is already in the database */
    public void persist(ProzessschrittZustandVorlage pszv) throws DuplicateProzessschrittZustandVorlageException {}

    /** Update an existing process step state template in the database
     * @param pszv - the process step state template to update in the database
     * @throws ProzessschrittVorlageNotFoundException if the process step state template cannot be found in the database */
    public void update(ProzessschrittZustandVorlage pszv) throws ProzessschrittVorlageNotFoundException{}

    /** Remove an existing process step state template from the database
     * @param pszv - the process step state template to remove from the database
     * @throws ProzessschrittVorlageNotFoundException if the process step state template cannot be found in the database */
    public void remove(ProzessschrittZustandVorlage pszv) throws ProzessschrittVorlageNotFoundException{}

    /** @return the class of a process step state template */
    public Class<ProzessschrittZustandVorlage> get(){
        return ProzessschrittZustandVorlage.class;
    }
}
