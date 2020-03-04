package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import de.unibremen.sfb.persistence.TransportAuftragDAO;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Service fuer AuftragService
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */
@Slf4j
@Getter
@Setter
@Transactional
public class AuftragService implements Serializable {

    @Inject
    TransportAuftragDAO transportAuftragDAO;

    @Inject
    ExperimentierStationService experimentierStationService;

    @Inject
    UserService userService;


    /**
     * Job DAO
     */
    @Inject
    private AuftragDAO auftragDAO;

    /**
     * Add a new job to the database
     *
     * @param a - the job to add
     * @throws DuplicateAuftragException on failure
     */
    public void add(Auftrag a) throws DuplicateAuftragException {
        auftragDAO.persist(a);
    }

    /**
     * Update a job in the database
     *
     * @param a - the job to update
     * @throws AuftragNotFoundException on failure
     */
    public void update(Auftrag a) throws AuftragNotFoundException {
        auftragDAO.update(a);
    }

    /**
     * Remove a job from the database
     *
     * @param a - the job to remove
     * @throws AuftragNotFoundException on failure
     */
    public void remove(Auftrag a) throws AuftragNotFoundException {
        auftragDAO.remove(a);
    }

    /**
     * Get a list of all jobs in the database
     *
     * @return a list of all jobs or an empty arraylist
     */
    public List<Auftrag> getAll() {
        return auftragDAO.getAll();
    }

    /**
     * Get a job using its id
     *
     * @param id - the id of the job
     * @return the job with a matching id
     * @throws AuftragNotFoundException on failure
     */
    public Auftrag getObjById(int id) throws AuftragNotFoundException {
        return auftragDAO.getObjById(id);
    }


    /**
     * searches for the Auftrag the ProzessSchritt belongs to
     *
     * @param ps the prozessSchrittList which's Auftrag is looked for
     * @return the Auftrag (or null, if none was found)
     */
    public Auftrag getAuftrag(ProzessSchritt ps) {
        for (Auftrag a :
                getAuftrage()) {
            if (a.getProzessSchritte().contains(ps)) {
                return a;
            }
        }
        return null;
    }

    public List<Auftrag> getAuftrage() {
        return auftragDAO.getAll();
    }

    /**
     * returns the ProzessSchritte currently waiting in all experimenting stations the user is assigned to
     *
     * @param u the user (a Technologe)
     * @return a list containing all process steps waiting
     */
    public List<ProzessSchritt> getPotentialStepsByUser(User u) {
        List<ProzessSchritt> ps = new ArrayList<>();
        for (ExperimentierStation e : experimentierStationService.getESByUser(u)) {
            ps.addAll(e.getNextPS());
        }
        ps.removeAll(Collections.singleton(null));
        return ps;
    }


    /**
     * Serialize a job to json
     */
    public void json() {

    }

    public void updateTransportZustand(TransportAuftrag transportAuftrag) throws TransportAuftragNotFoundException {
        transportAuftragDAO.update(transportAuftrag);
    }


    /**
     * Hole Alle ProzessSchritte die als Transport Zustand ERSTELLT haben
     *
     * @return alle prozessSchrittList fuer den Transport
     */
    public List<ProzessSchritt> getTransportSchritt() {
        var s = new HashSet<ProzessSchritt>();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> p.getTransportAuftrag().getZustandsAutomat() == TransportAuftragZustand.ERSTELLT)
                    .collect(Collectors.toSet()));
        }
        return s.isEmpty() ? new ArrayList<>() : List.copyOf(s);
    }

    public List<ProzessSchritt> getTransportSchritt2() throws UserNotFoundException {
        var s = new HashSet<ProzessSchritt>();
        User user = userService.getCurrentUser();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> (p.getTransportAuftrag().getZustandsAutomat() == TransportAuftragZustand.ABGEHOLT && p.getTransportAuftrag().getUser() == user))
                    .collect(Collectors.toSet()));
        }
        return s.isEmpty() ? new ArrayList<>() : List.copyOf(s);
    }

    /**
     *
     * @param value
     * @return The TransportAuftag with the specified value
     * @throws TransportAuftragNotFoundException
     */
    public TransportAuftrag getTransportAuftragByID(int value) throws TransportAuftragNotFoundException {
        return transportAuftragDAO.getTransportAuftragById(value);
    }

    public void sedTransportZustandAbgeholt(TransportAuftrag t) throws TransportAuftragNotFoundException {
        t.setZustandsAutomat(TransportAuftragZustand.ABGEHOLT);
        t.setAbgeholt(LocalDateTime.now());
        try {
            t.setUser(userService.getCurrentUser());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        updateTransportZustand(t);
    }

    public void sedTransportZustandAbgeliefert(TransportAuftrag t) throws TransportAuftragNotFoundException {
        t.setZustandsAutomat(TransportAuftragZustand.ABGELIEFERT);
        t.setAbgeholt(LocalDateTime.now());
        updateTransportZustand(t);
    }




}
