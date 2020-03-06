package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Named("dtPSPBean")
@RequestScoped
@Slf4j
@Setter
@Getter
public class PSPView implements Serializable {

    /** Process parameter service */
    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    /** Qualitative Descriptor service */
    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    /** Quantitative descriptor service */
    @Inject
    private QuantitativeEigenschaftService quantitativeEigenschaftService;

    /** List of all available process step parameters */
    private List<ProzessSchrittParameter> availableProzessSchrittParameter;

    /** List of all available qualitative descriptors */
    private List<QualitativeEigenschaft> availableQualitativeEigenschaften;

    /** List of selected qualitative descriptors */
    private List<QualitativeEigenschaft> selectedQualitativeEigenschaften;

    /** List of all available quantitative descriptors */
    private List<QuantitativeEigenschaft> availableQuantitativeEigenschaften;

    /** List of all selected quantitative descriptors */
    private List<QuantitativeEigenschaft> selectedQuantitativeEigenschaften;

    /** Selected name */
    private String selectedName;

    /** Init called on start */
    @PostConstruct
    private void init(){
        refresh();
    }

    /** Reload data */
    private void refresh(){
        availableProzessSchrittParameter = prozessSchrittParameterService.getAll();
        availableQualitativeEigenschaften = qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        availableQuantitativeEigenschaften = quantitativeEigenschaftService.getAllQuantitativeEigenschaften();
    }

    /** Create a new process step parameter */
    public void createPSP(){
        try {
            List<QualitativeEigenschaft> selectedEigenschaften = new ArrayList<>();
            if (selectedQuantitativeEigenschaften == null){
                selectedQuantitativeEigenschaften = new ArrayList<>();
            }
            if (selectedQualitativeEigenschaften == null){
                selectedQualitativeEigenschaften = new ArrayList<>();
            }
            selectedEigenschaften.addAll(selectedQualitativeEigenschaften);
            selectedEigenschaften.addAll(selectedQuantitativeEigenschaften);
            ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter(UUID.randomUUID().hashCode(),selectedName,selectedEigenschaften);
            prozessSchrittParameterService.addProcessSP(prozessSchrittParameter);
            log.info("Created new process parameter with name " + selectedName);
            facesNotification("Created new process parameter with name " + selectedName);
            refresh();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't create new process parameter! Error " + e.getMessage());
            facesError("Couldn't create new process parameter!");
        }
    }

    /** Edit a process step parameter
     * @param id - the id of the process step parameter to edit */
    public void editPSP(int id){
        try {
            ProzessSchrittParameter psp = prozessSchrittParameterService.getPSPByID(id);
            psp.setName(selectedName);
            List<QualitativeEigenschaft> selectedEigenschaften = new ArrayList<>();
            if (selectedQuantitativeEigenschaften == null){
                selectedQuantitativeEigenschaften = new ArrayList<>();
            }
            if (selectedQualitativeEigenschaften == null){
                selectedQualitativeEigenschaften = new ArrayList<>();
            }
            selectedEigenschaften.addAll(selectedQualitativeEigenschaften);
            selectedEigenschaften.addAll(selectedQuantitativeEigenschaften);
            psp.setQualitativeEigenschaften(selectedEigenschaften);
            prozessSchrittParameterService.update(psp);
            log.info("Updated process step parameter with id " + id);
            facesNotification("Updated process step parameter!");
            refresh();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't update process step parameter with id " + id);
            facesError("Couldn't update process step parameter!");
        }
    }

    /** Remove a process step parameter
     * @param id - the id of the process step parameter to remove */
    public void removePSP(int id){
        try{
            prozessSchrittParameterService.loscheParameter(prozessSchrittParameterService.getPSPByID(id));
            log.info("Removed process parameter with id " + id);
            facesNotification("Removed process parameter!");
            refresh();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't remove process step parameter with id " + id);
            facesError("Couldn't remove process parameter!");
        }
    }

    /** Row edit canceled */
    public void onRowEditCanceled(){
        facesNotification("Canceled!");
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
