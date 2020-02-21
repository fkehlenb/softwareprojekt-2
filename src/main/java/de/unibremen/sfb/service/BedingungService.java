package de.unibremen.sfb.service;

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
    private BedingungDAO qeDAO;

    /** Init on start */
    @PostConstruct
    public void init() {
        bs = qeDAO.getAll();
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
