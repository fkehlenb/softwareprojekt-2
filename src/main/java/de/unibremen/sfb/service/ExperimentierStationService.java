package de.unibremen.sfb.service;


import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.*;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


@Startup
@Getter
@Transactional
public class ExperimentierStationService implements Serializable {

    /** List containing all experimenting stations */
    private List<ExperimentierStation> esSet;

    /** ES DAO for database management */
    @Inject
    private ExperimentierStationDAO esDao;

    /** init called on startup */
    @PostConstruct
    public void init() {
        this.esSet= esDao.getAll();
    }

    /**
     * Get all experimenting stations from the database
     * @return all Stations
     */
    public  List<ExperimentierStation> getESListe() {
        return esDao.getAll();
    }


    /**
     * Add a new experimenting station
     * @param experimentierStation - the experimenting station to add
     * @throws DuplicateExperimentierStationException on failure */
    public void addES(ExperimentierStation experimentierStation) throws DuplicateExperimentierStationException {
        esDao.persist(experimentierStation);
    }

    /** Remove an experimenting station
     * @param experimentierStation - the experimenting station to delete
     * @throws  ExperimentierStationNotFoundException if experimentierStation could not be found
     */
    public void loescheES(ExperimentierStation experimentierStation) throws ExperimentierStationNotFoundException  {
        esDao.remove(experimentierStation);
//        esSet = getESListe();
    }

    /**
     * Find an experimenting station using its name
     * @param name - the experimenting station's name
     * @return the Found Station
     */
    public ExperimentierStation findByName(String name) {
        // FIXME Use String as ID or convert to String
        esSet = esDao.getAll();
        return this.esSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    /** Get an experimenting station using its name
     * @param name - the experimenting station's name
     * @return the Station which has the name
     * @throws ExperimentierStationNotFoundException on failure
     */
    public ExperimentierStation getStationByName(String name) throws ExperimentierStationNotFoundException{
        return esDao.getByName(name);
    }
    /**
     * sets the status for an experimenting station
     * @param e the station
     * @param esz the new status
     * @throws ExperimentierStationNotFoundException the station couldn't be found in the database
     */
    public void setZustand(ExperimentierStation e, ExperimentierStationZustand esz) throws ExperimentierStationNotFoundException {
        if(e == null || esz == null) {
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

    /** Get an experimenting station using its id
     * @param id - the experimenting station's id
     * @throws ExperimentierStationNotFoundException on failure
     * @return the Station which has the id
     */
    public ExperimentierStation getById(int id) throws ExperimentierStationNotFoundException{
        return esDao.getObjById(id);
    }

    /** @return a list of all experimenting stations in the system */
    public List<ExperimentierStation> getAll(){
        return esDao.getAll();
    }

    /** Update an existing experimenting station in the database
     * @param es - the experimenting station to update
     * @throws ExperimentierStationNotFoundException on failure */
    public void updateES(ExperimentierStation es) throws ExperimentierStationNotFoundException{
        esDao.update(es);
    }

    /**
     * Get all the Stations a User is assigned to
     * @param user the user for whose stations are wanted
     * @return a list containing all stations for this user
     */
    public List<ExperimentierStation> getESByUser(User user) {
       return esDao.getAll().stream()
               .filter(c -> c.getBenutzer().contains(user))
               .collect(Collectors.toList());
    }

    /**
     * returns all stationen fullfilling a Bedingung //TODO richtig?
     * @param b the Bedingung
     * @return a list containing all fitting stations
     */
    public List<ExperimentierStation> getAllESByBedingung(Bedingung b) {
        return esDao.getAll().stream()
                .filter(e -> e.getBedingungen().contains(b))
                .collect(Collectors.toList());
    }

//    public List<ExperimentierStation> getAllESByParameter(ProzessSchrittParameter p) {
//        return esDao.getAll().stream()
//                .filter(e -> e.getBedingungen().contains(p))
//                .collect(Collectors.toList());
//    }


}
