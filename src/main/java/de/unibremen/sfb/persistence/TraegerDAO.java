package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTraegerException;
import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.exception.TraegerArtNotFoundException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.model.TraegerArt;

import java.util.List;
import java.util.Set;

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
     * @param t - the container to update in the database
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
            em.remove(t);
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
            if (t==null){
                throw new TraegerNotFoundException();
            }
            return t;
        }
        catch (Exception e){
            throw new TraegerNotFoundException();
        }
    }

    /** Get all containers from a specific location
     * @return the containers in a specific location
     * @throws StandortNotFoundException if the container couldn't be found in the database */
    public List<Traeger> getTraegerByLocation(Standort s) throws StandortNotFoundException{
        try {
            List<Traeger> set = em.createNamedQuery("Traeger.findByLoc",get()).setParameter("standort",s).getResultList();
            if (set.isEmpty()){
                throw new StandortNotFoundException();
            }
            return set;
        }
        catch (Exception e){
            throw new StandortNotFoundException();
        }
    }

    /** Get the container containing a list of samples
     * @return the container containing a list of samples
     * @throws TraegerNotFoundException if the container couldn't be found in the database */
    public Traeger getTragerWithProben(List<Probe> p) throws TraegerNotFoundException{
        try {
            Traeger t = em.createNamedQuery("Trager.findByProben",get()).setParameter("proben",p).getSingleResult();
            if (t==null){
                throw new TraegerNotFoundException();
            }
            return t;
        }
        catch (Exception e){
            throw new TraegerNotFoundException();
        }
    }

    /** Get all containers of a specific type
     * @return list of all containers of a specific type
     * @throws TraegerArtNotFoundException if the container couldn't be found or the TraegerArt doesn't exist*/
    public List<Traeger> getTragerByArt(TraegerArt ta) throws TraegerArtNotFoundException{
        try {
            List<Traeger> t = em.createNamedQuery("Traeger.getByType",get()).setParameter("art",ta).getResultList();
            if (t.isEmpty()){
                throw new TraegerArtNotFoundException();
            }
            return t;
        }
        catch (Exception e){
            throw new TraegerArtNotFoundException();
        }
    }
}
