package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.exception.TransportAuftragNotFoundException;
import de.unibremen.sfb.exception.UserNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named("transportBean")
@RequestScoped
@Getter
@Setter
@Slf4j
@Transactional
public class TransporterBean implements Serializable {

    /**
     * Ertellte prozesschritte
     */
    private List<ProzessSchritt> prozessSchrittList;

    /**
     * Abgeholte prozesschritte, gehoeren dem user
     */
    private List<ProzessSchritt> prozessSchrittList2;

    /**
     * Abgelieferte prozessschritte
     */
    private List<ProzessSchritt> prozessSchrittList3;

    /**
     * Selected transport job
     */
    private List<TransportAuftrag> transportAuftragSelected;

    /**
     * Containers in job
     */
    private List<Traeger> traeger;

    /**
     * Job service
     */
    @Inject
    private AuftragService auftragService;

    /**
     * Process chain template service
     */
    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    /**
     * Process step service
     */
    @Inject
    private ProzessSchrittService prozessSchrittService;

    /** Sample setvice */
    @Inject
    private ProbenService probenService;

    /** Container Service */
    @Inject
    private TraegerService traegerService;

    @PostConstruct
    void init() {
        refresh();
    }

    /**
     * Fetch data
     */
    private void refresh() {
        prozessSchrittList = auftragService.getTransportSchritt();
        prozessSchrittList3 = auftragService.getTransportSchritt3();
        try {
            prozessSchrittList2 = auftragService.getTransportSchritt2();
        } catch (UserNotFoundException e) {
            prozessSchrittList2 = new ArrayList<>();
        }
    }

    /**
     * Get all of the user's jobs
     *
     * @return a list of all jobs available to the user
     */
    public List<ProzessSchritt> getAuftragList() {
        return auftragService.getTransportSchritt();
    }

    /**
     * Set the transport job to picked-up
     *
     * @param TransportID - the id of the job which's status to set
     */
    public void changeTransportZustandAbgeholt(int TransportID) {
        try {
            TransportAuftrag tr = auftragService.getTransportAuftragByID(TransportID);
            auftragService.sedTransportZustand(tr, TransportAuftragZustand.ABGEHOLT);
            log.info("TransportAuftragZustand wurde gewechselt auf Abgeholt " + TransportID);
            facesNotification("Der Zustand von " + TransportID + " wurde auf Abgeholt gesetzt.");
            updateTabellen();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to start transport job " + TransportID + " Error " + e.getMessage());
            facesError("Failed to start transport job!");
        }
    }

    /**
     * Set the transport job as delivered
     *
     * @param transportID - the id of the job which's status to set
     */
    public void changeTransportZustandAbgeliefert(int transportID) {
        try {
            TransportAuftrag tr = auftragService.getTransportAuftragByID(transportID);
            auftragService.sedTransportZustand(tr, TransportAuftragZustand.ABGELIEFERT);
            try {
                for (Traeger t : getTraeger()) {
                    for (Probe p : t.getProben()) {
                        p.setStandort(tr.getZiel());
                        probenService.update(p);
                    }
                    t.setStandort(tr.getZiel());
                    traegerService.update(t);
                }
            } catch (Exception f){

            }

            log.info("TransportAuftragZustand wurde gewechselt auf Abgeliefert " + transportID);
            facesNotification("Der Zustand von " + transportID + " wurde auf Abgeliefert gesetzt.");
            updateTabellen();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to change state to Abgeliefert" + transportID);
            facesError("Failed to change state to Abgeliefert" + transportID);
            updateTabellen();
        }
    }

    /**
     * the empty constructor
     */
    public TransporterBean() {
    }


    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(javax.faces.application.FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * Aktualisiert die Tabellen
     */
    public void updateTabellen() {
        refresh();
        PrimeFaces.current().ajax().update("content-panel:content-panel");
    }


    /**
     * Get the containers used in a process step
     *
     * @param ps - the id of the process step
     * @return a list of all containers used in the process step
     */
    public List<Traeger> getTraegerByPS(int ps) {
        try {
            traeger = auftragService.getTraegerByPS(prozessSchrittService.getObjById(ps));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return traeger==null?new ArrayList<>():traeger;
    }


}
