package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/** Data class for process chain steps */
@Data
//TODO @Entity
@NamedQueries({
        @NamedQuery(name = "ProzessSchritt.getByEs",
                query = "SELECT ps FROM ProzessSchritt ps WHERE ps.current = :experimentierStation")
})
public class ProzessSchritt {

    /** The process step id */
    @NonNull
    //TODO @Id
    public int psID;

    /** Whether or not the process step has been uploaded */
    public boolean uploaded;

    /**
    /** The experimenting station where the current process step is being carried out */
    @OneToMany
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
        this.prozessSchrittLog = new HashSet<>();
        this.prozessSchrittLog.add(logStart);


    }
}
