package de.unibremen.sfb.converter;

import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class UserConverterTest {
    @Mock
    UserService userService;
    @InjectMocks
    UserConverter userConverter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAsString() {
        String result = userConverter.getAsString(null, null, new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 8, 17, 10, 15), "language"));
        Assertions.assertEquals("username", result);
    }

    @Test
    void testGetAsObject() throws UserNotFoundException {
        when(userService.getUserByUsername(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 8, 17, 10, 15), "language"));

        User result = userConverter.getAsObject(null, null, "id");
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 8, 17, 10, 15), "language"), result);
    }
}

