package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class UserTest {
    //Field erstellungsDatum of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    User user = new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 49, 58), "language");

    @Test
    void testToString() {
        String result = user.toString();
        Assertions.assertEquals("username", result);
    }

    @Test
    void testSetValidData() {
        user.setValidData(true);
    }

    @Test
    void testSetId() {
        user.setId(0);
    }

    @Test
    void testSetVorname() {
        user.setVorname("vorname");
    }

    @Test
    void testSetNachname() {
        user.setNachname("nachname");
    }

    @Test
    void testSetEmail() {
        user.setEmail("email");
    }

    @Test
    void testSetTelefonnummer() {
        user.setTelefonnummer("telefonnummer");
    }

    @Test
    void testSetUsername() {
        user.setUsername("username");
    }

    @Test
    void testSetPassword() {
        user.setPassword("password");
    }

    @Test
    void testSetWurdeVerifiziert() {
        user.setWurdeVerifiziert(true);
    }

    @Test
    void testSetErstellungsDatum() {
        user.setErstellungsDatum(LocalDateTime.of(2020, Month.MARCH, 5, 16, 49, 58));
    }

    @Test
    void testSetLanguage() {
        user.setLanguage("language");
    }

    @Test
    void testEquals() {
        boolean result = user.equals("o");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testCanEqual() {
        boolean result = user.canEqual("other");
        Assertions.assertEquals(false, result);
    }

}

