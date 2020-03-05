package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class ExperimentierStationDAOTest {
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    ExperimentierStationDAO experimentierStationDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateExperimentierStationException {
        experimentierStationDAO.persist(new ExperimentierStation());
    }

    @Test
    void testUpdate() throws ExperimentierStationNotFoundException {
        experimentierStationDAO.update(new ExperimentierStation());
    }

    @Test
    void testRemove() throws ExperimentierStationNotFoundException {
        experimentierStationDAO.remove(new ExperimentierStation());
    }

    @Test
    void testGet() {
        Class<ExperimentierStation> result = experimentierStationDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetObjById() throws ExperimentierStationNotFoundException {
        ExperimentierStation result = experimentierStationDAO.getObjById(0);
        Assertions.assertEquals(new ExperimentierStation(), result);
    }

    @Test
    void testGetAll() {
        List<ExperimentierStation> result = experimentierStationDAO.getAll();
        Assertions.assertEquals(Arrays.<ExperimentierStation>asList(new ExperimentierStation()), result);
    }

    @Test
    void testGetByName() throws ExperimentierStationNotFoundException {
        ExperimentierStation result = experimentierStationDAO.getByName("name");
        Assertions.assertEquals(new ExperimentierStation(), result);
    }
}

