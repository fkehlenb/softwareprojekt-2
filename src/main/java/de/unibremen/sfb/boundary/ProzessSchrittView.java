package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
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
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("psView")
@RequestScoped
@Getter
@Setter
@Slf4j
public class ProzessSchrittView implements Serializable {


    /**
     * Process step template service
     */
    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    /**
     * Process step service
     */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    /**
     * Process step log service
     */
    @Inject
    private ProzessSchrittLogService prozessSchrittLogService;

    /**
     * Process step state automaton template service
     */
    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    /**
     * Process step state automaton service
     */
    @Inject
    private ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * List of all process step templates
     */
    private List<ProzessSchrittVorlage> prozessSchrittVorlagen;

    /**
     * Selected process step template
     */
    private ProzessSchrittVorlage selectedProzessSchrittVorlage;

    /**
     * All process steps in the system
     */
    private List<ProzessSchritt> prozessSchritte;

    /**
     * Process step attributes
     */
    private String prozessSchrittAttribute;

    /**
     * Process step name
     */
    private String prozessSchrittName;

    /**
     * List of all process step state automaton templates
     */
    private List<ProzessSchrittZustandsAutomatVorlage> prozessSchrittZustandsAutomatVorlagen;

    /**
     * Selected process step state automaton template
     */
    private ProzessSchrittZustandsAutomatVorlage selectedProzessSchrittZustandsAutomatVorlage;

    /**
     * List of all experimenting stations
     */
    private List<ExperimentierStation> experimentierStationen;

    /**
     * Process step parameter service
     */
    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    /**
     * List of all available process parameters
     */
    private List<ProzessSchrittParameter> availableProzessSchrittParameter;

    /**
     * List of all selected process step parameters
     */
    private List<ProzessSchrittParameter> selectedProzessSchrittParameter;

    /**
     * Process step duration
     */
    private String psDuration;

    /**
     * Experimenting station
     */
    private ExperimentierStation experimentierStation;

    /**
     * Urformend
     */
    private boolean urformend = false;

    /**
     * If urformend, amount of created samples
     */
    private int amountCreated = 0;

    /**
     * Container type service
     */
    @Inject
    private TraegerArtService traegerArtService;

    /**
     * Available container types
     */
    private List<String> availableTraegerArten;

    /**
     * Selected input container types
     */
    private List<String> selectedInputTraegerArten;

    /**
     * Selected output container types
     */
    private List<String> selectedOutputTraegerArten;

    /**
     * Init called on bean initialization
     */
    @PostConstruct
    private void init() {
        refetchData();
    }

    /**
     * Update all lists
     */
    private void refetchData() {
        prozessSchrittVorlagen = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        prozessSchritte = prozessSchrittService.getAll();
        prozessSchrittZustandsAutomatVorlagen = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        experimentierStationen = experimentierStationService.getAll();
        availableProzessSchrittParameter = prozessSchrittParameterService.getAll();
        availableTraegerArten = traegerArtService.getAll().get(0).getArten();
    }

    public ExperimentierStation getES(int id) {
        ExperimentierStation r = null;
        try {
            var ps = prozessSchrittService.getObjById(id);
            r = experimentierStationService.getESfromPS(ps);
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
        }
        return r;
    }

    public void setES(int esID, int psID) {
        ExperimentierStation es = null;
        ProzessSchritt ps = null;
        try {
            es = experimentierStationService.getById(esID);
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
            log.error("Could not find ES: " + esID);
        }
        try {
            ps = prozessSchrittService.getObjById(psID);
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
            log.error("Could not find ps: " + psID);
        }
        try {
            experimentierStationService.setES(ps, es);
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
            log.error("could not set es of ps");
        }
    }

