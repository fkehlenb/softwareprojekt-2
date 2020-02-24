package de.unibremen.sfb.service;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ProzessSchrittService implements Serializable {

    /**
     * Experimenting station service
     */
    @Inject
    private ExperimentierStationService experimentierStationService;

    /**
     * Process step dao
     */
    @Inject
    private ProzessSchrittDAO prozessSchrittDAO;

    /**
     * Get all PS which belong to user
     *
     * @param u the current user
     * @return the ps
     */
    public List<ProzessSchritt> getSchritteByUser(User u) {
        var ps = new ArrayList<ProzessSchritt>();
        for (ExperimentierStation e :
                experimentierStationService.getESByUser(u)) {
            ps.add(e.getCurrentPS());
        }
        return ps;
    }


    /** Get all process steps from the database
     * @return a list of all process steps */
    public List<ProzessSchritt> getAll() {
        return prozessSchrittDAO.getAll();
    }

    /** JSON export */
    public String toJson() {
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);

        // Create Jsonb with custom configuration
        Jsonb jsonb = JsonbBuilder.create(config);
        String result = jsonb.toJson(getAll());
        log.info("Export von den Auftraegen\n" + result);
        return result;
    }

}
