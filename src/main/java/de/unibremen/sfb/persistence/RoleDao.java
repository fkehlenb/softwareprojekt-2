package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateRoleException;
import de.unibremen.sfb.model.Role;
import lombok.extern.slf4j.Slf4j;

import de.unibremen.sfb.exception.RoleNotFoundException;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the conditions in the database
 */
@Slf4j
public class RoleDao extends ObjectDAO<Role> {

    /**
     * Add a condition to the database
     *
     * @param b - the condition to add to the database
     * @throws DuplicateRoleException if the condition already exists in the database
     */
    public void persist(Role b) throws DuplicateRoleException {
        if (b != null) {
            synchronized (Role.class) {
                if (em.contains(b)) {
                    try {
                        throw new DuplicateRoleException();
                    } catch (DuplicateRoleException e) {
                        e.printStackTrace();
                    }
                }
                em.persist(b);
            }
        }
    }

    /**
     * Update a condition in the database
     *
     * @param b - the condition to update in the database
     * @throws RoleNotFoundException if the condition couldn't be found
     */
    public void update(Role b) throws RoleNotFoundException {
        if (b != null) {
            em.merge(b);
        }
    }

    /**
     * Remove a condition from the database
     *
     * @param b - the condition to remove from the database
     * @throws RoleNotFoundException if the condition couldn't be found
     */
    public void remove(Role b) throws RoleNotFoundException {
        if (b != null) {
            if (!em.contains(b)) {
                throw new RoleNotFoundException();
            }
            b.setValidData(false);
            update(b);
        }
    }

    /**
     * @return the class of condition
     */
    public Class<Role> get() {
        return Role.class;
    }


    public List<Role> getAll() {
        try {
            List<Role> es = em.createQuery("SELECT b FROM Role b WHERE b.isValidData=true", get()).getResultList();
            if (es.isEmpty()) {
                log.info("No Rolen Found");
                return new ArrayList<>();
            }
            return es;
        } catch (EntityNotFoundException e) {
//            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /**
     * Get a role using its type
     *
     * @param r - the user
     * @return the role object matching the string
     */
    public List<Role> getObjByID(String r) {
            return em.createNamedQuery("Role.getByName",get()).setParameter("name",r).getResultList();
        }
}
