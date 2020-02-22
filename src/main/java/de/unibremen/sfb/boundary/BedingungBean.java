package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateBedingungException;
import de.unibremen.sfb.model.*;
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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named("bedingungBean")
@RequestScoped
@Getter
@Setter
@Log
public class BedingungBean implements Serializable {
    private List<ProzessSchrittParameter> verProzessSchrittParameters;
    private ProzessSchrittParameter[] ausProzessSchrittParameters;
    private List<Bedingung> bedingungen;
    
    
    private Bedingung b;
    
    @NotBlank
    private String name;
    
    @Min(1)
    private int anzahl;
    
    private List<ProzessSchrittParameter> PSPs;

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

    public String createB() throws DuplicateBedingungException
    {
        log.info("Erstelle neue Bedingung: "  + b.toString() + name);
        Bedingung bedingung = new Bedingung(UUID.randomUUID().hashCode(), name, PSPs);
        log.info("Persisting  Bedingung: "  + b.toString() + name);
        bedingungService.addBedingung(bedingung);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Bedingung:  " + bedingung.toString() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "admin/addES.xhtml?faces-redirect=true";
    }

//    public void delete() {
//        log.info("Loesche  Bedingung: "  + b.toString() + name);
//        bedingungService.addBedingung(bedingung);
//
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage("Erfolg", "Bedingung:  " + bedingung.toString() +
//                "erfolgreich erstellt"));
//        context.getExternalContext().getFlash().setKeepMessages(true);
//
//    }

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

