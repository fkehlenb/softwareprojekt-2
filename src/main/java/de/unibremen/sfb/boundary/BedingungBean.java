package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittParameter;
import de.unibremen.sfb.service.BedingungService;
import de.unibremen.sfb.service.ProzessKettenVorlageService;
import de.unibremen.sfb.service.ProzessSchrittParameterService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("bedingungBean")
@RequestScoped
@Getter
@Setter
@Log
public class BedingungBean implements Serializable {
    private List<ProzessSchrittParameter> verProzessSchrittParameters;
    private ProzessSchrittParameter[] ausProzessSchrittParameters;
    private ProzessKettenVorlage[] vorlagen;
    private List<Bedingung> bedingungen;

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    @Inject
    BedingungService bedingungService;


    @PostConstruct
    void init() {
        verProzessSchrittParameters = prozessSchrittParameterService.getParameterList();
        bedingungen = bedingungService.getBs();
//
    }

    public void onRowEdit(RowEditEvent<Bedingung> event) {
        log.info("Updating: "+ event.getObject().getName());
        bedingungService.edit(event.getObject());
        FacesMessage msg = new FacesMessage("Bedingung Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Bedingung> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}

