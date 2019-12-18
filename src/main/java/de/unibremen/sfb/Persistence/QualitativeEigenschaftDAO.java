package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.Exception.QualitativeEigenschaftNotFoundException;
import de.unibremen.sfb.Model.QualitativeEigenschaft;

/** This class handles the qualitative descriptor objects in the database */
public class QualitativeEigenschaftDAO extends ObjectDAO<QualitativeEigenschaft> {

    /** Add a qualitative descriptor object to the database
     * @param q - the qualitative descriptor object to add to the database
     * @throws DuplicateQualitativeEigenschaftException if the qualitative descriptor already exists in the database */
    public void persist(QualitativeEigenschaft q) throws DuplicateQualitativeEigenschaftException{}

    /** Update a qualitative descriptor object in the database
     * @param q - the qualitative descriptor object to update in the database
     * @throws QualitativeEigenschaftNotFoundException if the qualitative descriptor couldn't be found in the database */
    public void update(QualitativeEigenschaft q) throws QualitativeEigenschaftNotFoundException{}

    /** Remove a qualitative descriptor object from the database
     * @param q - the qualitative descriptor object to remove from the database
     * @throws QualitativeEigenschaftNotFoundException if the qualitative descriptor couldn't be found in the database */
    public void remove(QualitativeEigenschaft q) throws QualitativeEigenschaftNotFoundException{}

    /** Get a qualitative descriptor object from the database
     * @return the requested qualitative descriptor object
     * @throws QualitativeEigenschaftNotFoundException if the qualitative descriptor couldn't be found in the database */
    public QualitativeEigenschaft get() throws QualitativeEigenschaftNotFoundException{
        return null;
    }
}
