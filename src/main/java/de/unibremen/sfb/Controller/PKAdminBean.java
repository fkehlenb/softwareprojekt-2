package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;

import java.util.Set;

public class PKAdminBean {
    public User pkadmin;

    public Set<Auftrag> getAuftragList() { return null; }

    public Set<ProzessSchritt> getProzessSchrittList() { return null; }

    public void addProzessKettenVorlage(ProzessKettenVorlage pkv) {}

    public void editProzessKettenVorlage(ProzessKettenVorlage pkv) {}

    public void deleteProzessKettenVorlage(ProzessKettenVorlage pkv) {}

    public void addPSToAuftrag(ProzessSchritt ps) {}

    public void addProzessSchrittVorlage(ProzessKettenVorlage pkv, ProzessSchrittVorlage psv) {}

    public void editProzessSchrittVorlage(ProzessKettenVorlage pkv, ProzessSchrittVorlage psv) {}

    public void deleteProzessSchrittVorlage(ProzessKettenVorlage pkv, ProzessSchrittVorlage psv) {}

    public void addProbe (Probe p) {}

    public void deleteProbe(Probe p) {}

    public void addTraeger(Traeger t) {}

    public void editTraeger(Traeger t) {}

    public void deleteTraeger(Traeger t) {}

    public void createAuftrag(ProzessKettenVorlage pkv) {}

    public Auftrag deleteAuftrag(Auftrag auftrag) { return null; }

    public void editAuftrag(Auftrag a) {}

    public void setBedingung(ProzessSchritt ps, Bedingung b) {}

    public void editBedingung(ProzessSchritt ps, Bedingung b) {}

    public void removeBedingung(ProzessSchritt ps, Bedingung b) {}

    public void setExperimentierStationZuPS(ExperimentierStation es, ProzessSchritt ps) {}

    public void editExperimentierStationZuPS(ExperimentierStation es, ProzessSchritt ps) {}

    public void setPrio(Auftrag a) {}

    public void editPrio(Auftrag a) {}

    public void deleteProzessSchritt(ProzessSchritt ps) {}

    public void createZustandsAutomat(ProzessSchrittZustandsAutomatVorlage pszav) {}

    public void removeZustandsAutomat(ProzessSchrittZustandsAutomatVorlage pszav) {}

    public void editZustandsAutomat(ProzessSchrittZustandsAutomatVorlage pszav) {}

    public void setFreigegeben(Auftrag a) {}

    public void stopAuftrag(Auftrag a) {}

    public void viewESUsage() {}

    public void viewESUsage(ExperimentierStation es) {}

    public void exportJSON() {}
}
