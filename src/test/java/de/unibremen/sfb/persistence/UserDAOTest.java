package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateUserException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class UserDAOTest {
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    UserDAO userDAO;
    @Mock
    User user;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateUserException {
        userDAO.persist(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language"));
    }

    @Test
    void testUpdate() throws UserNotFoundException {
        when(user.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(user);
        when(em.contains(user)).thenReturn(true);
        userDAO.update(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language"));
    }

    @Test
    void testRemove() throws UserNotFoundException {
        when(user.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(user);
        when(em.contains(user)).thenReturn(true);
        userDAO.remove(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language"));
    }

    @Test
    void testGet() {
        Class<User> result = userDAO.get();
        Assertions.assertEquals(User.class, result);
    }

    @Test
    void testGetUserById() throws UserNotFoundException {
        User result = userDAO.getUserById(0);
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language"), result);
    }

    @Test
    void testGetUserByName() throws UserNotFoundException {
        User result = userDAO.getUserByName("n");
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language"), result);
    }

    @Test
    void testGetUserByMail() throws UserNotFoundException {
        User result = userDAO.getUserByMail("m");
        Assertions.assertEquals(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language"), result);
    }

    @Test
    void testGetAll() {
        List<User> result = userDAO.getAll();
        Assertions.assertEquals(Arrays.<User>asList(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 53, 45), "language")), result);
    }
}

