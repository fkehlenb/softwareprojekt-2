package de.unibremen.sfb.model;


import lombok.*;

import java.util.HashSet;
import java.util.Set;

/** Data class for process chain steps */
@RequiredArgsConstructor
@Setter
@Getter
@Data
// wir habe einen Konstruktor und einen non Args der Protected ist
// JPA ist gluecklich wegen dem Protected
public class ProzessSchritt {

    /** The process step id */
    @NonNull
    private int psID;

    /** Whether or not the process step has been uploaded */
    private boolean uploaded;

    /**
    /** The experimenting station where the current process step is being carried out */
    private ExperimentierStation current;


    /** The state Automaton for the process step */
    @NonNull
    private ProzessSchrittZustandsAutomat zustandsAutomat;

    /** The transport job to be carried out */
    private TransportAuftrag transportAuftrag;

    /** The process step's log */
    @NonNull
    private Set<ProzessSchrittLog> prozessSchrittLog;

    /** The process step template the process step was created from */
    @NonNull
    private ProzessSchrittVorlage prozessSchrittVorlage;

    /** The containers that are used in the process step */
    private Traeger traeger;


}
