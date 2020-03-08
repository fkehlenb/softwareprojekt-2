package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import de.unibremen.sfb.service.StandortService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SListViewTest {
    @Mock
    List<Standort> alleStandorte;
    @Mock
    List<Standort> filteredStandorte;
    @Mock
    List<Standort> selectedStandorte;
    @Mock
    StandortService standortService;
    @Mock
    StandortDAO standortDAO;
    @Mock
    Logger log;
    @InjectMocks
    SListView sListView;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testInit() {
        when(standortDAO.getAll()).thenReturn(Arrays.<Standort>asList(new Standort(0, "ort")));

        sListView.init();
    }

    @Test
    void testDeleteStandorte() {
        when(standortService.getStandorte()).thenReturn(Arrays.<Standort>asList(new Standort(0, "ort")));

        sListView.deleteStandorte();
    }


    @Test
    void testSetAlleStandorte() {
        sListView.setAlleStandorte(Arrays.<Standort>asList(new Standort(0, "ort")));
    }

    @Test
    void testSetFilteredStandorte() {
        sListView.setFilteredStandorte(Arrays.<Standort>asList(new Standort(0, "ort")));
    }

    @Test
    void testSetSelectedStandorte() {
        sListView.setSelectedStandorte(Arrays.<Standort>asList(new Standort(0, "ort")));
    }

    @Test
    void testSetStandortService() {
        sListView.setStandortService(new StandortService());
    }

    @Test
    void testSetStandortDAO() {
        sListView.setStandortDAO(new StandortDAO());
    }
}

