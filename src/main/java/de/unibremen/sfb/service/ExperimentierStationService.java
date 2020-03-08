package de.unibremen.sfb.service;


import de.unibremen.sfb.exception.*;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.*;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Getter
@Transactional
@Slf4j
public class ExperimentierStationService implements Serializable {

    /**
     * List containing all experimenting stations
     */
    private List<ExperimentierStation> esSet;

    /**
     * ES DAO for database management
     */
    @Inject
    private ExperimentierStationDAO esDao;

    /**
     * init called on startup
     */
    @PostConstruct
    public void init() {
        this.esSet = esDao.getAll();
    }

    /**
     * Get all experimenting stations from the database
     *
     * @return all Stations
     */
    public List<ExperimentierStation> getESListe() {
        return esDao.getAll();
    }


//    public List<Probe> getProben(ExperimentierStation es) {
//        List<Probe> r = new ArrayList<>();
//        if(es.getCurrentPS()!=null && es.getCurrentPS().getZugewieseneProben()!=null) {
//            r.addAll(es.getCurrentPS().getZugewieseneProben());
//        }
//        if(es.getNextPS()!=null) {
//            for(ProzessSchritt prozessSchrittList : es.getNextPS()) {
//                if(prozessSchrittList.getZugewieseneProben()!=null) {
//                    r.addAll(prozessSchrittList.getZugewieseneProben());
//                }
//            }
//        }
//        r.removeAll(Collections.singleton(null));
//        return r;
//    }

    /**
     * Add a new experimenting station
     *
     * @param experimentierStation - the experimenting station to add
     * @throws DuplicateExperimentierStationException on failure
     */
    public void addES(ExperimentierStation experimentierStation) throws DuplicateExperimentierStationException {
        esDao.persist(experimentierStation);
    }

    /**
     * Remove an experimenting station
     *
     * @param experimentierStation - the experimenting station to delete
     * @throws ExperimentierStationNotFoundException if experimentierStation could not be found
     */
    public void loescheES(ExperimentierStation experimentierStation) throws ExperimentierStationNotFoundException {
        esDao.remove(experimentierStation);
//        esSet = getESListe();
    }

