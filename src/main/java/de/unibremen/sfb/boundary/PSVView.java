package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("psvErstellenBean")
@ViewScoped
@Getter
@Setter
@Slf4j
@Transactional
public class PSVView implements Serializable {

    @NotEmpty
    private int psVID;

    @NonNull
    private String dauer;
    @NonNull
    private String name;

    @NonNull
    private List<TraegerArt> eingabeTraeger;

    @NotEmpty
    private List<TraegerArt> ausgabeTraeger;

    @NonNull
    private String psArt;

    @NonNull
    private List<ExperimentierStation> stationen;

    @NonNull
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatenVorlage;

     @NonNull
     private List<Bedingung> ausgewaehlteBedingungen;

    @NonNull
    private List<ExperimentierStation> ausgewaehlteStationen;

    private ProzessSchrittZustandsAutomatVorlage ausProzessSchrittZustandsAutomatVorlage;

    private List<TraegerArt> ausEInTragArt;

    private List<TraegerArt> ausAusTragArt;

    // Wir benoetigen die Parameter und Eigenschaften um diese dann auszuwaehlen
    private List<Bedingung> verfuegbareBedingunen;
    private List<ExperimentierStation> verfuegbareStationen;
    private List<ProzessSchrittVorlage> verfuegbarePSV;
    private List<ProzessSchrittVorlage> selectedPSV;
    private List<TraegerArt> verfuegbareTraegerArt;
    private List<ProzessSchrittZustandsAutomatVorlage> verPSZAV;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    transient private BedingungService bedingungService; // FIXME WhyThow https://stackoverflow.com/a/32284585

    @Inject
    private ExperimentierStationService experimentierStationService;


    @Inject
    private TraegerArtService traegerArtService;

    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

   // @Inject
   // private ProzessSchrittZustandsAutomatService


    @PostConstruct
    /*
      Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    public void init() {
        verfuegbareBedingunen = bedingungService.getAll();
        verfuegbarePSV = prozessSchrittVorlageService.getVorlagen();
        verfuegbareStationen = experimentierStationService.getESListe();
        verfuegbareTraegerArt = traegerArtService.getVerTraeger();
        verPSZAV = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
    }

    public String erstellePSV() {
        log.info("Erstelle Prozessschritt");
        // FIXME Wehre is es, persist auf  de.unibremen.sfb.model.ProzessSchrittVorlage.zustandsAutomat -> de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage

        ProzessSchrittVorlage psv = new ProzessSchrittVorlage(UUID.randomUUID().hashCode(), dauer, name,psArt,
                ausgewaehlteStationen, ausgewaehlteBedingungen, ausProzessSchrittZustandsAutomatVorlage);
        prozessSchrittVorlageService.persist(psv);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg",
                "Prozessschrittvorlage:  " + psv.getPsVID() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "psv?faces-redirect=true";
    }

    public void deletePSV() {
        prozessSchrittVorlageService.delete(selectedPSV);

    }

    public void onRowEdit(RowEditEvent<ProzessSchrittVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        //When The Persistence gefit be, we can uncomment that.
        //NO WORK AGAIN org.hibernate.LazyInitializationException

        prozessSchrittVorlageService.edit(event.getObject());
        FacesMessage msg = new FacesMessage("PSV Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
