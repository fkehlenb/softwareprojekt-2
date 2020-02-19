package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.List;

/** Data class for the process step templates */
@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
public class ProzessSchrittVorlage {

    /** Process step template id */
    @Id
    @Generated
    @NonNull
    private int psVID;

    /** Duration of the process step template */
    @NonNull
    private Duration dauer;

    /** Accepted container input types */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<TraegerArt> ausgabeTraeger;

    /** The process step type */
    @NonNull
    private String psArt;

    /** The experimenting stations accepted in the process step template */
    @OneToMany
    @NonNull
    private  List<ExperimentierStation> stationen;

    /** The process step parameters */
    @ManyToMany
    @NonNull
    private List<ProzessSchrittParameter> prozessSchrittParameter;

    public String toString() {
        return "PSV" + this.psVID;
    }
}
