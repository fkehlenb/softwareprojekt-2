package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ExperimentierStationZustand;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Inject
    private AuftragService auftragService;

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

    /**
     * Hole alle Proben, die an dieser Station sind
     * @return alle Proben der Station
     */
    public List<Probe> getProben() {
        List<Probe> proben = new ArrayList<>();
       List<Traeger> Traeger = auftragService.getAuftrag(station.getCurrentPS()).getTraeger().stream().collect(Collectors.toList());
        for (Traeger t :
                Traeger) {
            proben.addAll(t.getProben());
        }
        return proben;
    }

    public SingleStationBean() {}
}
