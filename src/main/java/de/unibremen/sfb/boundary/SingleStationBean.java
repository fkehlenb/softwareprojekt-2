package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ExperimentierStationZustand;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
@Getter
@Slf4j
public class SingleStationBean implements Serializable {

    private ExperimentierStation station;

    @Inject
    private ProbenService probenService;

    @Inject
    private ExperimentierStationService esService;

    @Inject
    private TechnologeView technologeView;

    public String singleStation(ExperimentierStation station) {
        this.station = station;
        return "singlestation.xhtml";
    }

    /**
     * reports an experimentation station as broken
     */
    public void reportBroken() {
        technologeView.reportBroken(station); //FIXME hier wie unten aufrufen

        try {
            esService.setZustand(station, ExperimentierStationZustand.KAPUTT);
            log.info("ExperimentierStation " + station.getEsID() + "was reported as broken.");
        } catch (ExperimentierStationNotFoundException e) {
            e.printStackTrace();
            log.info("an error occurred trying to report ExperimentierStation " + station.getEsID() + " as broken: " + e.getMessage());
        }
    }

    public List<Probe> getProben() {
        return esService.getProben(station);
    }

    public SingleStationBean() {}
}
