package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittLog;

import java.time.LocalDateTime;

/**
 * this class manages the interaction with models of process chain logs (ProzessKettenLog)
 */
public class ProzessSchrittLogController {

    /**
     * the ProzessSchrittLog managed by an instance of this controller
     */
    public ProzessSchrittLog pslog;

    /**
     * Sets the starting time of the ProzessSchritt corresponding to this protocol
     *
     * @param t The new starting time
     */
    public void setStart(LocalDateTime t) {}

    /**
     * Returns the starting time of the corresponding ProzessSchritt
     *
     * @return Die Startzeit
     */
    public LocalDateTime getStart() { return null; }

    /**
     * Sets the ending time of the corresponding ProzessSchritt
     *
     * @param t The new ending time
     */
    public void setBeendet(LocalDateTime t) {}

    /**
     * Returns the ending time of the corresponding ProzessSchritt
     *
     * @return the ending time
     */
    public LocalDateTime getBeendet() { return null; }

    /**
     * Sets the time at which the corresponding ProzessSchritt was archived
     *
     * @param t The new timestamp
     */
    public void setArchiviert(LocalDateTime t) {}

    /**
     * Returns the time at which the corresponding ProzessSchritt was archived
     *
     * @return The timestamp
     */
    public LocalDateTime getArchiviert() { return null; }
}
