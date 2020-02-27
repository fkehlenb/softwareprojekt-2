package de.unibremen.sfb.boundary;

import de.unibremen.sfb.service.*;
import de.unibremen.sfb.model.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
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
@Log
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
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        allePS = prozessSchrittService.getAll();
        allePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
    }


    /**
     * Once the row has been modified, update the object
     * @param event from ajax with new values
     */
    public void onRowEdit(RowEditEvent<ProzessSchrittVorlage> event) {
        prozessSchrittVorlageService.persist(event.getObject());
        facesNotification("Prozess schritt vorlage veraendert! ID: " + event.getObject().getPsVID());
    }

    /**
     * Canceled row edit
     * @param event from ajax with new values
     */
    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        facesNotification("Cancelled!");
    }

    /**
     * JSON export
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
