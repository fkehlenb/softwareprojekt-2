package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittLogException;
import de.unibremen.sfb.exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittLog;
import de.unibremen.sfb.persistence.ProzessSchrittLogDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;

public class ProzessSchrittLogService implements Serializable {

    @Inject
    private ProzessSchrittLogDAO pslDAO;

    /**
     * adds to a ProzessSchrittLog
     * @param l the log
     * @param d  the local date Time
     * @throws ProzessSchrittLogNotFoundException is there is none
     *
     */
    public void closeLog(ProzessSchrittLog l, LocalDateTime d) throws ProzessSchrittLogNotFoundException {
        l.setGeendet(d);
        pslDAO.update(l);
    }

    /**
     * creates a new log
     * @param z the new state
     * @throws DuplicateProzessSchrittLogException if there is already one PSL
     * @param d the local Date time
     * @return the new Log
     */
    public ProzessSchrittLog newLog(String z, LocalDateTime d) throws DuplicateProzessSchrittLogException {
        ProzessSchrittLog l = new ProzessSchrittLog(d, z);
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
