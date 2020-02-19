package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
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

    private  ProzessSchrittParameter prozessSchrittParameter;

    private String id;

    private String name;

    private List<QualitativeEigenschaft> qualitativeEigenschaften = new ArrayList<>();

    //Constructor///////
    public UpdatePSPBean() {
        try{
            id=(String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
            qualitativeEigenschaften = (List<QualitativeEigenschaft>) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("list");
            prozessSchrittParameter =(ProzessSchrittParameter) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("PSP");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /////////

    public void updatePSP(){
        try {
            id = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("id");
            ProzessSchrittParameter p =  prozessSchrittParameterService.getPSPByID(Integer.parseInt(id));
            prozessSchrittParameterService.update(p);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<QualitativeEigenschaft> deleteGewaltQein(String id){
        qualitativeEigenschaften.remove(qualitativeEigenschaftService.getQlEById(Integer.parseInt(id)));
        return qualitativeEigenschaften;
    }

    public List<QualitativeEigenschaft> qlE() {
        try {
            return qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }


}
