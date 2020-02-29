package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateRoleException;
import de.unibremen.sfb.exception.RoleNotFoundException;
import de.unibremen.sfb.model.Role;
import de.unibremen.sfb.persistence.RoleDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class RoleServiceTest {
    @Mock
    RoleDao roleDao;
    @InjectMocks
    RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testClearRoles() {
        when(roleDao.getAll()).thenReturn(Arrays.<Role>asList(new Role(0, "name")));

        try {
            roleService.clearRoles("username");
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testApplyRoles() {
        when(roleDao.getAll()).thenReturn(Arrays.<Role>asList(new Role(0, "name")));

        try {
            roleService.applyRoles(Arrays.<String>asList("String"), "username");
        } catch (RoleNotFoundException e) {
            e.printStackTrace();
        } catch (DuplicateRoleException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetRolesByUser() {
        when(roleDao.getRolesByUsername(anyString())).thenReturn(Arrays.<Role>asList(new Role(0, "name")));

        List<Role> result = roleService.getRolesByUser("username");
        Assertions.assertEquals(Arrays.<Role>asList(new Role(0, "name")), result);
    }
}