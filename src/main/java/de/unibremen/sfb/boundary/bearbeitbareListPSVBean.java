package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Transactional
@Named("bearbeitbareListPSVBean")
@Data
@Slf4j
public class bearbeitbareListPSVBean implements Serializable {

    @Inject
    ProzessSchrittVorlageService bearbeitbareListPSVBean;

    private List<ProzessSchrittVorlage> bearbeitbareList;

    @PostConstruct
    void init() {
        bearbeitbareList=bearbeitbareListPSVBean.akzeptiertePSV();
    }

    public void onRowEdit(RowEditEvent<ProzessSchrittVorlage> event) throws ProzessSchrittVorlageNotFoundException {
        log.info("Updating: "+ event.getObject().getPsVID());
        bearbeitbareListPSVBean.edit(  event.getObject());
        FacesMessage msg = new FacesMessage("Bedingung Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<Bedingung> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
