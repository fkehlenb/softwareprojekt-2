package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.*;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Service fuer ProzessSchritt
 * Anwendungsfall: Bearbeiten eines ProzessSchrittes oder Hinzufügen eines neuen
 */
@Slf4j
@Transactional
public class ProzessSchrittService implements Serializable {

    /**
     * Process step dao
     */
    @Inject
    private ProzessSchrittDAO prozessSchrittDAO;

    @Inject
    private ProzessSchrittZustandsAutomatDAO prozessSchrittZustandsAutomatDAO;

    @Inject
    ExperimentierStationService experimentierStationService;

    @Inject
    ProzessSchrittLogService prozessSchrittLogService;

    @Inject
    UserService userService;


    /**
     * Create a new process step
     *
     * @param ps - the process step to add
     * @throws DuplicateProzessSchrittException failure
     */
    public void createPS(ProzessSchritt ps) throws DuplicateProzessSchrittException {
        prozessSchrittDAO.persist(ps);
    }

    /**
     * Edit a process step
     *
     * @param ps - the process step to edit
     * @throws ProzessSchrittNotFoundException on failure
     */
    public void editPS(ProzessSchritt ps) throws ProzessSchrittNotFoundException {
        prozessSchrittDAO.update(ps);
    }

    /**
     * Remove a process step
     *
     * @param ps - the process step to remove
     * @throws ProzessSchrittNotFoundException on failure
     */
    public void removePS(ProzessSchritt ps) throws ProzessSchrittNotFoundException {
        prozessSchrittDAO.remove(ps);
    }

    /**
     * Get a process step using its id
     *
     * @param id - the process step id
     * @return the requested process step
     * @throws ProzessSchrittNotFoundException on failure
     */
    public ProzessSchritt getObjById(int id) throws ProzessSchrittNotFoundException {
        return prozessSchrittDAO.getObjById(id);
    }

    /**
     * Get all process steps from the database
     *
     * @return a list of all process steps or an empty arraylist
     */
    public List<ProzessSchritt> getAll() {
        return prozessSchrittDAO.getAll();
    }

    /**
     * Get all process steps that are not assigned
     *
     * @return a list of all process steps not yet assigned or an empty arraylist
     */
    public List<ProzessSchritt> getAllAvailable() {
        return prozessSchrittDAO.getAllAvailable();
    }

