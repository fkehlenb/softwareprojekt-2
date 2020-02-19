package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Slf4j
@Getter
@Data
/**
 * Service fuer AuftragService
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class AuftragService implements Serializable {
    private List<Auftrag> auftrage;

    @Inject
    AuftragDAO auftragDAO;


    private Auftrag auftrag;
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


    /**
     * Setze den Zustand von Auftrag a auf p und persistiere
     * @param a Der Auftrag
     * @param p Der Zustand
     */
    public void zustandswechsel(Auftrag a, ProzessKettenZustandsAutomat p) {
        a.setProzessKettenZustandsAutomat(p);
        upate(a);
    }

    @PostConstruct
    public void init() {
        auftrage = getAuftrage();
    }

    public List<Auftrag> getAuftrage() {
        return auftragDAO.getAll();
    }

    public void upate(Auftrag auftrag) {
        try {
            auftragDAO.update(auftrag);
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void add(Auftrag auftrag) {
        auftrage.add(auftrag);
        try {
            auftragDAO.persist(auftrag);
        } catch (DuplicateAuftragException e) {
            e.printStackTrace();
        }
    }

    public String toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(getAuftrage());
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

    public Auftrag getAuftrag(String value) {

        try {
            return auftragDAO.getObjById(Integer.parseInt(value));
        } catch (AuftragNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * sets the status of a job
     * @param a the job
     * @param zustand the new status
     * @throws AuftragNotFoundException the job couldn't be found in the database
     */
    public void setAuftragsZustand(Auftrag a, Enum<ProzessKettenZustandsAutomat> zustand) throws AuftragNotFoundException {
        a.setProzessKettenZustandsAutomat(zustand); //TODO wenn update in db fehlschlägt: Zustand zurücksetzen?
        auftragDAO.update(a);
    }

    /**
     * assigns a user to a job
     * @param t the user to be assigned
     * @param a the job to which they will be assigned
     * @throws AuftragNotFoundException the job couldn't be found in the database
     */
    public void assignToAuftrag(User t, Auftrag a) throws AuftragNotFoundException {
        //a.setAssigned(t);
        auftragDAO.update(a);
    }

}

