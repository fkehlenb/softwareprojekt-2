package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static de.unibremen.sfb.model.ProzessKettenZustandsAutomat.ABGELEHNT;
import static de.unibremen.sfb.model.ProzessKettenZustandsAutomat.GESTARTET;

/**
 * this class manages the interaction between the gui and the backend system for users who are logistic experts
 */
@Named("dtLogistikerBean")
@ViewScoped
@Getter
@Setter
@Slf4j
public class LogistikerBean implements Serializable {
    private List<Probe> proben;
    private List<Auftrag> auftrage;

    //TODO remove this
    @Inject
    private ProbeDAO probeDAO;

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
    private ZustandsService zustandsService;

    @Inject
    private AuftragView auftragView;

    /**
     * Traeger service
     */
    @Inject
    private TraegerService traegerService;

    /**
     * All containers
     */
    private List<Traeger> traegers;

    /**
     * All archived samples
     */
    private List<Probe> archiviert;

    @PostConstruct
    void init() {
        auftrage = auftragService.getAuftrage();
        proben = getProben();
        traegers = getTraegerList();
        archiviert = getAllArchviert();
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

    /**
     * creates a new carrier
     */
    public void createTraeger() {

    }

    /**
     * Update a container
     *
     * @param id - the id of the container to update
     */
    public void updateTraeger(int id) {

    }

    /**
     * deletes a carrier
     *
     * @param id - the id of the container to remove
     */
    public void deleteTraeger(int id) {
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
     * returns all jobs currently awaiting processing by the logistic
     *
     * @return a set containing all jobs currently awaiting processing
     */
    public Set<Auftrag> getAuftrag() {
        return null;
    }

    /**
     * assigns a sample to a job
     *
     * @param a the job
     * @param p the sample
     */
    public void zuorndnenProbe(Auftrag a, Probe p) {
    }

    /**
     * assigns a carrier to a job
     *
     * @param a the job
     * @param t the carrier
     */
    public void zuordnenTraeger(Auftrag a, Traeger t) {
    }

    /**
     * returns all samples currently existing
     *
     * @return a set containing all samples
     */
    public List<Probe> getProben() {
        return probeDAO.getProben(1, 100);
    }

    /**
     * returns all samples currently existing by whether or not they are archived
     *
     * @param archiviert True: display archived samples. False: display samples which are not archived
     * @return a set containing all samples which fullfill the condition
     */
    public Set<Probe> getProben(boolean archiviert) {
        return null;
    }

    /**
     * returns the current location of a sample
     *
     * @param p the sample
     * @return the location
     */
    public Standort getProbenStandort(Probe p) {
        return null;
    }

    /**
     * starts a job
     *
     * @param auftrag the job to be started
     */
    public void startAuftrag(int auftrag) {
        try {
            Auftrag a = auftragService.getAuftrag(auftrag);
            auftragService.zustandswechsel(a, GESTARTET);
            log.info("Auftrag wurde gestartet! ID: " + auftrag);
            facesNotification("Auftrag wurde gestartet! ID: " + auftrag);
            //Aktualisiert Auftragsliste
            auftragView.updateAuftragTabelle();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to change auftrag state! ID: " + auftrag);
            facesError("Failed to change auftrag state! ID: " + auftrag);
        }


    }

    /**
     * refuses a job (signals to the process chain administrator that this job cannot be started in the current form)
     *
     * @param auftrag the job
     * @param message the message to the process chain administrator
     */
    public void refuseAuftrag(int auftrag, String message) {
        try {
            Auftrag a = auftragService.getAuftrag(auftrag);
            auftragService.zustandswechsel(a, ABGELEHNT);
            log.info("Auftrag wurde abgelehnt! ID: " + auftrag);
            facesNotification("Auftrag wurde abgelehnt! ID: " + auftrag);
            //Aktualisiert Auftragsliste
            auftragView.updateAuftragTabelle();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to change auftrag state! ID: " + auftrag);
            facesError("Failed to change auftrag state! ID: " + auftrag);
        }
    }

    /**
     * returns an error message
     *
     * @return the error message
     */
    public String errorMessage() {
        return null;
    }

    /**
     * assigns himself to a job so that no other logistic expert starts working on it
     *
     * @param a the job
     */
    public void zuordnen(Auftrag a) {
    }

    /**
     * get's specific samples and their amount
     *
     * @param a the job
     * @return the Pair of samples with the amount.
     */
    public Set<Pair<QualitativeEigenschaft, Integer>> getAngeforderteProben(Auftrag a) {
        return null;
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
}
