package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittZustandsAutomatVorlage;

import java.util.HashSet;
import java.util.Set;

/**
 * this class manages the interaction with models of process chain state automat templates (ProzessSchrittZustandsAutomatVorlage)
 */
public class ProzessSchrittZustandsAutomatVorlageController {

    /**
     * the ProzessSchrittZustandsAutomatVorlage managed by an instance of this controller
     */
    public ProzessSchrittZustandsAutomatVorlage pszav;

    /**
     * Sets the possible states of this ProzessSchrittZustandsAutomat
     * @param pszavz A Set containing all possible states the ProzessSchrittZustandsAutomat should have
     */
    public void setProzessSchrittZustandsAutomatVorlageZustaende(Set<String> pszavz) {}

    /**
     * Returns the possible states of this ProzessSchrittZustandsAutomat
     * @return A Set containing all possible states
     */
    public Set<String> getProzessSchrittZustandsAutomatVorlageZustaende() { return null; }

    public Set<String> getDefaults() {
        Set<String> returns = new HashSet<String>();
        returns.add("Angenommen");
        returns.add("In Bearbeitung");
        returns.add("Bearbeitet");
        returns.add("Weitergeleitet");
        return returns; //TODO sind das alle
    }
}
