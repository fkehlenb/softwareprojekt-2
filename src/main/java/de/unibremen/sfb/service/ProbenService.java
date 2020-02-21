package de.unibremen.sfb.service;

import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class ProbenService {
    private List<Probe> proben;

    @Inject
    ProbeDAO probeDAO;

    @Inject
    QualitativeEigenschaftService qualitativeEigenschaftService;

    @Inject
    BedingungService bedingungService;

    @PostConstruct
    void init() {
        // FIME LOADING
        var s = new Standort(UUID.randomUUID().hashCode(), "Archiv");
        var s2 = new Standort(UUID.randomUUID().hashCode(), "Lager");
        var qEs = qualitativeEigenschaftService.getEigenschaften();
        var bs = bedingungService.getEigenschaften();
        var p1 = new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, s);
        p1.setQualitativeEigenschaften(qEs);
        var p2 = new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, s);
        p2.setBedingungen(bs);

        proben = new ArrayList<>();
        proben.add(p1);
        proben.add(p2);
        proben.add(new Probe(UUID.randomUUID().toString(), ProbenZustand.VORHANDEN, s2));

    }

    // https://www.primefaces.org/showcase/ui/data/datatable/filter.xhtml

    /**
     * Suche nach Proben die diese Eigenschaft erfuellen
     * @param q Eigenschaft
     * @return alle Proben die diese Eigenschaft besitzen
     */
    public List<Probe> getProbenByEigenschaft(QualitativeEigenschaft q) {
        return proben.stream()
                .filter(e -> e.getQualitativeEigenschaften().contains(q))
                .collect(Collectors.toList());
    }

    /**
     * Suche nach Proben die an diesem Stanndort liegen
     * @param s Der Standort
     * @return alle Proben die diese Eigenschaft besitzen
     */
    public List<Probe> getProbenByStandort(Standort s) {
        return proben.stream()
                .filter(e -> e.getStandort().equals(s))
                .collect(Collectors.toList());
    }

    /**
     * Suche nach Proben die dieser Bedingung entsprechen
     * @param b Die Bedingung
     * @return alle Proben die diese Bedingung entsprechen
     */
    public List<Probe> getProbenByPredicate(Bedingung b) {
        return proben.stream()
                .filter(e -> e.getBedingungen().contains(b))
                .collect(Collectors.toList());
    }

    @Inject
    ExperimentierStationService experimentierStationService;

    /**
     * Hole alle Proben die akutell in experimentierStationene sind,
     * welche dem Benuter zugewiesen sind
     * @param u der Benutzer
     * @return Alle akutell fuer den Benuzter relevanten Proben
     */
    public List<Probe> getProbenByUser(User u) {
        var proben = new ArrayList<Probe>();
        for (ExperimentierStation e :
        experimentierStationService.getESByUser(u)) {
            for (Probe p: e.getCurrentPS().getZugewieseneProben()) {
                proben.add(p);
            }
        }
        return proben;
    }


}