    /**
     * Create a new process step
     */
    public void createPS() {
        try {
            int id = selectedProzessSchrittVorlage.getPsVID();
            ProzessSchrittVorlage prozessSchrittVorlage = prozessSchrittVorlageService.getByID(id);
            List<String> zustaende = new ArrayList<>();
            for (String s : prozessSchrittVorlage.getZustandsAutomatVorlage().getZustaende()) {
                zustaende.add(s);
            }
            ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(),
                    prozessSchrittVorlage.getZustandsAutomatVorlage().getZustaende().get(0), zustaende);
            prozessSchrittZustandsAutomat.setName(prozessSchrittVorlage.getZustandsAutomatVorlage().getName());
            ProzessSchrittLog prozessSchrittLog = new ProzessSchrittLog(LocalDateTime.now(), "ERSTELLT");
            List<ProzessSchrittLog> psLog = new ArrayList<>();
            psLog.add(prozessSchrittLog);
            ProzessSchritt prozessSchritt = new ProzessSchritt(UUID.randomUUID().hashCode(), prozessSchrittZustandsAutomat,
                    prozessSchrittVorlage.getDauer(), List.copyOf(prozessSchrittVorlage.getProzessSchrittParameters())
                    , prozessSchrittAttribute, psLog, prozessSchrittName, urformend, amountCreated);
            prozessSchritt.setEingabe(List.copyOf(prozessSchrittVorlage.getEingabeTraeger()));
            prozessSchritt.setAusgabe(List.copyOf(prozessSchrittVorlage.getAusgabeTraeger()));
            prozessSchritt.setCreatedName(prozessSchrittVorlage.getNameOfCreated());
            setES(prozessSchrittVorlage.getExperimentierStation().getEsID(), prozessSchritt.getId());
            prozessSchrittZustandsAutomatService.add(prozessSchrittZustandsAutomat);
            prozessSchrittLogService.add(prozessSchrittLog);
            prozessSchrittService.createPS(prozessSchritt);
            log.info("Created new process step with name " + prozessSchrittName);
            facesNotification("Created new process step with name " + prozessSchrittName);
            refetchData();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't create new process step! Error " + e.getMessage());
            facesError("Couldn't create new process step! Error " + e.getMessage());
        }
    }

    /**
     * On row edit
     *
     * @param id - the id of the process step to edit
     */
    public void onRowEdit(int id) {
        try {
            ProzessSchritt prozessSchritt = prozessSchrittService.getObjById(id);
            if (prozessSchritt.getProzessSchrittZustandsAutomat().getCurrent().equals("Erstellt")) {
                prozessSchritt.setName(prozessSchrittName);
                prozessSchritt.setDuration(psDuration);
                List<String> zustaende = new ArrayList<>();
                for (String s : selectedProzessSchrittZustandsAutomatVorlage.getZustaende()) {
                    zustaende.add(s);
                }
                ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(),
                        selectedProzessSchrittZustandsAutomatVorlage.getZustaende().get(0), zustaende);
                prozessSchrittZustandsAutomat.setName(selectedProzessSchrittZustandsAutomatVorlage.getName());
                prozessSchritt.setProzessSchrittZustandsAutomat(prozessSchrittZustandsAutomat);
                setES(experimentierStation.getEsID(), id);
                prozessSchritt.setAttribute(prozessSchrittAttribute);
                prozessSchritt.setUrformend(urformend);
                prozessSchritt.setAmountCreated(amountCreated);
                prozessSchritt.setProzessSchrittParameters(List.copyOf(selectedProzessSchrittParameter));
                if (selectedInputTraegerArten == null){
                    selectedInputTraegerArten = new ArrayList<>();
                }
                if (selectedOutputTraegerArten == null){
                    selectedOutputTraegerArten = new ArrayList<>();
                }
                prozessSchritt.setEingabe(selectedInputTraegerArten);
                prozessSchritt.setAusgabe(selectedOutputTraegerArten);
                prozessSchrittZustandsAutomatService.add(prozessSchrittZustandsAutomat);
                prozessSchrittService.editPS(prozessSchritt);
                log.info("Updated process step with id " + id);
                facesNotification("Successfully updated process step!");
                refetchData();
            } else {
                facesError("Cannot edit an already started process step!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to edit process step with id " + id + " Error " + e.getMessage());
            facesError("Failed to edit process step!");
        }
    }

    /**
     * Row edit canceled
     */
    public void onRowEditCancelled() {
        facesNotification("Cancelled edit!");
    }

    /**
     * Remove a process step
     *
     * @param id - the id of the process step to remove
     */
    public void removePS(int id) {
        try {
            prozessSchrittService.removePS(prozessSchrittService.getObjById(id));
            log.info("Deleted process step with id " + id);
            facesNotification("Successfully deleted process step!");
            refetchData();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to remove process step with id " + id + " Error " + e.getMessage());
            facesError("Failed to remove process step!");
        }
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
