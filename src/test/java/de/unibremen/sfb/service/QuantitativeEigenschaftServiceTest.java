package de.unibremen.sfb.service;


import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuantitativeEigenschaftServiceTest {

    @Mock
    QuantitativeEigenschaftDAO quantitativeEigenschaftDAO;

    @Mock
    QuantitativeEigenschaft quantitativeEigenschaft;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(quantitativeEigenschaft.isValidData()).thenReturn(true);
        when(quantitativeEigenschaft.getId()).thenReturn(1);
        when(quantitativeEigenschaft.getWert()).thenReturn(10);
        when(quantitativeEigenschaft.getEinheit()).thenReturn("Stones");



    }

    @Test
    public void TesListQuati() {
        QuantitativeEigenschaft quantitativeEigenschaft = new QuantitativeEigenschaft();
        when(quantitativeEigenschaftDAO.getAll()).thenReturn(new ArrayList<QuantitativeEigenschaft>());
        assertEquals(quantitativeEigenschaftDAO.getAll(), new ArrayList<QuantitativeEigenschaft>());
    }

    @Test
    public void FirstTest() {

        QuantitativeEigenschaft quantitativeEigenschaft = mock(QuantitativeEigenschaft.class);
        when(quantitativeEigenschaft.getWert()).thenReturn(1);
        //quantitativeEigenschaftService.addQuantitativeEigenschaft(quantitativeEigenschaft);
        System.out.println("quantitativeEigenschaft: " + quantitativeEigenschaft.getWert());
        assertEquals(quantitativeEigenschaft.getWert(), 1);
    }
}