package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Data class for the process step templates */
@Data
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class ProzessSchrittVorlage {

    /** Process step template id */
    @Id
    @Generated
    @NonNull
    private int psVID;

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** Process step parameters */
    @ManyToMany(fetch = FetchType.LAZY)
    @NonNull
    private List<ProzessSchrittParameter> prozessSchrittParameters;

    /** The experimenting station the process step is being carried out at */
    @ManyToOne
    @NonNull
    private ExperimentierStation experimentierStation;

    /** Duration of the process step template */
    @NonNull
    private String dauer;

    /** Process step template name */
    @NonNull
    private String name;

    /** The state Automaton for the process step */
    @NonNull
    @ManyToOne
    private ProzessSchrittZustandsAutomatVorlage zustandsAutomatVorlage;

    /** Urformend */
    @NonNull
    private boolean urformend;

    /** Name of created samples */
    private String nameOfCreated;

    /** If urformend, amount created */
    @NonNull
    private int amountCreated;

    /** Accepted container input types */
    @ElementCollection
    private List<String> eingabeTraeger;

    /** Accepted container output types */
    @ElementCollection
    private List<String> ausgabeTraeger;


    @Override
    public String toString(){
        return name;
    }
}
