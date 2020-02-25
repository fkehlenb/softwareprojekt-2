package de.unibremen.sfb.service;

import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mockito.Mock;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import si.uom.SI;
@Setter
@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class QuantitativeEigenschaftService implements Serializable {



    /** The DAO */
    @Inject
    private QuantitativeEigenschaftDAO qneDAO;

    /** Add a new quantitative descriptor */
    public void addQuantitativeEigenschaft(QuantitativeEigenschaft quantitativeEigenschaft) {
        try {
            log.info("Trying Persis QuantitativeEigenschaft");
            qneDAO.persist(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED Persis QuantitativeEigenschaft");
        }
    }

    /** Get all quantitative descriptors in the database */
    public List<QuantitativeEigenschaft> getAllQuantitativeEigenschaften() {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = getAll");
            return qneDAO.getAll();
        } catch (Exception e) {
            //TODO LOG
            return null;
        }
    }

    /** Remove a quantitative descriptor from the database */
    public void remove(QuantitativeEigenschaft quantitativeEigenschaft) {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = remove");
            qneDAO.remove(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QuantitativeEigenschaft Methode = remove");
        }
    }

    /** Edit a quantitative descriptor in the database */
    public void edit(QuantitativeEigenschaft quantitativeEigenschaft)  {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = edit");
            qneDAO.update(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QuantitativeEigenschaft Methode = edit");
        }
    }

    public List<String> getEinheiten() {
        return List.of("second", "metre", "kilogram", "kilogram", "ampere", "mole", "candela");
    }

    /** Get a quantitative descriptor using its id*/
    public QuantitativeEigenschaft getQlEById(int parseInt) {
        return qneDAO.findQnEById(parseInt);
    }
}
