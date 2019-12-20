package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Probe;
import de.unibremen.sfb.Model.Standort;
import de.unibremen.sfb.Model.Archiv;
import java.time.LocalDateTime;
import org.apache.commons.lang3.tuple.Pair;

public class ProbeController {
    public Probe probe;

    public void setID(String id) {}

    public String getID() { return null; }

    public void addComment(Pair<LocalDateTime,String> p) {}

    public Pair<LocalDateTime,String> getComment() { return null; }

    //hier enum übergeben
    public void setZustand() {}

    //hier enum returnt
    public void getZustand() {}

    public void setStandort(Standort s) {}

    public Standort getStandort() { return null; }

    public void setArchiv(Archiv a) {}

    public Archiv getArchiv() { return null; }
}
