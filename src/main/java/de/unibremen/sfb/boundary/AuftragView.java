package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("dtAuftragBean")
@RequestScoped
@Getter
@Setter
@Slf4j
public class AuftragView implements Serializable {

    /**
     * Job service
     */
    @Inject
    private AuftragService auftragService;

    /**
     * Process step service
     */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    /**
     * Job template service
     */
    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    /**
     * Process step state automaton service
     */
    @Inject
    private ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    /**
     * Process step log service
     */
    @Inject
    private ProzessSchrittLogService prozessSchrittLogService;

    /**
     * Job log service
     */
    @Inject
    private AuftragsLogsService auftragsLogsService;

    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * List of all available jobs
     */
    private List<Auftrag> availableJobs;

    /**
     * List of all available process steps
     */
    private List<ProzessSchritt> availableProzessSchritte;

    /**
     * Selected process steps
     */
    private List<ProzessSchritt> selectedProzessSchritte;

    /** Dual list for picker */
    private DualListModel<ProzessSchritt> dualListModel;

    /**
     * List of all available process chain templates
     */
    private List<ProzessKettenVorlage> availableProzessKettenVorlagen;

    /**
     * Selected job template
     */
    private ProzessKettenVorlage selectedProzesskettenVorlage;

    /**
     * List of all available priorities
     */
    private List<AuftragsPrioritaet> availablePriorities;

    /**
     * Job priority
     */
    private AuftragsPrioritaet selectedPriority;

    /**
     * Job name
     */
    private String selectedName;

    /**
     * selected part from the job
     */
    private Auftrag selectedAuftraege;
    private List<Auftrag> filteredAuftrag;
    /**
     * A var for the job used in the front end
     */
    private List<Auftrag> auftrage;
    /**
     * a list of prioritys
     */
    private AuftragsPrioritaet[] prios;
    /**
     * a list of prozesskettenZustandsAutomaten
     */
    private ProzessKettenZustandsAutomat[] prozessKettenZustandsAutomatList;


    /**
     * Init called on start
     */
    @PostConstruct
    private void init() {
        refresh();
    }

    /**
     * Refresh lists
     */
    private void refresh() {
        availableJobs = auftragService.getAll();
        availableProzessKettenVorlagen = prozessKettenVorlageService.getAll();
        availableProzessSchritte = prozessSchrittService.getAllAvailable();
        availablePriorities = new ArrayList<>();
        availablePriorities.add(AuftragsPrioritaet.KEINE);
        availablePriorities.add(AuftragsPrioritaet.ETWAS);
        availablePriorities.add(AuftragsPrioritaet.VIEL);
        availablePriorities.add(AuftragsPrioritaet.HOCH);
        availablePriorities.add(AuftragsPrioritaet.SEHR_HOCH);
        selectedProzessSchritte = new ArrayList<>();
        dualListModel = new DualListModel<>(availableProzessSchritte,selectedProzessSchritte);
        auftrage = auftragService.getAll();
    }

    /**
     * Create a new job
     */
    public void createJob() {
        try {
            List<ProzessSchritt> prozessSchritts = new ArrayList<>();
            for (ProzessSchrittVorlage psv : selectedProzesskettenVorlage.getProzessSchrittVorlagen()) {
                ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = new ProzessSchrittZustandsAutomat(UUID.randomUUID().hashCode(),
                        "Erstellt", List.copyOf(psv.getZustandsAutomatVorlage().getZustaende()));
                prozessSchrittZustandsAutomat.setName(psv.getZustandsAutomatVorlage().getName());
                prozessSchrittZustandsAutomatService.add(prozessSchrittZustandsAutomat);
                ProzessSchrittLog prozessSchrittLog = new ProzessSchrittLog(LocalDateTime.now(), "GESTARTET");
                prozessSchrittLogService.add(prozessSchrittLog);
                ProzessSchritt ps = new ProzessSchritt(UUID.randomUUID().hashCode(), prozessSchrittZustandsAutomat,
                        psv.getDauer(), List.copyOf(psv.getProzessSchrittParameters()), "",
                        List.of(prozessSchrittLog), psv.getName(), psv.isUrformend(), psv.getAmountCreated());
                ps.setAssigned(true);
                prozessSchrittService.createPS(ps);
                experimentierStationService.setES(ps, psv.getExperimentierStation());
                prozessSchritts.add(ps);
            }
            AuftragsLog auftragsLog = new AuftragsLog(LocalDateTime.now());
            auftragsLogsService.add(auftragsLog);
            auftragService.add(new Auftrag(UUID.randomUUID().hashCode(), selectedName, selectedPriority, prozessSchritts, auftragsLog, ProzessKettenZustandsAutomat.INSTANZIIERT));
            log.info("Created new job with name " + selectedName);
            facesNotification("Created new job with name " + selectedName);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't create new job! Error " + e.getMessage());
            facesError("Couldn't create new job!");
        }
    }

