package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.*;

@Slf4j
@Getter
@Setter
public class ProzessKettenVorlageService implements Serializable {

    /**
     * Process chain template dao
     */
    @Inject
    private ProzessKettenVorlageDAO prozessKettenVorlageDAO;

    /**
     * Add a new process chain template to the database
     *
     * @param pkv - the process chain template to add
     * @throws DuplicateProzessKettenVorlageException on failure
     */
    public void persist(ProzessKettenVorlage pkv) throws DuplicateProzessKettenVorlageException {
        prozessKettenVorlageDAO.persist(pkv);
    }

    /**
     * Update a process chain template in the database
     *
     * @param pkv - the process chain template to update
     * @throws ProzessKettenVorlageNotFoundException on failure
     */
    public void update(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageDAO.update(pkv);
    }

    /**
     * Remove a process chain template from the database
     *
     * @param pkv - the process chain template to remove
     * @throws ProzessKettenVorlageNotFoundException on failure
     */
    public void remove(ProzessKettenVorlage pkv) throws ProzessKettenVorlageNotFoundException {
        prozessKettenVorlageDAO.remove(pkv);
    }

    @Inject
    QualitativeEigenschaftDAO qualitativeEigenschaftDAO;
    @Inject
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;
    @Inject
    StandortDAO standortDAO;
    @Inject
    UserDAO userDAO;
    @Inject
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;
   @Inject
   ProzessSchrittVorlageDAO prozessSchrittVorlageDAO;

    public void createPKVFromJSON(String json) {
        var config = new JsonbConfig().withFormatting(true);
        var jsonb = JsonbBuilder.create(config);
        List<ProzessKettenVorlage> tClass = new ArrayList<>();
        Type pkvType = new ArrayList<ProzessSchrittParameter>() {}.getClass().getGenericSuperclass();
        tClass =  jsonb.fromJson(json, pkvType);
        for (ProzessKettenVorlage pkv :
                tClass) {
            for (ProzessSchrittVorlage prozessSchrittVorlage:
                 pkv.getProzessSchrittVorlagen()) {
                for (ProzessSchrittParameter psp :
                        prozessSchrittVorlage.getProzessSchrittParameters()) {
                    for (QualitativeEigenschaft e :
                            psp.getQualitativeEigenschaften()) {
                        try {
                            qualitativeEigenschaftDAO.persist(e);
                        } catch (DuplicateQualitativeEigenschaftException ex) {
                            try {
                                qualitativeEigenschaftDAO.update(e);
                            } catch (QualitativeEigenschaftNotFoundException exc) {
                                exc.printStackTrace();
                            }
                        }
                        try {
                            prozessSchrittParameterDAO.persist(psp);
                        } catch (DuplicateProzessSchrittParameterException ex) {
                            try {
                                prozessSchrittParameterDAO.update(psp);
                            } catch (ProzessSchrittParameterNotFoundException exc) {
                                exc.printStackTrace();
                            }
                        }
                    }
                }
                try {
                    standortDAO.persist(prozessSchrittVorlage.getExperimentierStation().getStandort());
                } catch (DuplicateStandortException e) {
                    try {
                        standortDAO.update(prozessSchrittVorlage.getExperimentierStation().getStandort());
                    } catch (StandortNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                for (User u :
                        prozessSchrittVorlage.getExperimentierStation().getBenutzer()) {
                    try {
                        userDAO.persist(u);
                    } catch (DuplicateUserException e) {
                        try {
                            userDAO.update(u);
                        } catch (UserNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                try {
                    prozessSchrittZustandsAutomatVorlageDAO.persist(prozessSchrittVorlage.getZustandsAutomatVorlage());
                } catch (DuplicateProzessSchrittZustandsAutomatVorlageException e) {
                    try {
                        prozessSchrittZustandsAutomatVorlageDAO.update(prozessSchrittVorlage.getZustandsAutomatVorlage());
                    } catch (ProzessSchrittZustandsAutomatVorlageNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    prozessSchrittVorlageDAO.persist(prozessSchrittVorlage);
                } catch (DuplicateProzessSchrittVorlageException e) {
                    try {
                        prozessSchrittVorlageDAO.update(prozessSchrittVorlage);
                    } catch (ProzessSchrittVorlageNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    prozessKettenVorlageDAO.persist(pkv);
                } catch (DuplicateProzessKettenVorlageException e) {
                    try {
                        prozessKettenVorlageDAO.update(pkv);
                    } catch (ProzessKettenVorlageNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Get a process chain template using its id
     *
     * @param id - the process chain template id
     * @return the process chain template with a matching id
     * @throws ProzessKettenVorlageNotFoundException on failure
     */
    public ProzessKettenVorlage getObjById(int id) throws ProzessKettenVorlageNotFoundException {
        return prozessKettenVorlageDAO.getObjById(id);
    }

    /**
     * Get all process chain templates from the database
     *
     * @return a list of all process chain templates or an empty arraylist
     */
    public List<ProzessKettenVorlage> getAll() {
        return prozessKettenVorlageDAO.getAll();
    }
}
