package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.List;


@Startup
@Getter
@Singleton
public class ProzessSchrittParameterService {
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

    public void löscheParameter(ProzessSchrittParameter parameter) {
        this.parameterList.remove(parameter);
    }

    public ProzessSchrittParameter findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.parameterList.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }




}
