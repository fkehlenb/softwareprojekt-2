package de.unibremen.sfb.service;


import de.unibremen.sfb.model.QuantitativeEigenschaft;

import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
class QuantitativeEigenschaftServiceTest {

    @Mock
    QuantitativeEigenschaftDAO quantitativeEigenschaftDAO;

    @InjectMocks
    private QuantitativeEigenschaft quantitativeEigenschaft;

    @Test
    public void FirstTest() {

        QuantitativeEigenschaft quantitativeEigenschaft = mock(QuantitativeEigenschaft.class);
        when(quantitativeEigenschaft.getWert()).thenReturn(1);
        System.out.println("quantitativeEigenschaft: " + quantitativeEigenschaft.getWert());
        assertEquals(quantitativeEigenschaft.getWert(), 1);
    }

}