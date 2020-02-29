package de.unibremen.sfb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class ArchivTest {
    @Mock
    Auftrag auftrag;
    //Field datum of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @InjectMocks
    Archiv archiv;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        archiv.setValidData(true);
    }

    @Test
    void testSetId() {
        archiv.setId(0);
    }

    @Test
    void testSetAuftrag() {
        archiv.setAuftrag(new Auftrag());
    }

    @Test
    void testSetDatum() {
        archiv.setDatum(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 48, 34));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme