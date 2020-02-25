package de.unibremen.sfb.boundary;


import de.unibremen.sfb.exception.DuplicateProzessSchrittZustandsAutomatVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatVorlageNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DualListModel;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Named("pszavView")
@ViewScoped
@Getter
@Setter
@Log
public class PSZAVView implements Serializable {
    private List<ProzessSchrittZustandsAutomatVorlage> selpszav;
    private List<ProzessSchrittZustandsAutomatVorlage> verpszav;
    private List<String> sourceZ;
    private List<String> targetZ;
    private DualListModel<String> dualZ;

    @Inject
    private ProzessSchrittZustandsAutomatVorlageService prozessSchrittZustandsAutomatVorlageService;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    ZustandsService zustandsService;

    /**
     * Hier werden aus der Persitenz die benötigten Daten Geladen
     */
    @PostConstruct
    public void init() {
        sourceZ = new ArrayList<String>();
        targetZ = zustandsService.getPsZustaende();
        dualZ = new DualListModel<String>(sourceZ, targetZ);
        verpszav = prozessSchrittZustandsAutomatVorlageService.getProzessSchrittZustandsAutomatVorlagen();
        //
    }

    public String erstellePSZAV() {
        // FIXME Implementation
        log.info("Selected Zustaende: " + dualZ.getTarget());
        return "?faces-redirect=true";
    }

    public void deletePSZAV() {
        try {
            prozessSchrittZustandsAutomatVorlageService.delete(selpszav);
        } catch (ProzessSchrittVorlageNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void onRowEdit(RowEditEvent<ProzessSchrittZustandsAutomatVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        //When The Persistence gefit be, we can uncomment that.
        prozessSchrittZustandsAutomatVorlageService.edit(event.getObject());
        FacesMessage msg = new FacesMessage("PS Automat Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}


