package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.ArchivNotFoundException;
import de.unibremen.sfb.Exception.DuplicateArchivException;
import de.unibremen.sfb.Model.Archiv;

/** This class handles archives saved in the database */
public class ArchivDAO extends ObjectDAO<Archiv> {

    /** Add an archive object to the database
     * @param a - the archive to add to the database
     * @throws DuplicateArchivException if the archive already exists in the database */
    public void persist(Archiv a) throws DuplicateArchivException{}

    /** Update an existing archive in the database
     * @param a - the archive to update in the database
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public void update(Archiv a) throws ArchivNotFoundException{}

    /** Remove an existing archive from the database
     * @param a - the archive to remove from the database
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public void remove(Archiv a) throws ArchivNotFoundException{}

    /** Get an existing archive from the database
     * @return the requested archive
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public Archiv get() throws ArchivNotFoundException{
        return null;
    }

    /** Get an existing archive using its id
     * @param id - the id which's archive to get from the database
     * @return the archive with an id matching the entered one
     * @throws ArchivNotFoundException if the archive couldn't be found in the database */
    public Archiv getObjById(int id) throws ArchivNotFoundException{
        return null;
    }
}