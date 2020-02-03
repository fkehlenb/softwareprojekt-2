package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.ProzessSchrittZustandsAutomatVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittZustandsAutomatVorlageDAO;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction with models of process chain state automat templates (ProzessSchrittZustandsAutomatVorlage)
 */
public class ProzessSchrittZustandsAutomatVorlageController {

    /**
     * the ProzessSchrittZustandsAutomatVorlage managed by an instance of this controller
     */
    public ProzessSchrittZustandsAutomatVorlage pszav;

    @Inject
    private ProzessSchrittZustandsAutomatVorlageDAO pszavDAO;

    /**
     * Sets the possible states of this ProzessSchrittZustandsAutomat
     * @param pszavz A Set containing all possible states the ProzessSchrittZustandsAutomat should have
     */
    public void setProzessSchrittZustandsAutomatVorlageZustaende(List<String> pszavz) { //TODO list oder set
        if(pszavz!=null) {
            //TODO warum @Getter in ProzessSchrittZustandsAutomatVorlage? Warum keine Setter?
        }
    }

    /**
     * Returns the possible states of this ProzessSchrittZustandsAutomat
     * @return A Set containing all possible states
     */
    public List<String> getProzessSchrittZustandsAutomatVorlageZustaende() { return pszav.getZustaende(); }

    public Set<String> getDefaults() {
        Set<String> returns = new HashSet<>();
        returns.add("Angenommen");
        returns.add("In Bearbeitung");
        returns.add("Bearbeitet");
        returns.add("Weitergeleitet");
        return returns;
    }
}
