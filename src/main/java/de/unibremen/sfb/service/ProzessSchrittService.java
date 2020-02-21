package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.ProzessSchrittNotFoundException;
import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.model.ProzessSchritt;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Service fuer ProzessSchritt
 * Anwendungsfall: Bearbeiten eines ProzessSchrittes oder Hinzufügen eines neuen
 */
public class ProzessSchrittService implements Serializable {

    @Inject
    ProzessSchrittDAO prozessSchrittDAO;


    @Inject
    ExperimentierStationService experimentierStationService;

    /**
     * Get all PS which belong to user
     * @param u the current user
     * @return the ps
     */
    public List<ProzessSchritt> getProbenByUser(User u) {
        var ps = new ArrayList<ProzessSchritt>();
        for (ExperimentierStation e :
                experimentierStationService.getESByUser(u)) {
            ps.add(e.getCurrentPS());
        }
        return ps;
    }


    /**
     * sets the current state of this ProzessSchritt
     * @param ps the ProzessSchritt
     * @param zustand the new state
     * @throws ProzessSchrittNotFoundException the ProzessSchritt is not in the database
     */
    public void setZustand(ProzessSchritt ps, String zustand) throws ProzessSchrittNotFoundException {
        if(! ps.getZustandsAutomat().getProzessSchrittZustandsAutomatVorlage().getZustaende().contains(zustand)) {
            throw new IllegalArgumentException("state not possible for this ProzessSchritt");
        }
        else {
            ps.getZustandsAutomat().setCurrent(zustand);
            prozessSchrittDAO.update(ps);
        }
    }
}

