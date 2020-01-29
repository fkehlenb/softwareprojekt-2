package de.unibremen.sfb.controller;


import com.github.javafaker.Faker;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ExperimentierStationZustand;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.User;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashSet;
import java.util.Set;


@Startup
@Getter
@Singleton
public class ExperimentierStationService {
    private Set<ExperimentierStation> esSet;

    @PostConstruct
    public void init() {
        // TODO Load from DB
        this.esSet= createDefaultStation();
    }

    private Set<ExperimentierStation> createDefaultStation() {
        Set<ExperimentierStation> ergebnis = new HashSet<ExperimentierStation>();

        for (int i = 0; i < 20; i++) {
            Faker faker = new Faker();
            ergebnis.add(new ExperimentierStation(faker.random().nextInt(0, 500), new Standort(faker.lordOfTheRings().location()),
                    faker.lordOfTheRings().character(), ExperimentierStationZustand.VERFUEGBAR, new HashSet<User>()));
        }
        return ergebnis;
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
