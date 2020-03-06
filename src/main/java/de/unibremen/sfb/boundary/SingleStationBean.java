package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
@Getter
@Slf4j
public class SingleStationBean implements Serializable {

    /**
     * Currently selected station
     */
    private ExperimentierStation station;

    /**
     * Sample service
     */
    @Inject
    private ProbenService probenService;

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService esService;

    /**
     * WHY
     */
    @Inject
    private TechnologeView technologeView;

    /**
     * Job Service
     */
    @Inject
    private AuftragService auftragService;

    /**
     * Set the currently selected station
     */
    public String singleStation(ExperimentierStation station) {
        this.station = station;
        return "singlestation.xhtml";
    }

    /**
     * Report the current station as broken
     */
    public void reportBroken() {
        try {
            ExperimentierStation es = esService.getById(station.getEsID());
            if (!es.getStatus().equals(ExperimentierStationZustand.KAPUTT)) {
                es.setStatus(ExperimentierStationZustand.KAPUTT);
                esService.updateES(es);
                station = esService.getById(station.getEsID());
                log.info("Experimenting station was reported broken!");
                facesNotification("Experimenting station was reported broken!");
            } else {
                facesError("Experimenting station already marked as broken!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to set experimenting station as broken! ID " + station.getEsID());
            facesError("Couldn't set experimenting station as broken!");
        }
    }

    /**
     * Get all samples from an experimenting station
     * TODO test
     * @return a list of the experimenting station's samples
     */
    public List<Probe> getProben() {
        if (station.getCurrentPS() == null) {
            return new ArrayList<>();
        }
        List<Probe> proben = new ArrayList<>();
        Auftrag auftrag = auftragService.getAuftrag(station.getCurrentPS());
        if (auftrag == null) {
            return new ArrayList<>();
        }
        List<Traeger> Traeger = new ArrayList<>(auftrag.getTraeger());
        for (Traeger t :
                Traeger) {
            proben.addAll(t.getProben());
        }
        return proben;
    }

    /**
     * Constructor
     */
    public SingleStationBean() {
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
