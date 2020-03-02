package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import org.primefaces.model.SelectableDataModel;


@Named("dtAuftragBean")
@ViewScoped
@Getter
@Setter
@Slf4j
public class AuftragView implements Serializable {
    private List<Auftrag> auftrage;
    private List<ProzessSchritt> auftragPS;
    private List<ProzessKettenVorlage> vorlagen;
    private AuftragsPrioritaet[] prios;
    private Auftrag selected;
    private List<Auftrag> filteredAuftrag;
    private ProzessKettenZustandsAutomat[] prozessKettenZustandsAutomatList;

    private List<Auftrag> selectedAuftraege;
    private List<Auftrag> filteredAuftraege;
    //Der gewählte Auftrag

    // Auftrag Erstellen
    private ProzessKettenVorlage ausPKV;
    private AuftragsPrioritaet ausPrio;


    @Inject
    AuftragService auftragService;

    @Inject
    ProzessKettenVorlageService prozessKettenVorlageService;

    @PostConstruct
    void init() {
        auftrage = auftragService.getAuftrage();
        vorlagen = getPKVs();
        prios = AuftragsPrioritaet.values();
        prozessKettenZustandsAutomatList = ProzessKettenZustandsAutomat.values();


    }

    public void erstelleAuftrag() {
        int id = auftragService.erstelleAuftrag(ausPKV, ausPrio);
        facesNotification("Erfolgreich Auftrag: " + id + " erstellt");
        updateAuftragTabelle();
    }

    /**
     * Aktualisiert die Tabelle
     */
    public void updateAuftragTabelle() {
        auftrage = auftragService.getAuftrage();

        PrimeFaces.current().ajax().update("content");
    }

    public void onRowEdit(RowEditEvent<Auftrag> event) {
        log.info("Updating: " + event.getObject().getPkID());
        try {
            auftragService.update(event.getObject());
        } catch (de.unibremen.sfb.exception.AuftragNotFoundException e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        FacesMessage msg = new FacesMessage("Auftrag Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Auftrag> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String json() {
        return auftragService.toJson();
    }

    public List<ProzessKettenVorlage> getPKVs() {
        return prozessKettenVorlageService.getPKVs();
    }

    public void delete() {
        try {
            auftragService.delete(selectedAuftraege); //FIXME LIAM
            FacesMessage msg = new FacesMessage("Deleted", selectedAuftraege.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            facesError("Failed to delete Selection");
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

    public void clearTableState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll();
        showMessage();

    }

    private void showMessage( ) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO," multiview state has been cleared out", null));
    }

}

