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
    private StandortService standortService;

    @Inject
    private StandortDAO standortDAO;

    @PostConstruct
    public void init() {
        standorte = getStandorte();
    }

    public List<Standort> getStandorte() {
        return standortDAO.getAll();
    }



    public void onRowEdit(RowEditEvent event) {
//                Standort s = standortService.findById(((Standort) event.getOldValue()).getId());
//
//        try {
//            s.setOrt(event.getNewValue());
//            standortDAO.update(s);
//        } catch (Exception e) {
//            log.error("Not correct Type");
//        }
        FacesMessage msg = new FacesMessage("Standort Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }



    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Standort) event.getObject()).getOrt());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
