package de.unibremen.sfb.service;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
/**
 * Service fuer ProzessSchrittZustandsAutomatVorlagen
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittZustandsAutomatVorlage in einer ProzessKettenVorlage
 */
public class ProzessSchrittZustandsAutomatVorlageService {
    private Set<ProzessSchrittZustandsAutomatVorlage> automatenVorlagen;

    @PostConstruct
    public void init() {
        this.automatenVorlagen = erstelleStandartVorlagen();
    }

    // FIXME Add Default
    private Set<ProzessSchrittZustandsAutomatVorlage> erstelleStandartVorlagen() {
        Set<ProzessSchrittZustandsAutomatVorlage> ergebnis = new HashSet<>();
        List<String> zustaende = new ArrayList();
        zustaende.add("Angenommen");
        zustaende.add("In Brearbeitung");
        zustaende.add("Bearbeitet");
        zustaende.add("Weitergeleitet");

        ergebnis.add(new ProzessSchrittZustandsAutomatVorlage(zustaende, "Standart"));
        return ergebnis;
    }

    public Set<ProzessSchrittZustandsAutomatVorlage> getProzessSchrittZustandsAutomatVorlagen() {
        return automatenVorlagen;
    }

    /** Add a new process step template */
    public void addVorlage(ProzessSchrittZustandsAutomatVorlage ProzessSchrittZustandsAutomatVorlage) {
        this.automatenVorlagen.add(ProzessSchrittZustandsAutomatVorlage);
    }
}
