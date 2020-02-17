package de.unibremen.sfb.controller;

import de.unibremen.sfb.model.*;

import java.util.List;

/**
 * this class manages the interaction with models of assignments (Auftraege)
 */
public class AuftragController {

    /** The current job */
    public Auftrag auftrag;

    /**
     * returns the ID of this Auftrag
     *
     * @return the ID
     */
    public int getID() {
        return auftrag.getPkID();
    }

    /**
     * return the ProzessKettenVorlage which was used to instantiate this Auftrag
     *
     * @return the ProzessKettenVorlage
     */
    public ProzessKettenVorlage getPKV() {
        return auftrag.getVorlage();
    }

    /**
     * return the protocol of this Auftrag that was created thus far
     *
     * @return the protocol
     */
    public AuftragsLog getLog() {
        return auftrag.getLog();
    }


    /**
     * sets the protocol of this Auftrag
     *
     * @param al the new protocol
     */
    public void setLog(AuftragsLog al) {
        auftrag.setLog(al);
    }

    /**
     * returns the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgefuehrt (carried out)
     * @return the current Zustand
     */
    public Enum<ProzessKettenZustandsAutomat> getPKZ() {
        return auftrag.getProzessKettenZustandsAutomat();
    }

    /**
     * sets the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgefuehrt (carried out)
     */
    public void setPKZ(Enum<ProzessKettenZustandsAutomat> pkz) {
        auftrag.setProzessKettenZustandsAutomat(pkz);
    }

    /**
     * returns the current Prioritaet (priority) of this Auftrag
     * @return the current Prioritaet
     */
    public Enum<AuftragsPrioritaet> getPrio() {
        return auftrag.getPriority();
    }

    /**
     * sets the current Prioritaet (priority) of this Auftrag
     */
    public void setPrio(Enum<AuftragsPrioritaet> prio) {
        auftrag.setPriority(prio);
    }

    /**
     * returns the ProzessSchritte which the Auftrag consists of
     * @return a Set containing all ProzessSchritt
     */
    public List<ProzessSchritt> getPS() {
        return auftrag.getProzessSchritte();
    }
}
