package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.model.Auftrag;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the instantiated process chains in the database
 */
public class AuftragDAO extends ObjectDAO<Auftrag> {

    /**
     * Add a job to the database
     *
     * @param a - the job object to add to the database
     * @throws DuplicateAuftragException if the job already exists in the database
     */
    public void persist(Auftrag a) throws DuplicateAuftragException {
        if (a != null) {
            synchronized (Auftrag.class) {
                if (em.contains(em.find(get(), a.getPkID()))) {
                    throw new DuplicateAuftragException();
                }
                em.persist(a);
            }
        }
    }

    /**
     * Update a job in the database
     *
     * @param a - the job object to update in the database
     * @throws AuftragNotFoundException if the job couldn't be found
     */
    public void update(Auftrag a) throws AuftragNotFoundException {
        if (a != null) {
            if (!em.contains(em.find(get(), a.getPkID()))) {
                throw new AuftragNotFoundException();
            }
            em.merge(a);
        }
    }

    /**
     * Remove a job from the database
     *
     * @param a - the job object to remove from the database
     * @throws AuftragNotFoundException if the job object couldn't be found in the database
     */
    public void remove(Auftrag a) throws AuftragNotFoundException {
        if (a != null) {
            if (!em.contains(em.find(get(), a.getPkID()))) {
                throw new AuftragNotFoundException();
            }
            a.setValidData(false);
            update(a);
        }
    }

    /**
     * @return the class of job
     */
    public Class<Auftrag> get() {
        return Auftrag.class;
    }

    /**
     * Get a process chain step template from the database using its defined id
     *
     * @param id - the id of the requested process chain step template
     * @return the process chain step template matching the given id
     * @throws AuftragNotFoundException if the process chain step template couldn't be found
     */
    public Auftrag getObjById(int id) throws AuftragNotFoundException {
        try {
            Auftrag psv = em.find(get(), id);
            if (psv == null || !psv.isValidData()) {
                throw new AuftragNotFoundException();
            }
            return psv;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuftragNotFoundException();
        }
    }


    /**
     * Get a list of all users in the database
     *
     * @return all users in the database
     */
    public List<Auftrag> getAll() {
        try {
            return em.createNamedQuery("Auftrag.getAll", get()).getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /** Get all transport jobs
     * @return a list of all available transport jobs or an empty arraylist */
    public List<Auftrag> getAllTransport(){
        List<Auftrag> a = new ArrayList<>();
        try {
            a = em.createNamedQuery("Auftrag.getTransport",get()).getResultList();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return a;
    }

    /** Get a transport job using its id
     * @param id - the id of the transport job to get
     * @return the requested job
     * @throws AuftragNotFoundException if the job cannot be found */
    public Auftrag getTransportById(int id) throws AuftragNotFoundException{
        try {
            return em.createQuery("select a from Auftrag a where a.isValidData=true and a.pkID = :id and a.transportauftrag=true",get()).getSingleResult();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new AuftragNotFoundException();
        }
    }


}
