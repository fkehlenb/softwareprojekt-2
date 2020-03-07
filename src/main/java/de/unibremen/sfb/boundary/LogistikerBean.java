package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static de.unibremen.sfb.model.ProzessKettenZustandsAutomat.ABGELEHNT;
import static de.unibremen.sfb.model.ProzessKettenZustandsAutomat.GESTARTET;

/**
 * this class manages the interaction between the gui and the backend system for users who are logistic experts
 */
@Named("dtLogistikerBean")
@RequestScoped
@Getter
@Setter
@Slf4j
@Transactional
public class LogistikerBean implements Serializable {
    private List<Probe> proben;

    private List<Auftrag> auftrage;

    //TODO remove this
    @Inject
    private ProbeDAO probeDAO;

    private List<Auftrag> filteredAuftrag;

    private AuftragsPrioritaet selectedPriority;

    /**
     * Sample service
     */
    @Inject
    private ProbenService probenService;

    @Inject
    private AuftragService auftragService;

    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    @Inject
    private AuftragView auftragView;

    /**
     * Traeger service
     */
    @Inject
    private TraegerService traegerService;

    /**
     * Container type service
     */
    @Inject
    private TraegerArtService traegerArtService;

    /**
     * Location Service
     */
    @Inject
    private StandortService standortService;

    @Inject
    ExperimentierStationService experimentierStationService;

    /**
     * All containers
     */
    private List<Traeger> traegers;

    /**
     * Selected Containers
     */
    private List<Traeger> selectedTraeger;
    /**
     * Container type
     */
    private String traegerArt;

    /**
     * Container types
     */
    private List<String> traegerArts;

    /**
     * Container Location
     */
    private Standort traegerLocation;

    /**
     * Contains errorMessage for pkAdmin
     */
    private String errorMessage;

    /**
     * All archived samples
     */
    private List<Probe> archiviert;

    /**
     * Sample ID
     */
    private String probenID;

    /**
     * Chosen sample
     */
    private List<Probe> selectedProbe;

    /**
     * Sample amount
     */
    @Min(0)
    private int anzahl;


    @PostConstruct
    void init() {
        refresh();
    }


    /**
     * Reload data
     */
    private void refresh() {
        var jobs = auftragService.getAll();
        auftrage = new ArrayList<>();
        for (Auftrag a :
                jobs) {
            if (!a.getProzessKettenZustandsAutomat().equals(ProzessKettenZustandsAutomat.GESTARTET) && !a.getProzessKettenZustandsAutomat().equals(ProzessKettenZustandsAutomat.INSTANZIIERT) && !a.getProzessKettenZustandsAutomat().equals(ProzessKettenZustandsAutomat.DURCHGEFUEHRT) && !a.getProzessKettenZustandsAutomat().equals(ProzessKettenZustandsAutomat.ABGEBROCHEN) && !a.getProzessKettenZustandsAutomat().equals(ABGELEHNT)) {
                auftrage.add(a);
            }
        }
        proben = probenService.getAll();
        traegers = getTraegerList();
        archiviert = getAllArchviert();
        traegerArts = traegerArtService.getAll().get(0).getArten();
        selectedProbe = new ArrayList<>();
    }

    /**
     * the user managed by this bean
     */
    private User logistiker;

    /**
     * returns all carriers currently existing
     *
     * @return a set containing all containers
     */
    public List<Traeger> getTraegerList() {
        return traegerService.getAll();
    }

