package de.unibremen.sfb.persistence;


import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the process step state template
 */
@Slf4j
public class ProzessSchrittZustandsAutomatVorlageDAO extends ObjectDAO<ProzessSchrittZustandsAutomatVorlage> {

    /**
     * Add new process step state template to the database
     *
     * @param pszv - the process step state template to add to the database
     * @throws DuplicateProzessSchrittZustandsAutomatVorlageException if the process step state template is already in the database
     */
    public void persist(ProzessSchrittZustandsAutomatVorlage pszv) throws DuplicateProzessSchrittZustandsAutomatVorlageException {
        if (pszv != null) {
            synchronized (ProzessSchrittZustandsAutomatVorlage.class) {
                if (em.contains(em.find(get(), pszv.getId()))) {
                    throw new DuplicateProzessSchrittZustandsAutomatVorlageException();
                }
                em.persist(pszv);
            }
        }
    }

    /**
     * Update an existing process step state template in the database
     *
     * @param pszv - the process step state template to update in the database
     * @throws ProzessSchrittVorlageNotFoundException if the process step state template cannot be found in the database
     */
    public void update(ProzessSchrittZustandsAutomatVorlage pszv) throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        if (pszv != null) {
            if (!em.contains(em.find(get(), pszv.getId()))) {
                throw new ProzessSchrittZustandsAutomatVorlageNotFoundException();
            }
            em.merge(pszv);
        }
    }

    /**
     * Remove an existing process step state template from the database
     *
     * @param pszv - the process step state template to remove from the database
     * @throws ProzessSchrittZustandsAutomatVorlageNotFoundException if the process step state template cannot be found in the database
     */
    public void remove(ProzessSchrittZustandsAutomatVorlage pszv) throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        try {
            if (pszv != null) {
                if (!em.contains(em.find(get(), pszv.getId()))) {
                    throw new ProzessSchrittZustandsAutomatVorlageNotFoundException();
                }
                pszv.setValidData(false);
                update(pszv);
            }
        }
        catch (Exception e){
            throw new ProzessSchrittZustandsAutomatVorlageNotFoundException();
        }
    }

    /**
     * Get a list of all process step state automaton templates
     *
     * @return a list of all process step state automaton templates or an empty arraylist
     */
    public List<ProzessSchrittZustandsAutomatVorlage> getAll() {
        try {
            List<ProzessSchrittZustandsAutomatVorlage> es = em.createQuery("SELECT e FROM ProzessSchrittZustandsAutomatVorlage e WHERE e.isValidData=true", get()).getResultList();
            if (es.isEmpty()) {
                log.info("No ProzessSchrittZustandsAutomatVorlagee Found");
                return new ArrayList<>();
            }
            return es;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * @return the class of a process step state template
     */
    public Class<ProzessSchrittZustandsAutomatVorlage> get() {
        return ProzessSchrittZustandsAutomatVorlage.class;
    }

    /**
     * Get a process step state automaton template using its id
     *
     * @param id - the id of the process step state automaton
     * @return the process step state automaton matching the entered id
     * @throws ProzessSchrittZustandsAutomatVorlageNotFoundException on failure
     */
    public ProzessSchrittZustandsAutomatVorlage getById(int id) throws ProzessSchrittZustandsAutomatVorlageNotFoundException {
        try {
            return em.find(ProzessSchrittZustandsAutomatVorlage.class, id);
        } catch (Exception e) {
            throw new ProzessSchrittZustandsAutomatVorlageNotFoundException();
        }
    }
}
