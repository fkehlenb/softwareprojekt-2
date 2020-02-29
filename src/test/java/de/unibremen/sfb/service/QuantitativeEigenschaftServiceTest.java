package de.unibremen.sfb.service;

import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class QuantitativeEigenschaftServiceTest {
    @Mock
    QuantitativeEigenschaftDAO qneDAO;
    @Mock
    Logger log;
    @InjectMocks
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddQuantitativeEigenschaft() {
        quantitativeEigenschaftService.addQuantitativeEigenschaft(new QuantitativeEigenschaft(0, "name"));
    }

    @Test
    void testGetAllQuantitativeEigenschaften() {
        when(qneDAO.getAll()).thenReturn(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")));

        List<QuantitativeEigenschaft> result = quantitativeEigenschaftService.getAllQuantitativeEigenschaften();
        Assertions.assertEquals(Arrays.<QuantitativeEigenschaft>asList(new QuantitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testRemove() {
        quantitativeEigenschaftService.remove(new QuantitativeEigenschaft(0, "name"));
    }

    @Test
    void testEdit() {
        quantitativeEigenschaftService.edit(new QuantitativeEigenschaft(0, "name"));
    }

    @Test
    void testGetEinheiten() {
        List<String> result = quantitativeEigenschaftService.getEinheiten();
        Assertions.assertEquals(Arrays.<String>asList("String"), result);
    }

    @Test
    void testGetQlEById() {
        when(qneDAO.findQnEById(anyInt())).thenReturn(new QuantitativeEigenschaft(0, "name"));

        QuantitativeEigenschaft result = quantitativeEigenschaftService.getQlEById(0);
        Assertions.assertEquals(new QuantitativeEigenschaft(0, "name"), result);
    }

    @Test
    void testSetQneDAO() {
        quantitativeEigenschaftService.setQneDAO(new QuantitativeEigenschaftDAO());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme