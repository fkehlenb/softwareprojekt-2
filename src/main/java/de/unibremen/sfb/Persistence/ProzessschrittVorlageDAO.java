package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessschrittVorlageException;
import de.unibremen.sfb.Exception.ProzessschrittVorlageNotFoundException;
import de.unibremen.sfb.Model.ProzessschrittVorlage;

/** This class manages the process chain step templates in the database */
public class ProzessschrittVorlageDAO extends ObjectDAO<ProzessschrittVorlage> {

    /** Add a process chain step template to the database
     * @param psv - the process chain step template to add to the database
     * @throws DuplicateProzessschrittVorlageException if the process chain step template already exists in the database */
    public void persist(ProzessschrittVorlage psv) throws DuplicateProzessschrittVorlageException {}

    /** Update a process chain step template in the database
     * @param psv - the process chain step template to update in the database
     * @throws ProzessschrittVorlageNotFoundException if the process chain step template couldn't be found */
    public void update(ProzessschrittVorlage psv) throws ProzessschrittVorlageNotFoundException{}

    /** Remove a process chain step template from the database
     * @param psv - the process chain step template to remove from the database
     * @throws ProzessschrittVorlageNotFoundException if the process chain step template couldn't be found */
    public void remove(ProzessschrittVorlage psv) throws ProzessschrittVorlageNotFoundException{}

    /** @return the class of process chain templates */
    public Class<ProzessschrittVorlage> get(){
        return ProzessschrittVorlage.class;
    }

    /** Get a process chain step template from the database using its defined id
     * @param id - the id of the requested process chain step template
     * @return the process chain step template matching the given id
     * @throws ProzessschrittVorlageNotFoundException if the process chain step template couldn't be found */
    public ProzessschrittVorlage getObjById(int id) throws ProzessschrittVorlageNotFoundException{
        return null;
    }
}
