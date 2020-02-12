package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.exception.QualitativeEigenschaftNotFoundException;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Transactional
@Named
@RequestScoped
@Slf4j
@Data
public class QlEBean implements Serializable {

    String nameQualitativeEigenschaft;
    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    public void addQualitativeEigenschaft() throws DuplicateQualitativeEigenschaftException {
        QualitativeEigenschaft qualitativeEigenschaft = new QualitativeEigenschaft(nameQualitativeEigenschaft);
        qualitativeEigenschaft.setId(UUID.randomUUID().hashCode());
        qualitativeEigenschaftService.addQualitativeEigenschaft(qualitativeEigenschaft);
    }

    public List<QualitativeEigenschaft> findAll() {
        try {
            return qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }

    public void update() throws QualitativeEigenschaftNotFoundException {

    }

    public void editQlE(String IdQlE) {
        try {
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id",IdQlE);
            QualitativeEigenschaft qualitativeEigenschaft = qualitativeEigenschaftService.getQlEById(Integer.parseInt(IdQlE));
            qualitativeEigenschaft.setName(nameQualitativeEigenschaft);
            qualitativeEigenschaftService.edit(qualitativeEigenschaft);
            nameQualitativeEigenschaft = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteQlE(String nameQlE) {
        try {
            qualitativeEigenschaftService.remove(qualitativeEigenschaftService.getQlEById(Integer.parseInt(nameQlE)));
        } catch (Exception e) {

        }
    }

}