    /**
     * Create a job from existing process steps
     */
    public void createJobFromAvailable() {
        try {
            AuftragsLog auftragsLog = new AuftragsLog(LocalDateTime.now());
            auftragsLogsService.add(auftragsLog);
            for (ProzessSchritt p : dualListModel.getTarget()) {
                p.setAssigned(true);
                prozessSchrittService.editPS(p);
            }
            auftragService.add(new Auftrag(UUID.randomUUID().hashCode(), selectedName, selectedPriority, List.copyOf(selectedProzessSchritte), auftragsLog, ProzessKettenZustandsAutomat.INSTANZIIERT));
            log.info("Created new job with name " + selectedName);
            facesNotification("Created new job with name " + selectedName);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't create new job! Error " + e.getMessage());
            facesError("Couldn't create new job!");
        }
    }

    /**
     * Edit a job
     *
     * @param id - the id of the job to edit
     */
    public void edit(int id) {
        try {
            Auftrag a = auftragService.getObjById(id);
            if (a.getProzessKettenZustandsAutomat().equals(ProzessKettenZustandsAutomat.INSTANZIIERT)) {
                a.setPriority(selectedPriority);
                for (ProzessSchritt p : a.getProzessSchritte()) {
                    p.setAssigned(false);
                }
                for (ProzessSchritt p : dualListModel.getTarget()) {
                    p.setAssigned(true);
                }
                a.setProzessSchritte(List.copyOf(dualListModel.getTarget()));
                a.setName(selectedName);
                auftragService.update(a);
                log.info("Updated job with id " + id);
                facesNotification("Updated job!");
                refresh();
            }
            else{
                facesError("Can't edit an already started job!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't edit job with id " + id + " Error " + e.getMessage());
            facesError("Couldn't edit job!");
        }
    }

    /**
     * Remove a job
     *
     * @param id - the id of the job to remove
     */
    public void remove(int id) {
        try {
            Auftrag a = auftragService.getObjById(id);
            for (ProzessSchritt p : a.getProzessSchritte()) {
                prozessSchrittService.removePS(p);
            }
            auftragService.remove(a);
            log.info("Removed job with id " + id);
            facesNotification("Removed job!");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to remove job with id " + id);
            facesError("Failed to remove job!");
        }
    }

    /** Stop a job
     * @param id - the id of the job to stop */
    public void stopJob(int id){
        try {
            Auftrag a = auftragService.getObjById(id);
            for (ProzessSchritt p : a.getProzessSchritte()) {
                prozessSchrittService.removePS(p);
            }
            //TODO proben ins lager
            auftragService.remove(a);
            log.info("Stopped job with id " + id);
            facesNotification("Stopped job!");
            refresh();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to stop job with id " + id);
            facesError("Failed to stop job!");
        }
    }

    /**
     * Start a job
     *
     * @param id - the id of the job to start
     */
    public void setStarted(int id) {
        try {
            Auftrag a = auftragService.getObjById(id);
            if (a.getProzessKettenZustandsAutomat().equals(ProzessKettenZustandsAutomat.INSTANZIIERT)) {
                a.setProzessKettenZustandsAutomat(ProzessKettenZustandsAutomat.GESTARTET);
                auftragService.update(a);
                log.info("Started job with id " + id);
                facesNotification("Started job!");
                refresh();
            }
            else{
                facesError("Job has already been started!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't start job with id " + id + " Error " + e.getMessage());
            facesError("Couldn't start job!");
        }
    }

    public ExperimentierStation getES(int id) {
        ExperimentierStation r = null;
        try {
            var ps =  prozessSchrittService.getObjById(id);
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
            log.error("Could not find ES: "+ esID);
        }
        try {
            ps = prozessSchrittService.getObjById(psID);
        } catch (ProzessSchrittNotFoundException e) {
            e.printStackTrace();
            log.error("Could not find ps: "+ psID);
        }
        try {
            experimentierStationService.setES(ps, es);
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
            log.error("could not set es of ps");
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

