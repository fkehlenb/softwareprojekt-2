package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.AuftragService;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import de.unibremen.sfb.service.UserService;
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

@Named("dtAuftragBean")
@ViewScoped
@Getter
@Setter
@Log
public class AuftragBean implements Serializable {
    private List<Auftrag> auftrage;

    @Inject
    AuftragService auftragService;


    @PostConstruct
    void init() {
        auftrage = auftragService.getAuftrage();
        // lol
    }

    public void onRowEdit(RowEditEvent<Auftrag> event) {
        auftragService.add(event.getObject());
        FacesMessage msg = new FacesMessage("Auftrag Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Auftrag> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

