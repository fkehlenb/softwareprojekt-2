package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Named
@RequestScoped
@Slf4j
@Getter
@Setter
public class QlEView implements Serializable {

    /**
     * Qualitative descriptor service
     */
    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    /**
     * Quantitative descriptor service
     */
    @Inject
    private QuantitativeEigenschaftService quantitativeEigenschaftService;

    /**
     * List of all available qualitative descriptors
     */
    private List<QualitativeEigenschaft> availableQualitativeEigenschaften;

    /**
     * List of all quantitative descriptors
     */
    private List<QuantitativeEigenschaft> availableQuantitativeEigenschaften;

    /**
     * Selected qualitative descriptor name
     */
    private String selectedQualitativeName;

    /**
     * Selected quantitative descriptor name
     */
    private String selectedQuantitativeName;

    /**
     * Quantitative descriptor value
     */
    private String value;

    /**
     * Quantitative descriptor measurement
     */
    private String measurement;

    /**
     * Init called on start
     */
    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * refresh data
     */
    private void refresh() {
        availableQualitativeEigenschaften = qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        availableQuantitativeEigenschaften = quantitativeEigenschaftService.getAllQuantitativeEigenschaften();
    }

    /**
     * Add a new qualitative descriptor
     */
    public void addQualitative() {
        try {
            qualitativeEigenschaftService.addQualitativeEigenschaft(new QualitativeEigenschaft(UUID.randomUUID().hashCode(), selectedQualitativeName));
            log.info("Created new qualitative descriptor with name " + selectedQualitativeName);
            facesNotification("Created new qualitative descriptor with name " + selectedQualitativeName);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to create new qualitative descriptor with name " + selectedQualitativeName);
            facesError("Failed to create new qualitative descriptor with name " + selectedQualitativeName);
        }
    }

    /**
     * Add a new quantitative descriptor
     */
    public void addQuantitative() {
        try {
            quantitativeEigenschaftService.addQuantitativeEigenschaft(new QuantitativeEigenschaft(UUID.randomUUID().hashCode(), selectedQuantitativeName, Integer.parseInt(value), measurement));
            log.info("Created new quantitative descriptor with name " + selectedQuantitativeName);
            facesNotification("Created new quantitative descriptor with name " + selectedQuantitativeName);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to create new quantitative descriptor with name " + selectedQuantitativeName);
            facesError("Failed to create new quantitative descriptor with name " + selectedQuantitativeName);
        }
    }

    /**
     * Update a qualitative descriptor
     *
     * @param id - the id of the qualitative descriptor to edit
     */
    public void editQualitative(int id) {
        try {
            QualitativeEigenschaft q = qualitativeEigenschaftService.getQlEById(id);
            q.setName(selectedQualitativeName);
            qualitativeEigenschaftService.edit(q);
            log.info("Edited qualitative descriptor with id " + id);
            facesNotification("Edited qualitative descriptor!");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to edit qualitative descriptor with id " + id);
            facesError("Failed to edit qualitative descriptor!");
        }
    }

    /**
     * Edit a quantitative descriptor
     *
     * @param id - the id of the quantitative descriptor to edit
     */
    public void editQuantitative(int id) {
        try {
            QuantitativeEigenschaft q = quantitativeEigenschaftService.getQlEById(id);
            q.setName(selectedQuantitativeName);
            q.setWert(Integer.parseInt(value));
            q.setEinheit(measurement);
            quantitativeEigenschaftService.edit(q);
            log.info("Edited quantitative descriptor with id " + id);
            facesNotification("Edited quantitative descriptor!");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to edit quantitative descriptor with id " + id);
            facesError("Failed to edit quantitative descriptor!");
        }
    }

    /**
     * Remove a qualitative descriptor
     *
     * @param id - the id of the qualitative descriptor to remove
     */
    public void removeQualitative(int id) {
        try {
            qualitativeEigenschaftService.remove(qualitativeEigenschaftService.getQlEById(id));
            log.info("Removed qualitative descriptor with id " + id);
            facesNotification("Removed qualitative descriptor!");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to remove qualitative descriptor with id " + id);
            facesError("Failed to remove qualitative descriptor!");
        }
    }

    /**
     * Remove a quantitative descriptor
     *
     * @param id - the id of the quantitative descriptor to remove
     */
    public void removeQuantitative(int id) {
        try {
            quantitativeEigenschaftService.remove(quantitativeEigenschaftService.getQlEById(id));
            log.info("Removed quantitative descriptor with id " + id);
            facesNotification("Removed quantitative descriptor!");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to remove quantitative descriptor with id " + id);
            facesError("Failed to remove quantitative descriptor!");
        }
    }

    /**
     * Row edit canceled
     */
    public void onRowEditCancelled() {
        facesNotification("Cancelled!");
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
