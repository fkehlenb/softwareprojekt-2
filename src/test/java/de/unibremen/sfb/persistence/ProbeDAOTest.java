package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.model.TraegerArt;
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

class ProbeDAOTest {
    @Mock
    Logger log;
    @Mock
    EntityManager em;
    @InjectMocks
    ProbeDAO probeDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPersist() throws DuplicateProbeException {
        probeDAO.persist(new Probe("probenID", 0, null, new Standort(0, "ort")));
    }

    @Test
    void testUpdate() throws ProbeNotFoundException {
        probeDAO.update(new Probe("probenID", 0, null, new Standort(0, "ort")));
    }

    @Test
    void testRemove() throws ProbeNotFoundException {
        probeDAO.remove(new Probe("probenID", 0, null, new Standort(0, "ort")));
    }

    @Test
    void testGet() {
        Class<Probe> result = probeDAO.get();
        Assertions.assertEquals(null, result);
    }

    @Test
    void testGetObjById() throws ProbeNotFoundException {
        Probe result = probeDAO.getObjById("id");
        Assertions.assertEquals(new Probe("probenID", 0, null, new Standort(0, "ort")), result);
    }

    @Test
    void testGetProbenByLocation() throws ProbeNotFoundException {
        List<Probe> result = probeDAO.getProbenByLocation(new Standort(0, "ort"));
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetProbenByTraeger() throws ProbeNotFoundException {
        List<Probe> result = probeDAO.getProbenByTraeger(new Traeger(0, "art", Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), new Standort(0, "ort")));
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetProbenCount() {
        int result = probeDAO.getProbenCount();
        Assertions.assertEquals(0, result);
    }

    @Test
    void testGetAllArchived() {
        List<Probe> result = probeDAO.getAllArchived();
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), result);
    }

    @Test
    void testGetAll() {
        List<Probe> result = probeDAO.getAll();
        Assertions.assertEquals(Arrays.<Probe>asList(new Probe("probenID", 0, null, new Standort(0, "ort"))), result);
    }
}

