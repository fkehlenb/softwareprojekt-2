package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessSchritt;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Getter
@Setter
public class ProzessSchrittVorlageService implements Serializable {

    public List<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = getProzessSchrittVorlagen();
    }

    @Inject
    private ProzessSchrittVorlageDAO psvDAO;

    @Inject
    private AuftragService auftragService;


    public List<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return psvDAO.getAll();
    }

    /** Add a new process step template to the database
     * @param psv - the process step template to add
     * @throws DuplicateProzessSchrittVorlageException on failure */
    public void persist(ProzessSchrittVorlage psv) throws DuplicateProzessSchrittVorlageException {
        psvDAO.persist(psv);
        vorlagen.add(psv);
    }

    /** Get a process step template using its id
     * @param id - the id of the process step template
     * @return the requested process step template
     * @throws ProzessSchrittVorlageNotFoundException on failure */
    public ProzessSchrittVorlage ByID(int id) throws ProzessSchrittVorlageNotFoundException {
        return psvDAO.getObjById(id);
    }

    /** Update a process step template
     * @param psv - the process step template to update
     * @throws ProzessSchrittVorlageNotFoundException on failure */
    public void edit(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException {
        psvDAO.update(psv);
    }

    /** Remove a process step template
     * @param psv - the process step template to remove
     * @throws ProzessSchrittVorlageNotFoundException on failure */
    public void remove(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException{
        psvDAO.remove(psv);
    }

    /**
     * Hole die PSV durch ihre ID
     *
     * @param id die ID von psv
     * @return die PSV
     * @throws ProzessSchrittVorlageNotFoundException falls es sie nicht gibt
     */
    public ProzessSchrittVorlage getByID(int id) throws ProzessSchrittVorlageNotFoundException {
        return psvDAO.getObjById(id);
    }

    /** Get a list of all available process step templates
     * @return a list of all available process step templates or an empty arraylist */
    public List<ProzessSchrittVorlage> getAll(){
        return psvDAO.getAll();
    }

    /**
     * Duerfen diese PS bearbeitet werden
     *
     * @return die Liste welche bearbeitet werden darf
     */
    public List<ProzessSchritt> darftBearbeiten() {
        var r = new ArrayList<ProzessSchritt>();
        for (Auftrag a :
                auftragService.getAll()) {
            istBearbeitbar(r, a);
        }
        return r;
    }

    /**
     * Fuege alle PS r in den Auftrag a ein
     *
     * @param r die PS
     * @param a der Auftrag
     */
    private void istBearbeitbar(ArrayList<ProzessSchritt> r, Auftrag a) {
        r.addAll(a.getProzessSchritte().stream().filter(p -> !p.getProzessSchrittZustandsAutomat().
                getCurrent().equals("In Bearbeitung")).
                collect(Collectors.toList()));
    }

    public List<ProzessSchrittVorlage> akzeptiertePSV() {
        List<ProzessSchrittVorlage> psv = new ArrayList<>();
//        for (ProzessSchritt prozessSchrittList :
//                darftBearbeiten()) {
//            psv.add(prozessSchrittList.getProzessSchrittVorlage());
//        }
        return psv;
    }
}
