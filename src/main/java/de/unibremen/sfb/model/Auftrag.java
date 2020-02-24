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
@NamedQueries({
        @NamedQuery(name = "Auftrag.getAll", query = "SELECT a FROM Auftrag a WHERE a.isValidData = true")
})
public class Auftrag {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

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
    @ManyToOne(fetch = FetchType.LAZY)
    private ProzessKettenVorlage vorlage;

    /**
     * The job's priority
     */
    @NonNull
    private Enum<AuftragsPrioritaet> priority;

    /**
     * The job's process steps TODO hier ID
     */
    @NonNull
    @OneToMany(fetch = FetchType.LAZY)
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
    private Enum<ProzessKettenZustandsAutomat> prozessKettenZustandsAutomat;


    public String toString() {
        return "Auftrag: " + this.pkID;
    }
}
