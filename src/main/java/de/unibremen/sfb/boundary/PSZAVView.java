package de.unibremen.sfb.boundary;


import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatZustaendeDAO;
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
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Transactional
@Named("pszavView")
@RequestScoped
@Getter
@Setter
@Slf4j
public class PSZAVView implements Serializable {

    /**
     *
     */
    private List<ProzessSchrittZustandsAutomatVorlage> selpszav;

    /**
     * Available process step state automaton templates
     */
    private List<ProzessSchrittZustandsAutomatVorlage> verpszav;

    private List<ProzessSchrittZustandsAutomatVorlage> filteredpszav;

    /**
     * Available states
     */
    private List<String> sourceZ = new ArrayList<>();

    /**
     * Selected states
     */
    private List<String> targetZ = new ArrayList<>();

    /**
     * Containing available and selected states
     */
    private DualListModel<String> dualZ;

    private String toaddd;

    /**
     * Process step state automaton template name
     */
    private String name;

    /**
     * Process step state automaton states DAO object containing all states
     */
    @Inject
    private ProzessSchrittZustandsAutomatZustaendeDAO prozessSchrittZustandsAutomatZustaendeDAO;

    /**
     * Process step state automaton template service
     */
    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    /**
     * Process step template service
     */
    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    /**
     * Process step state name
     */
    private String zustandsname;

    /** Array containing the newly ordered process step state automaton states */
    private String[] newOrder;

    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        try {
            ProzessSchrittZustandsAutomatZustaende pszaz = new ProzessSchrittZustandsAutomatZustaende(123, new ArrayList<>());
            prozessSchrittZustandsAutomatZustaendeDAO.persist(pszaz);
            sourceZ.add("Erstellt");
            pszaz.setZustaende(sourceZ);
            prozessSchrittZustandsAutomatZustaendeDAO.update(pszaz);
        } catch (Exception e) {
            sourceZ = prozessSchrittZustandsAutomatZustaendeDAO.getById(123).getZustaende();
        }
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        dualZ = new DualListModel<>(sourceZ, targetZ);
    }

    /**
     * Refresh data
     */
    public void refresh() {
        sourceZ = prozessSchrittZustandsAutomatZustaendeDAO.getById(123).getZustaende();
        targetZ = new ArrayList<>();
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        dualZ = new DualListModel<>(sourceZ, targetZ);
    }

    /**
     * Add a new step to the state automaton
     */
    public void addToAutomaton() {
        if (!prozessSchrittZustandsAutomatZustaendeDAO.getById(123).getZustaende().contains(zustandsname)) {
            ProzessSchrittZustandsAutomatZustaende pszaz = prozessSchrittZustandsAutomatZustaendeDAO.getById(123);
            List<String> current = pszaz.getZustaende();
            current.add(zustandsname);
            pszaz.setZustaende(current);
            prozessSchrittZustandsAutomatZustaendeDAO.update(pszaz);
            refresh();
            log.info("Added new Process Step state to automaton! Name " + zustandsname);
            facesNotification("Added new Process Step state to automaton! Name " + zustandsname);
            refresh();
        }
    }

    /**
     * Create a new process step state automaton template
     */
    public void erstellePSZAV() {
        try {
            List<String> selected = dualZ.getTarget();
            try {
                if (selected.get(0).equals("Erstellt")) {
                    prozessSchrittZustandsAutomatVorlageService.addVorlage(new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(), selected, name));
                } else {
                    facesError("Der erste Zustand muss Erstellt sein!");
                }
                refresh();
            } catch (Exception f) {
                facesError("Bitte suchen Sie mindestens 1 Zustaend aus!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't create new process step automaton with name " + name);
            facesError("Couldn't create new process step automaton with name " + name);
        }
    }

    /**
     * Delete a process step state automaton template
     *
     * @param id - the id of the process step state automaton to delete
     */
    public void deletePSZAV(int id) {
        try {
            prozessSchrittZustandsAutomatVorlageService.remove(prozessSchrittZustandsAutomatVorlageService.getByID(id));
            log.info("Removed process step state automaton template with ID " + id);
            facesNotification("Removed process step state automaton template!");
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't remove process step state automaton with ID " + id);
            facesError("Couldn't remove process step state automaton!");
        }
        refresh();
    }

    /** Edit a process step state automaton template
     * @param id - the id of the process step state automaton to edit */
    public void onRowEdit(int id) {
        try {
            List<String> selected = dualZ.getTarget();
            if (selected.get(0).equals("Erstellt")) {
                ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage = prozessSchrittZustandsAutomatVorlageService.getByID(id);
                prozessSchrittZustandsAutomatVorlage.setName(name);
                prozessSchrittZustandsAutomatVorlage.setZustaende(selected);
                prozessSchrittZustandsAutomatVorlageService.edit(prozessSchrittZustandsAutomatVorlage);
                refresh();
            } else {
                facesError("Der erste Zustand muss Erstellt sein!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't edit process step automaton with name " + name);
            facesError("Couldn't edit process step automaton with name " + name);
        }
    }

    /** Canceled row edit */
    public void onRowCancel() {
        facesNotification("Edit canceled!");
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


