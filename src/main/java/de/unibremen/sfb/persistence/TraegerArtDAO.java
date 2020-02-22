package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTraegerArtException;
import de.unibremen.sfb.exception.TraegerArtNotFoundException;
import de.unibremen.sfb.model.TraegerArt;

import java.util.ArrayList;
import java.util.List;

/** This class manages the container type objects in the database */
public class TraegerArtDAO extends ObjectDAO<TraegerArt> {

    /** Add a new container type object to the database
     * @param ta - the container type to add to the database
     * @throws DuplicateTraegerArtException if the container type object already exists in the database */
    public void persist(TraegerArt ta) throws DuplicateTraegerArtException {
        if (ta!=null){
            synchronized (TraegerArt.class){
                if (em.contains(ta)){
                    throw new DuplicateTraegerArtException();
                }
                em.persist(ta);
            }
        }
    }

    /** Update an existing container type object in the database
     * @param ta - the container type object to update in the database
     * @throws TraegerArtNotFoundException if the container type couldn't be found in the database */
    public void update(TraegerArt ta) throws TraegerArtNotFoundException{
        if (ta!=null){
            if (!em.contains(ta)){
                throw new TraegerArtNotFoundException();
            }
            em.merge(ta);
        }
    }

    /** Remove an existing container type object from the database
     * @param ta - the container type object to remove from the database
     * @throws TraegerArtNotFoundException if the container type couldn't be found in the database */
    public void remove(TraegerArt ta) throws TraegerArtNotFoundException{
        if (ta!=null){
            if (!em.contains(ta)){
                throw new TraegerArtNotFoundException();
            }
            ta.setValidData(false);
            update(ta);
        }
    }

    /** @return the class of containerType */
    public Class<TraegerArt> get(){
        return TraegerArt.class;
    }

    /** Get all container types from the database
     * @return a list of all container types in the database */
    public List<TraegerArt> getAll(){
        List<TraegerArt> arten = new ArrayList<>();
        try {
            arten = em.createQuery("SELECT art FROM TraegerArt art WHERE art.isValidData=true",get()).getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return arten;
    }

    /** Get a container type by name
     * @param taName - the container name
     * @throws TraegerArtNotFoundException if the container type couldn't be found in the database */
    public TraegerArt getByName(String taName) throws TraegerArtNotFoundException{
        try {
            return (TraegerArt) em.createQuery("SELECT ta FROM TraegerArt ta WHERE ta.art = :taName AND ta.isValidData=true").getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new TraegerArtNotFoundException();
        }
    }

    /** Get a container type by id
     * @param id - the container type id
     * @throws TraegerArtNotFoundException if the container type couldn't be found in the database */
    public TraegerArt getById(int id) throws TraegerArtNotFoundException{
        try {
            TraegerArt ta =  em.find(get(),id);
            if (!ta.isValidData()){
                throw new Exception();
            }
            return ta;
        }
        catch (Exception e){
            throw new TraegerArtNotFoundException();
        }
    }
}
