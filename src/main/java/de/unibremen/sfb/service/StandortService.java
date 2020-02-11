package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

@Getter
public class StandortService {

    /** list of all locations */
    private List<Standort> standorte;

    /** The dao for the locations */
    @Inject
    private StandortDAO standortDAO;

    /** Init is called on startup */
    @PostConstruct
    public void init() {
        // FIXME Load from db
        standorte = standortDAO.getAll();
    }

    /** Add a new location */
    public void addStandort(Standort standort) {
        standorte.add(standort);
    }

    /** Remove a location */
    public void loescheStandort(Standort standort) {
        this.standorte.remove(standort);
    }

    /** Find a standort based on ts location */
    public Standort findByStandort(String standort) {
        // qFIXME Use String as ID or convert to String
        return this.standorte.stream().filter(c -> c.getOrt().equals(standort)).findFirst().orElse(null);
    }
}
