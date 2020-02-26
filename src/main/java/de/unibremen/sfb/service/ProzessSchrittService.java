package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service fuer ProzessSchritt
 * Anwendungsfall: Bearbeiten eines ProzessSchrittes oder Hinzufügen eines neuen
 */
@Slf4j
@Transactional
public class ProzessSchrittService implements Serializable {

    /**
     * List of all process steps
     */
    private List<ProzessSchritt> psListe;

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * Process step dao
     */
    @Inject
    private ProzessSchrittDAO prozessSchrittDAO;


    @Inject
    private ProzessSchrittLogService pslService;

    @Inject
    AuftragService auftragService;


    @PostConstruct
    public void init() {
        psListe = getAll();
    }

    /**
     * Get all PS which belong to user
     *
     * @param u the current user
     * @return the ps
     */
    public List<ProzessSchritt> getSchritteByUser(User u) {
        var ps = new ArrayList<ProzessSchritt>();
        for (ExperimentierStation e :
                experimentierStationService.getESByUser(u)) {
            ps.add(e.getCurrentPS());
        }
        return ps;
    }


    /** Get all process steps from the database
     * @return a list of all process steps */

    /**
     * sets the current state of this ProzessSchritt
     *
     * @param ps      the ProzessSchritt
     * @param zustand the new state
     * @throws ProzessSchrittNotFoundException the ProzessSchritt is not in the database
     */
    public void setZustand(ProzessSchritt ps, String zustand)
            throws ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException, DuplicateProzessSchrittLogException {
        if (!ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().contains(zustand)) {
            throw new IllegalArgumentException("state not possible for this ProzessSchritt");
        } else {
            ps.getProzessSchrittZustandsAutomat().setCurrent(zustand);
            pslService.closeLog(ps.getProzessSchrittLog().get(ps.getProzessSchrittLog().size() - 1));
            ps.getProzessSchrittLog().add(pslService.newLog(zustand));
            prozessSchrittDAO.update(ps);
        }
    }

    /**
     * searches for the Auftrag the ProzessSchritt belongs to //TODO yikes
     *
     * @param ps the ps which's Auftrag is looked for
     * @return the Auftrag (or null, if none was found)
     */
    //TODO das funktioniert alles nicht... vllt mit Hibernate.initialize
    /*
    (aber müsste dafür dependency hinzufügen ...)
    oder (extrem unschön) Fetch Type auf eager stellen in auftrag.java
     */
    public Auftrag getAuftrag(ProzessSchritt ps) {
        /*
        filtert liste von allen aufträgen
        jeder auftrag, der den prozessschritt in seiner liste hat, kommt in ergebnis
        da das nur einer sein sollte, reicht findfirst
         */
        return auftragService.getAuftrage().stream()
                .filter((a) -> (a.getProzessSchritte().stream()
                        .anyMatch((p) -> p.getPsID() == ps.getPsID()))
                ).findFirst().orElse(null);

    }

    public List<ProzessSchritt> getAll() {
        return prozessSchrittDAO.getAll();
    }

    /**
     * JSON export
     */
    public String toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(psListe);
        // FIXME LEO
        //String result = "JSON IS Broken";
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

    public void add(ProzessSchritt ps) throws DuplicateProzessSchrittException {
        prozessSchrittDAO.persist(ps);
    }
}

