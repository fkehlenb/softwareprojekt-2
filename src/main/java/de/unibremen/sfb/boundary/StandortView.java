package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.StandortService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@Transactional
@RequestScoped
@Slf4j
public class StandortView implements Serializable {

    /**
     * Location service
     */
    @Inject
    private StandortService standortService;

    /**
     * The location's name (location)
     */
    @Getter
    @Setter
    private String standortName;
    /**
     * Add a new location
     */
    public void addStandort() {
        try {
            standortService.findByLocation(standortName);
            log.error("Standort with name " + standortName + " already exists!");
            facesError("Standort with name " + standortName + " already exists!");
        }
        catch (Exception e){
            try {
                Standort s = new Standort(UUID.randomUUID().hashCode(), standortName);
                standortService.add(s);
                log.info("Added new location with name " + standortName);
                facesNotification("Added new location with name " + standortName);
            }
            catch (Exception f){
                f.printStackTrace();
                log.error("Couldn't add new location with name " + standortName);
                facesError("Couldn't add new location with name " + standortName);
            }
        }
    }

    /** Update an existing location on row edit
     * @param id - the id of the location object to update */
    public void updateOnRowEdit(int id){
        try {
            Standort s = standortService.findById(id);
            s.setOrt(standortName);
            standortService.update(s);
            log.info("Successfully updated location with id " + id);
            facesNotification("Successfully updated location with id " + id);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Couldn't update location object with id " + id);
            facesError("Couldn't update location object with id " + id);
        }
    }

    /** On row edit cancel */
    public void onRowEditCancel(){
        standortName = "";
        facesNotification("Canceled!");
    }

    /** Remove an existing location
     * @param id - the id of the location to remove */
    public void remove(int id){
        try {
            Standort s = standortService.findById(id);
            standortService.remove(s);
            log.info("Successfully removed location with id " + id);
            facesNotification("Successfully removed location with id " + id);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to remove location with id " + id);
            facesError("Failed to remove location with id " + id);
        }
    }

    /** Get all locations from the database
     * @return a list of all locations */
    public List<Standort> getAll(){
        return standortService.getStandorte();
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
     * Adds a new SEVERITY_INFO FacesMessage for the ui
     *
     * @param message Info Message
     */
    private void facesNotification(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }
}
