package de.unibremen.sfb.model;

import lombok.*;
import org.apache.commons.lang3.tuple.Pair;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/** Data class for process chain steps */
@Data
@Entity
//@NamedQueries({
//        @NamedQuery(name = "ProzessSchritt.getByEs",
//                query = "SELECT ps FROM ProzessSchritt ps WHERE ps.current = :experimentierStation")
//})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchritt {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** The process step id */
    @NonNull
    @Id
    private int psID;


    /** Whether or not the process step has been uploaded */
    private boolean uploaded;

    /** The transport job to be carried out */
    @OneToOne
    private TransportAuftrag transportAuftrag;

    /** The process step's log */
    @NonNull
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProzessSchrittLog> prozessSchrittLog;

    /** The process step template the process step was created from */
    @NonNull
    @ManyToOne
    private ProzessSchrittVorlage prozessSchrittVorlage;


    /** Die Zugewiesenen Proben fuer diesen Schritt */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Probe> zugewieseneProben;

    /** The process step state automaton template the automaton was created from (containing all possible states) */
    @NonNull
    @OneToOne
    private ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;

    /** The duration of the process step */
    @NonNull
    private String dauer = "";

    /** Location the process step is processed */
    @ManyToOne
    private ExperimentierStation experimentierStation = null;
}
