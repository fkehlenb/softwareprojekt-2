package de.unibremen.sfb.model;

import lombok.*;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Data class for process chain steps
 */
@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "PS.getAllAvailable", query = "select ps from ProzessSchritt ps where ps.isValidData=true and ps.assigned = false")
})
public class ProzessSchritt implements Serializable {

    /**
     * Valid data
     */
    private boolean isValidData = true;

    /**
     * ID
     */
    @Id
    @NonNull
    private int id;

    /** Whether this step is assigned to a job */
    private boolean assigned = false;

    /**
     * Process step state automaton
     */
    @NonNull
    @ManyToOne
    private ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;

    /**
     * Duration of the process step
     */
    @NonNull
    private String duration;

    /**
     * Process step parameters
     */
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProzessSchrittParameter> prozessSchrittParameters;

    /**
     * Process step attributes
     */
    @NonNull
    private String attribute;

    /** Process step transport job */
    @ManyToOne
    private TransportAuftrag transportAuftrag;

    /** Process step log */
    @NonNull
    @OneToMany
    private List<ProzessSchrittLog> prozessSchrittLog;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProzessSchritt)) return false;
        ProzessSchritt that = (ProzessSchritt) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Process step name
     */
    @NonNull
    private String name;

    /** Urformend */
    @NonNull
    private boolean urformend;

    /** If urformend, amount created */
    @NonNull
    private int amountCreated;

    /** ID of created samples */
    private String createdName;

    /** Accepted input container types */
    @ElementCollection
    private List<String> eingabe;

    /** Output container types */
    @ElementCollection
    private List<String> ausgabe;

    boolean uploaded = false;

    @Override
    public String toString(){
        return name;
    }
}
