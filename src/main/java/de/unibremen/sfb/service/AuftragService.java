package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.TransportAuftragDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
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
        var as = getAuftrage();
        for (Auftrag a :
                as) {
            var r = a.getProzessSchritte().stream().filter(p -> p.getId() == ps.getId()).findFirst().orElse(null);
            if (r != null) {
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
     * Updates the given TransportAuftrag
     *
     * @param transportAuftrag TransportAuftrag, which needs to be updated
     * @throws TransportAuftragNotFoundException when TransportAuftrag was not found.
     */
    public void updateTransportZustand(TransportAuftrag transportAuftrag) throws TransportAuftragNotFoundException {
        transportAuftragDAO.update(transportAuftrag);
    }


    /**
     * Filters the Transport Jobs to find all ERSTELLT.
     *
     * @return List of ProzessSchritt, which have a TransportAuftrag with TransportAuftragsZustand on ERSTELLT
     */
    public List<ProzessSchritt> getTransportSchritt() {
        var s = new ArrayList<ProzessSchritt>();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> (p.getTransportAuftrag() != null && p.getTransportAuftrag().getZustandsAutomat() == TransportAuftragZustand.ERSTELLT))
                    .collect(Collectors.toList()));
        }
        return s;
    }

    /**
     * Filters the Transport Jobs to find all ABGEHOLT.
     *
     * @return List of ProzessSchritt, which have a TransportAuftrag with TransportAuftragsZustand on ABGEHOLT
     */
    public List<ProzessSchritt> getTransportSchritt2() throws UserNotFoundException {
        var s = new HashSet<ProzessSchritt>();
        User user = userService.getCurrentUser();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> (p.getTransportAuftrag() != null && p.getTransportAuftrag().getZustandsAutomat() == TransportAuftragZustand.ABGEHOLT && p.getTransportAuftrag().getUser() == user))
                    .collect(Collectors.toSet()));
        }
        return s.isEmpty() ? new ArrayList<>() : List.copyOf(s);
    }

    /**
     * Filters the Transport Jobs to find all ABGELIEFERT.
     *
     * @return List of ProzessSchritt, which have a TransportAuftrag with TransportAuftragsZustand on ABGELIEFERT
     */
    public List<ProzessSchritt> getTransportSchritt3() {
        var s = new HashSet<ProzessSchritt>();
        for (Auftrag a :
                getAuftrage()) {
            s.addAll(a.getProzessSchritte().stream()
                    .filter(p -> p.getTransportAuftrag() != null && p.getTransportAuftrag().getZustandsAutomat() == TransportAuftragZustand.ABGELIEFERT)
                    .collect(Collectors.toSet()));
        }
        return s.isEmpty() ? new ArrayList<>() : List.copyOf(s);
    }

    /**
     * Returns the TransportAuftrag with id value.
     *
     * @param value given id
     * @return The TransportAuftag with the specified value
     * @throws TransportAuftragNotFoundException if no Transport Auftrag is found
     */
    public TransportAuftrag getTransportAuftragByID(int value) throws TransportAuftragNotFoundException {
        return transportAuftragDAO.getTransportAuftragById(value);
    }

    /**
     * Sets transportAuftragsZustand to ABGEHOLT.
     *
     * @param t übergebener TransportAuftrag
     * @throws TransportAuftragNotFoundException when transportAuftrag wasn't found.
     */
    public void sedTransportZustand(TransportAuftrag t, TransportAuftragZustand taz) throws TransportAuftragNotFoundException {
        t.setZustandsAutomat(taz);
        if(taz == TransportAuftragZustand.ABGEHOLT){
            t.setAbgeholt(LocalDateTime.now());
        }
        if(taz ==  TransportAuftragZustand.ABGELIEFERT){
            t.setAbgeliefert(LocalDateTime.now());
        }

        try {
            t.setUser(userService.getCurrentUser());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        updateTransportZustand(t);
    }

    public List<Traeger> getTraegerByPS (ProzessSchritt prozessSchritt){
        return getAuftrag(prozessSchritt).getTraeger();

//        List<Auftrag> auftragList = getAll();
//        Traeger traeger=null;
//        for (Auftrag a :
//                auftragList) {
//            if(a.getProzessSchritte().contains(prozessSchritt)){
//
//                traeger=a.getTraeger().;
//            }
//        }
//        return traeger;
    }
}
