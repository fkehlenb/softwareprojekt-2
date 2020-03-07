package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateRoleException;
import de.unibremen.sfb.exception.RoleNotFoundException;
import de.unibremen.sfb.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class RoleDaoTest {
    @Mock
    Role role;
    @Mock
    List<Role> roles;
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    RoleDao roleDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateRoleException {
        roleDao.persist(new Role(0, "name"));
    }


    @Test
    void testUpdate() throws RoleNotFoundException {
        roleDao.update(role);
        verify(em).merge(role);
    }

    @Test
    void testRemove() throws RoleNotFoundException {
        roleDao.remove(role);
    }

    @Test
    void testGet() {
        Class<Role> result = roleDao.get();
        Assertions.assertEquals( roles.getClass(), result.getClass());
    }

    @Test
    void testGetAll() {
        List<Role> result = roleDao.getAll();
        Assertions.assertEquals(roles, result);
    }

    @Test
    void testGetObjByID() {
        List<Role> result = roleDao.getObjByID("r");
        Assertions.assertEquals(roles, result);
    }

    @Test
    void testGetRolesByUsername() {
        List<Role> result = roleDao.getRolesByUsername("username");
        Assertions.assertEquals("[]", result);
    }
}

