package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("pkvView")
@ViewScoped
@Getter
@Setter
@Log
public class PKVView implements Serializable {
    private List<ProzessKettenVorlage> selPKV;
    private List<ProzessKettenVorlage> verPKV;
    private List<ProzessSchrittVorlage> sourcePSV;
    private List<ProzessSchrittVorlage> targetPSV;
    private DualListModel<ProzessSchrittVorlage> psvs;

   @Inject
   private ProzessKettenVorlageService prozessKettenVorlageService;
   
   @Inject
   private ProzessSchrittVorlageService prozessSchrittVorlageService;
  
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        sourcePSV = prozessSchrittVorlageService.getVorlagen();
        targetPSV = new ArrayList<>();
        psvs = new DualListModel<>(sourcePSV, targetPSV);
        verPKV = prozessKettenVorlageService.getPKVs();
        //
    }

    public String erstellePSK() {
        // FIXME
        var target = psvs.getTarget();
        var pk = new ProzessKettenVorlage(UUID.randomUUID().hashCode(), target);
        try {
            prozessKettenVorlageService.persist(pk);
            log.info("Succesfully Added pkv: "+ pk.getPkvID());
        } catch (DuplicateProzessKettenVorlageException e) {
            e.printStackTrace();
        }
        return "pkv?faces-redirect=true";
    }

    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    public void deletePKV() {
        try {
            prozessKettenVorlageService.delete(selPKV);
            facesNotification("Erfolgreich Gelöscht");
            verPKV = prozessKettenVorlageService.getPKVs();
        } catch (DuplicateProzessKettenVorlageException e) {
            e.printStackTrace();
            facesNotification("Fehler im Löschen");
        }
    }

    public void onRowEdit(RowEditEvent<ProzessKettenVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        //When The Persistence gefit be, we can uncomment that.
        try {
            prozessKettenVorlageService.edit(event.getObject());
            FacesMessage msg = new FacesMessage("PSV Edited", event.getObject().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (ProzessKettenVorlageNotFoundException e) {
            e.printStackTrace();
            FacesMessage msg = new FacesMessage("PSV Edit error", event.getObject().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}

