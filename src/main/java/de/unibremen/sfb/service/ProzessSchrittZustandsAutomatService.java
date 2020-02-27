package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.List;

@Singleton
/*
  Service fuer ProzessSchrittZustandsAutomatn
  Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittZustandsAutomat in einer ProzessKettenVorlage
 */
public class ProzessSchrittZustandsAutomatService {

    @Inject
    ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;

    /**
     * Loesche die Liste dieser ProzessSchrittZustandsAutomaten
     * @param selpsza die List der zu loeschenden ProzessSchrittZustandsAutomaten
     * @throws ProzessSchrittZustandsAutomatNotFoundException falls es ihn nicht gibt
     */
    public void delete(List<ProzessSchrittZustandsAutomat> selpsza) throws ProzessSchrittZustandsAutomatNotFoundException {
        for (ProzessSchrittZustandsAutomat p :
                selpsza) {
            prozessSchrittZustandsAutomatDAO.remove(p);
        }
    }

   /* Bearbeite die Liste dieser ProzessSchrittZustandsAutomaten
     * @param selpsza die List der zu loeschenden ProzessSchrittZustandsAutomaten
     * @throws ProzessSchrittZustandsAutomatNotFoundException
     */
    public void edit(ProzessSchrittZustandsAutomat object) throws ProzessSchrittZustandsAutomatNotFoundException {
        prozessSchrittZustandsAutomatDAO.update(object);
    }

    /* Hinzufuegen eines ProzessSchrittZustandsAutomaten
     * @param prozessSchrittZustandsAutomat der Automat
     * @throws ProzessSchrittZustandsAutomatNotFoundException
     */
    public void add(ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat) throws DuplicateProzessSchrittZustandsAutomatException {
        prozessSchrittZustandsAutomatDAO.persist(prozessSchrittZustandsAutomat);
    }
}

