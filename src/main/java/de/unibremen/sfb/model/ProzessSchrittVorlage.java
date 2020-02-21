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

    /** On delete set to invalid */
    private boolean isValidData = true;

    /** Process step template id */
    @Id
    @Generated
    @NonNull
    private int psVID;

    /** Duration of the process step template */
    @NonNull
    private Duration dauer;

    /** Accepted container input types */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TraegerArt> eingabeTraeger;

    /** Accepted container output types */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<TraegerArt> ausgabeTraeger;

    /** The process step type */
    @NonNull
    private String psArt;

    /** The experimenting stations accepted in the process step template */
    @OneToMany
    @NonNull
    private  List<ExperimentierStation> stationen;

    /** The process step predicates */
    @ManyToMany
    @NonNull
    private List<Bedingung> bedingungen;

    public String toString() {
        return "PSV" + this.psVID;
    }
}
