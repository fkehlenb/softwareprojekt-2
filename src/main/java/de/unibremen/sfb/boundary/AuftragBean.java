package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import de.unibremen.sfb.service.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static de.unibremen.sfb.model.ProzessKettenZustandsAutomat.GESTARTET;

@Named("dtAuftragBean")
@ViewScoped
@Getter
@Setter
@Log
public class AuftragBean implements Serializable {
    private List<Auftrag> auftrage;
    private List<ProzessKettenVorlage> vorlagen;


    @Inject
    AuftragService auftragService;

    @Inject
    ProzessKettenVorlageService prozessKettenVorlageService;

    @Inject
    ZustandsService zustandsService;

    @Inject
    Auftrag auftrag;

    @PostConstruct
    void init() {
        auftrage = auftragService.getAuftrage();
        vorlagen = getPKVs();
        //FIX?
        auftragService.zustandswechsel(auftrag,GESTARTET);
        auftrag = getAuftrag();

    }
    //Soll den Zustand wechseln FUNKTIONIERT NICHT!
    public void zWechsel(){
         auftragService.zustandswechsel(auftrag,GESTARTET);
    }


    public void onRowEdit(RowEditEvent<Auftrag> event) {
        log.info("Updating: "+ event.getObject().getPkID());
        auftragService.upate(event.getObject());
        FacesMessage msg = new FacesMessage("Auftrag Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Auftrag> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String json() {
        return auftragService.toJson();
    }

    public List<ProzessKettenVorlage> getPKVs() {
        return prozessKettenVorlageService.getPKVs();
    }
}

