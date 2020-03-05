package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.Role;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.RoleDao;
import de.unibremen.sfb.persistence.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    UserDAO userDAO;
    @Mock
    MailingService mailingService;
    @Mock
    ExperimentierStationService experimentierStationService;
    @Mock
    RoleDao roleDao;
    @Mock
    List<User> users;
    @Mock
    List<Role> roles;
    @Mock
    Logger log;
    @Mock
    User user;
    @Mock
    List<ExperimentierStation> experimentierStations;
    @InjectMocks
    UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(userDAO.getAll()).thenReturn(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language")));

        userService.init();
    }

    @Test
    void testAddUser() {
        try {
            userService.addUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (DuplicateUserException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetUserById() {
        try {
            when(userDAO.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        User result = null;
        try {
            result = userService.getUserById(0);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"), result);
    }

    @Test
    void testGetUserByUsername() {
        try {
            when(userDAO.getUserByName(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        User result = null;
        try {
            result = userService.getUserByUsername("username");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"), result);
    }

    @Test
    void testGetUserByEmail() {
        try {
            when(userDAO.getUserByMail(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        User result = null;
        try {
            result = userService.getUserByEmail("email");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"), result);
    }

    @Test
    void testGetAll() {
        when(userDAO.getAll()).thenReturn(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language")));

        List<User> result = userService.getAll();
        Assertions.assertEquals(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language")), result);
    }

    @Test
    void testContainsUser() {
        try {
            when(userDAO.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        boolean result = false;
        result = userService.containsUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        Assertions.assertEquals(true, result);
    }

    @Test
    void testContainsUserWithUsername() {
        try {
            when(userDAO.getUserByName(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        boolean result = userService.containsUserWithUsername("username");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testContainsUserWithEmail() {
        try {
            when(userDAO.getUserByMail(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        boolean result = userService.containsUserWithEmail("email");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testUpdateUser() {
        try {
            userService.updateUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemoveUser() throws UserNotFoundException {
        userService.removeUser(user);
        verify(userDAO).remove(user);
    }

    @Test
    void testRemoveUserById() {
        try {
            when(userDAO.getUserById(anyInt())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        try {
            userService.removeUserById(0);
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemoveUserByUsername() {
        try {
            when(userDAO.getUserByName(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        try {
            userService.removeUserByUsername("username");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testRemoveUserByMail() {
        try {
            when(userDAO.getUserByMail(anyString())).thenReturn(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"));
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }

        try {
            userService.removeUserByMail("email");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetEsByUser() {
        List<ExperimentierStation> result = userService.getEsByUser(user);
        Assertions.assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testSendMail() {
        userService.sendMail(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 30, 1), "language"), "subject", "message");
    }


}