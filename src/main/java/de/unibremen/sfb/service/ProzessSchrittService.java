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
import java.util.stream.Collectors;

/**
 * Service fuer ProzessSchritt
 * Anwendungsfall: Bearbeiten eines ProzessSchrittes oder Hinzufügen eines neuen
 */
@Slf4j
@Transactional
public class ProzessSchrittService implements Serializable {

    /** Process step dao */
    @Inject
    private ProzessSchrittDAO prozessSchrittDAO;

    @Inject
    private ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;

    @Inject
    ExperimentierStationService experimentierStationService;

    @Inject
    ProzessSchrittLogService prozessSchrittLogService;

    /** Create a new process step
     * @param ps - the process step to add
     * @throws DuplicateProzessSchrittException failure */
    public void createPS(ProzessSchritt ps) throws DuplicateProzessSchrittException{
        prozessSchrittDAO.persist(ps);
    }

    /** Edit a process step
     * @param ps - the process step to edit
     * @throws ProzessSchrittNotFoundException on failure */
    public void editPS(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        prozessSchrittDAO.update(ps);
    }

    /** Remove a process step
     * @param ps - the process step to remove
     * @throws ProzessSchrittNotFoundException on failure */
    public void removePS(ProzessSchritt ps) throws ProzessSchrittNotFoundException{
        prozessSchrittDAO.remove(ps);
    }

    /** Get a process step using its id
     * @param id - the process step id
     * @return the requested process step
     * @throws ProzessSchrittNotFoundException on failure */
    public ProzessSchritt getObjById(int id) throws ProzessSchrittNotFoundException{
        return prozessSchrittDAO.getObjById(id);
    }

    /** Get all process steps from the database
     * @return a list of all process steps or an empty arraylist */
    public List<ProzessSchritt> getAll(){
        return prozessSchrittDAO.getAll();
    }

    /** Get all process steps that are not assigned
     * @return a list of all process steps not yet assigned or an empty arraylist */
    public List<ProzessSchritt> getAllAvailable(){
        return prozessSchrittDAO.getAllAvailable();
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
            while (!ps.getProzessSchrittZustandsAutomat().getZustaende().get(i).equals(ps.getProzessSchrittZustandsAutomat().getCurrent())) {
                i++;
            }
            setZustand(ps, ps.getProzessSchrittZustandsAutomat().getZustaende().get(i+1));
        }
    }
    /***
     * if a state is the last state of a process step
     * @param ps the process step
     * @param z the state to check
     * @return true, if last; otherwise false
     */
    public boolean lastZustand(ProzessSchritt ps, String z) {
        return ps.getProzessSchrittZustandsAutomat().getZustaende()
                .get(ps.getProzessSchrittZustandsAutomat().getZustaende().size() - 1)
                .equals(z);
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
        } else if (!ps.getProzessSchrittZustandsAutomat().getZustaende().contains(zustand)) {
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

    public ExperimentierStation findStation(ProzessSchritt ps)
            throws IllegalArgumentException {
        if(ps==null) {
            throw new IllegalArgumentException();
        }
        for(ExperimentierStation e : experimentierStationService.getAll()) { //TODO jeder schritt nur an einer station?
            List<Integer> psids = new ArrayList<>();
            for(ProzessSchritt p : e.getNextPS()) {
                psids.add(p.getId());
            }
            if(psids.contains(ps.getId()) || (e.getCurrentPS()!= null && e.getCurrentPS().getId() == ps.getId())) {
                return e;
            }
        }
        return null;
    }







}

