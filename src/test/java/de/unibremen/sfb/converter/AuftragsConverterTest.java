package de.unibremen.sfb.converter;

import de.unibremen.sfb.model.Auftrag;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

class AuftragsConverterTest {
    AuftragsConverter auftragsConverter = new AuftragsConverter();
    @Mock
    Auftrag auftrag;
    @BeforeEach

    @Test
    void testToString() {
        String result = auftragsConverter.toString();
        Assertions.assertEquals("AuftragConverter{auftragConverter}", result);
    }

    @Test
    void testGetAsString() {
        String result = auftragsConverter.getAsString(null, null, auftrag);
        Assertions.assertEquals("", result);
    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme