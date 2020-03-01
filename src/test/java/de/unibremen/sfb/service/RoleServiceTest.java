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
    @Mock
    List<Role> roles;
    @InjectMocks
    RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //@Test
    void testClearRoles() throws RoleNotFoundException {
        when(roleDao.getAll()).thenReturn(roles);
        roleService.clearRoles("username");
        verify(roleDao).getAll();
    }

    @Test
    void testApplyRoles() throws RoleNotFoundException, DuplicateRoleException {
        roleService.applyRoles(Arrays.<String>asList("String"), "username");

    }

    @Test
    void testGetRolesByUser() {
        when(roleDao.getRolesByUsername(anyString())).thenReturn(Arrays.<Role>asList(new Role(0, "name")));

        List<Role> result = roleService.getRolesByUser("username");
        Assertions.assertEquals(Arrays.<Role>asList(new Role(0, "name")), result);
    }
}