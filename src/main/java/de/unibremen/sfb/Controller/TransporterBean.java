package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.Auftrag;
import de.unibremen.sfb.Model.Probe;
import de.unibremen.sfb.Model.User;

import java.util.Set;

public class TransporterBean {

    public User transporter;

    public Set<Auftrag> getAuftragList() { return null; }

    public void reportLostProbe(Probe p) {}

    public void reportLostProbe(int id) {}
}
