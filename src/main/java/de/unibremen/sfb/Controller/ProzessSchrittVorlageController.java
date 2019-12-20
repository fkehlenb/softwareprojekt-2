package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ProzessSchrittVorlage;
import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.Traegerart;
import de.unibremen.sfb.Model.ProzessSchrittZustandsAutomat;
import de.unibremen.sfb.Model.ProzessSchrittArt;
import java.time.Duration;
import java.util.Set;

public class ProzessSchrittVorlageController {

    public ProzessSchrittVorlage psv;

    public Set<ExperimentierStation> getES() { return null; }

    public void setEs(ExperimentierStation es) {}

    public void setID(int i) {}

    public int getID() { return 0; }

    public Set<ProzessSchrittZustandsAutomat> getZustaende() { return null; }

    public void setZustaende(Set<ProzessSchrittZustandsAutomat> psza) {}

    public Set<Traegerart> getAusgabeTraeger() { return null; }

    public void SetAusgabeTraeger(Set<Traegerart> at) {}

    public Set<Traegerart> getEingabeTraeger() { return null; }

    public void setEingabeTrager(Set<Traegerart> et) {}

    public void setDauer(Duration d) {}

    public Duration getDauer() { return null; }

    public void setProzessSchrittZustandsAutomat(ProzessSchrittZustandsAutomat psza) { }

    public ProzessSchrittZustandsAutomat getProzessSchrittZustandsAutomat() { return null; }

    public void setProzessSchrittArt(ProzessSchrittArt psa) {}

    public ProzessSchrittArt getProzessSchrittArt() { return null; }
}
