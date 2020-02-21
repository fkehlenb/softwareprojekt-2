package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProbeException;
import de.unibremen.sfb.exception.ProbeNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ProbeDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class ProbenService implements Serializable {
    private List<Probe> proben;

    @Inject
    ProbeDAO probeDAO;

    @Inject
    QualitativeEigenschaftService qualitativeEigenschaftService;

    @Inject
    BedingungService bedingungService;

    @PostConstruct
    void init() {
        // FIXME LOADING
        var s = new Standort(UUID.randomUUID().hashCode(), "Archiv");
        var s2 = new Standort(UUID.randomUUID().hashCode(), "Lager");
        var qEs = qualitativeEigenschaftService.getEigenschaften();
        var bs = bedingungService.getBs();
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
            proben.addAll(e.getCurrentPS().getZugewieseneProben());
        }
        return proben;
    }

    /**
     * adds a comment to a sample
     * @param p the sample
     * @param c the new comment
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void addProbenComment(Probe p, String c) throws ProbeNotFoundException {
        Kommentar k = new Kommentar(LocalDateTime.now(), c);
        if(p.getKommentar() != null) {
            p.getKommentar().add(k);
        }
        else {
            List<Kommentar> ks = new LinkedList<>();
            ks.add(k);
            p.setKommentar(ks);
        }
        probeDAO.update(p);
    }

    /**
     * edits a comment of a sample
     * @param p the sample
     * @param c the new comment text
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void editProbenComment(Probe p, Kommentar k, String c) throws ProbeNotFoundException {
        if(p.getKommentar()!=null && p.getKommentar().contains(k)) {
            k.setText(c);
        }
        probeDAO.update(p);
    }

    /**
     * deletes a comment of a sample
     * @param p the sample
     * @param k the comment to be deleted //TODO macht das sinn so?
     * @throws ProbeNotFoundException the sample could not be found in the database
     */
    public void deleteProbenComment(Probe p, Kommentar k) throws ProbeNotFoundException {
        //TODO macht das alles überhaupt sinn so? idk... vor allem probedao update. nicht kommentardao update? (gibt es nicht, lel)
        p.getKommentar().remove(k);
        probeDAO.update(p);
    }

    /**
     * find a sample in the database by its id
     * @param id the id
     * @return the sample
     * @throws ProbeNotFoundException there is no sample with this id
     */
    public Probe getProbeById(String id) throws ProbeNotFoundException {
        return probeDAO.getObjById(id); //TODO in DAO id als string
    }

    /**
     * turns the set of comments of a sample into a string
     * every comment in a new paragraph
     * @param p the sample
     * @return the string with the text of all comments
     */
    public String KommentarToString(Probe p) {
        String res = "";
        for(Kommentar k : p.getKommentar()) {
            res += k.getText() + "\n";
        }
        return res;
    }

    /**
     * adds a new sample to the database
     * @param id the id of the new sample
     * @param k a comment (optional)
     * @param pz the current status
     * @param s the location
     * @param qe a list of  (optional)
     * @param t the carrier the sample is currently in (optional)
     * @throws DuplicateProbeException there is already a sample with this id
     */
    public void addNewSample(String id, Kommentar k, ProbenZustand pz, Standort s, List<QualitativeEigenschaft> qe, Traeger t) throws DuplicateProbeException {
        if(!id.matches("[A-Z][0-9][0-9].[0-9]+(.[0-9]+)+")) {
            throw new IllegalArgumentException();
        }
        Probe p = new Probe(id, pz, s);
        List<Kommentar> ks = new LinkedList<>();
        ks.add(k);
        p.setKommentar(ks);
        p.setQualitativeEigenschaften(qe);
        p.setCurrentTraeger(t);
        probeDAO.persist(p);
    }

}
