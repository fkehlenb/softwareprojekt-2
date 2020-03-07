package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
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
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;


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

    /** Urformend */
    private boolean urformend = false;

    /** If urformend, amount of created samples */
    @Min(0)
    private int amountCreated = 0;

    /** Container type service */
    @Inject
    private TraegerArtService traegerArtService;

    /** Available container types */
    private List<String> availableTraegerArten;

    /** Selected input container types */
    private List<String> selectedInputTraegerArten;

    /** Selected output container types */
    private List<String> selectedOutputTraegerArten;

    /** Name of created samples */
    private String nameOfCreated = "";

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
        availableProzessSchrittVorlageList = prozessSchrittVorlageService.getAll();
        availableTraegerArten = traegerArtService.getAll().get(0).getArten();
    }

    /**
     * Add a new process step template
     */
    public void createPSV() {
        String pattern = "[A-Z][0-9][0-9].[0-9]+(.[0-9]+)+";
        try {
            List<ProzessSchrittParameter> newPsp = new ArrayList<>(selectedProzessSchrittParameterList);
            ProzessSchrittVorlage psv = new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), newPsp,
                    selectedExperimentierStation, selectedDuration, selectedName, selectedProzessSchrittZustandsAutomatVorlage, urformend, amountCreated);
            if (selectedInputTraegerArten == null) {
                selectedInputTraegerArten = new ArrayList<>();
            }
            if (selectedOutputTraegerArten == null) {
                selectedOutputTraegerArten = new ArrayList<>();
            }

            if (psv.isUrformend()  && !Pattern.matches(pattern, nameOfCreated)) {
                    throw new IllegalArgumentException();
                }
            psv.setEingabeTraeger(selectedInputTraegerArten);
            psv.setAusgabeTraeger(selectedOutputTraegerArten);
            psv.setNameOfCreated(nameOfCreated);
            prozessSchrittVorlageService.persist(psv);
            log.info("Created new process step template with name " + selectedName);
            facesNotification("Created new process step template with name " + selectedName);

        } catch (IllegalArgumentException r) {
            facesError("Proben ID entspricht nicht! " + pattern);
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
        String pattern = "[A-Z][0-9][0-9].[0-9]+(.[0-9]+)+";
        try {
            ProzessSchrittVorlage prozessSchrittVorlage = prozessSchrittVorlageService.getByID(id);
            prozessSchrittVorlage.setName(selectedName);
            prozessSchrittVorlage.setDauer(selectedDuration);
            prozessSchrittVorlage.setZustandsAutomatVorlage(selectedProzessSchrittZustandsAutomatVorlage);
            prozessSchrittVorlage.setExperimentierStation(selectedExperimentierStation);
            List<ProzessSchrittParameter> newPsp = new ArrayList<>(selectedProzessSchrittParameterList);
            prozessSchrittVorlage.setProzessSchrittParameters(newPsp);
            prozessSchrittVorlage.setUrformend(urformend);
            prozessSchrittVorlage.setAmountCreated(amountCreated);
            if (selectedInputTraegerArten == null) {
                selectedInputTraegerArten = new ArrayList<>();
            }
            if (selectedOutputTraegerArten == null) {
                selectedOutputTraegerArten = new ArrayList<>();
            }
            prozessSchrittVorlage.setEingabeTraeger(selectedInputTraegerArten);
            prozessSchrittVorlage.setAusgabeTraeger(selectedOutputTraegerArten);
            prozessSchrittVorlage.setUrformend(urformend);
            if (urformend) {
                boolean regexIsMatching = Pattern.matches(pattern, nameOfCreated);
                if (!regexIsMatching) {
                    new IllegalArgumentException();
                } else {

                prozessSchrittVorlage.setNameOfCreated(nameOfCreated);
                prozessSchrittVorlage.setAmountCreated(amountCreated);
                }
            } else {
                prozessSchrittVorlage.setAmountCreated(0);
                prozessSchrittVorlage.setNameOfCreated("");
            }
            System.out.println(selectedInputTraegerArten.toString());
            prozessSchrittVorlageService.edit(prozessSchrittVorlage);
            log.info("Updated process step template with id " + id);
            facesNotification("Updated process step template successfully!");
            refresh();
        } catch (IllegalArgumentException t) {
            facesError("Proben ID entspricht nicht! " + pattern);
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
            refresh();
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
        refresh();
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
