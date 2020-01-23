package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateQuantitativeEigenschaftException;
import de.unibremen.sfb.exception.QuantitativeEingenschaftNotFoundException;
import de.unibremen.sfb.model.QuantitativeEigenschaft;

/** This class handles the quantitative descriptor objects in the database */
public class QuantitativeEigenschaftDAO extends ObjectDAO<QuantitativeEigenschaft> {

    /** Add a quantitative descriptor object to the database
     * @param q - the quantitative descriptor to add to the database
     * @throws DuplicateQuantitativeEigenschaftException if the quantitative desciptor already exists in the database */
    public void persist(QuantitativeEigenschaft q) throws DuplicateQuantitativeEigenschaftException{
        if (q!=null){
            synchronized (QuantitativeEigenschaft.class){
                if (em.contains(q)){
                    throw new DuplicateQuantitativeEigenschaftException();
                }
                em.persist(q);
            }
        }
    }

    /** Updates a quantitative descriptor object in the database
     * @param q - the quantitative descriptor to be updated in the database
     * @throws QuantitativeEingenschaftNotFoundException if the quantitative descriptor couldn't be found in the database */
    public void update(QuantitativeEigenschaft q) throws QuantitativeEingenschaftNotFoundException{
        if (q!=null){
            if (!em.contains(q)){
                throw new QuantitativeEingenschaftNotFoundException();
            }
            em.merge(q);
        }
    }

    /** Removes a quantitative descriptor object from the database
     * @param q - the quantitative descriptor object to remove from the database
     * @throws QuantitativeEingenschaftNotFoundException if the quantitative descriptor couldn't be found in the database */
    public void remove(QuantitativeEigenschaft q) throws QuantitativeEingenschaftNotFoundException{
        if (q!=null){
            if (!em.contains(q)){
                throw new QuantitativeEingenschaftNotFoundException();
            }
            em.remove(q);
        }
    }

    /** @return the class of Quantitative descriptors */
    public Class<QuantitativeEigenschaft> get(){
        return QuantitativeEigenschaft.class;
    }
}
