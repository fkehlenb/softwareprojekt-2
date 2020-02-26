package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.model.Archiv;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.persistence.ArchivDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import javax.persistence.PersistenceContext;

public class ArchivDAOTest {

//    @PersistenceContext
//    private ArchivDAO dao;
//
//    @Test
//    public void testDAO(){
//        Auftrag auftrag = new Auftrag();
//        Archiv a = new Archiv(auftrag);
//        try {
//            System.out.println(a);
//            dao.persist(a);
//            Archiv b = dao.getObjById(a.getId());
//            Assertions.assertEquals(a,b);
//            dao.remove(a);
//        }
//        catch (Exception e){
//            e.printStackTrace();
//            throw new IllegalArgumentException();
//        }
//    }
}
