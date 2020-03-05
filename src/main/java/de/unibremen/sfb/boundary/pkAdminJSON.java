package de.unibremen.sfb.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.File;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RequestScoped
@Named
@Getter
@Setter
@Slf4j
@SuppressWarnings("all")
public class pkAdminJSON {

    /** Process chain service */
    @Inject
    private AuftragService auftragService;

    /** Process chain template service */
    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    /** Process step service */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    /** Process step template service */
    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    /** Process step state automaton service */
    @Inject
    private ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    /** Process step state automaton template service */
    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    /** Process parameter service */
    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    /** Experimenting station service */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /** User service */
    @Inject
    private UserService userService;

    /** List of all available process steps */
    private List<ProzessSchritt> availableProzessSchritte;

    /** Selected process step */
    private ProzessSchritt selectedProzessSchritt;

    /** List of all available process chains */
    private List<Auftrag> availableAuftraege;

    /** Selected job */
    private Auftrag selectedAuftrag;

    /** JSONB Configs */
    private JsonbConfig config;
    private Jsonb jsonb;

    /** Init called on start */
    @PostConstruct
    private void init(){
        config = new JsonbConfig().withFormatting(true);
        jsonb = JsonbBuilder.create(config);
        availableProzessSchritte = prozessSchrittService.getAll();
        availableAuftraege = auftragService.getAll();
    }

    /** Auftrag Export */
    public void exportAuftrags(){
        try {
            String result = jsonb.toJson(auftragService.getAll());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** PKV Export */
    public void exportPKV(){
        try {
            String result = jsonb.toJson(prozessKettenVorlageService.getAll());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** TransportAuftrag Export */
    public void exportTA(){
        try {
            Map<Integer, TransportAuftrag> taLogs = new TreeMap<>();
            for (Auftrag a:
               auftragService.getAuftrage()) {
                for (ProzessSchritt ps :
                        a.getProzessSchritte()) {
                    taLogs.put(ps.getId(), ps.getTransportAuftrag());
                }
            }
            String result = jsonb.toJson(taLogs);
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** Process step export */
    public void exportPS(){
        try {
            String result = jsonb.toJson(prozessSchrittService.getAll());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** Process step template export */
    public void exportPSV(){
        try {
            String result = jsonb.toJson(prozessSchrittVorlageService.getProzessSchrittVorlagen());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** Process step parameter export */
    public void exportPSP(){
        try {
            String result = jsonb.toJson(prozessSchrittParameterService.getAll());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** Experimenting station export */
    public void exportES(){
        try {
            String result = jsonb.toJson(experimentierStationService.getAll());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** User export */
    public void exportUsers(){
        try {
            String result = jsonb.toJson(userService.getAll());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** Export log of selected process step */
    public void exportProcessStepLog(){
        try {
            String result = jsonb.toJson(selectedProzessSchritt.getProzessSchrittLog());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
        }
    }

    /** Export log of selected process chain */
    public void exportAuftragsLog(){
        try {
            String result = jsonb.toJson(selectedAuftrag.getLog());
            String fileName = "JSON_" + LocalDateTime.now().toString().replaceAll(":","_") + ".json";
            PrintWriter writer = new PrintWriter(fileName);
            writer.write(result);
            log.info("Successfully exported json to " + fileName);
            facesNotification("Successfully exported json to " + fileName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to export json " + e.getMessage());
            facesError("Failed to export to json!");
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
