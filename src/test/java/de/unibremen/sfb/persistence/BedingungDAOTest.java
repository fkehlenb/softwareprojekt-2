//package de.unibremen.sfb.persistence;
//
//import de.unibremen.sfb.exception.BedingungNotFoundException;
//import de.unibremen.sfb.model.ProzessSchrittParameter;
//import de.unibremen.sfb.model.QualitativeEigenschaft;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.slf4j.Logger;
//
//import javax.persistence.EntityManager;
//import java.util.Arrays;
//import java.util.List;
//
//class BedingungDAOTest {
//    @Mock
//    Logger log;
//    @Mock
//    EntityManager em;
//    @InjectMocks
//    BedingungDAO bedingungDAO;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    void testPersist() {
//        bedingungDAO.persist(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0));
//    }
//
//    @Test
//    void testUpdate() {
//        try {
//            bedingungDAO.update(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0));
//        } catch (BedingungNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testRemove() {
//        try {
//            bedingungDAO.remove(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0));
//        } catch (BedingungNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void testGet() {
//        Class<Bedingung> result = bedingungDAO.get();
//        Assertions.assertEquals(bedingungDAO.getClass(), result);
//    }
//
//    @Test
//    void testGetAll() {
//        List<Bedingung> result = bedingungDAO.getAll();
//        Assertions.assertEquals(Arrays.<Bedingung>asList(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0)), result);
//    }
//
//    @Test
//    void testFindById() {
//        Bedingung result = bedingungDAO.findById(0);
//        Assertions.assertEquals(new Bedingung(0, "name", Arrays.<ProzessSchrittParameter>asList(new ProzessSchrittParameter(0, "name", Arrays.<QualitativeEigenschaft>asList(new QualitativeEigenschaft(0, "name")))), 0), result);
//    }
//}
//
////Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme