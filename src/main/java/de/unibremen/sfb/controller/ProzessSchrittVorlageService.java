package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittVorlage;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
/**
 * Service f
 *
 */
public class ProzessSchrittVorlageService {
    private List<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = erstelleStandartVorlagen();
    }

    private List<ProzessSchrittVorlage> erstelleStandartVorlagen() {
        return new ArrayList<>();
    }

    public List<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return vorlagen;
    }

    public void addVorlage(ProzessSchrittVorlage prozessSchrittVorlage) {
        this.vorlagen.add(prozessSchrittVorlage);
    }
}
