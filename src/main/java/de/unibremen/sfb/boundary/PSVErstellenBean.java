package de.unibremen.sfb.boundary;

import de.unibremen.sfb.controller.ProzessSchrittParameterService;
import de.unibremen.sfb.controller.ProzessSchrittVorlageService;
import de.unibremen.sfb.controller.QualitativeEigenschaftService;
import de.unibremen.sfb.model.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named("psvErstellenBean")
@RequestScoped
@Getter
@Setter
public class PSVErstellenBean {

    @NotEmpty
    private  int psVID;

    @NonNull
    private Duration dauer;

    @NonNull
    private List<TraegerArt> eingabeTraeger;

    @NotEmpty
    private List<TraegerArt> ausgabeTraeger;

    @NonNull
    private String psArt;

    private Set<ExperimentierStation> stationen;

    @NonNull
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatenVorlage;

    @NonNull
    private Set<ProzessSchrittParameter> prozessSchrittParameters;

    // Wir benötigen die Parameter und Eigenschaften um diese dann auszuwählen
    private Set<ProzessSchrittParameter> verfügbareParameter;
    private Set<QualitativeEigenschaft> verfügbareEigenschaften;
    private Set<ProzessSchrittVorlage> verfügbarePSV;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    private  ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private QualitativeEigenschaftService qualitativeEigenschaftService;

    @PostConstruct
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    public void init() {
        verfügbareParameter = prozessSchrittParameterService.getPSP();
        verfügbareEigenschaften = qualitativeEigenschaftService.getEigenschaften(); // Hier weiter einschränken
        verfügbarePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
    }

    public String erstellePSV() {
        Set<ExperimentierStation> esSet = new HashSet<>(); // FIXME Automatische Zuweisung
        // FIXME ID Generation
        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(55 ,dauer, psArt, stationen, zustandsAutomatenVorlage, prozessSchrittParameters);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Prozessschrittvorlage:  " + psv.getPsVID() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "pkAdmin/createOrder.xhtml?faces-redirect=true";
    }

}
