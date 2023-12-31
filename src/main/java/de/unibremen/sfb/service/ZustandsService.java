package de.unibremen.sfb.service;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Getter
public class ZustandsService {
    private List<String> psZustaende = new ArrayList<>();
    private List<String> pkZustaende = new ArrayList<>();

    @PostConstruct
    /**
     * Hier werden die die Standart Zustaende erstellt
     */
    public void init() {
        psZustaende.add("Angenommen");
        psZustaende.add("In Bearbeitung");
        psZustaende.add("Bearbeitet");
        psZustaende.add("Weitergeleitet");

        pkZustaende.add("ERSTELLT");
        pkZustaende.add("INSTANZIERT");
        pkZustaende.add("BEENDET");
        pkZustaende.add("ARCHIVIERT");
    }

}
