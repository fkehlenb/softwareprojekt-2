package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittVorlage;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
/**
 * Service für ProzessSchrittVorlagen
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufügen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class ProzessSchrittVorlageService {
    private Set<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = erstelleStandartVorlagen();
    }

    // FIXME Add Default
    private Set<ProzessSchrittVorlage> erstelleStandartVorlagen() {
        return new HashSet<>();
    }

    public Set<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return vorlagen;
    }

    public void addVorlage(ProzessSchrittVorlage prozessSchrittVorlage) {
        this.vorlagen.add(prozessSchrittVorlage);
    }
}
