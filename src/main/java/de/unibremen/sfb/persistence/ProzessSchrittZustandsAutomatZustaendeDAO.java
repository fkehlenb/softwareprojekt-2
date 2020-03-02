package de.unibremen.sfb.persistence;

import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatZustaende;

public class ProzessSchrittZustandsAutomatZustaendeDAO extends ObjectDAO<ProzessSchrittZustandsAutomatZustaende> {

    /** Add the process step states to the database
     * @param pss - the process step states object to add
     * @throws IllegalArgumentException on failure */
    public void persist(ProzessSchrittZustandsAutomatZustaende pss) throws IllegalArgumentException{
        try {
            synchronized (ProzessSchrittZustandsAutomatZustaende.class) {
                em.persist(pss);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /** Update the process step states object in the database
     * @param pss - the process step states object to update
     * @throws IllegalArgumentException on failure */
    public void update(ProzessSchrittZustandsAutomatZustaende pss) throws IllegalArgumentException{
        try {
            em.merge(pss);
        }
        catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /** Remove the process step states object from the database
     * @param pss - the process step states object to remove
     * @throws UnsupportedOperationException always */
    public void remove(ProzessSchrittZustandsAutomatZustaende pss) throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    /** Get the object using its id
     * @return the states object */
    public ProzessSchrittZustandsAutomatZustaende getById(int id){
        return em.find(ProzessSchrittZustandsAutomatZustaende.class,id);
    }
}
