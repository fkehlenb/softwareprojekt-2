package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class StandortService implements Serializable {

    /**
     * The dao for the locations
     */
    @Inject
    private StandortDAO standortDAO;


    /**
     * list of all locations
     */
    private List<Standort> standorte;

    /**
     * Add a new location
     *
     * @param standort - the location object to add
     */
    public void persist(Standort standort) {
        try {
            standortDAO.persist(standort);
        } catch (Exception e) {
            facesError("Zu dieser Email existiert schon ein Benutzer!");
        }
    }

    /**
     * Init is called on startup
     */
    @PostConstruct
    public void init() {
        standorte = getStandorte();
    }

    /**
     * Get all locations from the database
     *
     * @return a list of al locations
     */
    public List<Standort> getStandorte() {
        standorte = standortDAO.getAll();
        return standorte;
    }

    /**
     * Remove a location
     *
     * @param standort - the location to remove
     * @throws StandortNotFoundException on failure
     */
    public void remove(Standort standort) throws StandortNotFoundException {
        standortDAO.remove(standort);
        this.standorte.remove(standort);
    }

    /**
     * Find a standort based on ts location
     *
     * @param standort - the name of the location
     * @return the Location which machted the String standort
     * @throws StandortNotFoundException if the location couldn't be found in the database
     */
    public Standort findByLocation(String standort) throws StandortNotFoundException {
        return standortDAO.getByOrt(standort);
    }

    /**
     * Get a location object using its id
     *
     * @param id - the id of the location object
     * @return the Location which matches the id
     * @throws StandortNotFoundException if the location couldn't be found in the database
     */
    public Standort findById(int id) throws StandortNotFoundException {
        return standortDAO.getObjById(id);
    }

    /**
     * Adds a new SEVERITY_ERROR FacesMessage for the ui
     *
     * @param message Error Message
     */
    private void facesError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
    }

    /**
     * Add a new location
     *
     * @param s - the new location to add
     * @throws DuplicateStandortException of failure
     */
    public void add(Standort s) throws DuplicateStandortException {
        standortDAO.persist(s);
        standorte = standortDAO.getAll();
    }

    /**
     * Update a location in the database
     *
     * @param s - the location to update
     * @throws StandortNotFoundException on failure
     */
    public void update(Standort s) throws StandortNotFoundException {
        standortDAO.update(s);
        standorte = standortDAO.getAll();
    }

    public void addStandort(Standort s) {
        try {
            standortDAO.persist(s);
        } catch (DuplicateStandortException e) {
            e.printStackTrace();
        }
    }
}
