package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.AuftragDAO;
import de.unibremen.sfb.persistence.KommentarDAO;
import de.unibremen.sfb.persistence.ProbeDAO;
import de.unibremen.sfb.persistence.QualitativeEigenschaftDAO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Slf4j
public class ProbenService implements Serializable {
    private List<Probe> proben;


    @Inject
    UserService userService;

    @Inject
    private ProbeDAO probeDAO;

    @Inject
    private KommentarDAO kommentarDAO;

    @Inject
    ProzessSchrittParameterService prozessSchrittParameterService;

    @Inject
    AuftragService auftragService;

    @Inject
    ExperimentierStationService experimentierStationService;

//    @Inject
//    BedingungService bedingungService;

    @Inject
    private ProzessSchrittService prozessSchrittService;

    // https://www.primefaces.org/showcase/ui/data/datatable/filter.xhtml

    public Probe erstelleProbe(Standort standort, String probenID, int anzahl) throws ProbeNotFoundException, DuplicateProbeException {
        int vorherigeAnzahl = 0;
        int verloreneAnzahl = 0;
        try {
            vorherigeAnzahl = probeDAO.getObjById(probenID).getAnzahl();
            verloreneAnzahl = probeDAO.getObjById(probenID).getLost();
        } catch (ProbeNotFoundException e) {
            log.info("vorherige Anzahl kann nicht gefunden werden, weil die Probe nicht existierte");
        }
        Probe p = new Probe(probenID, vorherigeAnzahl + anzahl, ProbenZustand.ARCHIVIERT, standort);
        p.setLost(verloreneAnzahl);
        update(p);
        persist(p);
        return p;
    }


    /**
     * Suche nach Proben die an diesem Stanndort liegen
     *
     * @param s Der Standort
     * @return alle Proben die diese Eigenschaft besitzen
     */
    public List<Probe> getProbenByStandort(Standort s) {
        List<Probe> akutelleProben = new ArrayList<>();
        akutelleProben = getAll();
        assert  !akutelleProben.isEmpty();
        return akutelleProben.stream()
                .filter(e -> e.getStandort().equals(s))
                .collect(Collectors.toList());
    }

    @Inject
    QualitativeEigenschaftDAO qualitativeEigenschaftDAO;



    /**
     * Suche nach Proben die dieser Bedingung entsprechen
     * @param b Die Bedingung
     * @return alle Proben die diese Bedingung entsprechen
     */
//    public List<Probe> getProbenByPredicate(Bedingung b) {
//        return proben.stream()
//                .filter(e -> e.getBedingungen().contains(b))
//                .collect(Collectors.toList());
//    }


    /**
     * Hole alle Proben die akutell in experimentierStationene sind,
     * welche dem Benuter zugewiesen sind
     * @param u der Benutzer
     * @return Alle akutell fuer den Benuzter relevanten Proben
     */
//    public List<Probe> getProbenByUser(User u) {
//        var proben = new ArrayList<Probe>();
//        var experimByUser = experimentierStationService.getESByUser(u);
//        for (ExperimentierStation e :
//        experimByUser) {
//            proben.addAll(prozessSchrittService.getProben(e.getCurrentPS()));
//        }
//        return proben;
//    }

    /**
     * adds a comment to a sample
     *
     * @param p the sample
     * @param c the new comment
     * @throws ProbeNotFoundException      the sample could not be found in the database
     * @throws DuplicateKommentarException if the comment already exists
     */
    public void addProbenComment(Probe p, String c)
            throws ProbeNotFoundException, IllegalArgumentException, DuplicateKommentarException {
        if (p == null || c == null) {
            throw new IllegalArgumentException();
        }
        Kommentar k = new Kommentar(LocalDateTime.now(), c);
        kommentarDAO.persist(k);
        if (p.getKommentar() != null) {
            p.getKommentar().add(k);
        } else {
            List<Kommentar> ks = new LinkedList<>();
            ks.add(k);
            p.setKommentar(ks);
        }
        probeDAO.update(p);
    }

    /**
     * edits a comment of a sample
     *
     * @param p the sample
     * @param c the new comment text
     * @param k the Class of the Comment
     * @throws ProbeNotFoundException     the sample could not be found in the database
     * @throws KommentarNotFoundException if the Comment could not be found
     */
    public void editProbenComment(Probe p, Kommentar k, String c)
            throws ProbeNotFoundException, IllegalArgumentException, KommentarNotFoundException {
        if (p == null || k == null || c == null) {
            throw new IllegalArgumentException();
        }
        if (p.getKommentar().contains(k)) {
            k.setText(c);
            kommentarDAO.update(k);
        }
        probeDAO.update(p);
    }

    /**
     * deletes a comment of a sample
     *
     * @param p the sample
     * @param k the comment to be deleted //TODO macht das sinn so?
     * @throws ProbeNotFoundException     the sample could not be found in the database
     * @throws KommentarNotFoundException if the Comment could not be found
     */
    public void deleteProbenComment(Probe p, Kommentar k)
            throws ProbeNotFoundException, IllegalArgumentException, KommentarNotFoundException {
        if (p == null || k == null) {
            throw new IllegalArgumentException();
        }
        p.getKommentar().remove(k);
        kommentarDAO.remove(k);
        probeDAO.update(p);
    }

