package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.BedingungNotFoundException;
import de.unibremen.sfb.exception.DuplicateBedingungException;
import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.persistence.BedingungDAO;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Slf4j
@Singleton
public class BedingungService implements Serializable {

    /** List of all predicates in the database */
    private List<Bedingung> bs;

    /** The DAO */
    @Inject
    private BedingungDAO qeDAO;

    /** Init on start */
    @PostConstruct
    public void init() {
        bs = qeDAO.getAll();
    }
    
    
    /** Add a new Bedingung
     * @param bedingung - the Bedingung to add
     * @throws DuplicateBedingungException on failure */
    public void addES(Bedingung bedingung) throws DuplicateBedingungException {
        qeDAO.persist(bedingung);
    }

    /** Remove an Bedingung
     * @param bedingung - the Bedingung to delete
     */
    public void loescheES(Bedingung bedingung) throws BedingungNotFoundException {
        qeDAO.remove(bedingung);
        bs = getAll();
    }

    /** Find an Bedingung using its name
     * @param name - the Bedingung's name */
    public Bedingung findByName(String name) {
        // FIXME Use String as ID or convert to String
        bs = qeDAO.getAll();
        return this.bs.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    /** @return a list of all bedingung in the system */
    public List<Bedingung> getAll(){
        return qeDAO.getAll();
    }

    /** Update an existing Bedingung in the database
     * @param es - the Bedingung to update
     * @throws BedingungNotFoundException on failure */
    public void updateES(Bedingung es) throws BedingungNotFoundException{
        qeDAO.update(es);
    }

    /** Add a new predicate */
    public void addBedingung(Bedingung bedingung) {
        try {
            log.info("Trying Persis Bedingung");
            qeDAO.persist(bedingung);
        } catch (Exception e) {
            log.info("FAILED Persis Bedingung");
        }
    }
    

    /** Remove a predicate from the database */
    public void remove(Bedingung bedingung) {
        try {
            log.info("Trying Bedingung Methode = remove");
            qeDAO.remove(bedingung);
        } catch (Exception e) {
            log.info("FAILED Bedingung Methode = remove");
        }
    }

    /** Edit a predicate in the database */
    public void edit(Bedingung bedingung) {
        try {
            log.info("Trying Bedingung Methode = edit");
            qeDAO.update(bedingung);
        } catch (Exception e) {
            log.info("FAILED Bedingung Methode = edit");
        }
    }
}
