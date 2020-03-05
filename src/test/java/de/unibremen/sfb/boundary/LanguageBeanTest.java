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
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Map;

import static org.mockito.Mockito.*;

class LanguageBeanTest {
    @Mock
    UserService userService;
    @Mock
    Map<String, Object> countries;
    @Mock
    Logger log;
    @InjectMocks
    LanguageBean languageBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetLocaleCode() throws UserNotFoundException {
        when(userService.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 31, 24), "language"));

        String result = languageBean.getLocaleCode();
        Assertions.assertEquals("language", result);
    }

    @Test
    void testSetLocaleCode() throws UserNotFoundException {
        when(userService.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 31, 24), "language"));

        languageBean.setLocaleCode("localeCode");
    }



    @Test
    void testSetUserService() {
        languageBean.setUserService(new UserService());
    }

    @Test
    void testSetUserID() {
        languageBean.setUserID(0);
    }
}

