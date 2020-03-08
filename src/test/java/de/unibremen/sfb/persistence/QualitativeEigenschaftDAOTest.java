package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.exception.QualitativeEigenschaftNotFoundException;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class QualitativeEigenschaftDAOTest {
    @Mock
    EntityManager em;
    @InjectMocks
    QualitativeEigenschaftDAO qualitativeEigenschaftDAO;
    @Mock
    QualitativeEigenschaft qualitativeEigenschaft;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void testPersist() throws DuplicateQualitativeEigenschaftException {
        qualitativeEigenschaftDAO.persist(new QualitativeEigenschaft(0, "name"));
    }

    @Test
    void testUpdate() throws QualitativeEigenschaftNotFoundException {
        when(qualitativeEigenschaft.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(qualitativeEigenschaft);
        when(em.contains(qualitativeEigenschaft)).thenReturn(true);
        qualitativeEigenschaftDAO.update(new QualitativeEigenschaft(0, "name"));
    }

    @Test
    void testRemove() throws QualitativeEigenschaftNotFoundException {
        when(qualitativeEigenschaft.getId()).thenReturn(1);
        when(em.find(any(), any())).thenReturn(qualitativeEigenschaft);
        when(em.contains(qualitativeEigenschaft)).thenReturn(true);
        qualitativeEigenschaftDAO.remove(new QualitativeEigenschaft(0, "name"));
    }

    @Test
    void testGet() {
        Class<QualitativeEigenschaft> result = qualitativeEigenschaftDAO.get();
        Assertions.assertEquals(QualitativeEigenschaft.class, result);
    }

    @Test
    void testGetAll() {
        List<QualitativeEigenschaft> result = qualitativeEigenschaftDAO.getAll();
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testGetAllQlEminusQnE() {
        List<QualitativeEigenschaft> result = qualitativeEigenschaftDAO.getAllQlEminusQnE();
        Assertions.assertEquals(Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")), result);
    }

    @Test
    void testGetQlEById() {
        QualitativeEigenschaft result = qualitativeEigenschaftDAO.getQlEById(0);
        Assertions.assertEquals(new QualitativeEigenschaft(0, "name"), result);
    }
}

