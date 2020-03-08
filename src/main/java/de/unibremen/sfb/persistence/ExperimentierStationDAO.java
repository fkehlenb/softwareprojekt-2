package de.unibremen.sfb.persistence;

import de.unibremen.sfb.exception.DuplicateExperimentierStationException;
import de.unibremen.sfb.exception.ExperimentierStationNotFoundException;
import de.unibremen.sfb.model.ExperimentierStation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * This class handles the experimenting station objects in the database
 */
@Slf4j
public class ExperimentierStationDAO extends ObjectDAO<ExperimentierStation> {

    /**
     * Add an experimenting station object to the database
     *
     * @param es - the experimenting station to add to the database
     * @throws DuplicateExperimentierStationException if the experimenting station already exists in the database
     */
    public void persist(ExperimentierStation es) throws DuplicateExperimentierStationException {
        if (es != null) {
            synchronized (ExperimentierStation.class) {
                if (em.contains(em.find(get(), es.getEsID()))) {
                    throw new DuplicateExperimentierStationException();
                }
            }
            em.persist(es);
        }
    }

    /**
     * Updates an existing experimenting station object in the database
     *
     * @param es - the experimenting station to update in the database
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database
     */
    public void update(ExperimentierStation es) throws ExperimentierStationNotFoundException {
        if (es != null) {
            if (!em.contains(em.find(get(), es.getEsID()))) {
                throw new ExperimentierStationNotFoundException();
            }
//            es = getObjById(es.getEsID());
            em.merge(es);
            log.info("Succes Updating ES: " + es.getEsID());
        }
    }

    /**
     * Removes an existing experimenting station object from the database
     *
     * @param es - the experimenting station to remove from the database
     */
    public void remove(ExperimentierStation es) throws ExperimentierStationNotFoundException {
        if (es != null) {
            if (!em.contains(em.find(get(), es.getEsID()))) {
                throw new ExperimentierStationNotFoundException();
            }
            es.setValidData(false);
            update(es);
        }
    }

    /**
     * @return the class of experimenting stations
     */
    public Class<ExperimentierStation> get() {
        return ExperimentierStation.class;
    }

    /**
     * Get an experimenting station object from the database using its unique id
     *
     * @param id - the unique id of the requested experimenting station
     * @return the experimenting station object which's id matches the entered one
     * @throws ExperimentierStationNotFoundException if the experimenting station couldn't be found in the database
     */
    public ExperimentierStation getObjById(int id) throws ExperimentierStationNotFoundException {
        try {
            ExperimentierStation es = em.find(get(), id);
            if (es == null || !es.isValidData()) {
                throw new ExperimentierStationNotFoundException();
            }
            return es;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExperimentierStationNotFoundException();
        }
    }

    /**
     * Get a list of all experimenting stations in the database
     *
     * @return a list of all experimenting stations
     * @throws IllegalArgumentException if the list is empty
     */
    public List<ExperimentierStation> getAll() throws IllegalArgumentException {
            return em.createNamedQuery("ExperimentierStation.getAll", get()).getResultList();
    }

    /**
     * Get an experimenting station using it's name
     *
     * @param name - the experimenting station's name
     * @return the experimenting station with a matching name
     * @throws ExperimentierStationNotFoundException if the experimenting station cannot be found in the database
     */
    public ExperimentierStation getByName(String name) throws ExperimentierStationNotFoundException {
        try {
            return em.createQuery("SELECT es FROM ExperimentierStation es WHERE es.name = :name AND es.isValidData=true", get())
                    .setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExperimentierStationNotFoundException();
        }
    }
}
