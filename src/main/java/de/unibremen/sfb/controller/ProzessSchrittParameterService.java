package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashSet;
import java.util.Set;


@Startup
@Getter
@Singleton
public class ProzessSchrittParameterService {
    private Set<ProzessSchrittParameter> parameterSet;

    @PostConstruct
    public void init() {
        // TODO Load from DB
        this.parameterSet = createDefaultParameter();
    }

    private Set<ProzessSchrittParameter> createDefaultParameter() {
        Set<ProzessSchrittParameter> ergebnis = new HashSet<ProzessSchrittParameter>();
        HashSet<QualitativeEigenschaft> eigenschaften = new HashSet<>();
        eigenschaften.add(new QualitativeEigenschaft("Default Eigenschaft"));
        ergebnis.add(new ProzessSchrittParameter("Testen", eigenschaften));
        return ergebnis;
    }

    public Set<ProzessSchrittParameter> getPSP() {
        return parameterSet;
    }

    public void addPSP(ProzessSchrittParameter prozessSchrittParameter) {
        this.parameterSet.add(prozessSchrittParameter);
    }

    public void löscheParameter(ProzessSchrittParameter parameter) {
        this.parameterSet.remove(parameter);
    }

    public ProzessSchrittParameter findByParameterId(String parameterId) {
        // FIXME Use String as ID or convert to String
        return this.parameterSet.stream().filter(c -> c.getId().equals(parameterId)).findFirst().orElse(null);
    }




}
