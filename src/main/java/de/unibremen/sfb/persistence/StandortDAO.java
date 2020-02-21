package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/** This class handles the location objects in the database*/
@Slf4j
public class StandortDAO extends ObjectDAO<Standort> {

    /** Add a location object to the database
     * @param s - the location object to add to the database
     * @throws DuplicateStandortException if the location already exists in the database */
    public void persist(Standort s) throws DuplicateStandortException {
        if (s!=null){
            synchronized (Standort.class){
                if (em.contains(s)){
                    throw new DuplicateStandortException();
                }
                em.persist(s);
            }
        }
    }

    /** Update a location object in the database
     * @param s - the location to update in the database
     * @throws StandortNotFoundException if the location couldn't be found in the database */
    public void update(Standort s) throws StandortNotFoundException{
        if (s!=null){
            if (!em.contains(s)){
                throw new StandortNotFoundException();
            }
            em.merge(s);
        }
    }

    /** Remove a location object from the database
     * @param s - the location object to remove from the database
     * @throws StandortNotFoundException if the location object couldn't be found in the database */
    public void remove(Standort s) throws StandortNotFoundException{
        if (s!=null){
            if (!em.contains(s)) {
                em.merge(s);
            }
            em.remove(s);
        }
    }

    /** @return the class of location */
    public Class<Standort> get(){
        return Standort.class;
    }

    /** Get a location object from the database using its unique id
     * @return the location object with an id matching the entered one
     * @param id - Die ID des Standortes
     * @throws StandortNotFoundException if the location object couldn't be found in the database */
    public Standort getObjById(int id) throws StandortNotFoundException{
        try{
            Standort s = em.find(get(),id);
            if (s==null){
                throw new StandortNotFoundException();
            }
            return s;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new StandortNotFoundException();
        }
    }

    /** Get a location object using the location string
     * @param l - the location String
     * @return the location object with a matching string
     * @throws StandortNotFoundException if a location with that string cannot be found */
    public Standort getByOrt(String l) throws StandortNotFoundException{
        try {
            return em.createNamedQuery("Standort.getByOrt", get()).setParameter("ort", l).getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new StandortNotFoundException();
        }
    }

    /** @return all locations */
    public List<Standort> getAll(){
        try {
            List<Standort> es = em.createQuery("SELECT es FROM Standort es",get()).getResultList();
            if (es.isEmpty()){
                log.info("No Standorte Found");
                return new ArrayList<>();
            }
            return es;
        }
        catch (Exception e){
//            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
