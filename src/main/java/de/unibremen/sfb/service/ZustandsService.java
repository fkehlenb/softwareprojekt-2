package de.unibremen.sfb.service;

import de.unibremen.sfb.exception.ZustandNotFoundException;
import de.unibremen.sfb.model.Zustand;
import de.unibremen.sfb.persistence.ZustandsDAO;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Getter
public class ZustandsService {

    private  Zustand psZ;
    private  Zustand pkZ;

    /** Process step states */
    private List<String> psZustaende = new ArrayList<>();

    /** Process chain states */
    private List<String> pkZustaende = new ArrayList<>();

    @Inject
    ZustandsDAO zustandsDAO;

    /**
     * Hier werden die die Standart Zustaende erstellt
     */
    @PostConstruct
    public void init() throws ZustandNotFoundException {

        psZ=zustandsDAO.getZustandById("psZ");

        pkZ=zustandsDAO.getZustandById("pkZ");

        psZustaende = psZ.getZustaende();
        pkZustaende = pkZ.getZustaende();
       /* psZustaende.add("Angenommen");
        psZustaende.add("In Bearbeitung");
        psZustaende.add("Bearbeitet");
        psZustaende.add("Weitergeleitet");

     //   psZustande = zustandsService.getByName("psZ");

        pkZustaende.add("ERSTELLT");
        pkZustaende.add("INSTANZIERT");
        pkZustaende.add("BEENDET");
        pkZustaende.add("ARCHIVIERT");*/

        //   psZustande = zustandsService.getByName("pkZ");
    }

}
