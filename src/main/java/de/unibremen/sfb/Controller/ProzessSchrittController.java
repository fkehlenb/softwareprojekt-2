package de.unibremen.sfb.Controller;


import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.ExperimentierStation;
import de.unibremen.sfb.Model.ProzessSchritt;
import de.unibremen.sfb.Model.ProzessKettenVorlage;
import de.unibremen.sfb.Model.ProzessSchrittLog;
import de.unibremen.sfb.Model.TransportAuftrag;

import java.util.Set;

public class ProzessSchrittController {

    public ProzessSchritt ps;

    public void setAuftrag(Auftrag a){}

    public Auftrag getAuftrag(){
        return null;
    }

    public boolean uploaded() { return true; }

    public void setUploaded() {}

    public String getZustand() { return ""; }

    public void setZustand() {}

    public Set<ExperimentierStation> getES(){
        return null;
    }

    public ProzessKettenVorlage getPSV() { return null; }

    public void setPSLog(ProzessSchrittLog psl) {}

    public ProzessSchrittLog getPSLog() { return null; }

    public void setTransportAuftrag(TransportAuftrag ta) {}

    public TransportAuftrag getTransportAuftrag() { return null; }
}
