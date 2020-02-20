package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import de.unibremen.sfb.service.StandortService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named("sListBean")
@Getter
@Setter
@Slf4j
@RequestScoped
@Transactional
public class SListBean implements Serializable {
    private List<Standort> alleStandorte;
    private List<Standort> filteredStandorte;
    private List<Standort> selectedStandorte;

    @Inject
    private StandortService standortService;

    @Inject
    StandortDAO standortDAO;


    @PostConstruct
    public void init() {
        alleStandorte = standortDAO.getAll();
    }



    public void deleteStandorte() {
        for (Standort s :
                selectedStandorte) {
            log.info("Loesche Standort " + s.getOrt());
            standortService.loescheStandort(s);
            try {
                standortDAO.remove(s);
            } catch (StandortNotFoundException e) {
                e.printStackTrace();
                // FIXME Fehler an das Front end
            }

            if (filteredStandorte != null) {
                this.filteredStandorte.remove(s);
            }

        }
        alleStandorte = standortService.getStandorte();
    }



    public void onRowEdit(RowEditEvent<Standort> event) {
        FacesMessage msg = new FacesMessage("Standort Edited", event.getObject().getOrt());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Standort> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getOrt());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
