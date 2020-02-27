package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.DuplicateProzessSchrittVorlageException;
import de.unibremen.sfb.exception.ProzessSchrittVorlageNotFoundException;
import de.unibremen.sfb.model.Auftrag;
import de.unibremen.sfb.model.ProzessSchritt;
import de.unibremen.sfb.model.ProzessSchrittVorlage;
import de.unibremen.sfb.persistence.ProzessSchrittVorlageDAO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Data
@Singleton

/*
  Service fuer ProzessSchrittVorlagen
  Anwendungsfall: Bearbeiten einer Vorlage oder hinzufuegen einer ProzessSchrittVorlage in einer ProzessKettenVorlage
 */

public class ProzessSchrittVorlageService implements Serializable {

    public List<ProzessSchrittVorlage> vorlagen;

    @PostConstruct
    public void init() {
        this.vorlagen = getProzessSchrittVorlagen();
    }


    @Inject
    ProzessSchrittVorlageDAO psvDAO;
    @Inject
    AuftragService auftragService;


    public List<ProzessSchrittVorlage> getProzessSchrittVorlagen() {
        return psvDAO.getAll();
    }

    /**
     * Persistieren der ProzessSchrittVorlage
     *
     * @param psv die Vorlage
     */
    public void persist(ProzessSchrittVorlage psv) {
        try {
            psvDAO.persist(psv);
        } catch (DuplicateProzessSchrittVorlageException e) {
            e.printStackTrace();
        }
        vorlagen.add(psv);
    }

    public ProzessSchrittVorlage ByID(int id) throws ProzessSchrittVorlageNotFoundException {
        try {
            log.info("Trying to find a PSP by ID");
            return psvDAO.getObjById(id);
        } catch (Exception e) {
            log.info("Error ProzessSchrittVorlageNotFoundException in PSVView");
            return null;
        }

    }

    /**
     * Bearbeiten der ProzessSchrittVorlage
     *
     * @param psv die zu bearbeitende ProzessSchrittVorlage
     * @throws ProzessSchrittVorlageNotFoundException falls es die PSV nicht gibt
     */
    public void edit(ProzessSchrittVorlage psv) throws ProzessSchrittVorlageNotFoundException {
        try {
            log.info("Trying try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
            psvDAO.update(psv);
        } catch (Exception e) {
            log.info("Error try to update a PSV" + psv+ "Class=ProzessSchrittVorlageService");
        }

        //var old = vorlagen.stream().filter(p -> psv.getPsVID() == p.getPsVID()).findFirst().orElse(null);
        //if (Collections.replaceAll(vorlagen, old, psv)) {
        //    log.info("Succesful edit " + psv);
        //} else {
        //    log.info("Failed to edit " + psv);
       // }
    }

    /**
     * Loeschen von ProzessSchrittVorlagen
     * @param psvs die Vorlagen
     */
    public void delete(List<ProzessSchrittVorlage> psvs) {
        for (ProzessSchrittVorlage psv :
                psvs) {

            vorlagen.remove(psv);

        }
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

    /**
     * Duerfen diese PS bearbeitet werden
     * @return die Liste welche bearbeitet werden darf
     */
    public List<ProzessSchritt> darftBearbeiten() {
        var r = new ArrayList<ProzessSchritt>();
        for (Auftrag a :
                auftragService.getAuftrage()) {
            istBearbeitbar(r, a);
        }
        return  r;
    }

    /**
     * Fuege alle PS r in den Auftrag a ein
     * @param r die PS
     * @param a der Auftrag
     */
    private void istBearbeitbar(ArrayList<ProzessSchritt> r, Auftrag a) {
        r.addAll( a.getProzessSchritte().stream().filter(p -> !p.getProzessSchrittZustandsAutomat().
                getCurrent().equals("In Bearbeitung")).
                collect(Collectors.toList()));
    }

    public List<ProzessSchrittVorlage> akzeptiertePSV(){
        List<ProzessSchrittVorlage> psv= new ArrayList<>();
        for (ProzessSchritt ps:
                darftBearbeiten()) {
            psv.add(ps.getProzessSchrittVorlage());
        }
        return psv;
    }
}
