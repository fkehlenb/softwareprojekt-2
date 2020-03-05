package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class RoleTest {
    Role role = new Role(0, "name");

    @Test
    void testSetId() {
        role.setId(0);
    }

    @Test
    void testSetValidData() {
        role.setValidData(true);
    }

    @Test
    void testSetName() {
        role.setName("name");
    }

    @Test
    void testSetUsername() {
        role.setUsername("username");
    }

    @Test
    void testEquals() {
        boolean result = role.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = role.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = role.hashCode();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testToString() {
        String result = role.toString();
        Assertions.assertEquals("Role(id=0, isValidData=true, name=name, username=null)", result);
    }
}

