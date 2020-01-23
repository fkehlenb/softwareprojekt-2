package de.unibremen.sfb.model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Id;
import java.util.Set;

/**
 * Job data class
 */
@Data
public class Auftrag {

    /**
     * The job's id
     */
    @NonNull
    @Id
    public int pkID;

    /**
     * The process chain template this job was created from
     */
    @NonNull
    public ProzessKettenVorlage vorlage;

    /**
     * The job's priority
     */
    @NonNull
    public Enum<AuftragsPrioritaet> priority;

    /**
     * The job's process steps TODO hier ID
     */
    @NonNull
    public Set<ProzessSchritt> prozessSchritte;

    /**
     * The job's log
     */
    @NonNull
    public AuftragsLog log;

    /**
     * The job's state automaton
     */
    @NonNull
    public Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;

    /**
     * The user assigned to the job
     */
    public User assigned;

    public Auftrag(ProzessKettenVorlage vorlage, Enum<AuftragsPrioritaet> priority, AuftragsLog log) {
        this.vorlage = vorlage;
        this.priority = priority;
        this.log = log;
        this.prozessKettenZustandsAutomat = ProzessKettenZustandsAutomat.INSTANZIIERT;
    }
}
