package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

@Singleton
@Getter
public class StandortService {
    private List<Standort> standorte;

    @Inject
    StandortDAO standortDAO;

    @PostConstruct
    public void init() {
        // FIXME Load from db
        this.standorte = standortDAO.getAll();
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
