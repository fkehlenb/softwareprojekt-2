package de.unibremen.sfb.Persistence;

import de.unibremen.sfb.Exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.Exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.Model.ExperimentierStation;

/** This class handles the experimenting station objects in the database */
public class ExperimentierStationDAO extends ObjectDAO<ExperimentierStation> {

    /** Add an experimenting station object to the database
     * @param es - the experimenting station to add to the database
     * @throws DuplicateExperimentierStationException if the experimenting station already exists in the database */
    public void persist(ExperimentierStation es) throws DuplicateExperimentierStationException{}

    /** Updates an existing experimenting station object in the database
     * @param es - the experimenting station to update in the database
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database */
    public void update(ExperimentierStation es) throws ExperimentierStationNotFoundException{}

    /** Removes an existing experimenting station object from the database
     * @param es - the experimenting station to remove from the database
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database */
    public void remove(ExperimentierStation es) throws ExperimentierStationNotFoundException{}

    /** @return the class of experimenting stations */
    public Class<ExperimentierStation> get(){
        return ExperimentierStation.class;
    }

    /** Get an experimenting station object from the database using its unique id
     * @param id - the unique id of the requested experimenting station
     * @return the experimenting station object which's id matches the entered one
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database */
    public ExperimentierStation getObjById(int id) throws ExperimentierStationNotFoundException{
        return null;
    }
}
