package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.service.StandortService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("sListBean")
@Getter
@Setter
@Slf4j
@ViewScoped
public class SListBean implements Serializable {
    private List<Standort> standorte;
    private List<Standort> filteredStandorte;
    private List<Standort> selectedStandorte;

    @Inject
    private StandortService standortService;

    @PostConstruct
    public void init() {
        standorte = standortService.getStandorte();
    }

    public void deleteStandorte() {
        for (Standort s :
                selectedStandorte) {
            log.info("Loesche Standort " + s.getOrt());
            this.standortService.löscheStandort(s);

            if (filteredStandorte != null) {
                this.filteredStandorte.remove(s);
            }
            this.standorte = standortService.getStandorte();
        }
    }
}
