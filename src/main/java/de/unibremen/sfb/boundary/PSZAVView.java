package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatZustaendeDAO;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Transactional
@Named("pszavView")
@ViewScoped
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

    /** Process step state automaton template name */
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

    /** Process step state name */
    private String zustandsname;

    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        try {
            ProzessSchrittZustandsAutomatZustaende pszaz = new ProzessSchrittZustandsAutomatZustaende(123, new ArrayList<>());
            prozessSchrittZustandsAutomatZustaendeDAO.persist(pszaz);
            sourceZ.add("Erstellt");
            sourceZ.add("Freigegeben");
            pszaz.setZustaende(sourceZ);
            prozessSchrittZustandsAutomatZustaendeDAO.update(pszaz);
        } catch (Exception e) {
            sourceZ = prozessSchrittZustandsAutomatZustaendeDAO.getById(123).getZustaende();
        }
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        dualZ = new DualListModel<>(sourceZ, targetZ);
    }

    /** Refresh data */
    public void refresh(){
        sourceZ = prozessSchrittZustandsAutomatZustaendeDAO.getById(123).getZustaende();
        targetZ = new ArrayList<>();
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        dualZ = new DualListModel<>(sourceZ, targetZ);
    }

    /** Add a new step to the state automaton */
    public void addToAutomaton(){
        if (!prozessSchrittZustandsAutomatZustaendeDAO.getById(123).getZustaende().contains(zustandsname)){
            ProzessSchrittZustandsAutomatZustaende pszaz = prozessSchrittZustandsAutomatZustaendeDAO.getById(123);
            List<String> current = pszaz.getZustaende();
            current.add(zustandsname);
            pszaz.setZustaende(current);
            prozessSchrittZustandsAutomatZustaendeDAO.update(pszaz);
            refresh();
            log.info("Added new Process Step state to automaton! Name " + zustandsname);
            facesNotification("Added new Process Step state to automaton! Name " + zustandsname);
        }
    }

    /** Update a process step state automaton template */
    public void toAdd(String id) throws ProzessSchrittVorlageNotFoundException {
        try {
            ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage =
                    prozessSchrittZustandsAutomatVorlageService.getByID(Integer.parseInt(id));
            List<String> newZustande = prozessSchrittZustandsAutomatVorlage.getZustaende();
            newZustande.add(toaddd);
            prozessSchrittZustandsAutomatVorlage.setZustaende(newZustande);
            prozessSchrittZustandsAutomatVorlageService.edit(prozessSchrittZustandsAutomatVorlage);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Create a new process step state automaton template */
    public void erstellePSZAV() {
        try {
            List<String> selected = dualZ.getTarget();
            try {
                if (selected.get(0).equals("Erstellt") && selected.get(1).equals("Freigegeben")) {
                    prozessSchrittZustandsAutomatVorlageService.addVorlage(new ProzessSchrittZustandsAutomatVorlage(UUID.randomUUID().hashCode(), selected, name));
                } else {
                    facesError("Der erste Zustand muss Erstellt, und der zweite Freigegeben sein!");
                }
                refresh();
            }
            catch (Exception f){
                facesError("Bitte suchen Sie mindestens 2 Zustaende aus!");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't create new process step automaton with name " + name);
            facesError("Couldn't create new process step automaton with name " + name);
        }
    }

    public void deletePSZAV() {
        try {
            prozessSchrittZustandsAutomatVorlageService.delete(selpszav);
            FacesMessage msg = new FacesMessage("PS Automat Delete");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
    }

    public void onRowEdit(RowEditEvent<ProzessSchrittZustandsAutomatVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        //When The Persistence gefit be, we can uncomment that.
        boolean a = true;
        if (a) {
            prozessSchrittZustandsAutomatVorlageService.edit(event.getObject());
            FacesMessage msg = new FacesMessage("PS Automat Edited", event.getObject().toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            onRowCancelWegenOrdnung();
        }

    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancelWegenOrdnung() {
        FacesMessage msg = new FacesMessage("Der Ordnung kann nicht gespeichern werden /n [Erstellt, Angenommen, In Bearbeitung, Bearbeitet, Weitergeleitet]");
        FacesContext.getCurrentInstance().addMessage(null, msg);
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


