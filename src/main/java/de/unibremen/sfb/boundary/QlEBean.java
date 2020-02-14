package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.UUID;

@Transactional
@Named
@RequestScoped
@Slf4j
@Data
public class QlEBean implements Serializable {

    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;
    @Inject
    private QuantitativeEigenschaftService quantitativeEigenschaftService;

    private String nameQualitativeEigenschaft;

    private String numberQuantitativeEigenschaft;

    private String nameQuantitativeEigenschaft;



    public void addQualitativeEigenschaft() throws DuplicateQualitativeEigenschaftException {
        QualitativeEigenschaft qualitativeEigenschaft = new QualitativeEigenschaft(nameQualitativeEigenschaft);
        qualitativeEigenschaft.setId(UUID.randomUUID().hashCode());
        qualitativeEigenschaftService.addQualitativeEigenschaft(qualitativeEigenschaft);
        nameQualitativeEigenschaft=null;
    }

    public List<QualitativeEigenschaft> findAllQual() {
        try {
            return qualitativeEigenschaftService.getAllQualitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }

    public void editQlE(String IdQlE) {
        try {
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
            e.printStackTrace();
        }
    }

    //
    ////Quantitative
    //
    public void addQuantitativeEigenschaft()  {
        try {
            String idF= (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdQnE");
            QuantitativeEigenschaft quantitativeEigenschaft=quantitativeEigenschaftService.getQlEById(Integer.parseInt(idF));
            quantitativeEigenschaft.setName(nameQuantitativeEigenschaft);
            quantitativeEigenschaft.setWert(Integer.parseInt(numberQuantitativeEigenschaft));
            quantitativeEigenschaftService.edit(quantitativeEigenschaft);
        } catch (Exception e) {
            QuantitativeEigenschaft quantitativeEigenschaft = new QuantitativeEigenschaft();
            quantitativeEigenschaft.setName(nameQuantitativeEigenschaft);
            quantitativeEigenschaft.setId(UUID.randomUUID().hashCode());
            quantitativeEigenschaft.setWert(Integer.parseInt(numberQuantitativeEigenschaft));
            quantitativeEigenschaftService.addQuantitativeEigenschaft(quantitativeEigenschaft);
        }
        resetvariables();
    }

    public void resetvariables() {
        nameQualitativeEigenschaft = null;
        nameQuantitativeEigenschaft = null;
        numberQuantitativeEigenschaft = null;
        //Control Vairablle
        String IdQnE = "";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdQnE", IdQnE);
    }

    public List<QuantitativeEigenschaft> findAllQuan() {
        try {
            return quantitativeEigenschaftService.getAllQuantitativeEigenschaften();
        } catch (Exception e) {
            return null;
        }
    }

    public void editQnE(String IdQnE) {
        try {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdQnE",IdQnE);
            nameQuantitativeEigenschaft=quantitativeEigenschaftService.getQlEById(Integer.parseInt(IdQnE)).getName();
            numberQuantitativeEigenschaft= NumberFormat.getInstance().format(quantitativeEigenschaftService.getQlEById(Integer.parseInt(IdQnE)).getWert());
            /*QuantitativeEigenschaft quantitativeEigenschaft = quantitativeEigenschaftService.getQlEById(Integer.parseInt(IdQnE));
            quantitativeEigenschaft.setName(nameQuantitativeEigenschaft);
            quantitativeEigenschaftService.edit(quantitativeEigenschaft);
            nameQuantitativeEigenschaft = null;
            numberQuantitativeEigenschaft=null;*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
