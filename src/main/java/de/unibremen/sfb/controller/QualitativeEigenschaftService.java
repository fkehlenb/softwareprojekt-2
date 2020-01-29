package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.QualitativeEigenschaft;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.HashSet;
import java.util.Set;

@Singleton
public class QualitativeEigenschaftService {
    private Set<QualitativeEigenschaft> eigenschaften;

    @PostConstruct
    public void init() {
        this.eigenschaften = ladeEigenschaften();
    }

    private Set<QualitativeEigenschaft> ladeEigenschaften() {
        // FIXME Connect to DB
        Set<QualitativeEigenschaft> ergebnis = new HashSet<>();
        ergebnis.add(new QualitativeEigenschaft("Default Eigenschaft"));
        return ergebnis;
    }

    public Set<QualitativeEigenschaft> getEigenschaften() {
        return eigenschaften;
    }

    public void addEigenschaft(QualitativeEigenschaft qualitativeEigenschaft) {
        this.eigenschaften.add(qualitativeEigenschaft);
    }

}
