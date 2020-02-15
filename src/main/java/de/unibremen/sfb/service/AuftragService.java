package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragNotFoundException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessKettenZustandsAutomat;
import de.unibremen.sfb.model.User;
import de.unibremen.sfb.persistence.AuftragDAO;

import javax.inject.Inject;
import java.io.Serializable;

public class AuftragService implements Serializable {

    /**
     * auftragDAO for the management of jobs in the database
     */
    @Inject
    private AuftragDAO auftragDAO;

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
