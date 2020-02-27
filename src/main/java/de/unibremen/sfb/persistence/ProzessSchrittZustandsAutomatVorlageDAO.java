package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/** This class manages the process step state template */
@Slf4j
public class ProzessSchrittZustandsAutomatVorlageDAO extends ObjectDAO<ProzessSchrittZustandsAutomatVorlage> {

    /** Add new process step state template to the database
     * @param pszv - the process step state template to add to the database
     * @throws DuplicateProzessSchrittZustandsAutomatVorlageException if the process step state template is already in the database */
    public void persist(ProzessSchrittZustandsAutomatVorlage pszv) throws DuplicateProzessSchrittZustandsAutomatVorlageException {
        if (pszv!=null){
            synchronized (ProzessSchrittZustandsAutomatVorlage.class){
                if (em.contains(pszv)){
                    throw new DuplicateProzessSchrittZustandsAutomatVorlageException();
                }
                em.persist(pszv);
            }
        }
    }

    /** Update an existing process step state template in the database
     * @param pszv - the process step state template to update in the database
     * @throws ProzessSchrittVorlageNotFoundException if the process step state template cannot be found in the database */
    public void update(ProzessSchrittZustandsAutomatVorlage pszv) throws ProzessSchrittVorlageNotFoundException{
        if (pszv!=null){
            //if (!em.contains(pszv)){
            //    throw new ProzessSchrittVorlageNotFoundException();
            //}
            em.merge(pszv);
        }
    }

    /** Remove an existing process step state template from the database
     * @param pszv - the process step state template to remove from the database
     * @throws ProzessSchrittVorlageNotFoundException if the process step state template cannot be found in the database */
    public void remove(ProzessSchrittZustandsAutomatVorlage pszv) throws ProzessSchrittVorlageNotFoundException{
        if (pszv!=null){
            if (!em.contains(pszv)){
                throw new ProzessSchrittVorlageNotFoundException();
            }
            pszv.setValidData(false);
            update(pszv);
        }
    }

    public List<ProzessSchrittZustandsAutomatVorlage> getAll(){
        try {
            List<ProzessSchrittZustandsAutomatVorlage> es = em.createQuery("SELECT e FROM ProzessSchrittZustandsAutomatVorlage e WHERE e.isValidData=true",get()).getResultList();
            if (es.isEmpty()){
                log.info("No ProzessSchrittZustandsAutomatVorlagee Found");
                return new ArrayList<>();
            }
            return es;
        }
        catch (Exception e){
//            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    /** @return the class of a process step state template */
    public Class<ProzessSchrittZustandsAutomatVorlage> get(){
        return ProzessSchrittZustandsAutomatVorlage.class;
    }

    public ProzessSchrittZustandsAutomatVorlage getById(int id) {
        try {
            return em.find(ProzessSchrittZustandsAutomatVorlage.class, id);
        } catch (Exception e) {
            throw new IllegalArgumentException("PS Automat not found");
        }
    }
}
