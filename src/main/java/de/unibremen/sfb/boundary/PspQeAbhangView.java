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

@Transactional
@Named("pspQeaBean")
@RequestScoped
@Slf4j
@Setter
@Getter
public class PspQeAbhangView implements Serializable {
    @Inject
    ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    QuantitativeEigenschaftService quantitativeEigenschaftService;

    @Inject
    QualitativeEigenschaftService qualitativeEigenschaftService;

    String idqEin;

    List<ProzessSchrittParameter> prozessSchrittParameters = new ArrayList<>();

    ///////CONSTRUCTOR
    @PostConstruct
    public void init() {
        idqEin = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IDqEin");
        //todos los PSP
        prozessSchrittParameters=prozessSchrittParameterService.findByQei(Integer.parseInt(idqEin));
    }
    /////////

    public List<ProzessSchrittParameter> getProzessSchrittParameters(){
        return prozessSchrittParameters;
    }
    public String deleteAb(String id){
        ProzessSchrittParameter prozessSchrittParameter = prozessSchrittParameterService.getPSPByID(Integer.parseInt(id));
        QualitativeEigenschaft quantitativeEigenschaftToRemove = qualitativeEigenschaftService.getQlEById(Integer.parseInt(idqEin));
        List<QualitativeEigenschaft> qualitativeEigenschaftP = prozessSchrittParameter.getQualitativeEigenschaften();
        System.out.println("VALOR:::::"+quantitativeEigenschaftToRemove.getName());
        qualitativeEigenschaftP.remove(quantitativeEigenschaftToRemove);
        prozessSchrittParameter.setQualitativeEigenschaften(qualitativeEigenschaftP);
        prozessSchrittParameterService.update(prozessSchrittParameter);
        return "abEvPP?faces-redirect=true";
    }

    public String linkQIE(){
        return "qEin?faces-redirect=true";
    }
}
