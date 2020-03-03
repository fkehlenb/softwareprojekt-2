package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateQualitativeEigenschaftException;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.model.QualitativeEigenschaft;
import de.unibremen.sfb.model.QuantitativeEigenschaft;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.service.QuantitativeEigenschaftService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
@Named
@ViewScoped
@Slf4j
@Data   // Warum Data
public class QlEView implements Serializable {

    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;
    @Inject
    private QuantitativeEigenschaftService quantitativeEigenschaftService;
    // FIXME Santi Edit Einheit

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    private String nameQualitativeEigenschaft;

    private String numberQuantitativeEigenschaft;

    private String nameQuantitativeEigenschaft;

    private String einheit;
    private List<String> einheiten;

    private List<QualitativeEigenschaft> qual;
    private List<QualitativeEigenschaft> filteredQual;
    private List<QuantitativeEigenschaft> filteredQuant;

    private List<QualitativeEigenschaft> verQualE;
    private List<QualitativeEigenschaft> verQuantE;

    @PostConstruct
    public void init() {
        einheiten = quantitativeEigenschaftService.getEinheiten();
        findAllQual();
    }

    public String addQualitativeEigenschaft() {
        QualitativeEigenschaft qualitativeEigenschaft = new QualitativeEigenschaft(UUID.randomUUID().hashCode(), nameQualitativeEigenschaft);
        qualitativeEigenschaftService.addQualitativeEigenschaft(qualitativeEigenschaft);
        nameQualitativeEigenschaft=null;
        return "qEin?faces-redirect=true";
    }

    public List<QualitativeEigenschaft> findAllQual() {
        try {
            qual = qualitativeEigenschaftService.getAllQualitativeEigenschaften();
            return qual;
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
            findAllQual();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String deleteQlE(String idQlE) {
        if (!abhangig(Integer.parseInt(idQlE))) {
            System.out.println("not abhangig");
            try {
                qualitativeEigenschaftService.remove(qualitativeEigenschaftService.getQlEById(Integer.parseInt(idQlE)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            findAllQual();
            return "qEin?faces-redirect=true";
        } else {
            System.out.println("abhanging");
            return "abEvPP?faces-redirect=true";
        }

    }

    //
    ////Quantitative
    //
    public String addQuantitativeEigenschaft() {
        try {
            String idF = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("IdQnE");
            QuantitativeEigenschaft quantitativeEigenschaft = quantitativeEigenschaftService.getQlEById(Integer.parseInt(idF));
            quantitativeEigenschaft.setName(nameQuantitativeEigenschaft);
            quantitativeEigenschaft.setWert(Integer.parseInt(numberQuantitativeEigenschaft));
            quantitativeEigenschaft.setEinheit(einheit);
            quantitativeEigenschaftService.edit(quantitativeEigenschaft);
        } catch (Exception e) {
            QuantitativeEigenschaft quantitativeEigenschaft = new QuantitativeEigenschaft();
            quantitativeEigenschaft.setName(nameQuantitativeEigenschaft);
            quantitativeEigenschaft.setId(UUID.randomUUID().hashCode());
            quantitativeEigenschaft.setWert(Integer.parseInt(numberQuantitativeEigenschaft));
            quantitativeEigenschaft.setEinheit(einheit);
            quantitativeEigenschaftService.addQuantitativeEigenschaft(quantitativeEigenschaft);
        }
        return "qEin?faces-redirect=true";
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
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IdQnE", IdQnE);
            nameQuantitativeEigenschaft = quantitativeEigenschaftService.getQlEById(Integer.parseInt(IdQnE)).getName();
            numberQuantitativeEigenschaft = NumberFormat.getInstance().format(quantitativeEigenschaftService.getQlEById(Integer.parseInt(IdQnE)).getWert());
            einheit = quantitativeEigenschaftService.getQlEById(Integer.parseInt(IdQnE)).getEinheit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean abhangig(int idQle) {
        //todos los PSP
        List<ProzessSchrittParameter> pspList = prozessSchrittParameterService.getAll();
        //lista para guardar los PSP si tiene Abhangig
        List<Integer> abhangigPSPID = new ArrayList<>();
        for (ProzessSchrittParameter psp : pspList) {
            List<QualitativeEigenschaft> qualitativeEigenschaftList = psp.getQualitativeEigenschaften();
            for (QualitativeEigenschaft ql : qualitativeEigenschaftList) {
                if (ql.getId() == idQle) {
                    abhangigPSPID.add(psp.getId());
                    System.out.println("Abhanging");
                }
            }
        }
        //Anmerkungen
        if (!abhangigPSPID.isEmpty()) {
            //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("abhangigPSPID", abhangigPSPID);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("IDqEin", Integer.toString(idQle));
            return true;
        }

        return false;
    }

}
