package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import de.unibremen.sfb.persistence.TraegerArtDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
/**
 * Service fuer ProzessSchrittZustandsAutomatn
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittZustandsAutomat in einer ProzessKettenVorlage
 */
public class ProzessSchrittZustandsAutomatService {

    @Inject
    ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;

    public void delete(List<ProzessSchrittZustandsAutomat> selpszav) throws ProzessSchrittZustandsAutomatNotFoundException {
        for (ProzessSchrittZustandsAutomat p :
                selpszav) {
            prozessSchrittZustandsAutomatDAO.remove(p);
        }
    }

    public void edit(ProzessSchrittZustandsAutomat object) throws ProzessSchrittZustandsAutomatNotFoundException {
        prozessSchrittZustandsAutomatDAO.update(object);
    }

    /** Add a new process step template */
    public void add(ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat) throws DuplicateProzessSchrittZustandsAutomatException {
        prozessSchrittZustandsAutomatDAO.persist(prozessSchrittZustandsAutomat);
    }
}

