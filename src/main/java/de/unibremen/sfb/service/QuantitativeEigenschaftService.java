package de.unibremen.sfb.service;

import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.persistence.QuantitativeEigenschaftDAO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Slf4j
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class QuantitativeEigenschaftService implements Serializable {



    /** The DAO */
    @Inject
    private QuantitativeEigenschaftDAO qneDAO;

    /**
     * Add a new quantitative descriptor
     * @param quantitativeEigenschaft to be added
     */
    public void addQuantitativeEigenschaft(QuantitativeEigenschaft quantitativeEigenschaft) {
        try {
            log.info("Trying Persis QuantitativeEigenschaft");
            qneDAO.persist(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED Persis QuantitativeEigenschaft");
        }
    }

    /**
     * Get all quantitative descriptors in the database
     * @return all Descriptors
     */
    public List<QuantitativeEigenschaft> getAllQuantitativeEigenschaften() {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = getAll");
            return qneDAO.getAll();
        } catch (Exception e) {
            //TODO LOG
            return null;
        }
    }

    /**
     *  Remove a quantitative descriptor from the database
     * @param quantitativeEigenschaft the one to be removed
     */
    public void remove(QuantitativeEigenschaft quantitativeEigenschaft) {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = remove");
            qneDAO.remove(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QuantitativeEigenschaft Methode = remove");
        }
    }

    /**
     * Edit a quantitative descriptor in the database
     * @param quantitativeEigenschaft to be deleted
     */
    public void edit(QuantitativeEigenschaft quantitativeEigenschaft)  {
        try {
            log.info("Trying QuantitativeEigenschaft Methode = edit");
            qneDAO.update(quantitativeEigenschaft);
        } catch (Exception e) {
            log.info("FAILED QuantitativeEigenschaft Methode = edit");
        }
    }

    /**
     * Alle SI einheiten
     * @return all SI Units
     */
    public List<String> getEinheiten() {
        return List.of("second", "metre", "kilogram", "kilogram", "ampere", "mole", "candela");
    }

    /**
     * Get a quantitative descriptor using its id
     * @param parseInt the id
     * @return the Descriptor which matches the id
     */
    public QuantitativeEigenschaft getQlEById(int parseInt) {
        return qneDAO.findQnEById(parseInt);
    }
}
