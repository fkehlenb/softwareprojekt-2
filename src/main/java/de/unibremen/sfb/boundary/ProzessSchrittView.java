package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.service.*;
import de.unibremen.sfb.model.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Named("psView")
@ViewScoped
@Getter
@Setter
@Slf4j
public class ProzessSchrittView implements Serializable {

    /**
     * All process steps
     */
    private List<ProzessSchritt> allePS;

    /**
     * All process step templates
     */
    private List<ProzessSchrittVorlage> allePSV;

    /**
     * Process step service
     */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    /**
     * Process step template service
     */
    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    /**
     * Process step template
     */
    private ProzessSchrittVorlage prozessSchrittVorlage;

    /**
     * List of all available process step templates
     */
    private List<ProzessSchrittVorlage> prozessSchrittVorlages;

    /**
     * Process step state automaton template
     */
    private ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage;

    /**
     * List of all process step state automaton templatese
     */
    private List<ProzessSchrittZustandsAutomatVorlage> prozessSchrittZustandsAutomatVorlages;

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
     * Process step template duration
     */
    private String psDauer;

    /**
     * Experimenting station
     */
    private ExperimentierStation experimentierStation;

    /**
     * List of all available experimenting stations
     */
    private List<ExperimentierStation> experimentierStations;

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * Process steps displayed in the table
     */
    private List<ProzessSchrittV2> prozessSchrittV2s;

    /**
     * Process step service
     */
    @Inject
    private ProzessSchrittV2Service prozessSchrittV2Service;

    /** Process parameter service */
    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    /** List of all process parameters */
    private List<ProzessSchrittParameter> prozessSchrittParameters;

    /** Process step log service */
    @Inject
    private ProzessSchrittLogService prozessSchrittLogService;

    /**
     * Process step name
     */
    private String prozessSchrittName;

    /**
     * Process step parameters
     */
    private ProzessSchrittParameter prozessSchrittParameter;

    /**
     * Process step attributes
     */
    private String attributes;

    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        allePS = prozessSchrittService.getAll();
        allePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        prozessSchrittVorlages = prozessSchrittVorlageService.getVorlagen();
        prozessSchrittZustandsAutomatVorlages = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        experimentierStations = experimentierStationService.getAll();
        prozessSchrittV2s = prozessSchrittV2Service.getAll();
        prozessSchrittParameters = prozessSchrittParameterService.getAll();
    }

    /**
     * Create a new process step
     */
    public void createPS() {
        try {
            ProzessSchrittLog prozessSchrittLog = new ProzessSchrittLog(LocalDateTime.now(), "ERSTELLT");
            ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(), "Erstellt", prozessSchrittZustandsAutomatVorlage);
            prozessSchrittZustandsAutomatService.add(prozessSchrittZustandsAutomat);
            prozessSchrittLogService.add(prozessSchrittLog);
            ProzessSchrittV2 prozessSchrittV2 = new ProzessSchrittV2(UUID.randomUUID().hashCode(), prozessSchrittName, experimentierStation, psDauer, prozessSchrittZustandsAutomat, prozessSchrittParameter, prozessSchrittLog, attributes);
            prozessSchrittV2Service.add(prozessSchrittV2);
            log.info("Added new process step!");
            facesNotification("Added new process step!");
            prozessSchrittV2s = prozessSchrittV2Service.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to create new process step! Error " + e.getMessage());
            facesError("Failed to create new process step! Error " + e.getMessage());
        }
    }


    /**
     * Update a process step that has not been startet yet
     *
     * @param id - the id of the process step
     */
    public void onRowEdit(int id) {
        try {
            ProzessSchrittV2 prozessSchritt = prozessSchrittV2Service.getObjById(id);
            if (prozessSchritt.getProzessSchrittZustandsAutomat().getCurrent().equals("Erstellt")) {
                prozessSchritt.setDuration(psDauer);
                prozessSchritt.setExperimentierStation(experimentierStation);
                prozessSchritt.setName(prozessSchrittName);
                prozessSchritt.setAttributes(attributes);
                ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = prozessSchritt.getProzessSchrittZustandsAutomat();
                prozessSchrittZustandsAutomat.setProzessSchrittZustandsAutomatVorlage(prozessSchrittZustandsAutomatVorlage);
                prozessSchrittZustandsAutomatService.edit(prozessSchrittZustandsAutomat);
                prozessSchrittV2Service.update(prozessSchritt);
                log.info("Updated process step with ID " + id);
                facesNotification("Updated process step with ID " + id);
                prozessSchrittV2s = prozessSchrittV2Service.getAll();
            } else {
                facesError("Cannot update a process step that has already been started!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't update process step with ID " + id);
            facesError("Couldn't update process step with ID " + id);
        }
    }

    /**
     * Row edit canceled
     */
    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        facesNotification("Canceled Edit!");
    }

    /**
     * Delete a non started process step
     *
     * @param id - the id of the process step to delete
     */
    public void delete(int id) {
        try {
            prozessSchrittV2Service.remove(prozessSchrittV2Service.getObjById(id));
            log.info("Removed process step with ID " + id);
            facesNotification("Removed process step with ID " + id);
            prozessSchrittV2s = prozessSchrittV2Service.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't delete process step with ID " + id);
            facesError("Couldn't delete process step with ID " + id);
        }

    }

    /**
     * JSON export
     *
     * @return the JSON as a String
     */
    public void toJson() {
        try {
            JsonbConfig config = new JsonbConfig()
                    .withFormatting(true);
            Jsonb jsonb = JsonbBuilder.create(config);
            String result = jsonb.toJson(prozessSchrittV2s);
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            writer.close();
            log.info("Sucessfully exported JSON to " + fileName);
            facesNotification("Sucessfully exported JSON to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("JSON export failed! Error " + e.getMessage());
            facesError("JSON export failed! Error " + e.getMessage());
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
