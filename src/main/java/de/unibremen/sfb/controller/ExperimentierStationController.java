package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.*;

import java.util.Set;

/**
 * this class manages communication with models of experimenting stations (ExperimentierStationen)
 */
public class ExperimentierStationController {

    public ExperimentierStation experimenteristation;

    /**
     * returns the ID of this station
     *
     * @return the ID
     */
    public int getESID() { return 0; }

    /**
     * Sets the Standort (location) of this station
     *
     * @return the new Standort
     */
    public String getESStandort() { return null; }

    /**
     * Sets the current Status (state) of the station
     * Possible values: Verfügbar(available), Besetzt(in use), Kaputt(broken)
     * @param esz the new Status of this ExperimentierStation
     */
    public void setStatus(ExperimentierStationZustand esz) {}

    /**
     * Returns the current Status (state) of this station.
     * Possible values: Verfügbar(available), Besetzt(in use), Kaputt(broken)
     * @return the current Status of this ExperimentierStation
     */
    public ExperimentierStationZustand getStatus() { return null; }

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
    public int getUsage() { return 0; }

    /**
     * sets the types carriers have to be in order to enter this experimenting station
     * @param ta a set containing all carrier types for entry
     */
    public void setEingabe(Set<TraegerArt> ta) {}

    /**
     * sets the types carriers are when they exit this experimenting station
     * @param ta a set containing all carrier types for exit
     */
    public void setAusgabe(Set<TraegerArt> ta) {}

    /**
     * sets the conditions for this experimenting station
     * @param b a set containing all conditions
     */
    public void setBedingung(Set<Bedingung> b) {}
}
