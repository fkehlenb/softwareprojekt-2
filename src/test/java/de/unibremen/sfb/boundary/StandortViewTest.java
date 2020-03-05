package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.StandortService;
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

class StandortViewTest {
    @Mock
    StandortService standortService;
    @Mock
    Logger log;
    @InjectMocks
    StandortView standortView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddStandort() throws StandortNotFoundException {
        when(standortService.findByLocation(anyString())).thenReturn(new Standort(0, "ort"));

        standortView.addStandort();
    }

    @Test
    void testUpdateOnRowEdit() throws StandortNotFoundException {
        when(standortService.findById(anyInt())).thenReturn(new Standort(0, "ort"));

        standortView.updateOnRowEdit(0);
    }


    @Test
    void testRemove() throws StandortNotFoundException {
        when(standortService.findById(anyInt())).thenReturn(new Standort(0, "ort"));
        standortView.remove(0);
    }

    @Test
    void testGetAll() {
        when(standortService.getStandorte()).thenReturn(Arrays.<Standort>asList(new Standort(0, "ort")));

        List<Standort> result = standortView.getAll();
        Assertions.assertEquals(Arrays.<Standort>asList(new Standort(0, "ort")), result);
    }

    @Test
    void testSetStandortName() {
        standortView.setStandortName("standortName");
    }
}

