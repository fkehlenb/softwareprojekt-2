package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
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


@Named("psView")
@ViewScoped
@Getter
@Setter
@Log
public class ProzessSchrittView implements Serializable {

    private List<ProzessSchritt> allePS;
    private List<ProzessSchrittVorlage> allePSV;

    @Inject
    transient private ProzessSchrittService prozessSchrittService;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @PostConstruct
    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    public void init() {
       allePS = prozessSchrittService.getAll();
       allePSV = prozessSchrittVorlageService.getProzessSchrittVorlagen();
    }


    public void onRowEdit(RowEditEvent<ProzessSchrittVorlage> event) {
        prozessSchrittVorlageService.persist(event.getObject());
        FacesMessage msg = new FacesMessage("PSV Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String json() {
        return prozessSchrittService.toJson();
    }

}
