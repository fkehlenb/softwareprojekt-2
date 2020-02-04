package de.unibremen.sfb.service;

import de.unibremen.sfb.model.QualitativeEigenschaft;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class QualitativeEigenschaftService {
    private List<QualitativeEigenschaft> eigenschaften;

    @PostConstruct
    public void init() {
        this.eigenschaften = ladeEigenschaften();
    }

    private List<QualitativeEigenschaft> ladeEigenschaften() {
        // FIXME Connect to DB
        List<QualitativeEigenschaft> ergebnis = new ArrayList<>();
        ergebnis.add(new QualitativeEigenschaft("Default Eigenschaft"));
        return ergebnis;
    }

    public List<QualitativeEigenschaft> getEigenschaften() {
        return eigenschaften;
    }

    public void addEigenschaft(QualitativeEigenschaft qualitativeEigenschaft) {
        this.eigenschaften.add(qualitativeEigenschaft);
    }

}
