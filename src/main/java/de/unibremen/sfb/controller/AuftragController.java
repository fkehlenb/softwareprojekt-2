package de.unibremen.sfb.controller;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;

import java.util.List;

/**
 * this class manages the interaction with models of assignments (Auftraege)
 */
public class AuftragController {

    /** The current job */
    public Auftrag auftrag;

    @Inject
    private AuftragDAO auftragDAO;

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
     *                  Abgebrochen (canceled), Durchgeführt (carried out)
     * @return the current Zustand
     */
    public Enum<ProzessKettenZustandsAutomat> getPKZ() {
        return auftrag.getProzessKettenZustandsAutomat();
    }

    /**
     * sets the current Zustand (state) of this Auftrag
     * possible values: Instanziiert (instantiated), Freigegeben (enabled), Gestartet (started),
     *                  Abgebrochen (canceled), Durchgeführt (carried out)
     */
    public void setPKZ(Enum<ProzessKettenZustandsAutomat> pkz) {
        auftrag.setProzessKettenZustandsAutomat(pkz);
    }

    /**
     * sets the current state for a job
     * @param a the job for which the state is to be updated
     * @param z the new state
     */
    public void setPKZforAuftrag(Auftrag a, ProzessKettenZustandsAutomat z) {
        ProzessKettenZustandsAutomat temp = a.getProzessKettenZustandsAutomat();
        a.setProzessKettenZustandsAutomat(z);
        try {
            auftragDAO.update(a);
        }
        catch(AuftragNotFoundException e) {
            a.setProzessKettenZustandsAutomat(temp);
            e.printStackTrace();
        }
    }

    /**
     * returns the current Prioritaet (priority) of this Auftrag
     * @return the current Prioritaet
     */
    public Enum<AuftragsPrioritaet> getPrio() {
        return auftrag.getPriority();
    }

    /**
     * sets the current Priorität (priority) of this Auftrag
     */
    public void setPrio(Enum<AuftragsPrioritaet> prio) {
        auftrag.setPriority(prio);
    }

    }

    /**
     * returns the ProzessSchritte which the Auftrag consists of
     * @return a Set containing all ProzessSchritt
     */
    public List<ProzessSchritt> getPS() {
        return auftrag.getProzessSchritte();
    }
}
