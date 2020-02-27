package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.Traeger;

import java.util.ArrayList;
import java.util.List;

/** This class handles the containers in the database */
public class TraegerDAO extends ObjectDAO<Traeger> {

    /** Add a container to the database
     * @param t - the container to add to the database
     * @throws DuplicateTraegerException if the container already exists in the database */
    public void persist(Traeger t) throws DuplicateTraegerException{
        if (t!=null){
            synchronized (Traeger.class){
                if (em.contains(t)){
                    throw new DuplicateTraegerException();
                }
                em.persist(t);
            }
        }
    }

    /** Update a container in the database
     * @param t - the container to update in the databasegit
     * @throws TraegerNotFoundException if the container cannot be found in the database */
    public void update(Traeger t) throws TraegerNotFoundException{
        if (t!=null){
            if (!em.contains(t)){
                throw new TraegerNotFoundException();
            }
            em.merge(t);
        }
    }

    /** Remove a container from the database
     * @param t - the container to remove from the database
     * @throws TraegerNotFoundException if the container cannot be found in the database */
    public void remove(Traeger t) throws TraegerNotFoundException{
        if (t!=null){
            if (!em.contains(t)){
                throw new TraegerNotFoundException();
            }
            t.setValidData(false);
            update(t);
        }
    }

    /** @return the class of container */
    public Class<Traeger> get(){
        return Traeger.class;
    }


    /** Get a container through its id
     * @param id - the id of the requested container
     * @return the requested container
     * @throws TraegerNotFoundException if the container cannot be found in the database */
    public Traeger getObjById(int id) throws TraegerNotFoundException{
        try {
            Traeger t = em.find(get(),id);
            if (t==null || !t.isValidData()){
                throw new TraegerNotFoundException();
            }
            return t;
        }
        catch (Exception e){
            throw new TraegerNotFoundException();
        }
    }

    /** Get a list of all containers in the database
     * @return a list of all containers in the database or an empty arraylist */
    public List<Traeger> getAll(){
        List<Traeger> traegers = new ArrayList<>();
        try{
            traegers = em.createQuery("select t from Traeger t where t.isValidData = true",get()).getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return traegers;
    }
}
