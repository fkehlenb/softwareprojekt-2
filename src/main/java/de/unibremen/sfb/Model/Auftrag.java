package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import java.util.Set;

/** Job data class */
@Data
public class Auftrag extends ProzessKettenVorlage{

    /** The job's id */
    @NonNull
    public int pkID;

    /** The process chain template this job was created from */
    @NonNull
    public ProzessKettenVorlage vorlage = this;

    /** The job's priority */
    @NonNull
    public Enum<AuftragsPrioritaet> priority;

    /** The job's process steps */
    @NonNull
    public Set<ProzessSchritt> prozessSchritte;

    /** The job's log */
    @NonNull
    public AuftragsLog log;

    /** The job's state automaton */
    @NonNull
    public Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;

    /** Constructor with call to superclass */
    public Auftrag(AuftragsLog log){
        super();
        this.log=log;
    }
}
