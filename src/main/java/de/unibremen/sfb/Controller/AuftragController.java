package de.unibremen.sfb.Controller;

import de.unibremen.sfb.Model.*;

import java.util.Set;

/**
 * this class manages the interaction with models of assignments (Auftraege)
 */
public class AuftragController {

    public Auftrag auftrag;

    /**
     * returns the ID of this Auftrag
     *
     * @return the ID
     */
    public int getID() { return 0; }

    /**
     * return the ProzessKettenVorlage which was used to instantiate this Auftrag
     *
     * @return the ProzessKettenVorlage
     */
    public ProzessKettenVorlage getPKV() { return null; }

    /**
     * return the protocol of this Auftrag that was created thus far
     *
     * @return the protocol
     */
    public AuftragsLog getLog() { return null; }

    /**
     * sets the protocol of this Auftrag
     *
     * @param al the new protocol
     */
    public void setLog(AuftragsLog al) { }

    /**
     * returns the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgeführt (carried out)
     * @return the current Zustand
     */
    public ProzessKettenZustandsAutomat getPKZ() { return null; }

    /**
     * sets the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgeführt (carried out)
     */
    public void setPKZ() {}

    /**
     * returns the current Prioritaet (priority) of this Auftrag
     * @return the current Prioritaet
     */
    public AuftragsPrioritaet getPrio() { return null; }

    /**
     * sets the current Priorität (priority) of this Auftrag
     */
    public void setPrio() {}

    /**
     * returns the ProzessSchritte which the Auftrag consists of
     * @return a Set containing all ProzessSchritt
     */
    public Set<ProzessSchritt> getPS() { return null; }
}
