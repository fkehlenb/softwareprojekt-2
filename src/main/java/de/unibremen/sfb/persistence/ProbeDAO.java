package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;

/** This class handles the samples in the database */
public class ProbeDAO extends ObjectDAO<Probe> {

    /** Add a sample to the database
     * @param p - the sample to add to the database
     * @throws DuplicateProbeException if the sample already exists in the database */
    public void persist(Probe p) throws DuplicateProbeException {
        if (p!=null) {
            synchronized (Probe.class) {
                if (em.contains(p)) {
                    throw new DuplicateProbeException();
                }
                em.persist(p);
            }
        }
    }

    /** Update a sample in the database
     * @param p - the sample to update in the database
     * @throws ProbeNotFoundException if the sample couldn't be found in the database */
    public void update(Probe p) throws ProbeNotFoundException{
        if (p!=null){
            if (!em.contains(p)){
                throw new ProbeNotFoundException();
            }
            em.merge(p);
        }
    }

    /** Remove a sample from the database
     * @param p - the sample to be removed from the database
     * @throws ProbeNotFoundException if the sample couldn't be found in the database */
    public void remove(Probe p) throws ProbeNotFoundException{
        if (p!=null){
            if (!em.contains(p)){
                throw new ProbeNotFoundException();
            }
            em.remove(p);
        }
    }

    /** @return the class of sample */
    public Class<Probe> get(){
        return Probe.class;
    }

    /** Use a sample id to get a specific sample
     * @param id - the id of the requested sample
     * @return the sample which's id matches the given one
     * @throws ProbeNotFoundException if the sample couldn't be found in the database */
    public Probe getObjById(int id) throws ProbeNotFoundException{
        return null;
    }

    /** Get a sample's location
     * @return the sample's location
     * @throws ProbeNotFoundException if the sample couldn't be found in the database */
    public Standort getLocation() throws ProbeNotFoundException{
        return null;
    }

    /** Get the container in which a sample is located
     * @return the container in which the sample is located
     * @throws ProbeNotFoundException if the sample couldn't be found in the database */
    public Traeger getTraeger() throws ProbeNotFoundException{
        return null;
    }
}
