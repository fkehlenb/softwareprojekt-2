package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittParameterException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Named
@ViewScoped
@Slf4j
@Setter
@Getter
public class PspBean implements Serializable {

    @Inject
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Inject
    QualitativeEigenschaftService qualitativeEigenschaftService;

    private String id;

    private String name;

    List<QualitativeEigenschaft> qualitativeEigenschafts = new ArrayList<>();


    public String creationLink() {
        return "pSCreation?faces-redirect=true";
    }

    public String add() {
        try {
            ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter();
            prozessSchrittParameter.setId(UUID.randomUUID().hashCode());
            prozessSchrittParameter.setName(name);
            prozessSchrittParameter.setQualitativeEigenschaften(qualitativeEigenschafts);
            prozessSchrittParameterService.addProcessSP(prozessSchrittParameter);
            log.info("Trying to persist der ProzzesSchritt"+prozessSchrittParameter.getName());

            return "pS?faces-redirect=true";
        } catch (Exception e) {
            log.info("Fail to persist der ProzzesSchritt");
            return "Not posible Creation";

        }
    }

    public void select(String idqE) {
        qualitativeEigenschafts.add(qualitativeEigenschaftService.getQlEById(Integer.parseInt(idqE)));
    }

    public List<QualitativeEigenschaft> qlEGewahlt() {
        try {
           return qualitativeEigenschafts;
        } catch (Exception e) {
            return null;
        }
    }

    public List<QualitativeEigenschaft> qlE() {
        try {
            return qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }

    public List<ProzessSchrittParameter> findAll() {
        return prozessSchrittParameterService.getAll();
    }
}
