package de.unibremen.sfb.service;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatVorlageDAO;
import de.unibremen.sfb.persistence.TraegerArtDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
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
    private List<ProzessSchrittZustandsAutomatVorlage> psvVorlagen;

    @Inject
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;

    @PostConstruct
    public void init() {
        this.psvVorlagen = prozessSchrittZustandsAutomatVorlageDAO.getAll();
    }



    public List<ProzessSchrittZustandsAutomatVorlage> getProzessSchrittZustandsAutomatVorlagen() {
        return psvVorlagen;
    }

    /** Add a new process step template */
    public void addVorlage(ProzessSchrittZustandsAutomatVorlage ProzessSchrittZustandsAutomatVorlage) {
        this.psvVorlagen.add(ProzessSchrittZustandsAutomatVorlage);
    }


}
