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
    User user;
    @Mock
    Enum<TransportAuftragZustand> zustandsAutomat;
    @Mock
    Standort start;
    @Mock
    Standort ziel;
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
        transportAuftrag.setErstellt(LocalDateTime.of(2020, Month.MARCH, 5, 16, 49, 47));
    }

    @Test
    void testSetAbgeholt() {
        transportAuftrag.setAbgeholt(LocalDateTime.of(2020, Month.MARCH, 5, 16, 49, 47));
    }

    @Test
    void testSetAbgeliefert() {
        transportAuftrag.setAbgeliefert(LocalDateTime.of(2020, Month.MARCH, 5, 16, 49, 47));
    }

    @Test
    void testSetUser() {
        transportAuftrag.setUser(new User(0, "vorname", "nachname", "email", "telefonnummer", "username", "password", true, LocalDateTime.of(2020, Month.MARCH, 5, 16, 49, 47), "language"));
    }

    @Test
    void testSetZustandsAutomat() {
        transportAuftrag.setZustandsAutomat(null);
    }

    @Test
    void testSetStart() {
        transportAuftrag.setStart(new Standort(0, "ort"));
    }

    @Test
    void testSetZiel() {
        transportAuftrag.setZiel(new Standort(0, "ort"));
    }
}

