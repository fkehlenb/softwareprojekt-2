package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateTraegerartException;
import de.unibremen.sfb.Exception.TraegerartNotFoundException;
import de.unibremen.sfb.Model.Traegerart;

/** This class manages the container type objects in the database */
public class TraegerartDAO extends ObjectDAO<Traegerart> {

    /** Add a new container type object to the database
     * @param ta - the container type to add to the database
     * @throws DuplicateTraegerartException if the container type object already exists in the database */
    public void persist(Traegerart ta) throws DuplicateTraegerartException {}

    /** Update an existing container type object in the database
     * @param ta - the container type object to update in the database
     * @throws TraegerartNotFoundException if the container type couldn't be found in the database */
    public void update(Traegerart ta) throws TraegerartNotFoundException{}

    /** Remove an existing container type object from the database
     * @param ta - the container type object to remove from the database
     * @throws TraegerartNotFoundException if the container type couldn't be found in the database */
    public void remove(Traegerart ta) throws TraegerartNotFoundException{}

    /** @return the class of containerType */
    public Class<Traegerart> get(){
        return Traegerart.class;
    }
}
