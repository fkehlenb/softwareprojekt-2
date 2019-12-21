package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;

import java.util.Set;

public class TechnologeBean {

    public User technologe;

    public Set<ExperimentierStation> getStationen() { return null; }

    public Set<Auftrag> getAuftrag() { return null; }

    public void setAuftragsZustand(Auftrag a) {}

    public void reportBroken(ExperimentierStation es) {}

    public void assignToAuftrag(Auftrag a) {}

    public void prioSort(Set<Auftrag> prio) {}

    public void createUrformend() {}

    public void addComment(ProzessSchritt ps, String c) {}

    public void editComment(ProzessSchritt ps, String c) {}

    public void deleteComment(ProzessSchritt ps, String c) {}

    public void addProbenComment(Probe p, String c) {}

    public void editProbenComment(Probe p, String c) {}

    public void deleteProbenComment(Probe p, String c) {}

    public Set<Probe> viewToBeUploaded() { return null; }

    public void upload(Probe p) {}

    public void reportLostProbe(Probe p) {}

    public void reportLostProbe(int id) {}

    public void errorMessage(String e) {}



}
