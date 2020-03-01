package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateKommentarException;
import de.unibremen.sfb.exception.KommentarNotFoundException;
import de.unibremen.sfb.model.Kommentar;

public class KommentarDAO extends ObjectDAO<Kommentar> {

    /**
     * Add an object to the database
     *
     * @param kommentar - the object to add to the database
     * @throws DuplicateKommentarException if the object couldn't be added to the database
     */
    @Override
    public void persist(Kommentar kommentar) throws DuplicateKommentarException {
        if(kommentar!=null) {
            synchronized (Kommentar.class) {
                if (em.contains(em.find(get(), kommentar.getId()))) {
                    throw new DuplicateKommentarException();
                }
                em.persist(kommentar);
            }
        }
    }

    /**
     * Update an object in the database
     *
     * @param kommentar - the object to update in the database
     * @throws KommentarNotFoundException if the object couldn't be updated in the database
     */
    @Override
    public void update(Kommentar kommentar) throws KommentarNotFoundException {
        if (kommentar != null) {
            if (!em.contains(em.find(get(), kommentar.getId()))) {
                throw new KommentarNotFoundException();
            }
            em.merge(kommentar);
        }
    }

    public Class<Kommentar> get() { return Kommentar.class; }


    /**
     * Remove an object from the database
     *
     * @param kommentar - the object to remove from the database
     * @throws KommentarNotFoundException if the requested object couldn't be removed from the database
     */
    @Override
    public void remove(Kommentar kommentar) throws KommentarNotFoundException {
        if (kommentar != null) {
            if (!em.contains(em.find(get(), kommentar.getId()))) {
                throw new KommentarNotFoundException();
            }
            kommentar.setValidData(false);
            update(kommentar);
        }
    }
}
