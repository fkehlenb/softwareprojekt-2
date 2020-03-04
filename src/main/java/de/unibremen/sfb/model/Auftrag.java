package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import org.primefaces.model.SelectableDataModel;

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
public class Auftrag implements Serializable {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /**
     * The job's id
     */
    @NonNull
    @Id
    private int pkID;

    /** The job name */
    @NonNull
    private String name;

    /**
     * The job's priority
     */
    @NonNull
    private AuftragsPrioritaet priority;

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

    /** The Carrier */
    @OneToMany
    private List<Traeger> traeger;
    
    private String ErrorMessage;

    @Override
    public String toString() {
        return "Auftrag: " + this.pkID;
    }

    @Override // FIXME Fabian: solltest du nicht noch gucken ob der Auftrag geloescht wurde
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Auftrag other = (Auftrag) obj;
        if (!Objects.equals(this.pkID, other.pkID)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isValidData(), getPkID(), getName(), getPriority(), getProzessSchritte(), getLog(), getProzessKettenZustandsAutomat(), getTraeger());
    }
}