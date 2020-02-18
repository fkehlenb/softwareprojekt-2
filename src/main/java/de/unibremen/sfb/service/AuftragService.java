package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragException;
import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessKettenZustandsAutomat;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Slf4j
/**
 * Service fuer ProzessSchrittVorlagen
 * Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class AuftragService implements Serializable {
    private List<Auftrag> auftrage;
    
    @Inject
    AuftragDAO auftragDAO;

    @Inject
    AuftragService auftragService;
    
    @PostConstruct
    public void init() {
        auftrage = getAuftrage();
        // Create custom configuration with formatted output
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);;
        log.info(jsonb.toJson(auftrage));
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
        a.setAssigned(t);
        auftragDAO.update(a);
    }

}

