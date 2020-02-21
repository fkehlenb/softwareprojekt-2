package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.User;

import java.util.ArrayList;
import java.util.List;

/** This class handles the instantiated process chains in the database */
public class AuftragDAO extends ObjectDAO<Auftrag> {

    /** Add a job to the database
     * @param a - the job object to add to the database
     * @throws DuplicateAuftragException if the job already exists in the database */
    public void persist(Auftrag a) throws DuplicateAuftragException {
        if (a!=null){
            synchronized (Auftrag.class){
                if (em.contains(a)){
                    throw new DuplicateAuftragException();
                }
                em.persist(a);
            }
        }
    }

    /** Update a job in the database
     * @param a - the job object to update in the database
     * @throws AuftragNotFoundException if the job couldn't be found */
    public void update(Auftrag a) throws AuftragNotFoundException{
        if (a!=null){
            if (!em.contains(a)){
                throw new AuftragNotFoundException();
            }
            em.merge(a);
        }
    }

    /** Remove a job from the database
     * @param a - the job object to remove from the database
     * @throws AuftragNotFoundException if the job object couldn't be found in the database */
    public void remove(Auftrag a) throws AuftragNotFoundException {
        if (a!=null){
            if (!em.contains(a)){
                throw new AuftragNotFoundException();
            }
            em.remove(a);
        }
    }

    /** @return the class of job */
    public Class<Auftrag> get(){
        return Auftrag.class;
    }

    /** Get a process chain step template from the database using its defined id
     * @param id - the id of the requested process chain step template
     * @return the process chain step template matching the given id
     * @throws AuftragNotFoundException if the process chain step template couldn't be found */
    public Auftrag getObjById(int id) throws AuftragNotFoundException{
        try {
            Auftrag psv = em.find(get(),id);
            if (psv==null){
                throw new AuftragNotFoundException();
            }
            return psv;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AuftragNotFoundException();
        }
    }


    /**
     * Get a list of all users in the database
     * @return all users in the database
     */
    public List<Auftrag> getAll() {
        try {
            return em.createNamedQuery("Auftrag.getAll", get()).getResultList();
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }
}
