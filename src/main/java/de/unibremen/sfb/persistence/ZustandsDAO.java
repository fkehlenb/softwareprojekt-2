package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateZustandException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.exception.ZustandNotFoundException;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.model.Zustand;

import java.util.ArrayList;
import java.util.List;


public class ZustandsDAO extends ObjectDAO<Zustand> {

    /**
     * add a assignment to the database
     *
     * @param zustand the new assignment
     * @throws DuplicateZustandException if the assignment already exists in the database
     */
    public void persist(Zustand zustand) throws DuplicateZustandException {
        if (zustand != null) {
            synchronized (Zustand.class) {
                if (em.contains(zustand)) {
                    throw new DuplicateZustandException();
                }
                em.persist(zustand);
            }
        }
    }

    /**
     * add a assignment to the database
     *
     * @param zustand the new assignment
     * @throws DuplicateZustandException if the assignment already exists in the database
     */
    public void update(Zustand zustand) throws DuplicateZustandException, ZustandNotFoundException {
        if (zustand != null) {
            if (!em.contains(zustand)) {
                throw new ZustandNotFoundException();
            }
            em.merge(zustand);
        }
    }

    /**
     * add a assignment to the database
     *
     * @param zustand the new assignment
     * @throws ZustandNotFoundException if the assignment already exists in the database
     */
    public void remove(Zustand zustand) throws ZustandNotFoundException, DuplicateZustandException {
        if (zustand != null) {
            if (!em.contains(zustand)) {
                throw new ZustandNotFoundException();
            }
            zustand.setValidData(false);
            update(zustand);
        }
    }

    /**
     * @return the class of Zustand
     */
    public Class<Zustand> get() {
        return Zustand.class;
    }

    /**
     * Get a list of all Zustand in the database
     *
     * @return all Zustand in the database
     */
    public List<Zustand> getAll() {
        try {
            return em.createQuery("SELECT b FROM Zustand b").getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    /**
     * Get a Zustand object using the Zustand's id
     *
     * @param id - the Zustand id whose user object to fetch from the database
     * @return the Zustand object matching the user id
     * @throws ZustandNotFoundException if the user couldn't be found
     */
    public Zustand getZustandById(String id) throws ZustandNotFoundException {
        Zustand z = em.find(get(), id);
        if (z == null || !z.isValidData()) {
            throw new ZustandNotFoundException();
        }
        return z;
    }

}
