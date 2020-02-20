package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.exception.QualitativeEigenschaftNotFoundException;
import de.unibremen.sfb.model.QualitativeEigenschaft;

import java.util.ArrayList;
import java.util.List;

/** This class handles the qualitative descriptor objects in the database */
public class QualitativeEigenschaftDAO extends ObjectDAO<QualitativeEigenschaft> {

    /** Add a qualitative descriptor object to the database
     * @param q - the qualitative descriptor object to add to the database
     * @throws DuplicateQualitativeEigenschaftException if the qualitative descriptor already exists in the database */
    public void persist(QualitativeEigenschaft q) throws DuplicateQualitativeEigenschaftException{
        if (q!=null){
            synchronized (QualitativeEigenschaft.class){
                if (em.contains(q)){
                    throw new DuplicateQualitativeEigenschaftException();
                }
                em.persist(q);
            }
        }
    }

    /** Update a qualitative descriptor object in the database
     * @param q - the qualitative descriptor object to update in the database
     * @throws QualitativeEigenschaftNotFoundException if the qualitative descriptor couldn't be found in the database */
    public void update(QualitativeEigenschaft q) throws QualitativeEigenschaftNotFoundException{
        if (q!=null){
            if (!em.contains(q)){
                throw new QualitativeEigenschaftNotFoundException();
            }
            em.merge(q);
        }
    }

    /** Remove a qualitative descriptor object from the database
     * @param q - the qualitative descriptor object to remove from the database
     * @throws QualitativeEigenschaftNotFoundException if the qualitative descriptor couldn't be found in the database */
    public void remove(QualitativeEigenschaft q) throws QualitativeEigenschaftNotFoundException{
        if (q!=null){
            if (!em.contains(q)){
                throw new QualitativeEigenschaftNotFoundException();
            }
            em.remove(q);
        }
    }

    /** @return the class of qualitative descriptors */
    public Class<QualitativeEigenschaft> get(){
        return QualitativeEigenschaft.class;
    }
    /**
     * @return a list of all qualitative descriptors in the database
     */
    public List<QualitativeEigenschaft> getAll() throws IllegalArgumentException {
        try {
            List<QualitativeEigenschaft> list = em.createQuery("SELECT q FROM QualitativeEigenschaft q", get()).getResultList();
            return list.isEmpty() ? new ArrayList<>() : list;
        } catch (Exception e) {
            throw new IllegalArgumentException("failed!");
        }
    }
    /**
     * @return a list of all qualitative descriptors in the system without Quantitative
     */
    public List<QualitativeEigenschaft> getAllQlEminusQnE() throws IllegalArgumentException {
        try {
            List<QualitativeEigenschaft> list = em.createQuery("SELECT q FROM QualitativeEigenschaft q WHERE NOT EXISTS (select qn FROM QuantitativeEigenschaft qn  where qn.id = q.id)", get()).getResultList();
            return list.isEmpty() ? new ArrayList<>() : list;
        } catch (Exception e) {
            throw new IllegalArgumentException("failed!");
        }
    }

    public QualitativeEigenschaft getQlEById(int QlEId) {
        try {
            return em.find(QualitativeEigenschaft.class, QlEId);
        } catch (Exception e) {
            throw new IllegalArgumentException("QualitativeEigenschaft not found");
        }
    }
}
