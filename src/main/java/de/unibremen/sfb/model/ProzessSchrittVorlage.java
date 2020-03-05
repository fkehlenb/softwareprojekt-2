package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

/** Data class for the process step templates */
@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class ProzessSchrittVorlage {

    /** Process step template id */
    @Id
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

    /** Accepted input container types */
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TraegerArt> eingabe;

    /** Output container types */
    @NonNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TraegerArt> ausgabe;

    @Override
    public String toString(){
        return name;
    }
}
