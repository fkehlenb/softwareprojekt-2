package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;


@Startup
@Data
@Getter
@Transactional
@Slf4j
public class ProzessSchrittParameterService implements Serializable {



    @Inject
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;
    //////////// Liam Implementierung ////
//////////////////////////////////////////
    private List<ProzessSchrittParameter> parameterList;

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
    }

    public ProzessSchrittParameter findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.parameterList.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init() {
        this.parameterList = prozessSchrittParameterDAO.getAll();
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

}
