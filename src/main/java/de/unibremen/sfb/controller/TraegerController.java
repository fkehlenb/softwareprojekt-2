package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.model.Traeger;
import de.unibremen.sfb.model.TraegerArt;

import java.util.Set;

/**
 * this class manages the interaction with models of carriers (Traeger)
 */
public class TraegerController {

    /**
     * the Traeger managed by an instance of this controller
     */
    public Traeger traeger;

    /**
     * Sets the ID of this Traeger (carrier)
     *
     * @param id The new Traeger-ID
     */
    public void setID(int id) {}

    /**
     * Returns the ID of this Traeger (carrier)
     *
     * @return The ID of this Traeger
     */
    public int getID() { return 0; }

    /**
     * Sets the Art (type) of this Traeger (carrier)
     */
    public void setArt() {}

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
    public void setTraegerArten(TraegerArt ta) {}

    /**
     * Returns all possible TraegerArten (carrier types) a Traeger (carrier) can be
     *
     * @return A Set containing all possible TraegerArten
     */
    public Set<String> getTraegerArten() { return null; }

    /**
     * Sets the Standort (location) of this Traeger (carrier)
     *
     * @param s The new Standort
     */
    public void setStandort(Standort s) {}

    /**
     * Returns the current Standort (location) of this Traeger (carrier)
     *
     * @return the current Standort
     */
    public Standort getStandort() { return null; }

    /**
     * Sets the Proben which are currently carried by this Traeger
     *
     * @param p A Set containing all Proben which the Traeger should carry
     */
    public void setProben(Set<Probe> p) {}

    /**
     * Returns the Proben which are currently carried by this Traeger
     *
     * @return A Set containing all Proben in this Traeger
     */
    public Set<Probe> getProben() { return null; }
}
