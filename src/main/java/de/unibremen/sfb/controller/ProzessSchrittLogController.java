package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ProzessSchrittLogNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittLog;
import de.unibremen.sfb.persistence.ProzessSchrittLogDAO;

import javax.inject.Inject;
import java.time.LocalDateTime;

/**
 * this class manages the interaction with models of process chain logs (ProzessKettenLog)
 */
public class ProzessSchrittLogController {

    /**
     * the ProzessSchrittLog managed by an instance of this controller
     */
    public ProzessSchrittLog pslog;

    @Inject
    private ProzessSchrittLogDAO pslDAO;

    /**
     * Sets the starting time of the ProzessSchritt corresponding to this protocol
     *
     * @param t The new starting time
     */
    public void setStart(LocalDateTime t) {
        if(t!=null) {
            pslog.setGestartet(t);
            try {
                pslDAO.update(pslog);
            }
            catch(ProzessSchrittLogNotFoundException e) {
                //TODO was machen? Error ausgeben?
            }
        }
    }

    /**
     * Returns the starting time of the corresponding ProzessSchritt
     *
     * @return Die Startzeit
     */
    public LocalDateTime getStart() { return pslog.getGestartet(); }

    /**
     * Sets the ending time of the corresponding ProzessSchritt
     *
     * @param t The new ending time
     */
    public void setBeendet(LocalDateTime t) {
        if(t!=null) {
            LocalDateTime s = getBeendet();
            pslog.setGeendet(t);
            try {
                pslDAO.update(pslog);
            }
            catch(ProzessSchrittLogNotFoundException e) {
                pslog.setGeendet(s);
            }
        }
    }

    /**
     * Returns the ending time of the corresponding ProzessSchritt
     *
     * @return the ending time
     */
    public LocalDateTime getBeendet() { return pslog.getGeendet(); }

    /**
     * Sets the time at which the corresponding ProzessSchritt was archived
     *
     * @param t The new timestamp
     */
    public void setArchiviert(LocalDateTime t) {
        if(t!=null) {
            //TODO Attribut in ProzessSchrittLog oder wenn ein ProzessSchritt archiviert, ganze Kette/Auftrag archiviert?
        }
    }

    /**
     * Returns the time at which the corresponding ProzessSchritt was archived
     *
     * @return The timestamp
     */
    public LocalDateTime getArchiviert() { return null; }
}
