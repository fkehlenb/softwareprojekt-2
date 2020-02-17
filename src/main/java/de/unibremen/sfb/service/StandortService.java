package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StandortService implements Serializable {

    /** The dao for the locations */
    @Inject
    private StandortDAO standortDAO;


    /** list of all locations */
    private List<Standort> standorte;

    public void persist(Standort standort) {
        try {
            standortDAO.persist(standort);
        } catch (Exception e) {
            facesError("Zu dieser Email existiert schon ein Benutzer!");
        }

    }

    /** Init is called on startup */
    @PostConstruct
    public void init() {

        standorte = getStandorte();
    }

    public List<Standort> getStandorte() {
        standorte = standortDAO.getAll();
        return standorte;
    }

    public List<Standort> getStandort2() {
        List<Standort> standorteNew = new ArrayList<>();
        for (int i = 0; i < 5 ; i++) {
            standorteNew.add(new Standort());
        }
        return standorteNew;
    }

    /** Add a new location */
    public void addStandort(Standort standort) {
        standorte.add(standort);
    }

    /** Remove a location */
    public void loescheStandort(Standort standort) {
        this.standorte.remove(standort);
        try {
            standortDAO.remove(standort);
        } catch (StandortNotFoundException e) {
            e.printStackTrace();
        }
    }

    /** Find a standort based on ts location */
    public Standort findByLocation(String standort) {
        // TODO qFIXME Use String as ID or convert to String
        return this.standorte.stream().filter(c -> c.getOrt().equals(standort)).findFirst().orElse(null);
    }
    public Standort findById(int Id) {
        // TODO qFIXME Use String as ID or convert to String
        return this.standorte.stream().filter(c -> c.getId() == (Id)).findFirst().orElse(null);
    }
    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }
}
