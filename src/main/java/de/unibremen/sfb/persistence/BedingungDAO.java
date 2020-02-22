package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.BedingungNotFoundException;
import de.unibremen.sfb.exception.DuplicateBedingungException;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.model.Standort;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/** This class handles the conditions in the database */
@Slf4j
public class BedingungDAO extends ObjectDAO<Bedingung> {

    /** Add a condition to the database
     * @param b - the condition to add to the database
     * @throws DuplicateBedingungException if the condition already exists in the database */
    public void persist(Bedingung b)   {
        if (b!=null){
            synchronized (Bedingung.class){
                if (em.contains(b)){
                    try {
                        throw new DuplicateBedingungException();
                    } catch (DuplicateBedingungException e) {
                        e.printStackTrace();
                    }
                }
                em.persist(b);
            }
        }
    }

    /** Update a condition in the database
     * @param b - the condition to update in the database
     * @throws BedingungNotFoundException if the condition couldn't be found */
    public void update(Bedingung b) throws BedingungNotFoundException{
        if (b!=null){
            em.merge(b);
        }
    }

    /** Remove a condition from the database
     * @param b - the condition to remove from the database
     * @throws BedingungNotFoundException if the condition couldn't be found */
    public void remove(Bedingung b) throws BedingungNotFoundException{
        if (b!=null){
            if (!em.contains(b)){
                throw new BedingungNotFoundException();
            }
            b.setValidData(false);
            update(b);
        }
    }

    /** @return the class of condition */
    public Class<Bedingung> get(){
        return Bedingung.class;
    }


    public List<Bedingung> getAll(){
        try {
            List<Bedingung> es = em.createQuery("SELECT b FROM Bedingung b WHERE b.isValidData=true",get()).getResultList();
            if (es.isEmpty()){
                log.info("No Bedingungen Found");
                return new ArrayList<>();
            }
            return es;
        }
        catch (Exception e){
//            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
    public Bedingung findById(int id){
        try {
            log.info("Trying to find Bedingungen");
            return em.find(Bedingung.class,id);
        }catch (Exception e){
            log.info("No Bedingungen Found");
            return null;
        }
    }
}
