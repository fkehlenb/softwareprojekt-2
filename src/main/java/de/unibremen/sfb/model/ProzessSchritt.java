package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
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

    /**
     * On delete set to invalid
     */
    @NonNull
    private boolean isValidData = true;

    /**
     * The process step id
     */
    @NonNull
    @Id
    private int psID;


    /**
     * Whether or not the process step has been uploaded
     */
    private boolean uploaded;

//    /** The experimenting station where the current process step is being carried out */
//    //@NonNull TOOD Wann beginnt die Zuweisung?
//    @OneToOne
//    private ExperimentierStation current;

    /**
     * The transport job to be carried out
     */
    @OneToOne
    private TransportAuftrag transportAuftrag;

    /**
     * The process step's log
     */
    @NonNull
    @OneToMany(fetch = FetchType.LAZY)
    private List<ProzessSchrittLog> prozessSchrittLog;

    /**
     * The process step template the process step was created from
     */
    @NonNull
    @ManyToOne
    private ProzessSchrittVorlage prozessSchrittVorlage;

    @Override
    public String toString() {
        return
                 " Eigenschaften=" +prozessSchrittVorlage.getName() + " Art=" + prozessSchrittVorlage.getPsArt() + "||"
                ;
    }



    /** The process step state automaton template the automaton was created from (containing all possible states) */
    @NonNull
    @OneToOne
    private ProzessSchrittZustandsAutomat prozessSchrittZustandsAutomat;
}
