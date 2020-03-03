package de.unibremen.sfb.model;

import lombok.*;


import javax.persistence.*;
import java.util.List;

/**
 * Data class for process chain steps
 */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "PS.getAllAvailable", query = "select ps from ProzessSchritt ps where ps.isValidData=true and ps.assigned = false")
})
public class ProzessSchritt {

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
     * The experimenting station this process step is being carried out at
     */
    @NonNull
    @ManyToOne
    private ExperimentierStation experimentierStation;

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
    @ManyToOne
    private ProzessSchrittLog prozessSchrittLog;

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

    @Override
    public String toString(){
        return name;
    }
}
