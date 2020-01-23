package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Data class for process chain steps */
@Data
public class ProzessSchritt {

    /** The process step id */
    @NonNull
    public int psID;

    /** Whether or not the process step has been uploaded */
    public boolean uploaded;

    /**
    /** The experimenting station where the current process step is being carried out */
    public ExperimentierStation current;


    /** The state Automaton for the process step */
    @NonNull
    public ProzessSchrittZustandsAutomat zustandsAutomat;

    /** The transport job to be carried out */
    public TransportAuftrag transportAuftrag;

    /** The process step's log */
    @NonNull
    public Set<ProzessSchrittLog> prozessSchrittLog;

    /** The process step template the process step was created from */
    @NonNull
    public ProzessSchrittVorlage prozessSchrittVorlage;

    /** The containers that are used in the process step */
    public Traeger traeger;

    public ProzessSchritt(int psID, ProzessSchrittZustandsAutomat zustandsAutomat,
                          ProzessSchrittVorlage prozessSchritVorlage,
                          ProzessSchrittZustandsAutomatVorlage prozessSchrittZustandsAutomatVorlage) {
        this.psID = psID;
        this.zustandsAutomat = zustandsAutomat;
        this.prozessSchrittVorlage = prozessSchritVorlage;
        this.zustandsAutomat = new ProzessSchrittZustandsAutomat(prozessSchrittZustandsAutomatVorlage);
        this.uploaded = false;
        ProzessSchrittLog logStart = new ProzessSchrittLog("Angenommen");
        this.prozessSchrittLog =  new HashSet<ProzessSchrittLog>();
        this.prozessSchrittLog.add(logStart);


    }
}
