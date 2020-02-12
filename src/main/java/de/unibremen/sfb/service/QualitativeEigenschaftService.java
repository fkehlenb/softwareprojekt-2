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
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Slf4j
public class QualitativeEigenschaftService implements Serializable {
    List<QualitativeEigenschaft> eigenschaften;

    @Inject
    QualitativeEigenschaftDAO qeDAO;

    @PostConstruct
    public void init() {
        eigenschaften = qeDAO.getAll();
    }

    public void addQualitativeEigenschaft(QualitativeEigenschaft qualitativeEigenschaft) throws DuplicateQualitativeEigenschaftException {
        try {
            log.info("Trying Persis QualitativeEigenschaft");
            qeDAO.persist(qualitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED Persis QualitativeEigenschaft");
        }
    }

    public List<QualitativeEigenschaft> getAllQualitativeEigenschaften() {
        try {
            log.info("Trying QualitativeEigenschaft Methode = getAll");
            return qeDAO.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public void remove(QualitativeEigenschaft qualitativeEigenschaft) throws QualitativeEigenschaftNotFoundException {
        try {
            log.info("Trying QualitativeEigenschaft Methode = remove");
            qeDAO.remove(qualitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QualitativeEigenschaft Methode = remove");
        }
    }

    public void edit(QualitativeEigenschaft qualitativeEigenschaft) throws QualitativeEigenschaftNotFoundException {
        try {
            log.info("Trying QualitativeEigenschaft Methode = edit");
            qeDAO.update(qualitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QualitativeEigenschaft Methode = edit");
        }
    }

    public QualitativeEigenschaft getQlEById(int id) throws QualitativeEigenschaftNotFoundException {
        return qeDAO.getQlEById(id);
    }
}
