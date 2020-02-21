package de.unibremen.sfb.service;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProzessSchrittDAO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProzessSchrittService {

    @Inject
    ExperimentierStationService experimentierStationService;

    /**
     * Get all PS which belong to user
      * @param u the current user
     * @return the ps
     */
public List<ProzessSchritt> getProbenByUser(User u) {
    var ps = new ArrayList<ProzessSchritt>();
    for (ExperimentierStation e :
            experimentierStationService.getESByUser(u)) {
        ps.add(e.getCurrentPS());
    }
    return ps;
}
}
