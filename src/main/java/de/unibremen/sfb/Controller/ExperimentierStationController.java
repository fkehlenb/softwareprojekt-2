package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.ProzessSchritt;

public class ExperimentierStationController {
    public ExperimentierStation experimenteristation;

    public int getESID() { return 0; }

    public String getESStandort() { return null; }

    //eigentlich enum übergeben
    public void setStatus() {}

    //eigentlich enum returnt
    public void getStatus() {}

    public void addNextPS(ProzessSchritt ps) {}

    public ProzessSchritt peekNextPS() { return null; }

    public void popNextPS(ProzessSchritt ps) {}
}
