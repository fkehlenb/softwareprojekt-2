package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessKettenVorlageException;
import de.unibremen.sfb.exception.ProzessKettenVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Named("pkvView")
@RequestScoped
@Getter
@Setter
@Slf4j
public class PKVView implements Serializable {


    private List<ProzessKettenVorlage> selPKV;

    private List<ProzessKettenVorlage> verPKV;

    /** Available process step templates */
    private List<ProzessSchrittVorlage> sourcePSV;

    /** Selected process step templates */
    private List<ProzessSchrittVorlage> targetPSV;

    /** Dual list containing source and target */
    private DualListModel<ProzessSchrittVorlage> psvs;

    /** Process chain template service */
   @Inject
   private ProzessKettenVorlageService prozessKettenVorlageService;

   /** Process step template service */
   @Inject
   private ProzessSchrittVorlageService prozessSchrittVorlageService;

   /** Selected process chain template name */
   private String selectedName;
  
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        refresh();
    }

    /** Refresh lists */
    private void refresh(){
        sourcePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        targetPSV = new ArrayList<>();
        psvs = new DualListModel<>(sourcePSV, targetPSV);
        verPKV = prozessKettenVorlageService.getAll();
    }

    /** Create new process chain template */
    public void erstellePSK() {
        try {
            prozessKettenVorlageService.persist(new ProzessKettenVorlage(UUID.randomUUID().hashCode(),selectedName,psvs.getTarget()));
            log.info("Created new process chain template with name " + selectedName);
            facesNotification("Created new process chain template with name " + selectedName);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't create new process chain template! Error " + e.getMessage());
            facesError("Couldn't create new process chain template!");
        }
    }

    /** Delete a process chain template
     * @param id - the id of the process chain template to delete */
    public void deletePKV(int id) {
        try {
            prozessKettenVorlageService.remove(prozessKettenVorlageService.getObjById(id));
            log.info("Deleted process chain template with id " + id);
            facesNotification("Deleted process chain template!");
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't delete process chain template with id " + id + " Error " + e.getMessage());
            facesError("Couldn't delete process chain template!");
        }
    }

    /** On row edit update the process chain template
     * @param id - the id of the process chain template to update */
    public void onRowEdit(int id) {
        try {
            ProzessKettenVorlage prozessKettenVorlage = prozessKettenVorlageService.getObjById(id);
            prozessKettenVorlage.setName(selectedName);
            prozessKettenVorlage.setProzessSchrittVorlagen(psvs.getTarget());
            prozessKettenVorlageService.update(prozessKettenVorlage);
            log.info("Updated process chain template with id " + id);
            facesNotification("Updated process chain template!");
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to update process chain template with id " + id + " Error " + e.getMessage());
            facesError("Failed to update process chain template!");
        }
    }

    public void onRowCancel() {
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

