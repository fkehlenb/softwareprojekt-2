package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.Zustand;
import de.unibremen.sfb.service.ZustandsService;
import lombok.Data;
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

@Named("pszBean")
@Transactional
@ViewScoped
@Data
public class PszBean implements Serializable {

    List<String> pszList;
    Zustand psz;
    @Inject
    ZustandsService zustandsService;

    @PostConstruct
    public void init(){
        psz=zustandsService.getPsZ();
        pszList = psz.getZustaende();
    }

    public void onRowEdit(RowEditEvent<Zustand> event) throws ProzessSchrittVorlageNotFoundException {
        zustandsService.edit(event.getObject());
        FacesMessage msg = new FacesMessage("PSV Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
