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

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    //@PersistenceContext(unitName = "sfb")
    //protected EntityManager em;

    @InjectMocks
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    @BeforeEach
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        //when(quantitativeEigenschaft.isValidData()).thenReturn(true);
        //when(quantitativeEigenschaft.getId()).thenReturn(1);
        //when(quantitativeEigenschaft.getWert()).thenReturn(10);
        //when(quantitativeEigenschaft.getEinheit()).thenReturn("Stones");
        when(quantitativeEigenschaftDAO.getAll()).thenReturn(new ArrayList<QuantitativeEigenschaft>());
        when(quantitativeEigenschaftDAO.findQnEById(1)).thenReturn(quantitativeEigenschaft);
    }

    @Test
    public void testgetQuantitativeEingeschaft() {
        assertEquals(quantitativeEigenschaftService.getQlEById(1), quantitativeEigenschaft);
        assertEquals(quantitativeEigenschaftService.getAllQuantitativeEigenschaften(), new ArrayList<QuantitativeEigenschaft>());

    }


}