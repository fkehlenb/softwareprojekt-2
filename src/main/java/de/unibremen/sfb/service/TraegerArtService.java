package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateTraegerArtException;
import de.unibremen.sfb.exception.TraegerArtNotFoundException;
import de.unibremen.sfb.model.TraegerArt;
import de.unibremen.sfb.persistence.TraegerArtDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/** This class manages the business logic for container types */
public class TraegerArtService implements Serializable {
    @Getter
    private List<TraegerArt> verTraeger;


    /** TraegerArt DAO */
    @Inject
    private TraegerArtDAO traegerArtDAO;

    /** TraegerArt */
    private List<TraegerArt> traegerArt;

    @PostConstruct
    private void init() {
        verTraeger = traegerArtDAO.getAll();
    }



    /** Add a new container type to the database
     * @param ta - the container type to add
     * @throws DuplicateTraegerArtException on failure */
    public void addTraegerArt(TraegerArt ta) throws DuplicateTraegerArtException {
        traegerArtDAO.persist(ta);
    }

    /** Update a container type in the database
     * @param ta - the container type to update
     * @throws TraegerArtNotFoundException on failure */
    public void updateTragerArt(TraegerArt ta) throws TraegerArtNotFoundException {
        traegerArtDAO.update(ta);
    }

    /** Remove a container type from the database
     * @param ta - the container type to remove
     * @throws TraegerArtNotFoundException on failure */
    public void removeTraegerArt(TraegerArt ta) throws TraegerArtNotFoundException{
        traegerArtDAO.remove(ta);
    }

    /** Get a container type by name
     * @param taName - the container type name
     * @throws TraegerArtNotFoundException on failure */
    public TraegerArt getByName(String taName) throws TraegerArtNotFoundException{
        return traegerArtDAO.getByName(taName);
    }

    /** Get a container type by id
     * @param id - the container type id
     * @throws TraegerArtNotFoundException on failure */
    public TraegerArt getById(int id) throws TraegerArtNotFoundException{
        return traegerArtDAO.getById(id);
    }

    /** Get all container types from the database
     * @return a list of all container types in the database */
    public List<TraegerArt> getAll(){
        return traegerArtDAO.getAll();
    }
}
