package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Car;
import de.unibremen.sfb.service.CarService;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Named("dtEditView")
@ViewScoped
public class EditView implements Serializable {

    @PersistenceContext
    EntityManager em;

    private List<Car> cars1;
    private List<Car> cars2;

    @Inject
    private CarService service;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @PostConstruct
    public void init() {
        cars1 = service.createCars(10);
        cars2 = service.createCars(10);
    }

    public List<Car> getCars1() {
        return cars1;
    }

    public List<Car> getCars2() {
        return cars2;
    }

    public List<String> getBrands() {
        return service.getBrands();
    }

    public List<String> getColors() {
        return service.getColors();
    }

    public void setService(CarService service) {
        this.service = service;
    }

    public void onRowEdit(RowEditEvent<Car> event) {
        FacesMessage msg = new FacesMessage("Car Edited", event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

  public void onRowCancel(RowEditEvent<Car> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            if (em.contains(newValue)) {
                em.merge(newValue);
            } else em.persist(newValue);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}