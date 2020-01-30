package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.User;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
@Getter
public class StandortService {
    private Set<Standort> standorte;

    @PostConstruct
    public void init() {
        // FIXME Load from db
        this.standorte = createDefaulStandort();
    }

    private Set<Standort> createDefaulStandort() {
        // FIXME Load from DB
        Standort s = new Standort("Test Standort");
        Set<Standort> ergebnis = new HashSet<>();
        ergebnis.add(s);
        return ergebnis;
    }

    public void addStandort(Standort standort) {
        this.standorte.add(standort);
    }

    public void löscheStandort(Standort standort) {
        this.standorte.remove(standort);
    }

    public Standort findByStandort(String standort) {
        // FIXME Use String as ID or convert to String
        return this.standorte.stream().filter(c -> c.getOrt().equals(standort)).findFirst().orElse(null);
    }


}
