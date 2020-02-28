package de.unibremen.sfb.service;

import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*
import de.unibremen.sfb.exception.DuplicateQuantitativeEigenschaftException;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testng.annotations.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QuantitativeEigenschaftServiceTest {

    @Mock
    QuantitativeEigenschaftDAO quantitativeEigenschaftDAO;

    @Mock
    QuantitativeEigenschaft quantitativeEigenschaft;

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
        when(quantitativeEigenschaftDAO.findQnEById(1)).thenReturn(quantitativeEigenschaft);
    }


    @Test()
    public void testgetQuantitativeEingeschaft() {
        quantitativeEigenschaftService.addQuantitativeEigenschaft(quantitativeEigenschaft);
        assertEquals( quantitativeEigenschaft,quantitativeEigenschaftService.getQlEById(1));
        assertEquals(quantitativeEigenschaftService.getAllQuantitativeEigenschaften(), new ArrayList<QuantitativeEigenschaft>());
        assertEquals(quantitativeEigenschaftService.getEinheiten(), List.of("second", "metre", "kilogram", "kilogram", "ampere", "mole", "candela"));
    }

    //@Test
    public void testPErsistenceQuantitativeEigeschaft() throws DuplicateQuantitativeEigenschaftException {
        //verify(quantitativeEigenschaftDAO,Haufigkeit).persist(quantitativeEigenschaft);
        //quantitativeEigenschaftService.addQuantitativeEigenschaft(quantitativeEigenschaft);
        //verify(quantitativeEigenschaftDAO).persist(any());
    }
*/

import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class QuantitativeEigenschaftServiceTest {
    @Mock
    QuantitativeEigenschaftDAO quantitativeEigenschaftDAO;

    @Mock
    QuantitativeEigenschaft quantitativeEigenschaft;

    @InjectMocks
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    @BeforeMethod(alwaysRun=true)
    public void injectDoubles() {
        MockitoAnnotations.initMocks(this); //This could be pulled up into a shared base class
        when(quantitativeEigenschaftDAO.getAll()).thenReturn(new ArrayList<QuantitativeEigenschaft>());
        when(quantitativeEigenschaftDAO.findQnEById(1)).thenReturn(quantitativeEigenschaft);
        when(quantitativeEigenschaftDAO.findQnEById(1)).thenReturn(quantitativeEigenschaft);
    }

    @Test
    public void testgetQuantitativeEingeschaft() {
        quantitativeEigenschaftService.addQuantitativeEigenschaft(quantitativeEigenschaft);
        Assert.assertEquals( quantitativeEigenschaft,quantitativeEigenschaftService.getQlEById(1));
        Assert.assertEquals(quantitativeEigenschaftService.getAllQuantitativeEigenschaften(), new ArrayList<QuantitativeEigenschaft>());
        Assert.assertEquals(quantitativeEigenschaftService.getEinheiten(), List.of("second", "metre", "kilogram", "kilogram", "ampere", "mole", "candela"));

    }

}