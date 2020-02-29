package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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
    private ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;


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

    /**
     * returns the ProzessSchritte currently waiting in all experimenting stations the user is assigned to
     * @param u the user (a Technologe)
     * @return a list containing all process steps waiting
     */
    public List<ProzessSchritt> getPotentialStepsByUser(User u) {
        List<ProzessSchritt> ps = new ArrayList<>();
        for(ExperimentierStation e : experimentierStationService.getESByUser(u)) {
            ps.addAll(e.getNextPS());
        }
        ps.removeAll(Collections.singleton(null));
        return ps;
    }

    public ExperimentierStation findStation(ProzessSchritt ps) {
        for(ExperimentierStation e : experimentierStationService.getAll()) { //TODO jeder schritt nur an einer station?
            if(e.getNextPS().contains(ps) || e.getCurrentPS() == ps) {
                return e;
            }
        }
        return null;
    }


    /** Get all process steps from the database
      sets the current state of this ProzessSchritt

     // TODO Add service which determines last Element of ProzessSchrittZustandsAutomatVorlage
     //      Check if there are any more special states
             If has reached Last State Continue with next PS
               - FIFO pop of nextPS with next step into currentPS
               - Upgrade Technologen View

     // This allows us to dynamically use the pszav, in doing so we let the pkAdmin make pszav
        <- Dynamische Zustandsautomaten

     // TODO Liam
     Add Field to Model for Calculating Average PS Time in PSV

      @param ps the ProzessSchritt
     * @param zustand the new state
     * @throws ProzessSchrittNotFoundException the ProzessSchritt is not in the database
     * @throws ProzessSchrittLogNotFoundException the ProzessSchritt is not in the database
     *  @throws DuplicateProzessSchrittLogException the ProzessSchritt is not in the database
     *   @throws ProzessSchrittZustandsAutomatNotFoundException the ProzessSchritt is not in the database
     */
    public void setZustand(ProzessSchritt ps, String zustand)
            throws ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException, DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException {
        if (ps == null || zustand == null) {
            throw new IllegalArgumentException();
        } else if (!ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().contains(zustand)) {
            throw new IllegalArgumentException("state not possible for this ProzessSchritt");
        } else {
            ps.getProzessSchrittZustandsAutomat().setCurrent(zustand);
            pslService.closeLog(ps.getProzessSchrittLog().get(ps.getProzessSchrittLog().size() - 1));
            ps.getProzessSchrittLog().add(pslService.newLog(zustand));
                prozessSchrittZustandsAutomatDAO.update(ps.getProzessSchrittZustandsAutomat());
            prozessSchrittDAO.update(ps);
        }
    }

    /**
     * searches for the Auftrag the ProzessSchritt belongs to
     *
     * @param ps the ps which's Auftrag is looked for
     * @return the Auftrag (or null, if none was found)
     */
    public Auftrag getAuftrag(ProzessSchritt ps) {
        for (Auftrag a :
                auftragService.getAuftrage()) {
            for (ProzessSchritt p : a.getProzessSchritte()) {
                if (p.getPsID() == ps.getPsID()) {
                    return a;
                }
            }
        }
        log.info("No Auftrag Found for: "+ ps.getPsID());
        return null;
    }

    /**
     * Return all ProzessSchritte
     * @return all PS
     */
    public List<ProzessSchritt> getAll() {
        return prozessSchrittDAO.getAll();
    }

    /**
     * JSON export
     *
     * @return the JSON as a String
     */
    public String toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
        //String result = jsonb.toJson(psListe);
        // FIXME LEO
        String result = "JSON IS Broken";
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

    /**
     * Fuege den ProzessSchritt in die Datenbank ein
     * @param ps wird eingefuegt
     * @throws DuplicateProzessSchrittException falls es ihn schon gibt
     */
    public void add(ProzessSchritt ps) throws DuplicateProzessSchrittException {
        prozessSchrittDAO.persist(ps);
    }
}

