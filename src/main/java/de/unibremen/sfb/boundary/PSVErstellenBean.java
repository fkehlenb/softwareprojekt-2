package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatVorlageDAO;
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
import java.util.Set;

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
    private List<ProzessSchrittParameter> ausgewählteProzessSchrittParameter;

    @NonNull
    private List<ExperimentierStation> ausgewählteStationen;

    // Wir benötigen die Parameter und Eigenschaften um diese dann auszuwählen
    private List<ProzessSchrittParameter> verfügbareParameter;
    private List<QualitativeEigenschaft> verfügbareEigenschaften;
    private List<ProzessSchrittVorlage> verfügbarePSV;
    private List<ExperimentierStation> verfügbareStationen;

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

    @Inject
    ProzessSchrittZustandsAutomatVorlageDAO prozessSchrittZustandsAutomatVorlageDAO;

    @PostConstruct
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    public void init() {
        verfügbareParameter = prozessSchrittParameterService.getPSP();
        verfügbareEigenschaften = qualitativeEigenschaftService.getEigenschaften(); // Hier weiter einschränken
        verfügbarePSV =  prozessSchrittVorlageService.getProzessSchrittVorlagen();
        verfügbareStationen =  experimentierStationService.getEsSet();
    }

    public String erstellePSV() throws DuplicateProzessSchrittZustandsAutomatVorlageException {
        // FIXME ID Generation
        log.info("Erstelle Prozessschritt");
        // FIXME Add this
        List<String> z = new ArrayList();
        z.add("Kapput");

        ProzessSchrittZustandsAutomatVorlage v =  new ProzessSchrittZustandsAutomatVorlage(z, "Platzhalter");
        prozessSchrittZustandsAutomatVorlageDAO.persist(v);
        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(55, Duration.ofHours(Long.parseLong(dauer)), psArt,
                ausgewählteStationen, v, ausgewählteProzessSchrittParameter);
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
