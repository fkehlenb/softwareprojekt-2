package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.service.ProbenService;
import lombok.Getter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
@Getter
public class SingleStationBean implements Serializable {

    private ExperimentierStation station;

    @Inject
    private ProbenService probenService;

    public String singleStation(ExperimentierStation station) {
        this.station = station;
        return "singlestation.xhtml";
    }

    public List<Probe> getProben() {
        return probenService.getProbenByStandort(station.getStandort());
    }
}
