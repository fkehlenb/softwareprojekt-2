package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;

import java.util.List;
import java.util.Set;
import java.util.Queue;

/**
 * this class manages communication with models of experimenting stations (ExperimentierStationen)
 */
public class ExperimentierStationController {

    /** The experimenting station */
    public ExperimentierStation experimenteristation;

    @Inject
    private ExperimentierStationDAO experimentierStationDAO;

    /**
     * returns the ID of this station
     *
     * @return the ID
     */
    public int getESID() {
        return experimenteristation.getEsID();
    }

    /**
     * Get the standort of the experimenting station
     * @return the new Standort
     */
    public Standort getESStandort() {
        return experimenteristation.getStandort();
    }

    /**
     * Sets the current Status (state) of the station
     * Possible values: Verfügbar(available), Besetzt(in use), Kaputt(broken)
     * @param esz the new Status of this ExperimentierStation
     */
    public void setStatus(ExperimentierStationZustand esz) {
        experimenteristation.setStatus(esz);
    }

    }

    /**
     * Returns the current Status (state) of this station.
     * Possible values: Verfügbar(available), Besetzt(in use), Kaputt(broken)
     * @return the current Status of this ExperimentierStation
     */
    public Enum<ExperimentierStationZustand> getStatus() {
        return experimenteristation.getStatus();
    }

    /**
     * Adds a ProzessSchritt to the waiting queue.
     * The ProzessSchritt is added at the spot fitting its priority.
     *
     * @param ps the added ProzessSchritt
     */
    public void addNextPS(ProzessSchritt ps) {
        //TODO
    }

    /**
     * Returns the next ProzessSchritt to be executed at this station.
     * The ProzessSchritt stays in the queue.
     *
     * @return the next durchzuführende ProzessSchritt
     */
    public ProzessSchritt peekNextPS() { return null; //TODO
         }

    /**
     * Returns the next ProzessSchritt to be executed at this station.
     * The ProzessSchritt is removed from the queue.
     *
     * @return the next ProzessSchritt
     */
    public ProzessSchritt popNextPS() { return null; //TODO
    }

    /**
     * Returns the usage of this station (how many jobs are currently being proccessed/waiting)
     * @return the usage
     */
    public int getUsage() {
        return 0; //TODO
    }

    /**
     * sets the types carriers have to be in order to enter this experimenting station
     * @param ta a set containing all carrier types for entry
     */
    public void setEingabe(List<TraegerArt> ta) {
        //TODO
    }

    }

    /**
     * sets the types carriers are when they exit this experimenting station
     * @param ta a set containing all carrier types for exit
     */
    public void setAusgabe(List<TraegerArt> ta) {
        //TODO
    }

    /**
     * sets the conditions for this experimenting station
     * @param b a set containing all conditions
     */
    public void setBedingung(List<Bedingung> b) {
        experimenteristation.setBedingungen(b);
    }

    /** Get the conditions for using the experimenting station
     * @return the conditions for using the station */
    public List<Bedingung> getBedingungen(){
        return experimenteristation.getBedingungen();
    }
}