    /**
     * sets the state of the step one further
     *
     * @param ps the process step
     * @throws ExperimentierStationNotFoundException          if no Station exists
     * @throws ProzessSchrittNotFoundException                if not Step could be found
     * @throws ProzessSchrittLogNotFoundException             if not PS Log could be found
     * @throws DuplicateProzessSchrittLogException            if a PS Log already exists
     * @throws ProzessSchrittZustandsAutomatNotFoundException if there is not PS Automata
     */
    public void oneFurther(ProzessSchritt ps, LocalDateTime d)
            throws IllegalArgumentException, ExperimentierStationNotFoundException, ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException, DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException {
        if (ps == null) {
            throw new IllegalArgumentException();
        }
        int i = 0;
        try {
            while (!ps.getProzessSchrittZustandsAutomat().getZustaende().get(i).equals(ps.getProzessSchrittZustandsAutomat().getCurrent())) {
                i++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        if (i+1<ps.getProzessSchrittZustandsAutomat().getZustaende().size()) {
            setZustand(ps, ps.getProzessSchrittZustandsAutomat().getZustaende().get(i + 1), d);
        }
    }

    public Boolean hasFinished(@NonNull ProzessSchritt prozessSchritt) {
        assert prozessSchritt.getProzessSchrittZustandsAutomat() != null;
        assert prozessSchritt.getProzessSchrittZustandsAutomat().getCurrent() != null;
        assert prozessSchritt.getProzessSchrittZustandsAutomat().getZustaende() != null;
        ProzessSchrittZustandsAutomat automat = prozessSchritt.getProzessSchrittZustandsAutomat();
        String current = automat.getCurrent();
        return (automat.getZustaende().indexOf(current) == automat.getZustaende().size() - 1);
    }

    public ProzessSchritt getCurrentStep(Auftrag auftrag) {
        for (ProzessSchritt ps :
                auftrag.getProzessSchritte()) {
            if (!hasFinished(ps)) {
                return ps;
            }
        }
        return null;
    }

    public Boolean isCurrentStep(@NonNull ProzessSchritt ps) {
        Auftrag currentAuftrag = auftragService.getAuftrag(ps);
        assert currentAuftrag != null;
        int psIndex = currentAuftrag.getProzessSchritte().indexOf(ps);
        Boolean current = true;
        for (int i = 0; i < psIndex; i++) {
            if (!hasFinished(currentAuftrag.getProzessSchritte().get(i))) {
                current = false;
            }
        }
        return current;

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

    @Inject
    AuftragService auftragService;

    /**
     * returns the current assignments of the stations
     *
     * @return a set containing all availabe jobs
     * @throws UserNotFoundException if there is no user which is logged in
     */
    public List<ProzessSchritt> getSchritte() throws UserNotFoundException {

        List<ProzessSchritt> r = experimentierStationService.getSchritteByUser(userService.getCurrentUser());
        r.removeAll(Collections.singleton(null));
        List<ProzessSchritt> result = new ArrayList<>();
        for (ProzessSchritt ps :
                r) {
            Auftrag curA = auftragService.getAuftrag(ps);
            if (curA == null) {
                return new ArrayList<>(); // Throw something?
            }
            assert curA.getProzessKettenZustandsAutomat() != null;
            Enum<ProzessKettenZustandsAutomat> pkA = curA.getProzessKettenZustandsAutomat();
            ProzessSchritt lastPS = getLastPS(ps);
            boolean moeglich = (!(pkA.equals(ProzessKettenZustandsAutomat.INSTANZIIERT)
                    || pkA.equals(ProzessKettenZustandsAutomat.ABGELEHNT)));
            boolean current = isCurrentStep(ps);
            boolean delivered = lastPS != null && isDelivered(lastPS, ps);

            if (moeglich && (current || delivered || ps.isUrformend())) {
                result.add(ps);
            }
        } // FIXME Add field is current and eta
        result.sort(Comparator.comparing(o -> {
            try {
                return auftragDAO.getObjById(auftragService.getAuftrag(o).getPkID()).getPriority();
            } catch (AuftragNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }));
        result.removeAll(Collections.singleton(null));
        return result;
    }

    /**
     * verfügbare schritte
     *
     * @return verfügbare Schritte
     */
    public List<ProzessSchritt> getJobs(User u) {
        return experimentierStationService.getJobsByUser(u);
    }

    @Inject
    AuftragDAO auftragDAO;

    public ProzessSchritt getLastPS(ProzessSchritt ps) {
        // Find out to which pk ps belongs
        var as = auftragDAO.getAll();
        Auftrag aC = null;
        for (Auftrag a :
                as) {
            // Does it contain the ps.id
            var r = a.getProzessSchritte().stream().filter(p -> p.getId() == ps.getId()).findFirst().orElse(null);
            if (r != null) {
                aC = a;
            }
        }
        // PK needs to have the ps
        assert aC != null;
        // Find out at wich step we are
        int currentIndex = aC.getProzessSchritte().indexOf(ps);
        if (currentIndex < 1) {
            return null;
        } else {
            // This is the next Step
            return aC.getProzessSchritte().get(currentIndex - 1);
        }
    }

    public Boolean isDelivered(ProzessSchritt prozessSchritt, ProzessSchritt ps) {
        boolean delivered = false;
        if (prozessSchritt.getTransportAuftrag() != null) {
            delivered = prozessSchritt.getTransportAuftrag().getAbgeliefert() != null;
        }
        return delivered;
    }
//        r.sort(Comparator.comparing(o -> auftragDAO.getObjById(o.getId()).getPriority();

    @Inject
    ProbeDAO probeDAO;

    /**
     * Get all process steps from the database
     * sets the current state of this ProzessSchritt
     * // TODO Liam
     * Add Field to Model for Calculating Average PS Time in PSV
     *
     * @param ps      the ProzessSchritt
     * @param zustand the new state
     * @throws ExperimentierStationNotFoundException          the station of the step was not found in the database
     * @throws ProzessSchrittNotFoundException                the ProzessSchritt is not in the database
     * @throws ProzessSchrittLogNotFoundException             the ProzessSchritt is not in the database
     * @throws DuplicateProzessSchrittLogException            the ProzessSchritt is not in the database
     * @throws ProzessSchrittZustandsAutomatNotFoundException the ProzessSchritt is not in the database
     */
    public void setZustand(ProzessSchritt ps, String zustand, LocalDateTime d)
            throws ExperimentierStationNotFoundException, ProzessSchrittNotFoundException, ProzessSchrittLogNotFoundException,
            DuplicateProzessSchrittLogException, ProzessSchrittZustandsAutomatNotFoundException {
        if (ps == null || zustand == null) {
            throw new IllegalArgumentException();
        } else if (!ps.getProzessSchrittZustandsAutomat().getZustaende().contains(zustand)) {
            throw new IllegalArgumentException("state not possible for this ProzessSchritt");
        } else {
            if (lastZustand(ps, zustand)) {
                try {
                    experimentierStationService.updateCurrent(ps, findStation(ps));
                } catch (DuplicateTransportAuftragException | StandortNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (ps.isUrformend() && ps.getProzessSchrittZustandsAutomat().getCurrent().equals("Erstellend")) {
                try {
                    erstelleProbe(findStation(ps).getStandort(), ps.getCreatedName(), ps.getAmountCreated());
                } catch (ProbeNotFoundException | DuplicateProbeException e) {
                    e.printStackTrace();
                }
            }
            ps.getProzessSchrittZustandsAutomat().setCurrent(zustand);
            prozessSchrittLogService.closeLog(ps.getProzessSchrittLog().get(ps.getProzessSchrittLog().size() - 1), d);
            ps.getProzessSchrittLog().add(prozessSchrittLogService.newLog(zustand, d));
            prozessSchrittZustandsAutomatDAO.update(ps.getProzessSchrittZustandsAutomat());
            prozessSchrittDAO.update(ps);
        }
    }

    public Probe erstelleProbe(Standort standort, String probenID, int anzahl) throws ProbeNotFoundException, DuplicateProbeException {
        int vorherigeAnzahl = 0;
        int verloreneAnzahl = 0;
        try {
            vorherigeAnzahl = probeDAO.getObjById(probenID).getAnzahl();
            verloreneAnzahl = probeDAO.getObjById(probenID).getLost();
        } catch (ProbeNotFoundException e) {
            log.info("vorherige Anzahl kann nicht gefunden werden, weil die Probe nicht existierte");
        }
        Probe p = new Probe(probenID, vorherigeAnzahl + anzahl, ProbenZustand.ARCHIVIERT, standort);
        p.setLost(verloreneAnzahl);
        probeDAO.update(p);
        probeDAO.persist(p);
        return p;
    }

    public ExperimentierStation findStation(ProzessSchritt ps)
            throws IllegalArgumentException {
        if (ps == null) {
            throw new IllegalArgumentException();
        }
        for (ExperimentierStation e : experimentierStationService.getAll()) { //TODO jeder schritt nur an einer station?
            List<Integer> psids = new ArrayList<>();
            for (ProzessSchritt p : e.getNextPS()) {
                psids.add(p.getId());
            }
            if (psids.contains(ps.getId()) || (e.getCurrentPS() != null && e.getCurrentPS().getId() == ps.getId())) {
                return e;
            }
        }
        return null;
    }


}

