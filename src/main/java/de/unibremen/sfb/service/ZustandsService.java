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

    /** Process step states */
    private List<String> psZustaende = new ArrayList<>();

    /** Process chain states */
    private List<String> pkZustaende = new ArrayList<>();

    /**
     * Hier werden die die Standart Zustaende erstellt
     */
    @PostConstruct
    public void init() {

    }

}
