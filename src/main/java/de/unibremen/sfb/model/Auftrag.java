package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * Job data class
 */
@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Auftrag {

    /**
     * The job's id
     */
    @NonNull
    @Id
    private int pkID;

    /**
     * The process chain template this job was created from
     */
    @NonNull
    @OneToOne
    private ProzessKettenVorlage vorlage;

    /**
     * The job's priority
     */
    @NonNull
    private AuftragsPrioritaet priority;

    /**
     * The job's process steps TODO hier ID
     */
    @NonNull
    @OneToMany
    private List<ProzessSchritt> prozessSchritte;

    /**
     * The job's log
     */
    @NonNull
    @OneToOne
    private AuftragsLog log;

    /**
     * The job's state automaton
     */
    @NonNull
    private ProzessKettenZustandsAutomat prozessKettenZustandsAutomat;

    /**
     * The user assigned to the job
     */
    @OneToOne
    private User assigned;
}
