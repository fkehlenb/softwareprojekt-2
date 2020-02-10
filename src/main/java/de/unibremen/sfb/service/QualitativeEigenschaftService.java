package de.unibremen.sfb.service;

import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.persistence.QualitativeEigenschaftDAO;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class QualitativeEigenschaftService {
    private List<QualitativeEigenschaft> eigenschaften;

    @Inject
    QualitativeEigenschaftDAO qeDAO;

    @PostConstruct
    // FIXME
    public void init() {
//        this.eigenschaften = qeDAO.getAll();
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
