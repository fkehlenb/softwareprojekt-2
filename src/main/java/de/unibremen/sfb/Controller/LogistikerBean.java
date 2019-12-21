package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;

import java.util.Set;

public class LogistikerBean {

    public User logistiker;

    public Set<Traeger> getTraegerList() { return null; }

    public Traeger createTraeger() { return null; }

    public void deleteTraeger(Traeger t) {}

    public Set<Auftrag> getAuftrag() { return null; }

    public void zuorndnenProbe(Auftrag a, Probe p) {}

    public void zuordnenTraeger(Auftrag a, Traeger t) {}

    public Set<Probe> getProben() { return null; }

    public Set<Probe> getProben(boolean archiviert) { return null; }

    public Standort getProbenStandort(Probe p) { return null; }

    public void startAuftrag(Auftrag a) {}

    public void refuseAuftrag(Auftrag a, String message) {}

    public String errorMessage() { return null; }

    public void zuordnen(Auftrag a) {}
}
