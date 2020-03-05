package de.unibremen.sfb.boundary;

import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class IndexViewTest {
    @Mock
    Subject subject;
    @InjectMocks
    IndexView indexView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetLanguage() {
        indexView.setLanguage("l");
    }

    @Test
    void testGetCurrentLanguage() {
        String result = indexView.getCurrentLanguage();
        Assertions.assertEquals("replaceMeWithExpectedResult", result);
    }
}

