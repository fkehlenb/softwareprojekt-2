package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.TraegerNotFoundException;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.persistence.TraegerDAO;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction with models of carriers (Traeger)
 */
public class TraegerController {

    /**
     * the Traeger managed by an instance of this controller
     */
    public Traeger traeger;

    @Inject
    private TraegerDAO traegerDAO;

    @Inject
    private TraegerArtController taController;

    /**
     * Sets the ID of this Traeger (carrier)
     *
     * @param id The new Traeger-ID
     */
    public void setID(int id) {
        try{
            traegerDAO.getObjById(id);
        }
        catch(TraegerNotFoundException e) {
            int temp = getID();
            traeger.setId(id);
            try{
                traegerDAO.update(traeger);
            }
            catch(TraegerNotFoundException f) {
                traeger.setId(temp);
            }
        }

    }

    /**
     * Returns the ID of this Traeger (carrier)
     *
     * @return The ID of this Traeger
     */
    public int getID() { return traeger.getId(); }

    /**
     * Sets the Art (type) of this Traeger (carrier)
     */
    public void setArt(String ta) {
        //if(taController.getTraegerArten().contains(ta)) {  }
        //TODO Arten als String und ein Objekt vom Typ TraegerArten, oder jede Art ein TraegerArt???
    }

    /**
     * Returns the Art (type) of this Traeger (carrier)
     * Examples for Types: vereinzelt (isolated), eingebettet (embedded), Glas (glass)
     * @return The type
     */
    public String getArt() { return null; }

    /**
     * Adds a new TragerArt (carrier type) to the possible TraegerArten
     * Examples for Types: vereinzelt (isolated), eingebettet (embedded), Glas (glass)
     *
     * @param ta The new TraegerArt
     */
    public void setTraegerArten(String ta) {
        Set<String> set = new HashSet<>();
        set.add(ta);
        taController.setTraegerArten(set);
    }

    /**
     * Returns all possible TraegerArten (carrier types) a Traeger (carrier) can be
     *
     * @return A Set containing all possible TraegerArten
     */
    public Set<String> getTraegerArten() { return taController.getTraegerArten(); }

    /**
     * Sets the Standort (location) of this Traeger (carrier)
     *
     * @param s The new Standort
     */
    public void setStandort(Standort s) {
        if(s!=null) {
            Standort temp = getStandort();
            traeger.setStandort(s);
            try{
                traegerDAO.update(traeger);
            }
            catch(TraegerNotFoundException e) {
                traeger.setStandort(temp);
            }
        }
    }

    /**
     * Returns the current Standort (location) of this Traeger (carrier)
     *
     * @return the current Standort
     */
    public Standort getStandort() { return traeger.getStandort(); }

    /**
     * Sets the Proben which are currently carried by this Traeger
     *
     * @param p A Set containing all Proben which the Traeger should carry
     */
    public void setProben(List<Probe> p) {
        if(p!=null) {
            List<Probe> temp = getProben();
            traeger.setProben(p);
            try{
                traegerDAO.update(traeger);
            }
            catch(TraegerNotFoundException e) {
                traeger.setProben(temp);
            }
        }

    }

    /**
     * Returns the Proben which are currently carried by this Traeger
     *
     * @return A Set containing all Proben in this Traeger
     */
    public List<Probe> getProben() { return traeger.getProben(); }

    /**
     * returns the Proben which are currently carried by a specific Traeger
     * @param t the Traeger
     * @return a List containing the samples
     */
    public List<Probe> getProben(Traeger t) {
        return t.getProben();
    }
}
