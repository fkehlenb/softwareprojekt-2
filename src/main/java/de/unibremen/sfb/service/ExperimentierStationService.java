package de.unibremen.sfb.service;


import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import de.unibremen.sfb.persistence.ExperimentierStationDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@Startup
@Getter
    /** List containing all experimenting stations */
public class ExperimentierStationService implements Serializable {
    private List<ExperimentierStation> esSet;

    /** ES DAO for database management */
    @Inject
    private ExperimentierStationDAO esDao;

    /** init called on startup */
    @PostConstruct
    public void init() {
        this.esSet= esDao.getAll();
    }

    /** Get all experimenting stations from the database */
    public  List<ExperimentierStation> getESListe() {
        return esDao.getAll();
    }


    /** Add a new experimenting station
     * @param experimentierStation - the experimenting station to add
     * @throws DuplicateExperimentierStationException on failure */
    public void addES(ExperimentierStation experimentierStation) throws DuplicateExperimentierStationException {
        esDao.persist(experimentierStation);
    }

    /** Remove an experimenting station
     * @param experimentierStation - the experimenting station to delete
     * @throws ExperimentierStationNotFoundException on failure */
    public void loescheES(ExperimentierStation experimentierStation) throws ExperimentierStationNotFoundException {
        esDao.remove(experimentierStation);
        esSet = getESListe();
    }

    /** Find an experimenting station using its name
     * @param name - the experimenting station's name */
    public ExperimentierStation findByName(String name) {
        // FIXME Use String as ID or convert to String
        return this.esSet.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }

    /** Get an experimenting station using its name
     * @param name - the experimenting station's name
     * @throws ExperimentierStationNotFoundException on failure */
    public ExperimentierStation getStationByName(String name) throws ExperimentierStationNotFoundException{
        return esDao.getByName(name);
    }

    /** Get an experimenting station using its id
     * @param id - the experimenting station's id
     * @throws ExperimentierStationNotFoundException on failure */
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

}
