package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class LoggedInBeanTest {
    @Mock
    User user;
    @InjectMocks
    LoggedInBean loggedInBean;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetView() {
        loggedInBean.setView();
    }

    @Test
    void testLogout() {
        loggedInBean.logout();
    }
}

