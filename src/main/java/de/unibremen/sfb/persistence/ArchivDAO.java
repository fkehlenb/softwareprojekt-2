package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.ArchivNotFoundException;
import de.unibremen.sfb.exception.DuplicateArchivException;
import de.unibremen.sfb.model.Archiv;

/** This class handles archives saved in the database */
public class ArchivDAO extends ObjectDAO<Archiv> {

    /** Add an archive object to the database
     * @param a - the archive to add to the database
     * @throws DuplicateArchivException if the archive already exists in the database */
    public void persist(Archiv a) throws DuplicateArchivException{
        if (a!=null) {
            synchronized (Archiv.class) {
                if (em.contains(a)) {
                    throw new DuplicateArchivException();
                }
                em.persist(a);
            }
        }
    }

    /** Update an existing archive in the database
     * @param a - the archive to update in the database
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public void update(Archiv a) throws ArchivNotFoundException{
        if (a!=null){
            if (!em.contains(a)){
                throw new ArchivNotFoundException();
            }
            em.merge(a);
        }
    }

    /** Remove an existing archive from the database
     * @param a - the archive to remove from the database
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public void remove(Archiv a) throws ArchivNotFoundException{
        if (a!=null){
            if (!em.contains(a)){
                throw new ArchivNotFoundException();
            }
            em.remove(a);
        }
    }

    /** @return the class of archive */
    public Class<Archiv> get(){
        return Archiv.class;
    }

    /** Get an existing archive using its id
     * @param id - the id which's archive to get from the database
     * @return the archive with an id matching the entered one
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public Archiv getObjById(int id) throws ArchivNotFoundException{
        if (id==0){
            throw new ArchivNotFoundException();
        }
        Archiv a = em.find(get(),id);
        if (a==null){
            throw new ArchivNotFoundException();
        }
        return a;
    }
}
