package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Probe;
import de.unibremen.sfb.model.ProbenZustand;
import de.unibremen.sfb.model.Standort;
import de.unibremen.sfb.persistence.ProbeDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ProbenService {
    private List<Probe> proben;

    @Inject
    ProbeDAO probeDAO;

    @PostConstruct
    void init() {
        // FIME LOADING

        proben = new ArrayList<>();
        proben.add(new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, new Standort(UUID.randomUUID().hashCode(), "Archiv")));
        proben.add(new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, new Standort(UUID.randomUUID().hashCode(), "Archiv")));
        proben.add(new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, new Standort(UUID.randomUUID().hashCode(), "Archiv")));

    }
}
