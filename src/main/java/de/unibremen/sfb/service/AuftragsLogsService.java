package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.AuftragsLogNotFoundException;
import de.unibremen.sfb.exception.DuplicateAuftragsLogException;
import de.unibremen.sfb.model.AuftragsLog;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.persistence.AuftragsLogDAO;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
public class AuftragsLogsService {
    private final List<AuftragsLog> auftragsLogs = new ArrayList<>();
    
    @Inject
    AuftragsLogDAO auftragsLogDAO;
    
    /**
     * Update an existing job in the database
     *
     * @param auftragsLog - the job to update
     * @throws AuftragsLogNotFoundException on failure
     */
    public void update(AuftragsLog auftragsLog) throws AuftragsLogNotFoundException {
        auftragsLogDAO.update(auftragsLog);
    }


    /**
     * Add a new Auftrag Log to the database
     *
     * @param auftragsLog - the job to add
     * @throws DuplicateAuftragsLogException on failure
     */
    public void add(AuftragsLog auftragsLog) throws DuplicateAuftragsLogException {
        auftragsLogDAO.persist(auftragsLog);
    }

    /**
     * Bearbeiten der Auftrags Log
     *
     * @param auftragsLog der Log der Bearbeitet wird
     */
    public void edit(AuftragsLog auftragsLog) {
        var old = auftragsLogs.stream().filter(p -> auftragsLog.getId() == p.getId()).findFirst().orElse(null);

        if (Collections.replaceAll(auftragsLogs, old, auftragsLog)) {
            log.info("Succesful edit " + auftragsLog);
        } else {
            log.info("Failed to edit " + auftragsLog);
        }
    }

    /**
     * Loeschen von ProzessKettenVorlagen
     * @param auftragsLogs die Vorlagen
     */
    public void delete(List<ProzessKettenVorlage> auftragsLogs) {
        for (ProzessKettenVorlage auftragsLog :
                auftragsLogs) {
            auftragsLogs.remove(auftragsLog);
        }
    }
}
