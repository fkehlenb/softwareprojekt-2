package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import de.unibremen.sfb.service.StandortService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Quelle https://www.primefaces.org/showcase/ui/data/datatable/edit.xhtml
 */
@Named("dtEditView")
@ViewScoped
@Getter
@Setter
@Slf4j
public class EditView implements Serializable {

    private List<Standort> standorte;

    @Inject
    private StandortDAO standortDAO;

    @PostConstruct
    public void init() {
        standorte = getStandorte();
    }

    public List<Standort> getStandorte() {
        standorte= standortDAO.getAll();
        return standorte;
    }



    public void onRowEdit(RowEditEvent event) {
        Standort s = null;

        try {
            s = (Standort) event.getObject();
            standortDAO.persist(s);
        } catch (Exception e) {
                log.error("Not correct Type");
        }
        FacesMessage msg = new FacesMessage("Standort Edited", s.getOrt());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString()); // potentiel fix me id
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();


        if (newValue != null && !newValue.equals(oldValue)) {
            try {
                standortDAO.persist( (Standort) newValue);
            } catch (DuplicateStandortException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Standort konnte nicht gespeichert werden", null));
                e.printStackTrace();
            }
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
