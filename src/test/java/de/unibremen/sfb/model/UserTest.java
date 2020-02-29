package de.unibremen.sfb.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

class UserTest {
    //Field erstellungsDatum of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    User user = new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 51, 18), "language");

    @Test
    void testToString() {
        String result = user.toString();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
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
        user.setErstellungsDatum(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 51, 18));
    }

    @Test
    void testSetLanguage() {
        user.setLanguage("language");
    }

    @Test
    void testEquals() {
        boolean result = user.equals("o");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testCanEqual() {
        boolean result = user.canEqual("other");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testHashCode() {
        int result = user.hashCode();
        Assertions.assertEquals(0, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme