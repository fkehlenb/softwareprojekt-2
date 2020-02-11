package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.AuftragsLog;
import java.time.LocalDateTime;

/**
 * this class manages the interaction with models of assignment protocols (AuftragsLogs)
 */
public class AuftragsLogController {

    /** The job log */
    public AuftragsLog log;

    /**
     * sets the starting time of the Auftrag which this protocol belongs to
     *
     * @param t the new starting time
     */
    public void setStart(LocalDateTime t) {
        log.setStart(t);
    }

    /**
     * returns the starting time of the Auftrag which this protocol belongs to
     *
     * @return the starting time
     */
    public LocalDateTime getStart() {
        return log.getStart();
    }

    /**
     * sets the time at which the corresponding  Auftrag was finished
     * @param t the new time
     */
    public void setBeendet(LocalDateTime t) {
        log.setBeendet(t);
    }

    /**
     * returns the time at which the corresponding Auftrag was finished
     *
     * @return the finish time
     */
    public LocalDateTime getBeendet() {
        return log.getBeendet();
    }

    /**
     * sets the time at which the corresponding Auftrag was archived
     *
     * @param t the new time
     */
    public void setArchiviert(LocalDateTime t) {
        log.setArchiviert(t);
    }

    /**
     * returns the time at which the corresponding Auftrag was archived
     *
     * @return the time
     */
    public LocalDateTime getArchiviert() {
        return log.getArchiviert();
    }
}
