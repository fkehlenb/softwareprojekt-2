package de.unibremen.sfb.service;

import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessKettenVorlage;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessKettenVorlageDAO;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * this class manages the interaction with models of process chain templates (ProzessKettenVorlage)
 */
@Singleton
@Slf4j
@Getter
public class ProzessKettenVorlageService {
    private List<ProzessKettenVorlage> vorlagen;

    @Inject
    ProzessKettenVorlageDAO prozessKettenVorlageDAO;


    @PostConstruct
    public void init() {
        vorlagen = getPKVs();
    }

    public List<ProzessKettenVorlage> getPKVs() {
        try {
            return prozessKettenVorlageDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * the ProzessKettenVorlage managed by an instance of this controller
     */
    public ProzessKettenVorlage pkv;

    /**
     * Sets the ID of this ProzessKettenVorlage.
     *
     * @param id the new ID
     */
    public void setID(int id) {}

    /**
     * Returns the ID of this ProzessKettenVorlage.
     *
     * @return the ID
     */
    public int getID() { return 0; }

    /**
     * Sets the ProzessSchrittVorlagen this ProzessKettenVorlage consists of.
     *
     * @param psv A set containing the ProzessSchrittVorlagen
     */
    public void setPSV(Set<ProzessSchrittVorlage> psv) {}

    /**
     * Returns the ProzessSchrittVorlagen this ProzessKettenVorlage consists of.
     *
     * @return A set containing the ProzessSchrittVorlagen
     */
    public Set<ProzessSchrittVorlage> getPSV() { return null; }
}
