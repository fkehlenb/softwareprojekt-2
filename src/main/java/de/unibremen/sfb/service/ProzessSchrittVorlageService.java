package de.unibremen.sfb.service;

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
    private List<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = erstelleStandartVorlagen();
    }

    // FIXME Add Default
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
