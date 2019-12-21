package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateTransportAuftragException;
import de.unibremen.sfb.Exception.TransportAuftragNotFoundException;
import de.unibremen.sfb.Model.TransportAuftrag;

/**
 * this class handles the transport assignments in the database
 */
public class TransportAuftragDAO extends ObjectDAO<TransportAuftrag> {

    /**
     * add a assignment to the database
     * @param a the new assignment
     * @throws DuplicateTransportAuftragException if the assignment already exists in the database
     */
    public void persist(TransportAuftrag a) throws DuplicateTransportAuftragException {}

    /**
     * edit an assignment in the database
     * @param a the assignment that is updated
     * @throws TransportAuftragNotFoundException if the assignment cannot be found
     */
    public void update(TransportAuftrag a) throws TransportAuftragNotFoundException{}

    /**
     * remove an assignment from the database
     * @param a the assignment that will be removed
     * @throws TransportAuftragNotFoundException if the assignment cannot be found
     */
    public void remove(TransportAuftrag a) throws TransportAuftragNotFoundException {}

    /**
     *
     * @return the class of assignment
     */
    public Class<TransportAuftrag> get(){
        return TransportAuftrag.class;
    }
}
