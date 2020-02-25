package de.unibremen.sfb.service;


import de.unibremen.sfb.model.QuantitativeEigenschaft;

import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
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