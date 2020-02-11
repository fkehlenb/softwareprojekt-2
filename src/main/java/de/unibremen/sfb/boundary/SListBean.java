package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.StandortNotFoundException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.StandortDAO;
import de.unibremen.sfb.service.StandortService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("sListBean")
@Getter
@Setter
@Slf4j
@RequestScoped
public class SListBean implements Serializable {
    private List<Standort> standorte;
    private List<Standort> filteredStandorte;
    private List<Standort> selectedStandorte;

    @Inject
    private StandortService standortService;

    @Inject
    StandortDAO standortDAO;


    @PostConstruct
    public void init() {
        standorte = standortDAO.getAll();
    }

    public void deleteStandorte() {
        for (Standort s :
                selectedStandorte) {
            log.info("Loesche Standort " + s.getOrt());
            this.standortService.loescheStandort(s);
            try {
                standortDAO.remove(s);
            } catch (StandortNotFoundException e) {
                e.printStackTrace();
                // FIXME Fehler an das Front end
            }

            if (filteredStandorte != null) {
                this.filteredStandorte.remove(s);
            }
            this.standorte = standortService.getStandorte();
        }
        standorte = standortDAO.getAll();
    }
}
