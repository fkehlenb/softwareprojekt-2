package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateBedingungException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittParameterDAO;
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
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named("bedingungBean")
@RequestScoped
@Getter
@Setter
@Log
public class BedingungView implements Serializable {
    private List<ProzessSchrittParameter> verProzessSchrittParameters;
    private List<ProzessSchrittParameter> ausProzessSchrittParameters;
    private List<Bedingung> bedingungen;

    @NotBlank
    private String name;
    

    private int anzahl;

    @Inject
    private ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    private ProzessKettenVorlageService prozessKettenVorlageService;

    @Inject
    transient private BedingungService bedingungService;

    @Inject
    private ProzessSchrittParameterDAO prozessSchrittParameterDAO;


    @PostConstruct
    void init() {
        verProzessSchrittParameters = prozessSchrittParameterDAO.getAll();
        bedingungen = bedingungService.getAll();
//
    }

    public String createB() throws DuplicateBedingungException
    {
        Bedingung bedingung = new Bedingung(UUID.randomUUID().hashCode(),name , ausProzessSchrittParameters,anzahl);

        log.info("Erstelle neue Bedingung: "  + bedingung.toString() + name);
        log.info("Persisting  Bedingung: "  + bedingung.toString() + name);
        bedingungService.addBedingung(bedingung);

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Bedingung:  " + bedingung.getName() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "bedingungen?faces-redirect=true";
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
    public void deleteBedingung(int id){
        Bedingung b = bedingungService.findByID(id);
        bedingungService.remove(b);
        bedingungen = bedingungService.getAll();
    }
    public void onRowEdit(RowEditEvent<Bedingung> event) {
        log.info("Updating: "+ event.getObject().getName());
        bedingungService.edit(  event.getObject());
        FacesMessage msg = new FacesMessage("Bedingung Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Bedingung> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
