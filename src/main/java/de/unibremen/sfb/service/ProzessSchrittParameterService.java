package de.unibremen.sfb.service;

import com.github.javafaker.Faker;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
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

        for (int i = 0; i < 100; i++) {
            Faker faker = new Faker();
            HashSet<QualitativeEigenschaft> eigenschaften = new HashSet<>();
            eigenschaften.add(new QualitativeEigenschaft(faker.lordOfTheRings().location()));
            eigenschaften.add(new QuantitativeEigenschaft(faker.funnyName().name(), faker.number().randomNumber()));
            ergebnis.add(new ProzessSchrittParameter(faker.lordOfTheRings().location(), eigenschaften));
        }

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

    public ProzessSchrittParameter findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.parameterSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }




}
