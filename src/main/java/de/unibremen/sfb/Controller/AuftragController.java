package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.ProzessKettenVorlage;
import de.unibremen.sfb.Model.AuftragsLog;
import de.unibremen.sfb.Model.ProzessSchritt;
import java.util.Set;

public class AuftragController {

    public Auftrag auftrag;

    public int getID() { return 0; }

    public ProzessKettenVorlage getPKV() { return null; }

    public AuftragsLog getLog() { return null; }

    public void setLog(AuftragsLog al) { }

    //returnt eigentlich enum (Zustände)!
    public void getPKZ() { }

    public void setPKZ() {}

    //returnt eigentlich enum
    public void getPrio() {}

    public void setPrio() {}

    public Set<ProzessSchritt> getPS() { return null; }
}
