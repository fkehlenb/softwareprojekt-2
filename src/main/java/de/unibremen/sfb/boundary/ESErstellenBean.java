package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import de.unibremen.sfb.service.StandortService;
import de.unibremen.sfb.service.UserService;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ExperimentierStationZustand;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.User;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Named("esErstellenBean")
@Getter
@Setter
@Slf4j
@RequestScoped
public class ESErstellenBean {

    @NonNull
    private  int id;

    @NonNull
    private Standort standort;

    @NotEmpty
    private Set<User> ausgewählteBenutzer;

    @NonNull
    private String name;

    private Set<User> availableUsers;
    private Set<Standort>  verfügbareStandorte;


    @Inject
    StandortService standortService;

    @Inject
    UserService userService;

    @Inject
    private ExperimentierStationDAO esDao;

    @PostConstruct
    public void init() {
        availableUsers = userService.getUsers();
        verfügbareStandorte = standortService.getStandorte();
    }

    public String createES() throws DuplicateExperimentierStationException {
        log.info("Erstelle neue Experimentierstation:"  + standort.toString() + name);
        ExperimentierStation experimentierStation = new ExperimentierStation(id, standort, name, ExperimentierStationZustand.VERFUEGBAR, ausgewählteBenutzer);
        esDao.persist(experimentierStation);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Experimentierstation:  " + experimentierStation.toString() +
                "erfolgreich erstellt"));
        context.getExternalContext().getFlash().setKeepMessages(true);

        return "admin/addES.xhtml?faces-redirect=true";
    }
}
