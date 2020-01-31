package de.unibremen.sfb.service;


import com.github.javafaker.Faker;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ExperimentierStationZustand;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Startup
@Getter
@Singleton
public class ExperimentierStationService {
    private List<ExperimentierStation> esSet;

    @Inject
    private ExperimentierStationDAO esDao;

    @PostConstruct
    public void init() {
        // TODO Load from DB
        this.esSet= esDao.getAll();
    }


    public void addES(ExperimentierStation experimentierStation) {
        this.esSet.add(experimentierStation);
    }

    public void löscheES(ExperimentierStation experimentierStationr) {
        this.esSet.remove(experimentierStationr);
    }

    public ExperimentierStation findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.esSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }


}
