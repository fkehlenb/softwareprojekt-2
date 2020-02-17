package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import de.unibremen.sfb.service.ExperimentierStationService;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import de.unibremen.sfb.service.QualitativeEigenschaftService;
import de.unibremen.sfb.model.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Named("psvErstellenBean")
@RequestScoped
@Getter
@Setter
@Log
public class PSVErstellenBean {

    @NotEmpty
    private int psVID;

    @NonNull
    private String dauer;

    @NonNull
    private List<TraegerArt> eingabeTraeger;

    @NotEmpty
    private List<TraegerArt> ausgabeTraeger;

    @NonNull
    private String psArt;

    private List<ExperimentierStation> stationen;

    @NonNull
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatenVorlage;

    @NonNull
    private List<ProzessSchrittParameter> ausgewaehlteProzessSchrittParameter;

    @NonNull
    private List<ExperimentierStation> ausgewaehlteStationen;

    // Wir benoetigen die Parameter und Eigenschaften um diese dann auszuwaehlen
    private List<ProzessSchrittParameter> verfuegbareParameter;
    private List<QualitativeEigenschaft> verfuegbareEigenschaften;
    private List<ExperimentierStation> verfuegbareStationen;
    private List<ProzessSchrittVorlage> verfuegbarePSV;


    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    @Inject
    private ExperimentierStationService experimentierStationService;

    @Inject
    private ProzessSchrittVorlageDAO prozessSchrittVorlageDAO;

    @PostConstruct
    /**
     * Hier werden aus der Persitenz die benoetigten Daten Geladen
     */
    public void init() {
        verfuegbareParameter = prozessSchrittParameterService.getPSP();
        verfuegbareEigenschaften = qualitativeEigenschaftService.getEigenschaften(); // Hier weiter einschraenken
        verfuegbarePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        verfuegbareStationen = experimentierStationService.getEsSet();
    }

    public String erstellePSV() {
        log.info("Erstelle Prozessschritt");
        // FIXME Add Service for Zustaende
        List<String> z = new ArrayList();
        z.add("Kapput");

        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), Duration.ofHours(Long.parseLong(dauer)), psArt,
                ausgewaehlteStationen, new ProzessSchrittZustandsAutomatVorlage(z, "Platzhalter"), ausgewaehlteProzessSchrittParameter);
        try {
            prozessSchrittVorlageDAO.persist(psv);
        } catch (DuplicateProzessSchrittVorlageException e) {
            e.printStackTrace();
        }
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Prozessschrittvorlage:  " + psv.getPsVID() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "pkAdmin/createOrder.xhtml?faces-redirect=true";
    }

}
