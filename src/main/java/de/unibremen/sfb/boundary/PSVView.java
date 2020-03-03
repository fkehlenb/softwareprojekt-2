package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import de.unibremen.sfb.service.ProzessSchrittZustandsAutomatVorlageService;
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
import java.util.List;
import java.util.UUID;


@Named("psvErstellenBean")
@RequestScoped
@Getter
@Setter
@Slf4j
@Transactional
public class PSVView implements Serializable {

    /**
     * Process step parameter service
     */
    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    /**
     * Available process step parameters
     */
    private List<ProzessSchrittParameter> availableProzessSchrittParameterList;

    /**
     * Process step parameters
     */
    private List<ProzessSchrittParameter> selectedProzessSchrittParameterList;

    /**
     * Selected process step name
     */
    private String selectedName;

    /**
     * Selected process step duration
     */
    private String selectedDuration;

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * Available experimenting stations
     */
    private List<ExperimentierStation> availableExperimentierStationList;

    /**
     * Selected experimenting station
     */
    private ExperimentierStation selectedExperimentierStation;

    /**
     * Process step state automaton template service
     */
    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    /**
     * List of all available process step state automaton templates
     */
    private List<ProzessSchrittZustandsAutomatVorlage> availableProzessSchrittZustandsAutomatVorlageList;

    /**
     * Selected process step state automaton template
     */
    private ProzessSchrittZustandsAutomatVorlage selectedProzessSchrittZustandsAutomatVorlage;

    /**
     * Process step template service
     */
    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    /** List of all available process step templates */
    private List<ProzessSchrittVorlage> availableProzessSchrittVorlageList;

    /**
     * Init called on bean initialization
     */
    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * Refresh lists
     */
    private void refresh() {
        availableProzessSchrittParameterList = prozessSchrittParameterService.getAll();
        availableExperimentierStationList = experimentierStationService.getAll();
        availableProzessSchrittZustandsAutomatVorlageList = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        availableProzessSchrittVorlageList = prozessSchrittVorlageService.getVorlagen();
    }

    /**
     * Add a new process step template
     */
    public void createPSV() {
        try {
            prozessSchrittVorlageService.persist(new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), selectedProzessSchrittParameterList,
                    selectedExperimentierStation, selectedDuration, selectedName, selectedProzessSchrittZustandsAutomatVorlage));
            log.info("Created new process step template with name " + selectedName);
            facesNotification("Created new process step template with name " + selectedName);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to create new process step template! Error " + e.getMessage());
            facesError("Failed to create new process step template!");
        }
    }

    /**
     * Edit an existing process step template
     *
     * @param id - the id of the process step template to edit
     */
    public void onRowEdit(int id) {
        try {
            ProzessSchrittVorlage prozessSchrittVorlage = prozessSchrittVorlageService.getByID(id);
            prozessSchrittVorlage.setName(selectedName);
            prozessSchrittVorlage.setDauer(selectedDuration);
            prozessSchrittVorlage.setZustandsAutomatVorlage(selectedProzessSchrittZustandsAutomatVorlage);
            prozessSchrittVorlage.setExperimentierStation(selectedExperimentierStation);
            prozessSchrittVorlage.setProzessSchrittParameters(selectedProzessSchrittParameterList);
            prozessSchrittVorlageService.edit(prozessSchrittVorlage);
            log.info("Updated process step template with id " + id);
            facesNotification("Updated process step template successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to edit process step template with id " + id + " Error " + e.getMessage());
            facesError("Failed to edit process step template!");
        }
    }

    /** Remove a process step template
     * @param id - the id of the process step template to remove */
    public void removePSV(int id){
        try {
            prozessSchrittVorlageService.remove(prozessSchrittVorlageService.getByID(id));
            log.info("Removed process step template with id " + id);
            facesNotification("Removed process step template!");
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to remove process step template with id " + id + " Error " + e.getMessage());
            facesError("Failed to remove process step template!");
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
