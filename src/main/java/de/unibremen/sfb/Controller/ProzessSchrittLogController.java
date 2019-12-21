package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittLog;
import java.time.LocalDate;

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
    public void setStart(LocalDate t) {}

    /**
     * Returns the starting time of the corresponding ProzessSchritt
     *
     * @return Die Startzeit
     */
    public LocalDate getStart() { return null; }

    /**
     * Sets the ending time of the corresponding ProzessSchritt
     *
     * @param t The new ending time
     */
    public void setBeendet(LocalDate t) {}

    /**
     * Returns the ending time of the corresponding ProzessSchritt
     *
     * @return the ending time
     */
    public LocalDate getBeendet() { return null; }

    /**
     * Sets the time at which the corresponding ProzessSchritt was archived
     *
     * @param t The new timestamp
     */
    public void setArchiviert(LocalDate t) {}

    /**
     * Returns the time at which the corresponding ProzessSchritt was archived
     *
     * @return The timestamp
     */
    public LocalDate getArchiviert() { return null; }
}
