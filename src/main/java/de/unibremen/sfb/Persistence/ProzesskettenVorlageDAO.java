package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.Exception.ProzesskettenVorlageNotFoundException;
import de.unibremen.sfb.Model.ProzesskettenVorlage;

/** This class handles the job templates in the database */
public class ProzesskettenVorlageDAO extends ObjectDAO<ProzesskettenVorlage> {

    /** Add a job template to the database
     * @param pkv - the job template to add to the database
     * @throws DuplicateProzessKettenVorlageException if the job template already exists in the database */
    public void persist(ProzesskettenVorlage pkv) throws DuplicateProzessKettenVorlageException {}

    /** Update an existing job template in the database
     * @param pkv - the job template to update in the database
     * @throws ProzesskettenVorlageNotFoundException if the job couldn't be found */
    public void update(ProzesskettenVorlage pkv) throws ProzesskettenVorlageNotFoundException{}

    /** Remove an existing job template from the database
     * @param pkv - the job template to remove from the database
     * @throws ProzesskettenVorlageNotFoundException if the job template couldn't be found */
    public void remove(ProzesskettenVorlage pkv) throws ProzesskettenVorlageNotFoundException{}

    /** @return the class of process chain templates */
    public Class<ProzesskettenVorlage> get(){
        return ProzesskettenVorlage.class;
    }

    /** Get a specific job template from the database matching a specific id
     * @param id - the id whose job template to fetch from the database
     * @return the job template matching the given id
     * @throws ProzesskettenVorlageNotFoundException if the job template couldn't be found */
    public ProzesskettenVorlage getObjById(int id) throws ProzesskettenVorlageNotFoundException{
        return null;
    }
}
