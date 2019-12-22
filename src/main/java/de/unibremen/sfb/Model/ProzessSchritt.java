package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

/** Data class for process chain steps */
@Data
public class ProzessSchritt {

    /** The process step id */
    @NonNull
    public int psID;

    /** Whether or not the process step has been uploaded */
    public boolean uploaded;

    /** The experimenting station where the current process step is being carried out */
    @NonNull
    public ExperimentierStation current;

    /** The state Automaton for the process step */
    @NonNull
    public ProzessSchrittZustandsAutomat zustandsAutomat;

    /** The transport job to be carried out */
    public TransportAuftrag transportAuftrag;

    /** The process step's log */
    @NonNull
    public ProzessSchrittLog prozessSchrittLog;

    /** The process step template the process step was created from */
    @NonNull
    public ProzessSchrittVorlage prozessSchrittVorlage;

    /** The containers that are used in the process step */
    public Traeger traeger;
}
