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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    @Inject
    private ProzessSchrittLogService prozessSchrittLogService;

    @Inject
    private ProzessSchrittZustandsAutomatService prozessSchrittZustandsAutomatService;

    /**
     * Process step dao
     */
    @Inject
    private ProzessSchrittDAO prozessSchrittDAO;

    @Inject
    private ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;

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

    public ExperimentierStation findStation(ProzessSchritt ps)
        throws IllegalArgumentException {
        if(ps==null) {
            throw new IllegalArgumentException();
        }
        for(ExperimentierStation e : experimentierStationService.getAll()) { //TODO jeder schritt nur an einer station?
            List<Integer> psids = new ArrayList<>();
            for(ProzessSchritt p : e.getNextPS()) {
                psids.add(p.getPsID());
            }
            if(psids.contains(ps.getPsID()) || (e.getCurrentPS()!= null && e.getCurrentPS().getPsID() == ps.getPsID())) {
                return e;
            }
        }
        return null;
    }


    /** Get all process steps from the database
      sets the current state of this ProzessSchritt
     // TODO Liam
     Add Field to Model for Calculating Average PS Time in PSV
      @param ps the ProzessSchritt
     * @param zustand the new state
     * @throws ExperimentierStationNotFoundException the station of the step was not found in the database
     * @throws ProzessSchrittNotFoundException the ProzessSchritt is not in the database
     * @throws ProzessSchrittLogNotFoundException the ProzessSchritt is not in the database
     *  @throws DuplicateProzessSchrittLogException the ProzessSchritt is not in the database
     *   @throws ProzessSchrittZustandsAutomatNotFoundException the ProzessSchritt is not in the database
     */
    public void setZustand(ProzessSchritt ps, String zustand)
            throws ExperimentierStationNotFoundException, ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException, DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException {
        if (ps == null || zustand == null) {
            throw new IllegalArgumentException();
        } else if (!ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().contains(zustand)) {
            throw new IllegalArgumentException("state not possible for this ProzessSchritt");
        } else {
            if(lastZustand(ps, zustand)) {
                experimentierStationService.updateCurrent(ps, findStation(ps));
            }
            ps.getProzessSchrittZustandsAutomat().setCurrent(zustand);
            prozessSchrittLogService.closeLog(ps.getProzessSchrittLog().get(ps.getProzessSchrittLog().size() - 1));
            ps.getProzessSchrittLog().add(prozessSchrittLogService.newLog(zustand));
            prozessSchrittZustandsAutomatDAO.update(ps.getProzessSchrittZustandsAutomat());
            prozessSchrittDAO.update(ps);
        }
    }

    /***
     * if a state is the last state of a process step
     * @param ps the process step
     * @param z the state to check
     * @return true, if last; otherwise false
     */
    private boolean lastZustand(ProzessSchritt ps, String z) {
        return ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende()
                .get(ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().size() - 1)
                .equals(z);
    }

    /**
     * sets the state of the step one further
     * @param ps the process step
     */
    public void oneFurther(ProzessSchritt ps)
    throws IllegalArgumentException, ExperimentierStationNotFoundException, ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException, DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException{
        if (ps == null) {
            throw new IllegalArgumentException();
        }
        if (!lastZustand(ps, ps.getProzessSchrittZustandsAutomat().getCurrent())) {
            int i = 0;
            while (!ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i).equals(ps.getProzessSchrittZustandsAutomat().getCurrent())) {
                i++;
            }
            setZustand(ps, ps.getProzessSchrittZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().get(i+1));
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

    /**
     * returns the samples of this prozessschritt
     * @param ps the process step
     * @return a list containing the samples
     */
    public List<Probe> getProben(ProzessSchritt ps) {
        return ps.getZugewieseneProben();
    }

    /** Update a process step that hasn't been startet yet
     * @param ps - the process step to update
     * @throws ProzessSchrittNotFoundException on failure */
    public void update(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        prozessSchrittDAO.update(ps);
    }

    /** Get a process step using its id
     * @param id - the id of the process step
     * @return the requested process step
     * @throws ProzessSchrittNotFoundException on failure */
    public ProzessSchritt getObjById(int id) throws ProzessSchrittNotFoundException{
        return prozessSchrittDAO.getObjById(id);
    }

    /** Remove a process step
     * @param ps - the process step to remove
     * @throws ProzessSchrittNotFoundException on failure */
    public void remove(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        prozessSchrittDAO.remove(ps);
    }
}

