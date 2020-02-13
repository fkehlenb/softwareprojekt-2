package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.QualitativeEigenschaftDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.List;

/**
 * this class manages the interaction with models of quality properties
 */

@Slf4j
public class QualitativeEigenschaftController {
    /**
     * the quality properity managed by this controller
     */

    @Inject
    private QualitativeEigenschaftDAO qualitativeEigenschaftDAO;

    private QualitativeEigenschaft qualitativeEigenschaft;

    @Setter
    @Getter
    private String name;

    /**
     * sets the property name
     * @param s the new name
     */
    public void setEigenschaft(String s) {
        this.name=name;
    }

    /**
     * returns the property name
     * @return the property name
     */
    public List<QualitativeEigenschaft> getEigenschaft() {
        List<QualitativeEigenschaft> qualitativeEigenschafts = qualitativeEigenschaftDAO.getAll();
        return qualitativeEigenschafts;
    }
}
