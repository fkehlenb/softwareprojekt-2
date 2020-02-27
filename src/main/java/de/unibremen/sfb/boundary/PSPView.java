package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

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
@Named("dtPSPBean")
@ViewScoped
@Slf4j
@Setter
@Getter
public class PSPView implements Serializable {

    @Inject
    ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    QualitativeEigenschaftService qualitativeEigenschaftService;

    @Inject
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    private String id;

    private String name;

    private List<QualitativeEigenschaft> qualitativeEigenschaften = new ArrayList<>();

    public String creationLink() {
        return "addPSP?faces-redirect=true";

    }


    public String add() {
        try {
            ProzessSchrittParameter prozessSchrittParameter = new ProzessSchrittParameter();
            prozessSchrittParameter.setId(UUID.randomUUID().hashCode());
            prozessSchrittParameter.setName(name);
            prozessSchrittParameter.setQualitativeEigenschaften(qualitativeEigenschaften);
            prozessSchrittParameterService.addProcessSP(prozessSchrittParameter);
            log.info("Trying to persist der ProzzesSchritt"+prozessSchrittParameter.getName());
            return "psp?faces-redirect=true";
        } catch (Exception e) {
            log.info("Fail to persist der ProzzesSchritt");
            return "Not posible Creation";

        }
    }

    public void select(String idqE) {
        try {
            qualitativeEigenschaften.add(qualitativeEigenschaftService.getQlEById(Integer.parseInt(idqE)));
        } catch (Exception e) {
            qualitativeEigenschaften.add(quantitativeEigenschaftService.getQlEById(Integer.parseInt(idqE)));
        }
    }

    public List<QualitativeEigenschaft> qlEGewahlt() {
        try {
           return qualitativeEigenschaften;
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

    public List<QuantitativeEigenschaft> qtE() {
        try {
            return quantitativeEigenschaftService.getAllQuantitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }

    public void deletePSP(String id){
        try {
            prozessSchrittParameterService.loscheParameter(prozessSchrittParameterService.getPSPByID(Integer.parseInt(id)));
        } catch (Exception e) {
            log.info("Failen remove ProzessSchrittParameterDAO Class=ProzessSchrittParameterService");
            e.printStackTrace();
        }
    }

   public String Edit(String id){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("id",id);
        List<QualitativeEigenschaft> list=prozessSchrittParameterService.getPSPByID(Integer.parseInt(id)).getQualitativeEigenschaften();
        ProzessSchrittParameter prozessSchrittParameter = prozessSchrittParameterService.getPSPByID(Integer.parseInt(id));
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("PSP",prozessSchrittParameter);
       FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("list",list);
        return "updatePSP?faces-redirect=true";
    }

    public List<ProzessSchrittParameter> findAll() {
        return prozessSchrittParameterService.getAll();
    }
}