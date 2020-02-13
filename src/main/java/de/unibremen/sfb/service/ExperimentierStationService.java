package de.unibremen.sfb.service;


import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;


@Startup
@Getter
public class ExperimentierStationService {
    private List<ExperimentierStation> esSet;

    @Inject
    private ExperimentierStationDAO esDao;

    @PostConstruct
    public void init() {
        this.esSet= esDao.getAll();
    }


    public void addES(ExperimentierStation experimentierStation) {
        this.esSet.add(experimentierStation);
    }

    public void loescheES(ExperimentierStation experimentierStationr) {
        this.esSet.remove(experimentierStationr);
    }

    public ExperimentierStation findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.esSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }


}
