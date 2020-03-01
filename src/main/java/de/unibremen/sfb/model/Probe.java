package de.unibremen.sfb.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/** Sample object data class */
@Data
@Entity
@NamedQueries({
        @NamedQuery(name = "Probe.getByLocation",
                query = "SELECT p FROM Probe p WHERE p.standort = :standort AND p.isValidData = true"),
        @NamedQuery(name = "Probe.getByTraeger",
                query = "SELECT p FROM Probe p WHERE p.currentTraeger = :traeger AND p.isValidData = true")
})
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Probe {

    /** On delete set to invalid */
    @NonNull
    private boolean isValidData = true;

    /** The sample's id */
    @NonNull
    @Id
    private String probenID;

    @NonNull
    private int anzahl;

    /** Comment added to the sample and when it was added */
    @OneToMany(fetch = FetchType.LAZY)
    private List<Kommentar> kommentar;
    /*@OneToOne(fetch = FetchType.LAZY)*/

    /** The sample's state */
    @NonNull
    private Enum<ProbenZustand> zustand;

    /** The sample's location */
    @NonNull
    @ManyToOne
    private Standort standort;

    /** The Predicate of a Sample */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Bedingung> bedingungen;

    /** The qualitative/quantitative descriptors of the sample */
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ProzessSchrittParameter> parameter;

    /** The container the sample is currently located in */
    @ManyToOne
    private Traeger currentTraeger;
}

