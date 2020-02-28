package de.unibremen.sfb.boundary;

import de.unibremen.sfb.controller.ProbeController;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.ProbenZustand;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Getter
@Setter
@Slf4j
@Named
public class ProbeMeldenView implements Serializable {

    private String probeMeldenID;

    @Inject
    private ProbenService probenService;

    /**
     * reports a sample as lost
     * @param p the sample
     */
    public void reportLostProbe(Probe p) {
        try {
            probenService.setZustandForProbe(p, ProbenZustand.VERLOREN);
            log.info("sample " + p.getProbenID() + " was reported as missing");
        }
        catch(ProbeNotFoundException e) {
            e.printStackTrace();
            log.info("sample " +p.getProbenID()+ " could not be found when trying to report as missing");
        }
        catch(IllegalArgumentException e) {
            errorMessage("invalid input");
        }
    }

    /**
     * reports a sample as lost by its id
     * @param id the id of the sample to be reported
     */
    public void reportLostProbe(String id) {
        if(id==null) {
            errorMessage("invalid input");
        }
        else {
            try {
                reportLostProbe(probenService.getProbeById(id));
            } catch (ProbeNotFoundException e) {
                e.printStackTrace();
                log.info("an error occurred trying to report a sample as lost: sample " + id + "could not be found in the database");
            }
        }
    }

    /**
     * reports the sample with the id written in probeMeldenID as broken
     */
    public void reportLostProbe() {
        reportLostProbe(probeMeldenID);
    }

    /**
     * creates and sends an error message
     * @param e error messsage
     */
    public void errorMessage(String e) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e, null));
        log.info("an error occurred" + e);
    }

}
