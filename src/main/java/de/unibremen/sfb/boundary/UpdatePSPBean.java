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

import javax.annotation.PostConstruct;
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
@Named("updatePSPBean")
@ViewScoped
@Slf4j
@Setter
@Getter
public class UpdatePSPBean implements Serializable {

    @Inject
    ProzessSchrittParameterService prozessSchrittParameterService;
    @Inject
    QualitativeEigenschaftService qualitativeEigenschaftService;
    @Inject
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    private  ProzessSchrittParameter prozessSchrittParameter;

    private String id;

    private String name;

    private List<QualitativeEigenschaft> qualitativeEigenschaften = new ArrayList<>();

    //Constructor///////
    public UpdatePSPBean() {
        try{
            id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
            qualitativeEigenschaften = (List<QualitativeEigenschaft>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("list");
            prozessSchrittParameter = (ProzessSchrittParameter) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("PSP");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /////////

    public String updatePSP(){
        try {
            id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
            ProzessSchrittParameter p =  prozessSchrittParameterService.getPSPByID(Integer.parseInt(id));
            p.setQualitativeEigenschaften(qualitativeEigenschaften);
            prozessSchrittParameterService.update(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pS?faces-redirect=true";
    }

    public List<QualitativeEigenschaft> deleteGewaltQein(String id){
        qualitativeEigenschaften.remove(qualitativeEigenschaftService.getQlEById(Integer.parseInt(id)));
        return qualitativeEigenschaften;
    }

    public List<QualitativeEigenschaft> listqlE() {
        try {
            return qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }
    public List<QuantitativeEigenschaft> listqtE() {
        try {
            return quantitativeEigenschaftService.getAllQuantitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }
    public void select(String idqE) {
        try {
            qualitativeEigenschaften.add(qualitativeEigenschaftService.getQlEById(Integer.parseInt(idqE)));
        } catch (Exception e) {
            qualitativeEigenschaften.add(quantitativeEigenschaftService.getQlEById(Integer.parseInt(idqE)));
        }
    }

}
