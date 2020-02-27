package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittLog;
import de.unibremen.sfb.persistence.ProzessSchrittLogDAO;

import javax.inject.Inject;
import java.time.LocalDateTime;

public class ProzessSchrittLogService {

    @Inject
    private ProzessSchrittLogDAO pslDAO;

    /**
     * adds to a ProzessSchrittLog
     * @param l the log
     */
    public void closeLog(ProzessSchrittLog l) throws ProzessSchrittLogNotFoundException {
        l.setGeendet(LocalDateTime.now());
        pslDAO.update(l);
    }

    /**
     * creates a new log
     * @param z the new state
     */
    public ProzessSchrittLog newLog(String z) throws DuplicateProzessSchrittLogException {
        ProzessSchrittLog l = new ProzessSchrittLog(LocalDateTime.now(), z);
        pslDAO.persist(l);
        return l;
    }

    /**
     * Persist the psl
     * @param prozessSchrittLog the log
     * @throws DuplicateProzessSchrittLogException is passed to bean
     */
    public void add(ProzessSchrittLog prozessSchrittLog) throws  DuplicateProzessSchrittLogException {
        pslDAO.persist(prozessSchrittLog);
    }

}
