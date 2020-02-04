package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import org.apache.commons.lang3.tuple.Pair;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction with models of process step templates (ProzessSchrittVorlage)
 */
public class ProzessSchrittVorlageController {

    /**
     * the ProzessSchrittVorlage managed by an instance of this controller
     */
    public ProzessSchrittVorlage psv;

    @Inject
    private ProzessSchrittVorlageDAO psvDAO;

    @Inject
    private ProzessSchrittZustandsAutomatVorlageController pszavController;

    /**
     * Returns the ExperimentierStationen at which this
     * ProzessSchrittVorlage could be executed once instantiated.
     *
     * @param b a set with conditions for this station
     * @return  A set with every possible ExperimentierStation
     */
    public List<ExperimentierStation> getES(Set<Bedingung> b) { return psv.getStationen(); } //TODO automatisch finden mit bedingungen?

    /**
     * Adds a ExperimentierStation to the ExperimentierStationen
     * at which this ProzessSchrittVorlage could be executed once instantiated as a ProzessSchritt
     *
     * @param es The new Experimentierstation
     */
    public void setES(ExperimentierStation es) {
        if(es!=null) {
            List<ExperimentierStation> temp = psv.getStationen();
            List<ExperimentierStation> set = temp;
            set.add(es);
            psv.setStationen(set);
            try {
                psvDAO.update(psv);
            }
            catch(ProzessSchrittVorlageNotFoundException e) {
                e.printStackTrace();
                psv.setStationen(temp);
            }
        }
    }

    /**
     * Adds a ExperimentierStation to the ExperimentierStationen //TODO what?
     * at which this ProzessSchrittVorlage coudld be executed once instantiated as a ProzessSchritt
     */
    public void setED() {}

    /**
     * Sets the ID for this ProzessSchrittVorlage
     *
     * @param id The new ID
     */
    public void setID(int id) {
        try {
            psvDAO.getObjById(id);
        }
        catch(ProzessSchrittVorlageNotFoundException e) {
            psv.setPsVID(id);
            try {
                psvDAO.update(psv);
            }
            catch(ProzessSchrittVorlageNotFoundException f) {
                f.printStackTrace();
            }
        }
    }

    /**
     * Returns the ID for this ProzessSchritt
     *
     * @return The ID
     */
    public int getID() { return psv.getPsVID(); }

    /**
     * Returns the Zustände (states) in which this ProzessSchrittVorlage
     * could be once instantiated as a ProzessSchritt
     *
     * @return A Set with every possible state
     */
    public Set<ProzessSchrittZustandsAutomat> getZustaende() {
        //return psv.getZustandsAutomat().getZustaende(); //TODO Set String <-> psza
        return null;
    }

    /**
     * Sets the Zustände (states) in which this ProzessSchrittVorlage
     * could be once instantiated as a ProzessSchritt
     *
     * @param psza A set with the new states
     */
    public void setZustaende(Set<ProzessSchrittZustandsAutomat> psza) {
        if(psza!=null) {
            //pszavController.setProzessSchrittZustandsAutomatVorlageZustaende(psza); //TODO set string <-> psza
        }
    }

    /**
     * Returns the TraegerArten (carrier types) this ProzessSchrittVorlage accepts for output
     * @return A Set containing all accepted TraegerArten
     */
    public Set<TraegerArt> getAusgabeTraeger() { return null; } //TODO TraegerArten Frage

    /**
     * Sets the TraegerArten (carrier types) this ProzessSchrittVorlage accepts for output
     * @param at A Set containing all accepted TraegerArten
     */
    public void SetAusgabeTraeger(Set<TraegerArt> at) {}

    /**
     * Returns the TraegerArten (carrier types) this ProzessSchrittVorlage accepts for input
     * @return A Set containing all accepted TraegerArten
     */
    public Set<TraegerArt> getEingabeTraeger() { return null; }

    /**
     * Sets the TraegerArten (carrier types) this ProzessSchrittVorlage accepts for input
     * @param et A Set containing all accepted TraegerArten
     */
    public void setEingabeTrager(Set<TraegerArt> et) {}

    /**
     * Sets the time it is approximately going to take
     * to execute this ProzessSchrittVorlage.
     *
     * @param d The approximate duration
     */
    public void setDauer(Duration d) {
        if(d!=null && !d.isNegative() && !d.isZero()) {
            Duration temp = getDauer();
            psv.setDauer(d);
            try {
                psvDAO.update(psv);
            }
            catch(ProzessSchrittVorlageNotFoundException e) {
                e.printStackTrace();
                psv.setDauer(temp);
            }
        }
    }

    /**
     * Returns the approximate time for the execution of this ProzessSchrittVorlage
     *
     * @return The approximate duration
     */
    public Duration getDauer() { return psv.getDauer(); }

    /**
     * This method sets the current Zustand (state) of this ProzessSchrittVorlage.
     * This method is not important for the ProzessSchrittVorlage, but for
     * the ProzessSchritt it might be instantiated to. //TODO warum ist das hier?
     *
     * @param psza The current Zustand
     */
    public void setProzessSchrittZustandsAutomat(ProzessSchrittZustandsAutomat psza) { }

    /**
     * Returns the current Zustand of this ProzessSchrittVorlage
     * This method is not important for the ProzessSchrittVorlage, but for
     * the ProzessSchritt it might be instantiated to.
     *
     * @return The current Zustand
     */
    public ProzessSchrittZustandsAutomat getProzessSchrittZustandsAutomat() { return null; }

    /**
     * Sets the ProzessSchrittArt (type) of this ProzessSchrittVorlage
     *
     * @param psa The new ProzessSchrittArt
     */
    public void setProzessSchrittArt(ProzessSchrittArt psa) {}

    /**
     * Returns the type of this ProzessSchrittVorlage
     *
     * @return The ProzessSchrittArt
     */
    public ProzessSchrittArt getProzessSchrittArt() { return null; }

    /**
     * sets the process step parameters of this process step template
     * @param psp a set containing all process step parameters this template is supposed to have
     */
    public void setProzessSchrittParameter(Set<ProzessSchrittParameter> psp) {}

    /**
     * returns all process step parameters of this process step template
     * @return a set containing all process step parameters of this template
     */
    public Set<ProzessSchrittParameter> getProzessSchrittParameter() { return null; }

    /**
     * gets propperties
     * @return a Set of QualitativeEigenschaft
     */
    public Set<QualitativeEigenschaft> getEigenschaften(){return null;}

    /**
     * sets / assigns Eigenschaften
     * @param qe a Set of QualitativeEigenschaft
     */
    public  void setEigenschaften(Set<QualitativeEigenschaft> qe){}

    /**
     * sets angeforderte Proben
     * @param ap angeforderte Proben with amount
     */
    public void setAngeforderteProben(Set<Pair<QualitativeEigenschaft,Integer>> ap){}
}
