package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateTransportAuftragException;
import de.unibremen.sfb.exception.TransportAuftragNotFoundException;
import de.unibremen.sfb.model.TransportAuftrag;

/**
 * this class handles the transport assignments in the database
 */
public class TransportAuftragDAO extends ObjectDAO<TransportAuftrag> {

    /**
     * add a assignment to the database
     * @param a the new assignment
     * @throws DuplicateTransportAuftragException if the assignment already exists in the database
     */
    public void persist(TransportAuftrag a) throws DuplicateTransportAuftragException {
        if (a!=null){
            synchronized (TransportAuftrag.class){
                if (em.contains(a)){
                    throw new DuplicateTransportAuftragException();
                }
                em.persist(a);
            }
        }
    }

    /**
     * edit an assignment in the database
     * @param a the assignment that is updated
     * @throws TransportAuftragNotFoundException if the assignment cannot be found
     */
    public void update(TransportAuftrag a) throws TransportAuftragNotFoundException{
        if (a!=null){
            if (!em.contains(a)){
                throw new TransportAuftragNotFoundException();
            }
            em.merge(a);
        }
    }

    /**
     * remove an assignment from the database
     * @param a the assignment that will be removed
     * @throws TransportAuftragNotFoundException if the assignment cannot be found
     */
    public void remove(TransportAuftrag a) throws TransportAuftragNotFoundException {
        if (a!=null){
            if (!em.contains(a)){
                throw new TransportAuftragNotFoundException();
            }
            em.remove(a);
        }
    }

    /**
     *
     * @return the class of assignment
     */
    public Class<TransportAuftrag> get(){
        return TransportAuftrag.class;
    }
}
