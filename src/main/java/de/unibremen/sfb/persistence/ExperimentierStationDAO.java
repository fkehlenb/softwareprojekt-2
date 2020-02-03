package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;

import java.util.List;

/** This class handles the experimenting station objects in the database */
public class ExperimentierStationDAO extends ObjectDAO<ExperimentierStation> {

    /** Add an experimenting station object to the database
     * @param es - the experimenting station to add to the database
     * @throws DuplicateExperimentierStationException if the experimenting station already exists in the database */
    public void persist(ExperimentierStation es) throws DuplicateExperimentierStationException{
        if (es!=null){
            synchronized (ExperimentierStation.class){
                if (em.contains(es)){
                    throw new DuplicateExperimentierStationException();
                }
            }
            em.persist(es);
        }
    }

    /** Updates an existing experimenting station object in the database
     * @param es - the experimenting station to update in the database
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database */
    public void update(ExperimentierStation es) throws ExperimentierStationNotFoundException{
        if (es!=null){
            if (!em.contains(es)){
                throw new ExperimentierStationNotFoundException();
            }
            em.merge(es);
        }
    }

    /** Removes an existing experimenting station object from the database
     * @param es - the experimenting station to remove from the database
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database */
    public void remove(ExperimentierStation es) throws ExperimentierStationNotFoundException{
        if (es!=null){
            if (!em.contains(es)){
                throw new ExperimentierStationNotFoundException();
            }
            em.remove(es);
        }
    }

    /** @return the class of experimenting stations */
    public Class<ExperimentierStation> get(){
        return ExperimentierStation.class;
    }

    /** Get an experimenting station object from the database using its unique id
     * @param id - the unique id of the requested experimenting station
     * @return the experimenting station object which's id matches the entered one
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database */
    public ExperimentierStation getObjById(int id) throws ExperimentierStationNotFoundException{
        try{
            ExperimentierStation es = em.find(get(),id);
            if (es==null){
                throw new ExperimentierStationNotFoundException();
            }
            return es;
        }
        catch (Exception e){
            e.printStackTrace();
            throw new ExperimentierStationNotFoundException();
        }
    }

    public List<ExperimentierStation> getAll(){

            List<ExperimentierStation> es = em.createQuery("SELECT es FROM ExperimentierStation es",get())
                    .setMaxResults(1000).getResultList();
            return es;

    }
}
