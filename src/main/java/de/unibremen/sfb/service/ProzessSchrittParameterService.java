package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.ProzessSchrittParameterNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Startup
@Data
@Getter
@Singleton
@Slf4j
public class ProzessSchrittParameterService implements Serializable {
    private List<ProzessSchrittParameter> parameterList;

    @Inject
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;

    @PostConstruct
    public void init() {
       this.parameterList = prozessSchrittParameterDAO.getAll();
    }



    public List<ProzessSchrittParameter> getPSP() {
        return parameterList;
    }

    public void addPSP(ProzessSchrittParameter prozessSchrittParameter) {
        this.parameterList.add(prozessSchrittParameter);
    }

    public List<QualitativeEigenschaft> getEigenschaften(ProzessSchrittParameter p) {
        return p.getQualitativeEigenschaften();
    }

    public void loscheParameter(ProzessSchrittParameter parameter) {
        this.parameterList.remove(parameter);
        try {
            log.info("Trying remove ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            prozessSchrittParameterDAO.remove(parameter);
        } catch (Exception e) {
            log.info("Failen remove ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
        }
    }

    public ProzessSchrittParameter getPSPByID(int idPSP) {

        try {
            log.info("Trying remove ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            return prozessSchrittParameterDAO.getPSPByID(idPSP);
        } catch (Exception e) {
            log.info("Failen remove ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            return null;
        }
    }

    public ProzessSchrittParameter findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.parameterList.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    //////// Santiago Implementierung ////
//////////////////////////////////////////
    public void addProcessSP(ProzessSchrittParameter prozessSchrittParameter) {
        try {
            log.info("Trying persist ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            prozessSchrittParameterDAO.persist(prozessSchrittParameter);
        } catch (Exception e) {
            log.info("error persist ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
        }
    }
    public List<ProzessSchrittParameter> getAll() {
        try {
            log.info("Trying get all ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            return prozessSchrittParameterDAO.getAll();
        } catch (Exception e) {
            log.info("error get all ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            return null;
        }
    }

    public void update(ProzessSchrittParameter prozessSchrittParameter) {
        try {
            prozessSchrittParameterDAO.update(prozessSchrittParameter);
        } catch (ProzessSchrittParameterNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<ProzessSchrittParameter> findByQei(int idqEin){
        List<ProzessSchrittParameter> pspList = prozessSchrittParameterDAO.getAll();
        List<ProzessSchrittParameter> prozessSchrittParameters = new ArrayList<>();

        //lista para guardar los PSP si tiene Abhangig
        for (ProzessSchrittParameter psp : pspList) {
            List<QualitativeEigenschaft> qualitativeEigenschaftList = psp.getQualitativeEigenschaften();
            for (QualitativeEigenschaft ql : qualitativeEigenschaftList) {
                if (ql.getId() == idqEin) {
                    prozessSchrittParameters.add(psp);
                    System.out.println("Post");
                }
            }
        }
        return prozessSchrittParameters;
    }

}
