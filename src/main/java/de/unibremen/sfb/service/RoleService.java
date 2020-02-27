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

    public void clearRoles(String username) throws RoleNotFoundException {
        List<Role> roles = roleDao.getAll();
        for (Role r : roles){
            if (r.getUsername().equals(username)){
                r.setValidData(false);
                roleDao.update(r);
            }
        }
    }

    public void applyRoles(List<String> roles,String username) throws RoleNotFoundException, DuplicateRoleException {
        clearRoles(username);
        for (String s : roles){
            Role r = new Role(UUID.randomUUID().hashCode(),s);
            r.setUsername(username);
            roleDao.persist(r);
        }
    }
}
