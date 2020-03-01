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
import java.io.Serializable;
import java.util.List;


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

    /** Process step state automaton service */
    @Inject
    private ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;


    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        allePS = prozessSchrittService.getAll();
        allePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        prozessSchrittVorlages = prozessSchrittVorlageService.getVorlagen();
        prozessSchrittZustandsAutomatVorlages = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
    }


    /**
     * Update a process step that has not been startet yet
     *
     * @param id - the id of the process step
     */
    public void onRowEdit(int id) {
        try {
            ProzessSchritt prozessSchritt = prozessSchrittService.getObjById(id);
            if (prozessSchritt.getProzessSchrittZustandsAutomat().getCurrent().equals("Erstellt")) {
                prozessSchritt.setProzessSchrittVorlage(prozessSchrittVorlage);
                ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat = prozessSchritt.getProzessSchrittZustandsAutomat();
                prozessSchrittZustandsAutomat.setProzessSchrittZustandsAutomatVorlage(prozessSchrittZustandsAutomatVorlage);
                prozessSchrittZustandsAutomatService.edit(prozessSchrittZustandsAutomat);
                prozessSchrittService.update(prozessSchritt);
                log.info("Updated process step with ID " + id);
                facesNotification("Updated process step with ID " + id);
                allePS = prozessSchrittService.getAll();
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
            if (prozessSchrittService.getObjById(id).getProzessSchrittZustandsAutomat().getCurrent().equals("Erstellt")) {
                prozessSchrittService.remove(prozessSchrittService.getObjById(id));
                log.info("Removed process step with ID " + id);
                facesNotification("Removed process step with ID " + id);
            } else {
                facesError("Cannot remove an already started process step!");
            }
            allePS = prozessSchrittService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Couldn't delete process step with ID " + id);
            facesError("Couldn't delete process step with ID " + id);
        }
    }

    /**
     * JSON export
     *
     * @return the JSON fo all PS
     */
    public String json() {
        return prozessSchrittService.toJson();
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
