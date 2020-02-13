package de.unibremen.sfb.service;


import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.exception.QualitativeEigenschaftNotFoundException;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QualitativeEigenschaftDAO;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Slf4j
public class QuantitativeEigenschaftService implements Serializable {
    @Inject
    QuantitativeEigenschaftDAO qneDAO;

    public void addQuantitativeEigenschaft(QuantitativeEigenschaft quantitativeEigenschaft) {
        try {
            log.info("Trying Persis QuantitativeEigenschaft");
            qneDAO.persist(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED Persis QuantitativeEigenschaft");
        }
    }

    public List<QuantitativeEigenschaft> getAllQuantitativeEigenschaften() {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = getAll");
            return qneDAO.getAll();
        } catch (Exception e) {
            return null;
        }
    }

    public void remove(QuantitativeEigenschaft quantitativeEigenschaft) {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = remove");
            qneDAO.remove(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QuantitativeEigenschaft Methode = remove");
        }
    }

    public void edit(QuantitativeEigenschaft quantitativeEigenschaft)  {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = edit");
            qneDAO.update(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QuantitativeEigenschaft Methode = edit");
        }
    }

    public QuantitativeEigenschaft getQlEById(int parseInt) {
        return qneDAO.findQnEById(parseInt);
    }
}
