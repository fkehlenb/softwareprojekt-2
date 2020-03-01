package de.unibremen.sfb.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;

import static org.mockito.Mockito.*;

class TransportAuftragTest {
    //Field erstellt of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field abgeholt of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    //Field abgeliefert of type LocalDateTime - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    Standort start;
    @Mock
    Standort ziel;
    @Mock
    Enum<TransportAuftragZustand> zustandsAutomat;
    @InjectMocks
    TransportAuftrag transportAuftrag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSetValidData() {
        transportAuftrag.setValidData(true);
    }

    @Test
    void testSetId() {
        transportAuftrag.setId(0);
    }

    @Test
    void testSetErstellt() {
        transportAuftrag.setErstellt(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 56, 9));
    }

    @Test
    void testSetAbgeholt() {
        transportAuftrag.setAbgeholt(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 56, 9));
    }

    @Test
    void testSetAbgeliefert() {
        transportAuftrag.setAbgeliefert(LocalDateTime.of(2020, Month.FEBRUARY, 29, 1, 56, 9));
    }

    @Test
    void testSetStart() {
        transportAuftrag.setStart(new Standort(0, "ort"));
    }

    @Test
    void testSetZiel() {
        transportAuftrag.setZiel(new Standort(0, "ort"));
    }

    @Test
    void testSetZustandsAutomat() {
        transportAuftrag.setZustandsAutomat(null);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme