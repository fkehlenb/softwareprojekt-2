package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import de.unibremen.sfb.service.*;
import de.unibremen.sfb.model.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("psvErstellenBean")
@ViewScoped
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

    private @NonNull List<Bedingung> ausgewaehlteBedingungen;

    @NonNull
    private List<ExperimentierStation> ausgewaehlteStationen;

    // Wir benoetigen die Parameter und Eigenschaften um diese dann auszuwaehlen
    private List<Bedingung> verfuegbareBedingunen;
    private List<ExperimentierStation> verfuegbareStationen;
    private List<ProzessSchrittVorlage> verfuegbarePSV;

    private List<Bedingung> ausBedingunen;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;


    @Inject
    transient private BedingungService bedingungService; // FIXME WhyThow https://stackoverflow.com/a/32284585

    @Inject
    private ExperimentierStationService experimentierStationService;

    @Inject
    ZustandsService  zustandsService;

    @Inject
    private ExperimentierStationDAO esDAO;

    private ProzessSchrittVorlageDAO prozessSchrittVorlageDAO;

    @PostConstruct
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    public void init() {
        verfuegbareBedingunen = bedingungService.getAll();
        verfuegbarePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        verfuegbareStationen = experimentierStationService.getESListe();
        zustandsService.getPsZustaende();
    }


    public String erstellePSV() {
        log.info("Erstelle Prozessschritt");
        // FIXME Add Service for Zustaende
        List<String> z = new ArrayList();
        z.add("Kapput");
        // FIXME Wehre is es, persist auf  de.unibremen.sfb.model.ProzessSchrittVorlage.zustandsAutomat -> de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage

        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), Duration.ofHours(Long.parseLong(dauer)), psArt,
                ausgewaehlteStationen, ausgewaehlteBedingungen);
        prozessSchrittVorlageService.addVorlage(psv);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Prozessschrittvorlage:  " + psv.getPsVID() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "pkAdmin/createOrder.xhtml?faces-redirect=true";
    }

    public void onRowEdit(RowEditEvent<ProzessSchrittVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        prozessSchrittVorlageService.edit(event.getObject());
        FacesMessage msg = new FacesMessage("PSV Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
