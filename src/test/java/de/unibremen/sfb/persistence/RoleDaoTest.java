package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateRoleException;
import de.unibremen.sfb.exception.RoleNotFoundException;
import de.unibremen.sfb.model.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    @Mock
    Query query;
    @InjectMocks
    RoleDao roleDao;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);
        when(role.getId()).thenReturn(0);
        when(em.find(any(), any())).thenReturn(role);
        when(em.contains(role)).thenReturn(true);
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
        Assertions.assertEquals(Role.class, result);
    }

    @Test
    void testGetAll() {
        //when(em.createQuery(anyString(),eq(Role.class))).thenReturn();
        //when(query.getResultList()).thenReturn(roles);
        //Mockito.doReturn(query).when(em).createQuery(anyString(),any());
        //Mockito.doReturn(roles).when(em.createQuery(anyString(),eq(Role.class))).getResultList();
        //when(em.createQuery(anyString(),eq(Role.class)).getResultList()).thenReturn(new ArrayList<Role>());
        List<Role> result = roleDao.getAll();
        Assertions.assertEquals(roles, result);
    }

    @Test
    void testGetObjByID() {
        List<Role> result = roleDao.getObjByID("2");
        Assertions.assertEquals(roles, result);
    }

    @Test
    void testGetRolesByUsername() {
        when(em.createNamedQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(roles);
        when(roleDao.getRolesByUsername(anyString())).thenReturn(roles);
        List<Role> result = roleDao.getRolesByUsername("name");
        Assertions.assertEquals("[]", result);
    }
}

