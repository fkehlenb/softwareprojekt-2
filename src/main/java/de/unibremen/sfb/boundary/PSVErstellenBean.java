package de.unibremen.sfb.boundary;

import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import de.unibremen.sfb.service.*;
import de.unibremen.sfb.model.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("psvErstellenBean")
@SessionScoped
@Getter
@Setter
@Log
public class PSVErstellenBean implements Serializable {

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
    private List<ExperimentierStation> verfügbareStationen;
    private List<ProzessSchrittVorlage> verfügbarePSV;


    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    @Inject
    private ExperimentierStationService experimentierStationService;

    @Inject
    ZustandsService  zustandsService;

    @Inject
    private ExperimentierStationDAO esDAO;

    @Inject
    private ProzessSchrittVorlageDAO prozessSchrittVorlageDAO;

    @PostConstruct
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    public void init() {
        verfügbareParameter = prozessSchrittParameterService.getPSP();
        verfügbareEigenschaften = qualitativeEigenschaftService.getEigenschaften(); // Hier weiter einschränken
        verfügbarePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
//        verfügbareStationen = experimentierStationService.getESListe();
        verfügbareStationen = esDAO.getAll();
        zustandsService.getPsZustaende();
    }

    public String erstellePSV() {
        log.info("Erstelle Prozessschritt");
        // FIXME Add Service for Zustaende
        List<String> z = new ArrayList();
        z.add("Kapput");
        // FIXME Wehre is es, persist auf  de.unibremen.sfb.model.ProzessSchrittVorlage.zustandsAutomat -> de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage

        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), Duration.ofHours(Long.parseLong(dauer)), psArt,
                ausgewählteStationen, new ProzessSchrittZustandsAutomatVorlage(z, "Platzhalter"), ausgewählteProzessSchrittParameter);
        prozessSchrittVorlageService.persist(psv);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Prozessschrittvorlage:  " + psv.getPsVID() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "pkAdmin/createOrder.xhtml?faces-redirect=true";
    }

}