    /**
     * find a sample in the database by its id
     *
     * @param id the id
     * @return the sample
     * @throws ProbeNotFoundException there is no sample with this id
     */
    public Probe getProbeById(String id) throws ProbeNotFoundException {
        return probeDAO.getObjById(id);
    }

    /**
     * turns the set of comments of a sample into a string
     * every comment in a new paragraph
     *
     * @param p the sample
     * @return the string with the text of all comments
     */
    public String KommentarToString(Probe p) {
        StringBuilder res = new StringBuilder();
        for (Kommentar k : p.getKommentar()) {
            res.append(k.getText()).append("\n");
        }
        return res.toString();
    }

    /**
     * sets the state of a sample
     *
     * @param p the sample
     * @param z the new state
     * @throws ProbeNotFoundException   sample not in database
     * @throws IllegalArgumentException sample and/or state null
     */
    public void setZustandForProbe(Probe p, ProbenZustand z) throws ProbeNotFoundException, IllegalArgumentException {
        if (p == null || z == null) {
            throw new IllegalArgumentException();
        } else {
            p.setZustand(z);
            probeDAO.update(p);
        }
    }

    /**
     * sets the state of a number of samples
     *
     * @param p      the sample
     * @param anzahl number of samples, which should be changed
     * @param z      the new state
     * @throws ProbeNotFoundException   sample not in database
     * @throws IllegalArgumentException sample and/or state null
     * @throws DuplicateProbeException  sample allready exists in the db
     */
    public void setZustandForProbe(Probe p, int anzahl, ProbenZustand z) throws ProbeNotFoundException, IllegalArgumentException, DuplicateProbeException {
        if (p == null || z == null) {
            throw new IllegalArgumentException();
        } else {
            p.setAnzahl(p.getAnzahl() - anzahl);
            probeDAO.update(p);


            p.setProbenID(p.getProbenID() + ".VERLOREN");
            p.setAnzahl(anzahl);
            p.setZustand(z);
            probeDAO.persist(p);
        }
    }

    public void probeVerloren(Probe p, int anzahl, int lostAnzahl, int vorherLost) throws ProbeNotFoundException {
        if (p == null) {
            throw new IllegalArgumentException();
        } else {
            p.setAnzahl(anzahl - lostAnzahl);
            p.setLost(vorherLost + lostAnzahl);
            probeDAO.update(p);

        }
    }

    /**
     * returns all samples to which the user has not yet uploaded data
     *
     * @return a set containing all those samples
     * @throws AuftragNotFoundException if no Auftrag exists.
     */
    public List<Probe> viewToBeUploaded() throws AuftragNotFoundException {
        List<Probe> res = new LinkedList<>();
        try {
            for (ProzessSchritt ps : prozessSchrittService.getSchritte()) {
                if (!ps.isUploaded()) {
                    var traeger = auftragService.getAuftrag(ps).getTraeger();
                    for (Traeger t :
                            traeger) {
                        res.addAll(t.getProben());
                    }
                }
            }
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Converts JSON to List of Eigenschaften
     * @param json the json input
     * @param ps to this step
     *             * @throws ProbeNotFoundException                if the Probe could not be found
     */
    public void addJSONEigenschaft(String json, ProzessSchritt ps) throws ProbeNotFoundException {
        var config = new JsonbConfig().withFormatting(true);
        var jsonb = JsonbBuilder.create(config);
        List<QualitativeEigenschaft> tClass = new ArrayList<>();
        Type eType = new ArrayList<QualitativeEigenschaft>() {}.getClass().getGenericSuperclass();
        tClass =  jsonb.fromJson(json, eType);
        List<Traeger> traegers = auftragService.getAuftrag(ps).getTraeger();
        for (QualitativeEigenschaft e :
                tClass) {
            try {
                qualitativeEigenschaftDAO.persist(e);
            } catch (DuplicateQualitativeEigenschaftException ex) {
                try {
                    qualitativeEigenschaftDAO.update(e);
                } catch (QualitativeEigenschaftNotFoundException exc) {
                    exc.printStackTrace();
                }
            }
        }
        for (Traeger t :
                traegers) {
            for (Probe p :
                    t.getProben()) {
                p.getEigenschaften().addAll(tClass);
                update(p);
            }
        }

    }



    /**
     * counts the samples in the database
     *
     * @return the amount of samples in the database
     */
    public int getProbenTotalCount() {
        return probeDAO.getProbenCount();
    }


    /**
     * Get all archived samples
     *
     * @return a list of all archived samples
     */
    public List<Probe> getAllArchived() {
        return probeDAO.getAllArchived();
    }

    /**
     * Add a new sample
     *
     * @param p - the sample to add
     * @throws DuplicateProbeException on failure
     */
    public void persist(Probe p) throws DuplicateProbeException {
        probeDAO.persist(p);
    }

    /**
     * Update a sample
     *
     * @param p - the sample to update
     * @throws ProbeNotFoundException on failure
     */
    public void update(Probe p) throws ProbeNotFoundException {
        probeDAO.update(p);
    }

    /**
     * Remove a sample
     *
     * @param p - the sample to remove
     * @throws ProbeNotFoundException on failure
     */
    public void remove(Probe p) throws ProbeNotFoundException {
        probeDAO.remove(p);
    }

    /**
     * Get all samples from the database
     *
     * @return a list of all samples from the database
     */
    public List<Probe> getAll() {
        return probeDAO.getAll();
    }
}
