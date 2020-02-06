package de.unibremen.sfb.boundary;

import de.unibremen.sfb.exception.DuplicateStandortException;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import de.unibremen.sfb.persistence.StandortDAO;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named("sErstellenBean")
@Getter
@Setter
@Slf4j
@RequestScoped
public class SErstellenBean {

    @Inject
    StandortDAO sDao;

    @NonNull
    private String name;

    public String createS() {

        log.info("Erstelle neuen Standort: " + name);
        Standort standort = new Standort(UUID.randomUUID().hashCode(), name);

        log.info("Persisting Standort: "  + name);
        try {
            sDao.persist(standort);
        } catch (DuplicateStandortException e) {
            e.printStackTrace();
        }

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Erfolg", "Standort:  " + standort.toString() +
                "erfolgreich erstellt."));
        context.getExternalContext().getFlash().setKeepMessages(true);


        return "admin/addS.xhtml?faces-redirect=true";
    }
}
