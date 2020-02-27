package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateRoleException;
import de.unibremen.sfb.exception.RoleNotFoundException;
import de.unibremen.sfb.model.Role;
import de.unibremen.sfb.persistence.RoleDao;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

public class RoleService {

    @Inject
    private RoleDao roleDao;

    /** Clear the roles assigned to a user
     * @param username - the username of the user whose roles to clear
     * @throws RoleNotFoundException on persistence failure */
    public void clearRoles(String username) throws RoleNotFoundException {
        List<Role> roles = roleDao.getAll();
        for (Role r : roles){
            if (r.getUsername().equals(username)){
                r.setValidData(false);
                roleDao.update(r);
            }
        }
    }

    /** Add roles to a user
     * @param roles - a list of strings containing the roles which to add to a user
     * @param username - the username of the user whose getting the above roles
     * @throws RoleNotFoundException on clear user roles failure
     * @throws DuplicateRoleException on persistence failure */
    public void applyRoles(List<String> roles,String username) throws RoleNotFoundException, DuplicateRoleException {
        clearRoles(username);
        for (String s : roles){
            Role r = new Role(UUID.randomUUID().hashCode(),s);
            r.setUsername(username);
            roleDao.persist(r);
        }
    }

    /** Get the roles assigned to a user
     * @param username - the username of the user whose roles to fetch
     * @return a list of the user's roles */
    public List<Role> getRolesByUser(String username){
        return roleDao.getRolesByUsername(username);
    }
}
