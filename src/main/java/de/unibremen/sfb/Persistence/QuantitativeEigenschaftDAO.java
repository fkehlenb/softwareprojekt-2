package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateQuantitativeEigenschaftException;
import de.unibremen.sfb.Exception.QuantitativeEingenschaftNotFoundException;
import de.unibremen.sfb.Model.QuantitativeEigenschaft;

/** This class handles the quantitative descriptor objects in the database */
public class QuantitativeEigenschaftDAO extends ObjectDAO<QuantitativeEigenschaft> {

    /** Add a quantitative descriptor object to the database
     * @param q - the quantitative descriptor to add to the database
     * @throws DuplicateQuantitativeEigenschaftException if the quantitative desciptor already exists in the database */
    public void persist(QuantitativeEigenschaft q) throws DuplicateQuantitativeEigenschaftException{}

    /** Updates a quantitative descriptor object in the database
     * @param q - the quantitative descriptor to be updated in the database
     * @throws QuantitativeEingenschaftNotFoundException if the quantitative descriptor couldn't be found in the database */
    public void update(QuantitativeEigenschaft q) throws QuantitativeEingenschaftNotFoundException{}

    /** Removes a quantitative descriptor object from the database
     * @param q - the quantitative descriptor object to remove from the database
     * @throws QuantitativeEingenschaftNotFoundException if the quantitative descriptor couldn't be found in the database */
    public void remove(QuantitativeEigenschaft q) throws QuantitativeEingenschaftNotFoundException{}

    /** Get a quantitative descriptor object from the database
     * @return the requested quantitative descriptor object from the database
     * @throws QuantitativeEingenschaftNotFoundException if the quantitative descriptor couldn't be found in the database */
    public QuantitativeEigenschaft get() throws QuantitativeEingenschaftNotFoundException{
        return null;
    }
}