    /**
     * Find an experimenting station using its name
     *
     * @param name - the experimenting station's name
     * @return the Found Station
     */
    public ExperimentierStation findByName(String name) {
        // FIXME Use String as ID or convert to String
        esSet = esDao.getAll();
        return this.esSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    /**
     * Get an experimenting station using its name
     *
     * @param name - the experimenting station's name
     * @return the Station which has the name
     * @throws ExperimentierStationNotFoundException on failure
     */
    public ExperimentierStation getStationByName(String name) throws ExperimentierStationNotFoundException {
        return esDao.getByName(name);
    }

    /**
     * sets the status for an experimenting station
     *
     * @param e   the station
     * @param esz the new status
     * @throws ExperimentierStationNotFoundException the station couldn't be found in the database
     */
    public void setZustand(ExperimentierStation e, ExperimentierStationZustand esz) throws ExperimentierStationNotFoundException {
        if (e == null || esz == null) {
            throw new IllegalArgumentException();
        }
        /*
        von der anderen setZustand-Methode:
         */
        /*var eN = esDao.getObjById(es.getEsID());
        if (z.equals(ExperimentierStationZustand.VERFUEGBAR)) {
            // FIXME Pop form Queue to Current
        }
        eN.setStatus(z);
        esDao.update(eN);*/
        e.setStatus(esz);
        esDao.update(e);
    }

    /**
     * Get an experimenting station using its id
     *
     * @param id - the experimenting station's id
     * @return the Station which has the id
     * @throws ExperimentierStationNotFoundException on failure
     */
    public ExperimentierStation getById(int id) throws ExperimentierStationNotFoundException {
        return esDao.getObjById(id);
    }

    /**
     * @return a list of all experimenting stations in the system
     */
    public List<ExperimentierStation> getAll() {
        return esDao.getAll();
    }

    /**
     * Update an existing experimenting station in the database
     *
     * @param es - the experimenting station to update
     * @throws ExperimentierStationNotFoundException on failure
     */
    public void updateES(ExperimentierStation es) throws ExperimentierStationNotFoundException {
        esDao.update(es);
    }

    /**
     * Get all the Stations a User is assigned to
     *
     * @param user the user for whose stations are wanted
     * @return a list containing all stations for this user
     */
    public List<ExperimentierStation> getESByUser(User user) {
        return esDao.getAll().stream()
                .filter(c -> c.getBenutzer().contains(user))
                .collect(Collectors.toList());
    }

    /** Get experimenting station of the process step
     * @param ps - the process step
     * @return the location its being carried out at */
    public ExperimentierStation findStation(ProzessSchritt ps)
            throws IllegalArgumentException {
        if (ps == null) {
            throw new IllegalArgumentException();
        }
        for (ExperimentierStation e : getAll()) { //TODO jeder schritt nur an einer station?
            List<Integer> psids = new ArrayList<>();
            for (ProzessSchritt p : e.getNextPS()) {
                psids.add(p.getId());
            }
            if (psids.contains(ps.getId()) || (e.getCurrentPS() != null && e.getCurrentPS().getId() == ps.getId())) {
                return e;
            }
        }
        return null;
    }

    /**
     * Füge ein PS an eine Experimentier Station hinzu
     * In Current nichts zugwiesen, ansonsten in die Warteschlange
     *
     * @param ps                   der akutelle Schritt
     * @param experimentierStation die Station
     * @throws ExperimentierStationNotFoundException falls es diese nicht gibt
     */
    public void setES(ProzessSchritt ps, ExperimentierStation experimentierStation) throws ExperimentierStationNotFoundException {
        if (experimentierStation.getCurrentPS() == null) {
            experimentierStation.setCurrentPS(ps);
        } else {
            experimentierStation.getNextPS().add(ps);
        }
        esDao.update(experimentierStation);
    }

    /**
     * Hole die Experimentier Station an die ein Ein PS zugewiesen wurde
     *
     * @param ps der ProzessSchritt
     * @return die ExperimentierStation and  der PS durchgeührt wird
     */
    public ExperimentierStation getESfromPS(ProzessSchritt ps) {
        var allES = getAll();
        ExperimentierStation r = allES.stream().filter(e -> e.getCurrentPS() != null && e.getCurrentPS().getId() == ps.getId()).findFirst().orElse(null);
        if (r == null) {
            r = allES.stream().filter(e -> e.getNextPS().contains(ps)).findFirst().orElse(null);
        }
        if (r == null) {
            return allES.get(0); // FIXME
        } else return r;

    }

    /**
     * Get all current PS which belong to user
     *
     * @param u the current user
     * @return the prozessSchrittList
     */
    public List<ProzessSchritt> getSchritteByUser(User u) {
        var ps = new ArrayList<ProzessSchritt>();
        var gu = getESByUser(u);
        for (ExperimentierStation e : gu) {
            if (e.getCurrentPS()!=null&&(!e.getCurrentPS().isAssigned()||!e.getCurrentPS().isValidData())){
                List<ProzessSchritt> newPSS = new ArrayList<>();
                for (int i=0;i<e.getNextPS().size();i++){
                    if (e.getNextPS().get(i).isValidData()&&e.getNextPS().get(i).isAssigned()){
                        newPSS.add(e.getNextPS().get(i));
                    }
                }
                if (!newPSS.isEmpty()){
                    e.setCurrentPS(newPSS.get(0));
                    newPSS.remove(newPSS.get(0));
                    e.setNextPS(newPSS);
                }
                else{
                    e.setCurrentPS(null);
                    e.setNextPS(newPSS);
                }
                try {
                    updateES(e);
                }
                catch (Exception f){
                    f.printStackTrace();
                }
            }
        }
        for (ExperimentierStation e : gu){
            ps.add(e.getCurrentPS());
        }
        return ps;
    }

    /**
     * next ps for user
     * @param u user
     * @return list
     */
    public List<ProzessSchritt> getJobsByUser(User u) {
        List<ProzessSchritt> ps = new ArrayList<>();
        List<ExperimentierStation> es = getESByUser(u);
        for(ExperimentierStation e : es) {
            for (ProzessSchritt p : e.getNextPS()){
                if (p.isValidData()){
                    ps.add(p);
                }
            }
        }
        ps.removeAll(Collections.singleton(null));
        return ps;
    }

    /**
     * returns all stationen fullfilling a Bedingung
     *
     * @param b the Bedingung
     * @return a list containing all fitting stations
     */
    public List<ExperimentierStation> getAllESByBedingung(ProzessSchrittParameter b) {
        return esDao.getAll().stream()
                .filter(e -> e.getRequirements().contains(b))
                .collect(Collectors.toList());
    }

//    public List<ExperimentierStation> getAllESByParameter(ProzessSchrittParameter p) {
//        return esDao.getAll().stream()
//                .filter(e -> e.getBedingungen().contains(p))
//                .collect(Collectors.toList());
//    }

    /**
     * sets the current process step of a station
     *
     * @param ps the process step
     * @param es the station
     * @throws IllegalArgumentException              the station has a current step, or does not have prozessSchrittList in its list of next steps
     * @throws ExperimentierStationNotFoundException the station was not found in the database
     */
    public void setCurrentPS(ProzessSchritt ps, ExperimentierStation es)
            throws IllegalArgumentException, ExperimentierStationNotFoundException {
        if (es.getCurrentPS() != null || !es.getNextPS().contains(ps)) {
            throw new IllegalArgumentException();
        } else {
            es.setCurrentPS(ps);
            es.getNextPS().remove(ps);
            esDao.update(es);
        }
    }

    /**
     * deletes the current process step of a station
     *
     * @param ps the process step that is currently the current one
     * @param es the station
     * @throws IllegalArgumentException              prozessSchrittList or es null, or prozessSchrittList not the current one at es
     * @throws ExperimentierStationNotFoundException the es was not found in the database
     */
    public void deleteCurrent(ProzessSchritt ps, ExperimentierStation es)
            throws IllegalArgumentException, ExperimentierStationNotFoundException {
        if (ps == null || es == null) {
            throw new IllegalArgumentException();
        } else {
            if (es.getCurrentPS().getId() == ps.getId()) {
                es.setCurrentPS(null);
                esDao.update(es);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }


    @Inject
    private TransportAuftragDAO transportAuftragDAO;

    @Inject
    StandortService standortService;

    @Inject
    AuftragDAO auftragDAO;

   @Inject
   ProbeDAO probeDAO;

   @Inject
   QualitativeEigenschaftDAO qualitativeEigenschaftDAO;

    public Auftrag getAuftragOfPS(ProzessSchritt ps) {
        List<Auftrag> as = auftragDAO.getAll();
        for (Auftrag a :
                as) {
            var r = a.getProzessSchritte().stream().filter(p -> p.getId() == ps.getId()).findFirst().orElse(null);
            if (r != null) {
                return a;
            }
        }
        return null;
    }

    /**
     * Fuegt alle Atribute einer PS auf alle Proben die dem Auftrag zugwiesen sind
     * Die Atribute werden durch kommas getrennt
     * Die Probe wird akutallisiert und dann persistiert
     * @param ps der ProzessSchritt
     * @throws DuplicateQualitativeEigenschaftException falls es diese Eigenschaft schon gibt
     * @throws ProbeNotFoundException  falls es diese Probe nicht gibt
     */
    public void addEigToTraeger(ProzessSchritt ps) throws DuplicateQualitativeEigenschaftException, ProbeNotFoundException {
        String str = ps.getAttribute();
        List<String> attributList = Arrays.asList(str.split(","));
        List<Traeger> traegers = getAuftragOfPS(ps).getTraeger();
        for (Traeger t :
                traegers) {
            for (Probe p :
                    t.getProben()) {
                    List <QualitativeEigenschaft> akutelleEigenschaften = p.getEigenschaften();
                for (String s :
                        attributList) {
                   QualitativeEigenschaft e = new QualitativeEigenschaft(UUID.randomUUID().hashCode(), s);
                   qualitativeEigenschaftDAO.persist(e);
                   akutelleEigenschaften.add(e);
                }
                p.setEigenschaften(akutelleEigenschaften);
                probeDAO.update(p);
            }
        }
    }


    /**
     * updates the currentPS to the next one in the stations waiting queue
     *
     * @param ps the current process step
     * @param es the station
     * @throws IllegalArgumentException              prozessSchrittList or es null, or prozessSchrittList not the current one at es
     * @throws ExperimentierStationNotFoundException the es was not found in the database
     * @throws DuplicateTransportAuftragException    if TA already exists
     * @throws StandortNotFoundException             if archiv not found
     * @throws ProbeNotFoundException                if the Probe could not be found
     * @throws DuplicateQualitativeEigenschaftException if the Probe already exists
     */
    public void updateCurrent(ProzessSchritt ps, ExperimentierStation es)
            throws IllegalArgumentException, ExperimentierStationNotFoundException, DuplicateTransportAuftragException, StandortNotFoundException, ProbeNotFoundException, DuplicateQualitativeEigenschaftException {
        deleteCurrent(ps, es);
        // If there is a waiting step add it now
        if (es.getNextPS() != null && !es.getNextPS().isEmpty()) {
            es.setCurrentPS(es.getNextPS().get(0));
            es.getNextPS().remove(0);
            log.info("ES: " + es.getEsID() + "current: " + es.getCurrentPS().getId());
            esDao.update(es);
        }

        // Find out to which pk ps belongs
        var as = auftragDAO.getAll();
        Auftrag aC = null;
        for (Auftrag a :
                as) {
            // Does it contain the ps.id
            var r = a.getProzessSchritte().stream().filter(p -> p.getId() == ps.getId()).findFirst().orElse(null);
            if (r != null) {
                aC = a;
            }
        }
        // PK needs to have the ps
        assert aC != null;
        // Find out at wich step we are
        TransportAuftrag ta;
        int currentIndex = aC.getProzessSchritte().indexOf(ps);
        if (ps.getAttribute().isEmpty()) {
            addEigToTraeger(ps);
        }
        if (currentIndex < aC.getProzessSchritte().size()-1) {
            // This is the next Step
            var nextPS = aC.getProzessSchritte().get(currentIndex + 1);
            Standort nextStandort = standortService.findByLocation("Lager");

            // Find out where the next step will happen
            for (ExperimentierStation e : getAll()) {
                List<Integer> psids = new ArrayList<>();
                // Add all ids of possible steps to the list
                for (ProzessSchritt p : e.getNextPS()) {
                    psids.add(p.getId());
                }
                if (e.getCurrentPS()!=null) {
                    psids.add(e.getCurrentPS().getId());
                }
                // IF the List contains the id we are searching for
                // OR
                //   if the current PS exists and is the step we are looking for
                if (psids.contains(ps.getId()) || (e.getCurrentPS() != null && e.getCurrentPS().getId() == nextPS.getId())) {
                    nextStandort = e.getStandort();
                    break;
                }
            }
            ta = new TransportAuftrag(LocalDateTime.now(), TransportAuftragZustand.ERSTELLT, es.getStandort(), nextStandort);
        } else {
            ta = new TransportAuftrag(LocalDateTime.now(), TransportAuftragZustand.ERSTELLT, es.getStandort(), standortService.findByLocation("Lager"));
        }
        try {
            if (!es.getNextPS().isEmpty()){
                for (ProzessSchritt p : es.getNextPS()){
                    if (p.isValidData()&&p.isAssigned()){
                        es.setCurrentPS(p);
                        break;
                    }
                }
            }
            updateES(es);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        transportAuftragDAO.persist(ta);
        ps.setTransportAuftrag(ta);
    }


}
