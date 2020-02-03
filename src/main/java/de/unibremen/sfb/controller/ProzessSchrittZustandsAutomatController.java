package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ProzessSchrittZustandsAutomatNotFoundException;
import de.unibremen.sfb.model.ProzessSchrittZustandsAutomat;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatDAO;

import javax.inject.Inject;

/**
 * this class manages the interaction with models of process step state automats (ProzessSchrittZustandsAutomat)
 */
public class ProzessSchrittZustandsAutomatController {

    /**
     * the ProzessSchrittZustandsAutomat managed by an instance of this controller
     */
    public ProzessSchrittZustandsAutomat psza;

    @Inject
    private ProzessSchrittZustandsAutomatDAO pszaDAO;

    /**
     * sets the current state of this ProzessSchrittZustandsAutomat
     */
    public void setZustand(String zustand) {
        if(zustand!=null) {
            String t = getZustand();
            psza.setCurrent(zustand);
            try {
                pszaDAO.update(psza);
            }
            catch(ProzessSchrittZustandsAutomatNotFoundException e) {
                psza.setCurrent(t);
            }
        }
    }

    /**
     * Returns the current state of this ProzessSchrittZustandsAutomat
     * @return the current state
     */
    public String getZustand() { return psza.getCurrent(); }
}
