package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.exception.QualitativeEigenschaftNotFoundException;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.QualitativeEigenschaftDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Slf4j
@Singleton
public class QualitativeEigenschaftService implements Serializable {

    /** List of all qualitative descriptors in the database */
    private List<QualitativeEigenschaft> eigenschaften;

    /** The DAO */
    @Inject
    private QualitativeEigenschaftDAO qeDAO;

    /** Init on start */
    @PostConstruct
    public void init() {
        eigenschaften = qeDAO.getAll();
    }

    /** Add a new qualitative descriptor */
    public void addQualitativeEigenschaft(QualitativeEigenschaft qualitativeEigenschaft) {
        try {
            log.info("Trying Persis QualitativeEigenschaft");
            qeDAO.persist(qualitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED Persis QualitativeEigenschaft");
        }
    }

    /** Get all qualitative descriptors from the database */
    public List<QualitativeEigenschaft> getAllQualitativeEigenschaften() {
        try {
            log.info("Trying QualitativeEigenschaft Methode = getAll");
            return qeDAO.getAllQlEminusQnE();
        } catch (Exception e) {
            //TODO LOG
            return new ArrayList<QualitativeEigenschaft>();
        }
    }

    /** Remove a qualitative descriptor from the database */
    public void remove(QualitativeEigenschaft qualitativeEigenschaft) {
        try {
            log.info("Trying QualitativeEigenschaft Methode = remove");
            qeDAO.remove(qualitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QualitativeEigenschaft Methode = remove");
        }
    }

    /** Edit a qualitative descriptor in the database */
    public void edit(QualitativeEigenschaft qualitativeEigenschaft) {
        try {
            log.info("Trying QualitativeEigenschaft Methode = edit");
            qeDAO.update(qualitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QualitativeEigenschaft Methode = edit");
        }
    }

    /** Get a qualitative descriptor using its id */
    public QualitativeEigenschaft getQlEById(int id) {
        return qeDAO.getQlEById(id);
    }
}
