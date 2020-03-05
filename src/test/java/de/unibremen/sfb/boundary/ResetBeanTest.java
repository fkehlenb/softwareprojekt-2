package de.unibremen.sfb.boundary;

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

class ResetBeanTest {
    @Mock
    UserService userService;
    @InjectMocks
    ResetBean resetBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testResetByUsername() throws UserNotFoundException {
        when(userService.getUserByUsername(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 37, 55), "language"));

        resetBean.resetByUsername();
    }

    @Test
    void testResetByEmail() throws UserNotFoundException {
        when(userService.getUserByEmail(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 37, 55), "language"));

        resetBean.resetByEmail();
    }

    @Test
    void testSetLanguage() {
        resetBean.setLanguage("l");
    }

    @Test
    void testGetCurrentLanguage() {
        String result = resetBean.getCurrentLanguage();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testSetUsername() {
        resetBean.setUsername("username");
    }

    @Test
    void testSetEmail() {
        resetBean.setEmail("email");
    }
}

