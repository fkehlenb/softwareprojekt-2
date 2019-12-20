package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateTraegerException;
import de.unibremen.sfb.Exception.TraegerNotFoundException;
import de.unibremen.sfb.Model.Probe;
import de.unibremen.sfb.Model.Standort;
import de.unibremen.sfb.Model.Traeger;

import java.util.List;

/** This class handles the containers in the database */
public class TraegerDAO extends ObjectDAO<Traeger> {

    /** Add a container to the database
     * @param t - the container to add to the database
     * @throws DuplicateTraegerException if the container already exists in the database */
    public void persist(Traeger t) throws DuplicateTraegerException{}

    /** Update a container in the database
     * @param t - the container to update in the database
     * @throws TraegerNotFoundException if the container cannot be found in the database */
    public void update(Traeger t) throws TraegerNotFoundException{}

    /** Remove a container from the database
     * @param t - the container to remove from the database
     * @throws TraegerNotFoundException if the container cannot be found in the database */
    public void remove(Traeger t) throws TraegerNotFoundException{}

    /** @return the class of container */
    public Class<Traeger> get(){
        return Traeger.class;
    }


    /** Get a container through its id
     * @param id - the id of the requested container
     * @return the requested container
     * @throws TraegerNotFoundException if the container cannot be found in the database */
    public Traeger getObjById(int id) throws TraegerNotFoundException{
        return null;
    }

    /** Get the location of a container
     * @return the container's location
     * @throws TraegerNotFoundException if the container couldn't be found in the database */
    public Standort getLocation() throws TraegerNotFoundException{
        return null;
    }

    /** Get the samples located inside a container
     * @return the samples located inside a specific container
     * @throws TraegerNotFoundException if the container couldn't be found in the database */
    public List<Probe> getProben() throws TraegerNotFoundException{
        return null;
    }
}
