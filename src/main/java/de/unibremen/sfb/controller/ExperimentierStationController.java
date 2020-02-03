package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;

import javax.inject.Inject;
import java.util.Set;
import java.util.Queue;

/**
 * this class manages communication with models of experimenting stations (ExperimentierStationen)
 */
public class ExperimentierStationController {

    public ExperimentierStation experimenteristation;

    @Inject
    private ExperimentierStationDAO experimentierStationDAO;

    /**
     * returns the ID of this station
     *
     * @return the ID
     */
    public int getESID() { return experimenteristation.getEsID(); }

    /**
     * Sets the Standort (location) of this station
     *
     * @return the new Standort
     */
    public Standort getESStandort() { return experimenteristation.getStandort(); }

    /**
     * Sets the current Status (state) of the station
     * Possible values: Verfügbar(available), Besetzt(in use), Kaputt(broken)
     * @param esz the new Status of this ExperimentierStation
     */
    public void setStatus(ExperimentierStationZustand esz) {
        if(esz != null) {
            ExperimentierStationZustand temp = getStatus();
            experimenteristation.setStatus(esz);
            try {
                experimentierStationDAO.update(experimenteristation);
            }
            catch(ExperimentierStationNotFoundException e) {
                experimenteristation.setStatus(temp);
            }
        }

    }

    /**
     * Returns the current Status (state) of this station.
     * Possible values: Verfügbar(available), Besetzt(in use), Kaputt(broken)
     * @return the current Status of this ExperimentierStation
     */
    public ExperimentierStationZustand getStatus() { return experimenteristation.getStatus(); }

    /**
     * Adds a ProzessSchritt to the waiting queue.
     * The ProzessSchritt is added at the spot fitting its priority.
     *
     * @param ps the added ProzessSchritt
     */
    public void addNextPS(ProzessSchritt ps) {}

    /**
     * Returns the next ProzessSchritt to be executed at this station.
     * The ProzessSchritt stays in the queue.
     *
     * @return the next durchzuführende ProzessSchritt
     */
    public ProzessSchritt peekNextPS() { return null; }

    /**
     * Returns the next ProzessSchritt to be executed at this station.
     * The ProzessSchritt is removed from the queue.
     *
     * @return the next ProzessSchritt
     */
    public ProzessSchritt popNextPS() { return null; }

    /**
     * Returns the usage of this station (how many jobs are currently being proccessed/waiting)
     * @return the usage
     */
    public int getUsage() {
        return 0;
    }

    /**
     * sets the types carriers have to be in order to enter this experimenting station
     * @param ta a set containing all carrier types for entry
     */
    public void setEingabe(Set<TraegerArt> ta) {

    }

    /**
     * sets the types carriers are when they exit this experimenting station
     * @param ta a set containing all carrier types for exit
     */
    public void setAusgabe(Set<TraegerArt> ta) {}

    /**
     * sets the conditions for this experimenting station
     * @param b a set containing all conditions
     */
    public void setBedingung(Set<Bedingung> b) {
        if(b != null) {
            Set<Bedingung> temp = experimenteristation.getBedingungen();
            experimenteristation.setBedingungen(b);
            try {
                experimentierStationDAO.update(experimenteristation);
            }
            catch(ExperimentierStationNotFoundException e) {
                experimenteristation.setBedingungen(temp);
            }
        }
    }

    /**
     * reports an experimenting station as broken
     * @param es the broken experimenting station
     */
    public void reportBroken(ExperimentierStation es) {
        es.setStatus(ExperimentierStationZustand.KAPUTT);
        try {
            experimentierStationDAO.update(es);
        }
        catch(ExperimentierStationNotFoundException e) {

        }
    }

    public Queue<ProzessSchritt> getQueue(ExperimentierStation es) {
        //return es.getNextPS();
        return null;
    }
}
