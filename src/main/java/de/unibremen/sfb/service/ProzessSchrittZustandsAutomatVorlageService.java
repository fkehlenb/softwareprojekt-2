package de.unibremen.sfb.service;
import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatVorlageDAO;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


public class ProzessSchrittZustandsAutomatVorlageService implements Serializable {
    private List<ProzessSchrittZustandsAutomatVorlage> psvVorlagen;

    @Inject
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;

    /**
     * Loesche alle diese ProzessSchrittZustandsAutomatVorlage
     * @param selpszav die zu loeschenden ProzessSchrittZustandsAutomatVorlage
     * @throws  ProzessSchrittZustandsAutomatVorlageNotFoundException  falls es die PS Zustands Automaten Vorlage nicht gibt
     */
    public void delete(List<ProzessSchrittZustandsAutomatVorlage> selpszav) throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        for (ProzessSchrittZustandsAutomatVorlage p :
                selpszav) {
            prozessSchrittZustandsAutomatVorlageDAO.remove(p);
        }
    }


    /**
     * Edit alle diese ProzessSchrittZustandsAutomatVorlage
     * @param selpszav die zu bearbeitende ProzessSchrittZustandsAutomatVorlage
     * @throws ProzessSchrittZustandsAutomatVorlageNotFoundException falls es sie nicht gibt
     */
    public void edit(ProzessSchrittZustandsAutomatVorlage selpszav) throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        prozessSchrittZustandsAutomatVorlageDAO.update(selpszav);
    }

    @PostConstruct
    /*
      Init vom Service, laedet alle Vorlagen
     */
    public void init() {
        this.psvVorlagen = prozessSchrittZustandsAutomatVorlageDAO.getAll();
    }


    /**
     * Hole alle ProzessSchrittZustandsAutomatVorlagen
     * @return alle ProzessSchrittZustandsAutomatVorlagen
     */
    public List<ProzessSchrittZustandsAutomatVorlage> getProzessSchrittZustandsAutomatVorlagen() {
        psvVorlagen = prozessSchrittZustandsAutomatVorlageDAO.getAll();
        return  psvVorlagen;
    }

    /**
     * Add a new process step template
     * @param prozessSchrittZustandsAutomatVorlage the one to be added
     * @throws DuplicateProzessSchrittZustandsAutomatVorlageException if already in the System
     */
    public void addVorlage(ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage) throws DuplicateProzessSchrittZustandsAutomatVorlageException {
        this.psvVorlagen.add(prozessSchrittZustandsAutomatVorlage);
        prozessSchrittZustandsAutomatVorlageDAO.persist(prozessSchrittZustandsAutomatVorlage);
    }

    /** Remove a process step state automaton template
     * @param pszav - the process step state automaton template to remove
     * @throws ProzessSchrittZustandsAutomatVorlageNotFoundException on failure */
    public void remove(ProzessSchrittZustandsAutomatVorlage pszav) throws ProzessSchrittZustandsAutomatVorlageNotFoundException{
        prozessSchrittZustandsAutomatVorlageDAO.remove(pszav);
    }

    /**
     * Get the ProzessSchrittZustandsAutomatVorlage by its ID
     * @param id of ProzessSchrittZustandsAutomatVorlage
     * @throws ProzessSchrittZustandsAutomatVorlageNotFoundException which corresponds to the ID
     * @return the PS Template with the id

     */
    public ProzessSchrittZustandsAutomatVorlage getByID(int id) throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        return prozessSchrittZustandsAutomatVorlageDAO.getById(id);
    }
}
