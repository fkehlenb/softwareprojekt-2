package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.BedingungNotFoundException;
import de.unibremen.sfb.exception.DuplicateBedingungException;
import de.unibremen.sfb.model.Bedingung;
import de.unibremen.sfb.persistence.BedingungDAO;
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
    private BedingungDAO bedingungDAO;

    /** Init on start */
    @PostConstruct
    public void init() {
        bs = bedingungDAO.getAll();
    }
    
    
    /** Add a new Bedingung
     * @param bedingung - the Bedingung to add
     * @throws DuplicateBedingungException on failure */
    public void addES(Bedingung bedingung) throws DuplicateBedingungException {
        bedingungDAO.persist(bedingung);
    }

    /** Remove an Bedingung
     * @param bedingung - the Bedingung to delete
     */
    public void loescheES(Bedingung bedingung) throws BedingungNotFoundException {
        bedingungDAO.remove(bedingung);
        bs = getAll();
    }

    /** Find an Bedingung using its id
     * @param id - the Bedingung's name */
    public Bedingung findByID(int id) {
        // FIXME Use String as ID or convert to String
        Bedingung bs = bedingungDAO.findById(id);
        return bs;
    }

    /** @return a list of all bedingung in the system */
    public List<Bedingung> getAll(){
        return bedingungDAO.getAll();
    }

    /** Update an existing Bedingung in the database
     * @param es - the Bedingung to update
     * @throws BedingungNotFoundException on failure */
    public void updateES(Bedingung es) throws BedingungNotFoundException{
        bedingungDAO.update(es);
    }

    /** Add a new predicate */
    public void addBedingung(Bedingung bedingung) {
        try {
            log.info("Trying Persist Bedingung");
            bedingungDAO.persist(bedingung);
        } catch (Exception e) {
            log.info("FAILED Persist Bedingung");
        }
    }
    

    /** Remove a predicate from the database */
    public void remove(Bedingung bedingung) {
        try {
            log.info("Trying Bedingung Methode = remove");
            bedingungDAO.remove(bedingung);
        } catch (Exception e) {
            log.info("FAILED Bedingung Methode = remove");
        }
    }

    /** Edit a predicate in the database */
    public void edit(Bedingung bedingung) {
        try {
            log.info("Trying Bedingung Methode = edit");
            bedingungDAO.update(bedingung);
        } catch (Exception e) {
            log.info("FAILED Bedingung Methode = edit class BedingungService");
        }
    }

}
