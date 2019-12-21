package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateTraegerArtException;
import de.unibremen.sfb.Exception.TraegerArtNotFoundException;
import de.unibremen.sfb.Model.TraegerArt;

/** This class manages the container type objects in the database */
public class TraegerArtDAO extends ObjectDAO<TraegerArt> {

    /** Add a new container type object to the database
     * @param ta - the container type to add to the database
     * @throws DuplicateTraegerArtException if the container type object already exists in the database */
    public void persist(TraegerArt ta) throws DuplicateTraegerArtException {}

    /** Update an existing container type object in the database
     * @param ta - the container type object to update in the database
     * @throws TraegerArtNotFoundException if the container type couldn't be found in the database */
    public void update(TraegerArt ta) throws TraegerArtNotFoundException{}

    /** Remove an existing container type object from the database
     * @param ta - the container type object to remove from the database
     * @throws TraegerArtNotFoundException if the container type couldn't be found in the database */
    public void remove(TraegerArt ta) throws TraegerArtNotFoundException{}

    /** @return the class of containerType */
    public Class<TraegerArt> get(){
        return TraegerArt.class;
    }
}
