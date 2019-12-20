package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Traeger;
import de.unibremen.sfb.Model.TraegerArt;
import de.unibremen.sfb.Model.Standort;
import de.unibremen.sfb.Model.Probe;

import java.util.Set;

public class TraegerController {

    public Traeger traeger;

    public void setID(int id) {}

    public int getID() { return 0; }

    public void setArt() {}

    public String getArt() { return null; }

    public void setTraegerArten(TraegerArt ta) {}

    public Set<String> getTraegerArten() { return null; }

    public void setStandort(Standort s) {}

    public Standort getStandort() { return null; }

    public void setProben(Set<Probe> p) {}

    public Set<Probe> getProben() { return null; }
}