    public void onRowEditt(int id) throws AuftragNotFoundException {
        FacesMessage msg = new FacesMessage("Auftrag Edited");
        Auftrag a = auftragService.getObjById(id);
        try {
            a.setTraeger(selectedTraeger);
            auftragService.update(a);
        } catch (AuftragNotFoundException aa) {
            aa.printStackTrace();
            log.info("Auftrag konnte nicht gefunden werden");
        }


        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Auftrag> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "" + event.getObject().getPkID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * creates a new carrier
     */
    public void createTraeger() {
        if (selectedProbe == null) {
            selectedProbe = new ArrayList<>();
        }
        Traeger traeger = new Traeger(UUID.randomUUID().hashCode(), traegerArt, selectedProbe, traegerLocation);
        try {
            traegerService.persist(traeger);
            facesNotification("Added new Traeger with Art: " + traegerArt);
            log.info("Added new Traeger with Art: " + traegerArt);
            traegers = getTraegerList();
        } catch (Exception e) {
            e.printStackTrace();
            facesError("Failed to add new Traeger with Art: " + traegerArt);
            log.error("Failed to add new Traeger with Art: " + traegerArt);
        }
        refresh();
    }

    /**
     * Update a container on row edit
     *
     * @param id - the id of the container to update
     */
    public void onRowEditUpdateTraeger(int id) {
        try {
            Traeger t = traegerService.getTraegerById(id);
            t.setStandort(traegerLocation);
            t.setArt(traegerArt);
            for (Probe p : t.getProben()) {
                try {
                    p.setCurrentTraeger(null);
                    probenService.update(p);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (selectedProbe == null) {
                t.setProben(new ArrayList<>());
            } else {
                List<Probe> actual = new ArrayList<>();
                for (Probe p : selectedProbe) {
                    if (p.getStandort().getOrt().equals(traegerLocation.getOrt())) {
                        try {
                            Traeger old = p.getCurrentTraeger();
                            List<Probe> oldProben = old.getProben();
                            oldProben.removeIf(f -> f.getProbenID().equals(p.getProbenID()));
                            old.setProben(oldProben);
                            traegerService.update(old);
                        } catch (Exception e) {
                            System.out.println("Didnt have an old container");
                        }
                        p.setCurrentTraeger(t);
                        probenService.update(p);
                        actual.add(p);
                    } else {
                        facesError("Probe und Traeger nicht am gleichen standort!");
                    }
                }
                t.setProben(actual);
            }
            traegerService.update(t);
            facesNotification("Edited trager with ID: " + id);
            log.info("Edited trager with ID: " + id);
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            facesError("Failed to edit traeger with ID: " + id);
            log.error("Failed to edit traeger with ID: " + id);
        }
    }

    /**
     * On row edit cancel, restore default variables
     */
    public void onRowEditCancelTraegerEdit() {
        refresh();
    }

    /**
     * deletes a carrier
     *
     * @param id - the id of the container to remove
     */
    public void deleteTraeger(int id) {
        try {
            traegerService.remove(traegerService.getTraegerById(id));
            facesNotification("Removed trager with ID: " + id);
            log.info("Removed trager with ID: " + id);
            traegers = getTraegerList();
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
            facesError("Failed to remove traeger with ID: " + id);
            log.error("Failed to remove traeger with ID: " + id);
        }
    }

    /**
     * Get all archived samples
     *
     * @return a list of all archived samples
     */
    public List<Probe> getAllArchviert() {
        return probenService.getAllArchived();
    }

    /**
     * Add a new sample to the system
     */
    public void addProbe() {
        Standort standort;
        try {
            standort = standortService.findByLocation("Lager");
        } catch (StandortNotFoundException e) {
            facesError("Der Standort Lager wurde nicht gefunden und wird nun erstellt, Proben können lediglich im Lager erstellt werden!");
            standort = new Standort(UUID.randomUUID().hashCode(), "Lager");
            standortService.persist(standort);
        }

        int vorherigeAnzahl = 0;
        int verloreneAnzahl = 0;
        try {
            vorherigeAnzahl = probeDAO.getObjById(probenID).getAnzahl();
            verloreneAnzahl = probeDAO.getObjById(probenID).getLost();
        } catch (ProbeNotFoundException e) {
            log.info("vorherige Anzahl kann nicht gefunden werden, weil die Probe nicht existierte!");
        }
        Probe p = new Probe(probenID, vorherigeAnzahl + anzahl, ProbenZustand.ARCHIVIERT, standort);
        p.setLost(verloreneAnzahl);
        try {
            probenService.update(p);
            facesNotification("ERFOLG! die Probe wurde hinzugefügt " + p.getProbenID());
        } catch (ProbeNotFoundException e) {
            log.info("Probe wurde nicht gefunden,es wird versucht sie zu persistieren!");
            try {
                probenService.persist(p);
                facesNotification("ERFOLG! die Probe wurde hinzugefügt " + p.getProbenID());

            } catch (DuplicateProbeException ex) {
                facesError("Die Probe existiert bereits!: " + e.getMessage());
            }
        }
        refresh();

    }

    /**
     * starts a job
     *
     * @param auftrag the job to be started
     */
    public void startAuftrag(int auftrag) {
        try {
            Auftrag a = auftragService.getObjById(auftrag);
            a.setProzessKettenZustandsAutomat(GESTARTET);
            log.info("Auftrag wurde gestartet! ID: " + auftrag);
            facesNotification("Auftrag wurde gestartet! ID: " + auftrag);
            //Aktualisiert Auftragsliste
            //auftragView.updateAuftragTabelle();
            auftragService.update(a);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to change auftrag state! ID: " + auftrag);
            facesError("Failed to change auftrag state! ID: " + auftrag);
        }
        refresh();


    }

    /**
     * refuses a job (signals to the process chain administrator that this job cannot be started in the current form)
     *
     * @param auftrag the job
     */
    public void refuseAuftrag(int auftrag) {
        String selctError = errorMessage;
        if (selctError == null) {
            facesError("Darf nicht leer sein");
            log.info("ErrorMessage darf nicht leer sein!");
        } else {
            try {
                Auftrag a = auftragService.getObjById(auftrag);
                a.setProzessKettenZustandsAutomat(ABGELEHNT);
                log.info("Auftrag wurde abgelehnt! ID: " + auftrag);
                facesNotification("Auftrag wurde abgelehnt! ID: " + auftrag);

                //Aktualisiert Auftragsliste

                //log.info(errorMessage);
                //errorMessageAnPkA(a);
                auftragService.update(a);
                log.info("Test" + auftrag);
                Thread.sleep(100);
                //return "Auftragsuebersicht?faces-redirect=true";
                //PrimeFaces.current().ajax().update("content-panel");


            } catch (Exception e) {
                e.printStackTrace();
                log.error("Failed to change auftrag state! ID: " + auftrag);
                facesError("Failed to change auftrag state! ID: " + auftrag);
            }
            //return null;
        }
        refresh();
    }

    /**
     * the empty constructor
     */
    public LogistikerBean() {
    }

    /**
     * returns the logistic expert managed by this bean
     *
     * @return the user
     */
    public User getLogistiker() {
        return logistiker;
    }

    /**
     * sets the logistic expert managed by this bean
     *
     * @param logistiker the user
     */
    public void setLogistiker(User logistiker) {
        this.logistiker = logistiker;
        refresh();
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

    public void errorMessageAnPkA(Auftrag a) throws AuftragNotFoundException {
        a.setErrorMessage(errorMessage);
        auftragService.update(a);
        log.info("Auftrag wurde aktualisiert mit errotText!" + a);
    }
}
