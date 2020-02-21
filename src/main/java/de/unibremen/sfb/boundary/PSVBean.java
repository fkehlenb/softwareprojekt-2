package de.unibremen.sfb.boundary;

import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.service.ProzessSchrittVorlageService;
import de.unibremen.sfb.service.UserService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.primefaces.event.RowEditEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("dtPSVBean")
@ViewScoped
@Getter
@Setter
@Log
public class PSVBean implements Serializable {

    private List<ProzessSchrittVorlage> prozessSchrittVorlageListe;

    @Inject
    private ProzessSchrittVorlageService prozessSchrittVorlageService;

    @Inject
    UserService userService;

    private List<User> verfuegbareBenutzer;

    @PostConstruct
    void init() {
//        prozessSchrittVorlageListe = prozessSchrittVorlageService.getProzessSchrittVorlagen();
        prozessSchrittVorlageListe = new ArrayList<ProzessSchrittVorlage>();
        verfuegbareBenutzer = userService.getAll();
        // lol
    }

    public void onRowEdit(RowEditEvent<ProzessSchrittVorlage> event) {
        prozessSchrittVorlageService.persist(event.getObject());
        FacesMessage msg = new FacesMessage("PSV Edited", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

      public void onRowCancel(RowEditEvent<ProzessSchrittVorlage> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
