package de.unibremen.sfb.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Set;

/** Job data class */
@Data
@Entity
public class Auftrag{

    /** The job's id */
    @NonNull
    @Id
    public int pkID;

    /** The process chain template this job was created from */
    @NonNull
    @OneToOne
    public ProzessKettenVorlage vorlage;

    /** The job's priority */
    @NonNull
    public Enum<AuftragsPrioritaet> priority;

    /** The job's process steps */
    @NonNull
    @OneToMany
    public Set<ProzessSchritt> prozessSchritte;

    /** The job's log */
    @NonNull
    @OneToOne
    public AuftragsLog log;

    /** The job's state automaton */
    @NonNull
    public Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;

    /** The user assigned to the job */
    @OneToOne
    public User assigned;
}
