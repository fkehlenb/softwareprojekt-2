package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.BedingungNotFoundException;
import de.unibremen.sfb.Exception.DuplicateBedingungException;
import de.unibremen.sfb.Model.Bedingung;

/** This class handles the conditions in the database */
public class BindungungDAO extends ObjectDAO<Bedingung> {

    /** Add a condition to the database
     * @param b - the condition to add to the database
     * @throws DuplicateBedingungException if the condition already exists in the database */
    public void persist(Bedingung b) throws DuplicateBedingungException {}

    /** Update a condition in the database
     * @param b - the condition to update in the database
     * @throws BedingungNotFoundException if the condition couldn't be found */
    public void update(Bedingung b) throws BedingungNotFoundException{}

    /** Remove a condition from the database
     * @param b - the condition to remove from the database
     * @throws BedingungNotFoundException if the condition couldn't be found */
    public void remove(Bedingung b) throws BedingungNotFoundException{}

    /** @return the class of condition */
    public Class<Bedingung> get(){
        return Bedingung.class;
    }
}
