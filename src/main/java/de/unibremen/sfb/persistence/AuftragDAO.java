package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.User;

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

    /** Get a job object from the database using the job id
     * @param id - the id whose job object to fetch from the database
     * @throws AuftragNotFoundException if the job couldn't be found in the database
     * @return Gibt den Auftrag zuruueck
     */

    public Auftrag getObjById(int id) throws AuftragNotFoundException{
        return null;
    }

    /** Get the jobs of a specific user
     * @param u - the user whose jobs to get
     * @return the jobs belonging to a user
     * @throws AuftragNotFoundException if the job couldn't be found */
    public List<Auftrag> getAuftragByUser(User u) throws AuftragNotFoundException{
        return null;
    }
}
