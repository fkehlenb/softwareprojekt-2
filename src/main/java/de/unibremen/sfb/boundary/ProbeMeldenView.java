package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import java.io.Serializable;

import static de.unibremen.sfb.model.ProbenZustand.VERLOREN;

@ViewScoped
@Getter
@Setter
@Slf4j
@Named
public class ProbeMeldenView implements Serializable {

    private String probeMeldenID;
    @Min(0) private int probenAnzahl;

    @Inject
    private ProbenService probenService;


    /**
     * Reports samples as lost.
     */
    public void reportLost() {
        try {
            if(probenService.getProbeById(probeMeldenID).getAnzahl() >= probenAnzahl) {
                try {

                    probenService.probeVerloren(probenService.getProbeById(probeMeldenID), probenService.getProbeById(probeMeldenID).getAnzahl(), probenAnzahl, probenService.getProbeById(probeMeldenID).getLost());
                    log.info(probenAnzahl + " Samples of " + probeMeldenID + " were reported as missing.");
                    facesNotification(probenAnzahl + " Samples of " + probeMeldenID + " were reported as missing.");
                    //Checks if all samples are lost then sets the Zustand to VERLOREN
                    if (alleLost()){
                        Probe p;
                        p= probenService.getProbeById(probeMeldenID);
                        p.setZustand(VERLOREN);
                        probenService.update(p);
                        facesNotification("Die Probe mit der ID:" + probeMeldenID +" gilt nun als komplett Verloren!" );
                    }

                } catch (ProbeNotFoundException e) {
                    log.info("sample " + probeMeldenID + " could not be found when trying to report as missing.");
                }
            } else {
                log.info("The given number of samples is higher than the number of samples in the database.");
                facesError("The given number of samples is higher than the number of samples in the database.");
            }
        } catch (ProbeNotFoundException e) {
            facesError("sample " + probeMeldenID + " could not be found when trying to report as missing.");
            log.info("sample " + probeMeldenID + " could not be found when trying to report as missing.");
        }
    }

    public boolean alleLost (){
        try {
            if(probenService.getProbeById(probeMeldenID).getAnzahl() ==0){
                Probe p;
                p= probenService.getProbeById(probeMeldenID);
                p.setZustand(VERLOREN);
                probenService.update(p);
                return true;
            }
        } catch (ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("Probe " + probeMeldenID + " wurde nicht gefunden!");
            facesError("Probe " + probeMeldenID + " wurde nicht gefunden!");
        }
        return false;
    }

    /**
     * Reports samples as lost with the Sample and number.
     *
     * @param p sample which is lost
     * @param anzahl number of samples which are lost
     */
    public void reportLostProbe(Probe p, int anzahl) {
        try {
            try {
                probenService.setZustandForProbe(p, anzahl, VERLOREN);
            } catch (DuplicateProbeException e) {
                e.printStackTrace();
            }
            log.info("sample " + p.getProbenID() + " was reported as missing");
        }
        catch(ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("sample " +p.getProbenID()+ " could not be found when trying to report as missing");
        }
        catch(IllegalArgumentException e) {
            facesError("invalid input");
            //Test
        }
    }

    /**
     * Reports a sample as lost by its id.
     *
     * @param id the id of the sample to be reported
     * @param anzahl the number of samples to be reported
     */
    public void reportLostProbe(@NonNull String id, @NonNull int anzahl) {

            try {
                if(probenService.getProbeById(id).getAnzahl() >= anzahl) {
                    reportLostProbe(probenService.getProbeById(id), anzahl);
                } else {
                    log.info("The given number of samples is higher than the number of samples in the database.");
                    facesError("The given number of samples is higher than the number of samples in the database.");
                    throw new Exception();
                }
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to report a sample as lost: sample " + id + "could not be found in the database");
            } catch (Exception f){
                f.printStackTrace();
                log.error("haloo");
            }

    }

    /**
     * reports a number of samples with the id written in probeMeldenID as lost
     */
    public void reportLostProbe() {
        reportLostProbe(probeMeldenID, probenAnzahl);
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
