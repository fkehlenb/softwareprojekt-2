package de.unibremen.sfb.service;

import de.unibremen.sfb.model.ProzessSchrittParameter;
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
    private List<ProzessSchrittParameter> parameterSet;

    @Inject
    ProzessSchrittParameterDAO prozessSchrittParameterDAO;

    @PostConstruct
    public void init() {
       this.parameterSet = prozessSchrittParameterDAO.getAll();
    }



    public List<ProzessSchrittParameter> getPSP() {
        return parameterSet;
    }

    public void addPSP(ProzessSchrittParameter prozessSchrittParameter) {
        this.parameterSet.add(prozessSchrittParameter);
    }

    public void löscheParameter(ProzessSchrittParameter parameter) {
        this.parameterSet.remove(parameter);
    }

    public ProzessSchrittParameter findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.parameterSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }




}
